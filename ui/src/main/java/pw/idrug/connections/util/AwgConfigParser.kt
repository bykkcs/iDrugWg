package pw.idrug.connections.util

import org.amnezia.awg.config.Config
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.Locale

/**
 * Parser wrapper that tolerates malformed Amnezia config lines seen in the wild.
 */
object AwgConfigParser {
    private val junkKeyOnlyPattern = Regex("""^\s*(I[1-5])\s*=\s*$""", RegexOption.IGNORE_CASE)
    private val junkKeyCommentPattern = Regex("""^\s*(I[1-5])\s*=\s*#.*$""", RegexOption.IGNORE_CASE)
    private val junkPeerMarkerPattern = Regex("""^\s*(I[1-5])\s*=\s*\[Peer]\s*$""", RegexOption.IGNORE_CASE)

    fun parse(stream: InputStream): Config {
        val text = stream.bufferedReader(StandardCharsets.UTF_8).use { it.readText() }
        return parse(text)
    }

    fun parse(reader: BufferedReader): Config {
        return parse(reader.readText())
    }

    fun parse(raw: String): Config {
        val normalized = normalize(raw)
        return Config.parse(ByteArrayInputStream(normalized.toByteArray(StandardCharsets.UTF_8)))
    }

    private fun normalize(raw: String): String {
        val lines = raw.replace("\r\n", "\n").replace('\r', '\n').split('\n')
        val out = ArrayList<String>(lines.size)

        for (i in lines.indices) {
            val line = lines[i]
            if (junkKeyOnlyPattern.matches(line) || junkKeyCommentPattern.matches(line)) {
                continue
            }
            if (junkPeerMarkerPattern.matches(line)) {
                val nextMeaningful = nextMeaningfulLine(lines, i + 1)
                if (nextMeaningful?.equals("[Peer]", ignoreCase = true) == true) {
                    continue
                }
            }
            out.add(line)
        }

        return out.joinToString("\n")
    }

    private fun nextMeaningfulLine(lines: List<String>, start: Int): String? {
        for (idx in start until lines.size) {
            val t = lines[idx].trim()
            if (t.isEmpty()) continue
            return t.lowercase(Locale.ENGLISH)
        }
        return null
    }
}
