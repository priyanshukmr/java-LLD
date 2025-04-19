package Firewall.model;

public class Rule {
    CIDR cidr;
    Mode mode;

    public Rule(CIDR cidr, Mode mode) {
        this.cidr = cidr;
        this.mode = mode;
    }

    public Mode getMode() {
        return mode;
    }

    public CIDR getCidr(){
        return this.cidr;
    }
}