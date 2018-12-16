import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
  public class BlockVal {
    int val;
    int indx;

    public BlockVal(int v, int i) {
      val = v;
      indx = i;
    }
  }

  public int calculate(String s) {
    return compute(s, 0).val;
  }

  private BlockVal compute(String s, int indx) {
    char cops = 'N';
    int coperand = '0';
    int i = indx;

    for (; i < s.length();) {
      char ch = s.charAt(i);
      if (ch == ' ') {
        i++;
        continue;
      }

      int num = 0;

      if (ch == '(') {
        BlockVal bv = compute(s, i + 1);
        i = bv.indx;
        num = bv.val;
      } else if (ch == ')') {
        return new BlockVal(coperand, i + 1);
      } else if (ch == '+' || ch == '-') {
        cops = ch;
        System.out.println("new operator = " + cops);
        i++;
        continue;
      } else {
        BlockVal nextBv = getNumber(s, i);
        i = nextBv.indx;
        num = nextBv.val;
      }

      /* nothing in the stack */
      if (cops == 'N') {
        coperand = num;
        System.out.println("new operand = " + coperand);
        continue;
      }

      /* something already in the stack */
      if (cops == '+' || cops == '-') {
        /* we already have a value */
        if (cops == '+') {
          coperand += num;
        } else {
          coperand -= num;
        }

        System.out.println("Next val = " + coperand);
        /* reset the cops */
        cops = 'N';
      }
    }

    return new BlockVal(coperand, i);
  }

  public BlockVal getNumber(String s, int indx) {
    int num = 0;
    int multi = 1;
    int i = indx;

    for (; i < s.length(); i++) {
      char ch = s.charAt(i);

      if (ch >= '0' && ch <= '9') {
        num = (ch - '0') * multi;
        multi *= 10;
      } else {
        break;
      }
    }

    System.out.println("Next number = " + num);

    return new BlockVal(num, i);
  }

  private static void test(Integer num) {
    num++;
  }

  private void test9() {
    Pattern p = Pattern.compile("\\w+ (\\d+) [\\w ]+ (\\d{2}:\\d{2})");
    String oneline = "Eat 12 carrots at 12:30";
    Matcher m = p.matcher(oneline);
    if (m.matches()) {
      System.out.println("The quantity is " + m.group(1));
      System.out.println("The time is " + m.group(2));
    }

    oneline =
        "  cookie=0x0, duration=17.428s, table=2, n_packets=0, n_bytes=0, priority=201,ip,reg0=0x40002f/0xdfffff,nw_src=8.8.8.8 actions=set_field:10.128.96.132->ip_src,goto_table:4   ";
    p = Pattern.compile(
        "(\\w.*) n_packets=(\\d+), n_bytes=(\\d+), (\\w.*) actions=set_field:(\\d+.\\d+.\\d+.\\d+)->(\\w.*)");

    String key = "10.10.10.10";
    System.out.println("key is " + key.replace(".", "-"));
    /*
     * oneline = "cookie=0x0, duration=72809.141s, table=2, n_packets=10, n_bytes=20, " +
     * "priority=201,ip,reg0=0x400030/0xdfffff,nw_src=8.8.8.8 actions=set_field:10.128.96.132->ip_src,"
     * ; p = Pattern.compile(
     * "(\\w.*) n_packets=(\\d+), n_bytes=(\\d+), (\\w.*) actions=set_field:(\\d+.\\d+.\\d+.\\d+)->(\\w.*)"
     * );
     */
    m = p.matcher(oneline.trim());
    System.out.println("egress matches = " + m.groupCount());
    if (m.matches()) {
      System.out.println("The n_packets is " + m.group(2));
      System.out.println("The n_bytes is " + m.group(3));
      System.out.println("The IP is " + m.group(5));
    }

    oneline =
        "cookie=0x0, duration=94634.069s, table=6, n_packets=1485, n_bytes=145530, priority=200,"
            + "ip,nw_dst=10.128.96.132 actions=load:0x1->NXM_NX_REG0[22..23],load:0x2e->NXM_NX_REG1[0..20],set_field:8.8.8.8->ip_dst,goto_table:7";
    p = Pattern.compile(
        "(\\w.*) n_packets=(\\d+), n_bytes=(\\d+), (\\w.*)nw_dst=(\\d+.\\d+.\\d+.\\d+) (\\w.*)");
    m = p.matcher(oneline);
    System.out.println("matches = " + m.groupCount());
    if (m.matches()) {
      System.out.println("The n_packets is " + m.group(2));
      System.out.println("The n_bytes is " + m.group(3));
      System.out.println("The IP is " + m.group(5));
    }


  }

  private void test10() {
    String inp = "_uuid               : ab7b73f2-203d-4ced-acb0-3e4b35578b2f\n"
        + "admin_state         : down\n" + "bfd                 : {}\n"
        + "bfd_status          : {}\n" + "cfm_fault           : []\n" + "cfm_fault_status    : []\n"
        + "cfm_flap_count      : []\n" + "cfm_health          : []\n" + "cfm_mpid            : []\n"
        + "cfm_remote_mpids    : []\n" + "cfm_remote_opstate  : []\n" + "duplex              : []\n"
        + "error               : []\n" + "external_ids        : {opc-id=\"ipnp-91340\"}\n"
        + "ifindex             : 0\n" + "ingress_policing_burst: 0\n" + "ingress_policing_rate: 0\n"
        + "lacp_current        : []\n" + "link_resets         : 0\n" + "link_speed          : []\n"
        + "link_state          : down\n" + "lldp                : {}\n"
        + "mac                 : []\n" + "mac_in_use          : []\n" + "mtu                 : []\n"
        + "mtu_request         : []\n" + "name                : \"ipnp-91340\"\n"
        + "ofport              : 1299\n" + "ofport_request      : []\n"
        + "options             : {}\n" + "other_config        : {}\n"
        + "statistics          : {collisions=0, rx_bytes=215923, rx_crc_err=0, rx_dropped=0, rx_errors=0, rx_frame_err=0, rx_over_err=0, rx_packets=2580, tx_bytes=25902, tx_dropped=0, tx_errors=0, tx_packets=607}\n"
        + "status              : {driver_name=openvswitch}\n" + "type                : internal\n"
        + "_uuid               : 74df6e7c-691f-4208-a6c0-3123c7620ef4\n"
        + "admin_state         : down\n" + "bfd                 : {}\n"
        + "bfd_status          : {}\n" + "cfm_fault           : []\n" + "cfm_fault_status    : []\n"
        + "cfm_flap_count      : []\n" + "cfm_health          : []\n" + "cfm_mpid            : []\n"
        + "cfm_remote_mpids    : []\n" + "cfm_remote_opstate  : []\n" + "duplex              : []\n"
        + "error               : []\n" + "external_ids        : {opc-id=\"pgwp-365\"}\n"
        + "ifindex             : 0\n" + "ingress_policing_burst: 0\n" + "ingress_policing_rate: 0\n"
        + "lacp_current        : []\n" + "link_resets         : 0\n" + "link_speed          : []\n"
        + "link_state          : down\n" + "lldp                : {}\n"
        + "mac                 : []\n" + "mac_in_use          : []\n" + "mtu                 : []\n"
        + "mtu_request         : []\n" + "name                : \"pgwp-365\"\n"
        + "ofport              : 368\n" + "ofport_request      : []\n"
        + "options             : {}\n" + "other_config        : {}\n"
        + "statistics          : {collisions=0, rx_bytes=0, rx_crc_err=0, rx_dropped=0, rx_errors=0, rx_frame_err=0, rx_over_err=0, rx_packets=0, tx_bytes=828, tx_dropped=0, tx_errors=0, tx_packets=10}\n"
        + "status              : {driver_name=openvswitch}\n" + "type                : internal\n"
        + "";

    String[] inStr = inp.split("\n");
    List<String> inStrList = Arrays.asList(inStr);
    for (Integer indx = 0; indx < inStrList.size();) {
      String currS = inStrList.get(indx);
      if (currS.contains("uuid")) {
        /* parse rest of the content for interface characteristics */
        String uuid = currS.split(":")[1];
        String name = null;
        Long rxBytes = 0L;
        Long rxPkts = 0L;
        Long txBytes = 0L;
        Long txPkts = 0L;
        Integer intfIndx = indx + 1;
        Pattern namePattern = Pattern.compile("name.*: \"(.*)\"");
        Pattern statPattern = Pattern.compile(
            "(.*), rx_bytes=(\\d+),(.*)rx_packets=(\\d+), tx_bytes=(\\d+),(.*), tx_packets=(\\d+)(.*)");

        for (; intfIndx < inStrList.size()
            && inStrList.get(intfIndx).contains("uuid") == false; intfIndx++) {
          String intfData = inStrList.get(intfIndx);
          if (intfData.matches("name.*:.*")) {
            Matcher m = namePattern.matcher(intfData);
            if (m.matches()) {
              System.out.println("name is " + m.group(1));
            }
          } else if (intfData.contains("statistics")) {
            /* parse statistics */
            String statData = intfData.split(":")[1];
            System.out.println("stat data is " + statData);
            Matcher m = statPattern.matcher(statData);
            if (m.matches()) {
              System.out.println("The rx bytes is " + m.group(2));
              System.out.println("The rx packets is " + m.group(4));
              System.out.println("The tx bytes is " + m.group(5));
              System.out.println("The tx packets is " + m.group(7));
            }
          }
          indx = intfIndx;
          if (name != null) {
            // Initialize a new interface container
          }
        }
      } else {
        indx++;
      }
    }
  }

  public static void test11() {
    String hexValue = "a0909090";
    String ip = "";

    Long startTimeNs = System.nanoTime();

    for (int i = 0; i < hexValue.length(); i = i + 2) {
      ip = ip + Integer.valueOf(hexValue.substring(i, i + 2), 16) + ".";
    }
    System.out.println("new ip is :" + ip);
    Long endTimeNs = System.nanoTime();
    System.out.println((endTimeNs - startTimeNs) / 1000); // prints PT1M3.553S

  }

  public static void test12() {
    String timeVal1 = "  Last read 10w2d22h, hold time is 180, keepalive interval is 60 seconds";
    Pattern timePattern1 = Pattern.compile("(\\d+):");
    Pattern statPattern = Pattern.compile(
        "(.*), rx_bytes=(\\d+),(.*)rx_packets=(\\d+), tx_bytes=(\\d+),(.*), tx_packets=(\\d+)(.*)");
  }

  public static void main(String[] args) {
    Solution s = new Solution();

    test11();

    // s.test9();
    s.test10();

    Integer num = 0;
    test(num);
    System.out.println(num);

    String mathString = " (2-1)";
    System.out.println("Computing value for " + mathString + " = " + s.calculate(mathString));

    mathString = "1 + 1";
    System.out.println("Computing value for " + mathString + " = " + s.calculate(mathString));

    mathString = " 2-1 + 2 ";
    System.out.println("Computing value for " + mathString + " = " + s.calculate(mathString));

    mathString = "(1+(4+5+2)-3)+(6+8)";
    System.out.println("Computing value for " + mathString + " = " + s.calculate(mathString));
  }

}
