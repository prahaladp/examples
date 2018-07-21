import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class DataCenter {
  private Long dId;
  private Long dBytes;
  private boolean healthState = true;
  Map<Long, Rack> rcMap = new ConcurrentHashMap<>();
  SortedSet<Map.Entry<Long, Rack>> sortedRack;

  public DataCenter(Long dcId) {
    dId = dcId;
    dBytes = (long) 0;
  }

  public void addRack(Long rId) {
    if (rcMap.get(rId) == null) {
      rcMap.put(rId, new Rack(rId, dId));
      return;
    }
  }

  public void addHost(Long rackId, Long hostId, Long bytes) {
    addRack(rackId);
    rcMap.get(rackId).addHost(hostId, bytes);
  }

  public void startFind() {
    sortedRack = new TreeSet<Map.Entry<Long, Rack>>(new Comparator<Map.Entry<Long, Rack>>() {
      @Override
      public int compare(Map.Entry<Long, Rack> e1, Map.Entry<Long, Rack> e2) {
        if (e1.getValue().getBytes() > e1.getValue().getBytes()) {
          return 1;
        }
        return -1;
      }
    });
    sortedRack.addAll(rcMap.entrySet());

    Iterator<Entry<Long, Rack>> rackIter = sortedRack.iterator();

    while (rackIter.hasNext()) {
      Entry<Long, Rack> rack = rackIter.next();
      rack.getValue().startFind();
    }
  }

  public void stopFind() {

    Iterator<Entry<Long, Rack>> rackIter = sortedRack.iterator();

    while (rackIter.hasNext()) {
      Entry<Long, Rack> rack = rackIter.next();
      rack.getValue().stopFind();
    }

    sortedRack = null;
  }

  public Host findHost(Long bytes, List<Host> selectedHosts, List<Long> selectedRacks) {
    Iterator<Entry<Long, Rack>> rackIter = sortedRack.iterator();

    while (rackIter.hasNext()) {
      Entry<Long, Rack> rackEntry = rackIter.next();
      if (selectedRacks.contains(rackEntry.getValue().getId()) == true) {
        continue;
      }

      if (rackEntry.getValue().getHealth() == false) {
        // ignore
        continue;
      }

      Host selectedHost = rackEntry.getValue().findHost(bytes, selectedHosts);
      if (selectedHost != null) {
        return selectedHost;
      }
    }

    rackIter = sortedRack.iterator();

    while (rackIter.hasNext()) {
      Entry<Long, Rack> rackEntry = rackIter.next();

      if (rackEntry.getValue().getHealth() == false) {
        // ignore
        continue;
      }

      Host selectedHost = rackEntry.getValue().findHost(bytes, selectedHosts);
      if (selectedHost != null) {
        return selectedHost;
      }
    }

    return null;
  }

  public void setHealth(boolean state) {
    healthState = state;
  }

  public Long getBytes() {
    return dBytes;
  }

  public boolean getHealth() {
    return healthState;
  }

  public void setBytes(Long bytes) {
    dBytes = bytes;
  }

  @Override
  public String toString() {

    return "DataCenter [dId=" + dId + ", dBytes=" + dBytes + ", healthState=" + healthState
        + ", rcMap=" + rcMap.values().toString() + "]";
  }
}
