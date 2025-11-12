package pw.idrug.connections.config;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.amnezia.awg.config.Interface;
import org.amnezia.awg.config.BadConfigException;
import org.amnezia.awg.crypto.KeyPair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public final class InterfaceAwg15Test {

    @Test
    public void testAwg15FieldsRoundTrip() throws Exception {
        final Interface.Builder builder = new Interface.Builder();
        builder.setKeyPair(new KeyPair());
        builder.setCookieReplyPacketJunkSize(123);
        builder.setTransportPacketJunkSize(456);
        builder.setJunkPacketCount(2);
        builder.setInitPacketJunkSize(10);
        builder.setResponsePacketJunkSize(11);
        builder.setI1("<b 0x01>");
        builder.setI2("<b 0x02>");
        builder.setJ1("<b 0x03>");
        builder.setJ2("<b 0x04>");
        builder.setItimeSeconds(45);
        final Interface iface = builder.build();

        assertEquals(Optional.of(123), iface.getCookieReplyPacketJunkSize());
        assertEquals(Optional.of(456), iface.getTransportPacketJunkSize());
        assertEquals(Optional.of("<b 0x01>"), iface.getSpecialJunkPacket1());
        assertEquals(Optional.of("<b 0x02>"), iface.getSpecialJunkPacket2());
        assertEquals(Optional.of("<b 0x03>"), iface.getControlledJunkPacket1());
        assertEquals(Optional.of("<b 0x04>"), iface.getControlledJunkPacket2());
        assertEquals(Optional.of(45), iface.getItimeSeconds());

        final String quick = iface.toAwgQuickString();
        assertTrue(quick.contains("S3 = 123"));
        assertTrue(quick.contains("S4 = 456"));
        assertTrue(quick.contains("I1 = <b 0x01>"));
        assertTrue(quick.contains("J1 = <b 0x03>"));
        assertTrue(quick.contains("ITime = 45"));

        final List<String> lines = Arrays.stream(quick.split("\n"))
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
        final Interface parsed = Interface.parse(lines);
        assertEquals(iface.getCookieReplyPacketJunkSize(), parsed.getCookieReplyPacketJunkSize());
        assertEquals(iface.getTransportPacketJunkSize(), parsed.getTransportPacketJunkSize());
        assertEquals(iface.getSpecialJunkPacket1(), parsed.getSpecialJunkPacket1());
        assertEquals(iface.getControlledJunkPacket1(), parsed.getControlledJunkPacket1());
        assertEquals(iface.getItimeSeconds(), parsed.getItimeSeconds());
    }

    @Test(expected = BadConfigException.class)
    public void testSpecialJunkMustBeConsecutive() throws Exception {
        final Interface.Builder builder = new Interface.Builder();
        builder.setKeyPair(new KeyPair());
        builder.setI2("<b 0x01>");
        builder.build();
    }

    @Test(expected = BadConfigException.class)
    public void testSpecialJunkMustMatchTaggedHex() throws Exception {
        final Interface.Builder builder = new Interface.Builder();
        builder.setKeyPair(new KeyPair());
        builder.setI1("invalid");
        builder.build();
    }
}
