/*
Design a CIDR firewall that allows following operations:

1. Add a CIDR rule to firewall
eg. ("ALLOW", "192.168.1.0/24"), ("ALLOW", "1.2.3.4"), ("DENY", "10.5.12.10/28")

2. check if given ip passes firewallL: not denied by any rule and passed by any 1 at least
*/

package Firewall.service;
import Firewall.model.Rule;

public interface Firewall {
    void addRule(Rule rule);
    boolean checkIp(String ip);
}
