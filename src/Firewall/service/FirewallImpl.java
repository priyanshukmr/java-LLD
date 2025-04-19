package Firewall.service;

import Firewall.model.Mode;
import Firewall.model.Rule;
import java.util.*;

public class FirewallImpl implements Firewall{
    List<Rule> rules;

    public FirewallImpl() {
        this.rules = new ArrayList<>();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public boolean checkIp(String ip){
        boolean allow = false;
        for(Rule rule: rules) {
            if(rule.getCidr().containsIp(ip)) {
                if(rule.getMode()== Mode.DENY) {
                    return false;
                }
                allow = true;
            }
        }
        return allow;
    }
}