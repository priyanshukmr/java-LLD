package Firewall.model;

public class CIDR {
    int ip;
    int fixed;

    public CIDR(String cidr) {
        if(!cidr.contains("/")) {
            cidr = cidr + "/32";
        }
        this.ip = ipToInt(cidr.split("/")[0]);
        this.fixed = Integer.parseInt(cidr.split("/")[1]);
    }

    public int getIp() {
        return this.ip;
    }

    public int getFixed() {
        return this.fixed;
    }

    public String toString() {
        return this.ip + "/" + this.fixed;
    }

    static int ipToInt(String ip) {
        String[] octets = ip.split("\\.");
        int ipInt = 0;
        ipInt |= Integer.parseInt(octets[0])<<24;
        ipInt |= Integer.parseInt(octets[1])<<16;
        ipInt |= Integer.parseInt(octets[2])<<8;
        ipInt |= Integer.parseInt(octets[3]);
        return ipInt;
    }

    public boolean containsIp(String ip) {
        if(this.fixed ==0) return true;
        long queryIp = ipToInt(ip);
        return ((queryIp>>(32-fixed))^(this.ip>>(32-fixed))) == 0;
    }
}
