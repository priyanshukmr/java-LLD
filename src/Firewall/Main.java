package Firewall;

import Firewall.model.CIDR;
import Firewall.model.Mode;
import Firewall.service.Firewall;
import Firewall.service.FirewallImpl;
import org.junit.Test;

import static org.junit.Assert.*;
import Firewall.model.Rule;


public class Main {

    @Test
    public void testFirewall() {
        CIDR cidr = new CIDR("192.168.1.0/24");
        Rule rule = new Rule(cidr, Mode.ALLOW);
        Firewall firewall = new FirewallImpl();
        firewall.addRule(rule);
        assertTrue("Pass", firewall.checkIp("192.168.1.37"));
        assertFalse("Fail", firewall.checkIp("244.168.1.37"));
        CIDR _cidr = new CIDR("192.168.1.0/30");
        Rule _rule = new Rule(_cidr, Mode.DENY);
        firewall.addRule(_rule);
        assertFalse("Fail", firewall.checkIp("192.168.1.1"));
        assertFalse("Fail", firewall.checkIp("192.168.1.2"));
        assertFalse("Fail", firewall.checkIp("192.168.1.3"));
        assertTrue("Pass", firewall.checkIp("192.168.1.4"));

        assertFalse("Fail coz not passing any ALLOW rule", firewall.checkIp("255.168.1.4"));
    }

    // tests for containsIp
    @Test
    public void testExactIpMatch_CIDR32() {
        CIDR cidr = new CIDR("192.168.1.1");
        assertFalse("Should not contain a different IP", cidr.containsIp("192.168.1.2"));
        assertTrue("Should not contain a different IP", cidr.containsIp("192.168.1.1"));
    }

    @Test
    public void testCIDR24_Subnet() {
        CIDR cidr = new CIDR("192.168.1.0/24");

        assertTrue("Should be in the /24 range", cidr.containsIp("192.168.1.1"));
        assertTrue("Last IP in /24 range", cidr.containsIp("192.168.1.255"));
        assertFalse("Outside the /24 range", cidr.containsIp("192.168.2.0"));
    }

    @Test
    public void testCIDR16_Subnet() {
        CIDR cidr = new CIDR("10.0.0.0/16");
        assertTrue("Should be within 10.0.0.0/16", cidr.containsIp("10.0.1.5"));
        assertFalse("Should be outside 10.0.0.0/16", cidr.containsIp("10.1.0.1"));
    }

    @Test
    public void testCIDR8_Subnet() {
        CIDR cidr = new CIDR("172.0.0.0/8");
        assertTrue("Within /8 range", cidr.containsIp("172.255.255.255"));
        assertFalse("Outside /8 range", cidr.containsIp("173.0.0.1"));
    }

    @Test
    public void testCIDR0_AllIpsMatch() {
        CIDR cidr = new CIDR("0.0.0.0/0");
        assertTrue("All IPs should match /0", cidr.containsIp("255.255.255.255"));
        assertTrue("All IPs should match /0", cidr.containsIp("1.2.3.4"));
    }
}
