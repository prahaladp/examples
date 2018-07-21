import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class TestBasic1 {

  @Test
  void test1() {
    HostPicker h = new HostPicker();

    Host h1 = new Host(1L, 1L, 1L, 10000L);
    h.updateHost(h1.toString());
    List<Long> hosts = h.pickHosts(1000, 1);
    assertTrue(hosts.size() == 1);

    assertTrue(hosts.contains(h1.getHostId()) == true);
  }

  @Test
  void test2() {
    HostPicker h = new HostPicker();

    Host h1 = new Host(1L, 1L, 1L, 10000L);
    Host h2 = new Host(2L, 2L, 2L, 10000L);

    h.updateHost(h1.toString());
    h.updateHost(h2.toString());

    System.out.println("Test2 : hostPicker  = " + h);

    List<Long> hosts = h.pickHosts(1000, 2);

    System.out.println("Test2 : hosts = " + hosts);

    assertTrue(hosts.size() == 2);
    assertTrue(hosts.contains(h1.getHostId()) == true);
    assertTrue(hosts.contains(h2.getHostId()) == true);
  }

  @Test
  void test3() {
    HostPicker h = new HostPicker();

    Host h1 = new Host(1L, 1L, 1L, 10000L);
    Host h2 = new Host(2L, 2L, 1L, 10000L);

    h.updateHost(h1.toString());
    h.updateHost(h2.toString());

    System.out.println("Test3 : hostPicker  = " + h);

    List<Long> hosts = h.pickHosts(1000, 2);

    System.out.println("Test3 : hosts = " + hosts);

    assertTrue(hosts.size() == 2);
    assertTrue(hosts.contains(h1.getHostId()) == true);
    assertTrue(hosts.contains(h2.getHostId()) == true);
  }

  @Test
  void test4() {
    HostPicker h = new HostPicker();

    Host h1 = new Host(1L, 1L, 1L, 10000L);
    Host h2 = new Host(2L, 1L, 1L, 10000L);

    h.updateHost(h1.toString());
    h.updateHost(h2.toString());

    System.out.println("Test4 : hostPicker  = " + h);

    List<Long> hosts = h.pickHosts(1000, 2);

    System.out.println("Test4 : hosts = " + hosts);

    assertTrue(hosts.size() == 2);
    assertTrue(hosts.contains(h1.getHostId()) == true);
    assertTrue(hosts.contains(h2.getHostId()) == true);
  }
}
