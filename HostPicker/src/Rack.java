import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class Rack {
  private Long rId;
  private Long dcId;
  private Long rBytes;
  private boolean healthState = true;
  Map<Long, Host> hsMap = new ConcurrentHashMap<>();
  SortedSet<Map.Entry<Long, Host>> sortedHosts;

  public Rack(Long rackId, Long dId) {
    rBytes = (long) 0;
    rId = rackId;
    dcId = dId;
  }

  public Long getId() {
    return rId;
  }

  public void setHealth(boolean state) {
    healthState = state;
  }

  public Long getBytes() {
    return rBytes;
  }

  public boolean getHealth() {
    return healthState;
  }

  public void startFind() {
    sortedHosts = new TreeSet<Map.Entry<Long, Host>>(new Comparator<Map.Entry<Long, Host>>() {
      @Override
      public int compare(Map.Entry<Long, Host> e1, Map.Entry<Long, Host> e2) {
        if (e1.getValue().getBytes() > e1.getValue().getBytes()) {
          return 1;
        }
        return -1;
      }
    });
    sortedHosts.addAll(hsMap.entrySet());
  }

  public void stopFind() {
    sortedHosts = null;
  }

  public Host findHost(Long bytes, List<Host> selectedHosts) {
    Iterator<Entry<Long, Host>> hostIter = sortedHosts.iterator();

    while (hostIter.hasNext()) {
      Entry<Long, Host> hostEntry = hostIter.next();
      if (selectedHosts.contains(hostEntry.getValue().getHostId()) == true) {
        continue;
      }

      if (hostEntry.getValue().getHealth() == false) {
        // ignore
        continue;
      }

      if (hostEntry.getValue().getBytes() >= bytes) {
        hostIter.remove();
        return hostEntry.getValue();
      }
    }

    return null;
  }

  public void addHost(Long hostId, Long bytes) {
    hsMap.putIfAbsent(hostId, new Host(hostId, rId, dcId, bytes));
  }

  @Override
  public String toString() {

    return "Rack [rId=" + rId + ", dcId=" + dcId + ", rBytes=" + rBytes + ", healthState="
        + healthState + ", hsMap=" + hsMap.values().toString() + "]";
  }

}
