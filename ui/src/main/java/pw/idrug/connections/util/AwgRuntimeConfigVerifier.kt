package pw.idrug.connections.util

import android.util.Log
import org.amnezia.awg.backend.Backend
import org.amnezia.awg.backend.Tunnel
import org.amnezia.awg.config.Config
import java.lang.reflect.Method
import java.util.Locale

object AwgRuntimeConfigVerifier {
    private const val TAG = "iDrugConnections/AwgVerify"

    fun verifyAndLog(backend: Backend, tunnel: Tunnel, expectedConfig: Config) {
        val expected = expectedAwgInterfaceMap(expectedConfig)
        if (expected.isEmpty()) {
            Log.i(TAG, "AWG_VERIFY_SKIP tunnel=${tunnel.name}: no Amnezia interface fields in config")
            return
        }
        val handle = getCurrentTunnelHandle(backend)
        if (handle < 0) {
            Log.w(TAG, "AWG_VERIFY_FAIL tunnel=${tunnel.name}: backend handle is invalid")
            return
        }
        val runtimeConfig = getRuntimeConfig(backend, handle)
        if (runtimeConfig.isNullOrBlank()) {
            Log.w(TAG, "AWG_VERIFY_FAIL tunnel=${tunnel.name}: runtime config is empty")
            return
        }
        val actual = parseUapiConfig(runtimeConfig)
        val mismatches = mutableListOf<String>()
        for ((key, expectedValue) in expected) {
            val actualValue = actual[key]
            when {
                actualValue == null -> mismatches.add("$key=<missing>")
                actualValue != expectedValue -> mismatches.add("$key exp=${redact(expectedValue)} got=${redact(actualValue)}")
            }
        }
        if (mismatches.isEmpty()) {
            Log.i(TAG, "AWG_VERIFY_OK tunnel=${tunnel.name} keys=${expected.keys.joinToString(",")}")
        } else {
            Log.w(TAG, "AWG_VERIFY_MISMATCH tunnel=${tunnel.name}: ${mismatches.joinToString("; ")}")
        }
    }

    private fun expectedAwgInterfaceMap(config: Config): LinkedHashMap<String, String> {
        val map = linkedMapOf<String, String>()
        val iface = config.`interface`

        iface.junkPacketCount.ifPresent { map["jc"] = it.toString() }
        iface.junkPacketMinSize.ifPresent { map["jmin"] = it.toString() }
        iface.junkPacketMaxSize.ifPresent { map["jmax"] = it.toString() }
        iface.initPacketJunkSize.ifPresent { map["s1"] = it.toString() }
        iface.responsePacketJunkSize.ifPresent { map["s2"] = it.toString() }
        iface.cookieReplyPacketJunkSize.ifPresent { map["s3"] = it.toString() }
        iface.transportPacketJunkSize.ifPresent { map["s4"] = it.toString() }
        iface.initPacketMagicHeader.ifPresent { map["h1"] = it.trim() }
        iface.responsePacketMagicHeader.ifPresent { map["h2"] = it.trim() }
        iface.underloadPacketMagicHeader.ifPresent { map["h3"] = it.trim() }
        iface.transportPacketMagicHeader.ifPresent { map["h4"] = it.trim() }
        iface.specialJunkI1.ifPresent { value -> value.trim().takeIf { it.isNotEmpty() }?.let { map["i1"] = it } }
        iface.specialJunkI2.ifPresent { value -> value.trim().takeIf { it.isNotEmpty() }?.let { map["i2"] = it } }
        iface.specialJunkI3.ifPresent { value -> value.trim().takeIf { it.isNotEmpty() }?.let { map["i3"] = it } }
        iface.specialJunkI4.ifPresent { value -> value.trim().takeIf { it.isNotEmpty() }?.let { map["i4"] = it } }
        iface.specialJunkI5.ifPresent { value -> value.trim().takeIf { it.isNotEmpty() }?.let { map["i5"] = it } }

        return map
    }

    private fun parseUapiConfig(raw: String): Map<String, String> {
        val values = linkedMapOf<String, String>()
        for (line in raw.lineSequence()) {
            val idx = line.indexOf('=')
            if (idx <= 0) continue
            val key = line.substring(0, idx).trim().lowercase(Locale.US)
            val value = line.substring(idx + 1).trim()
            if (key.isNotEmpty()) values[key] = value
        }
        return values
    }

    private fun getCurrentTunnelHandle(backend: Backend): Int {
        return try {
            val field = findField(backend.javaClass, "currentTunnelHandle") ?: return -1
            field.isAccessible = true
            (field.get(backend) as? Int) ?: -1
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to read currentTunnelHandle via reflection", e)
            -1
        }
    }

    private fun getRuntimeConfig(backend: Backend, handle: Int): String? {
        return try {
            val method = findMethod(backend.javaClass, "getTunnelConfig", Int::class.javaPrimitiveType!!)
                ?: return null
            method.isAccessible = true
            method.invoke(backend, handle) as? String
        } catch (e: Throwable) {
            Log.w(TAG, "Failed to read runtime tunnel config via reflection", e)
            null
        }
    }

    private fun findMethod(clazz: Class<*>, methodName: String, vararg paramTypes: Class<*>): Method? {
        var current: Class<*>? = clazz
        while (current != null) {
            try {
                return current.getDeclaredMethod(methodName, *paramTypes)
            } catch (_: NoSuchMethodException) {
                current = current.superclass
            }
        }
        return null
    }

    private fun findField(clazz: Class<*>, fieldName: String): java.lang.reflect.Field? {
        var current: Class<*>? = clazz
        while (current != null) {
            try {
                return current.getDeclaredField(fieldName)
            } catch (_: NoSuchFieldException) {
                current = current.superclass
            }
        }
        return null
    }

    private fun redact(value: String): String {
        if (value.length <= 24) return value
        return value.take(12) + "...(len=" + value.length + ")"
    }
}
