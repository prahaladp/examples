import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HostPicker {

  Map<Long, DataCenter> dcMap = new ConcurrentHashMap<>();

  /**
   * Initialization method that takes the name of the file that contains the host list as described
   * earlier. This method is guaranteed to be called before any of the other methods below.
   * 
   * @throws IOException
   */
  void initialize(String fileName) throws IOException {
    /*
     * format of the file is : hostId, rackId, datacenterId, availableBytes
     */
    try (Stream<String> fsStream = Files.lines(Paths.get(fileName))) {
      fsStream.forEach(entry -> updateHost(entry));
    }

  }

  public void updateHost(String entry) {
    String[] tokens = entry.split(",");
    Long hostId = Long.parseLong(tokens[0].trim());
    Long rackId = Long.parseLong(tokens[1].trim());
    Long dcId = Long.parseLong(tokens[2].trim());
    Long bytes = Long.parseLong(tokens[3].trim());

    dcMap.putIfAbsent(dcId, new DataCenter(dcId));
    dcMap.get(dcId).addHost(rackId, hostId, bytes);
  }

  /**
   * This method should return numReplicas number of hostIds. Each host in the returned list should
   * have at least objectSizeInBytes amount of free space.
   *
   * Additionally, the hosts should be as fault isolated as possible. Fault isolation between hosts
   * is described as below: - Hosts on the same rack are the least fault isolated. - Hosts in the
   * same datacenter are more fault isolated than hosts on the same rack. - Hosts in different
   * datacenters are the most fault isolated.
   *
   * All the hosts in the return list should be in the healthy state (see the APIs below). All
   * hosts, racks, and datacenters start out in the healthy state.
   * 
   * Please return an empty list if it is not possible to satisfy the request.
   *
   * Note: You should not update the free space for a host based on this API. The client may or may
   * not end up storing the objects on the returned hosts. Host free space should only be updated
   * via the updateHostSpace api below.
   */
  List<Long> pickHosts(long objectSizeInBytes, int numReplicas) {
    /*
     * 1. Pick the available DC based on who has the most bytes 1a. Inside the DC, pickRack(..) 2.
     * Goto next DC in a round-robin till all numReplicas are found.
     */
    SortedSet<Map.Entry<Long, DataCenter>> sortedDc =
        new TreeSet<Map.Entry<Long, DataCenter>>(new Comparator<Map.Entry<Long, DataCenter>>() {
          @Override
          public int compare(Map.Entry<Long, DataCenter> e1, Map.Entry<Long, DataCenter> e2) {
            if (e1.getValue().getBytes() > e1.getValue().getBytes()) {
              return 1;
            }
            return -1;
          }
        });
    sortedDc.addAll(dcMap.entrySet());

    dcMap.entrySet().stream().forEach(dcEntry -> dcEntry.getValue().startFind());

    List<Host> selectedHosts = new LinkedList<>();
    List<Long> selectedRacks = new LinkedList<>();
    Iterator<Entry<Long, DataCenter>> dcIter = sortedDc.iterator();

    while (dcIter.hasNext() && numReplicas > 0) {
      Entry<Long, DataCenter> dcEntry = dcIter.next();
      Host selectedHost =
          dcEntry.getValue().findHost(objectSizeInBytes, selectedHosts, selectedRacks);
      if (selectedHost == null) {
        // no host was found here
        dcIter.remove();
        continue;
      }

      selectedHosts.add(selectedHost);
      selectedHost.setBytes(selectedHost.getBytes() - objectSizeInBytes);
      selectedRacks.add(selectedHost.getRackId());
      numReplicas--;

      if (dcIter.hasNext() == false) {
        // reinitialize the iteration
        dcIter = sortedDc.iterator();
      }
    }

    if (numReplicas == 0) {
      // found all the hosts we need
      return selectedHosts.stream().map(h -> h.getHostId()).collect(Collectors.toList());
    }

    // put back any bytes which have been reserved
    selectedHosts.stream().forEach(host -> {
      host.setBytes(host.getBytes() + objectSizeInBytes);
    });

    dcMap.entrySet().stream().forEach(dcEntry -> dcEntry.getValue().stopFind());

    return null;
  }

  /**
   * Updates the amount of storage space available on the given host.
   */
  void updateHostSpace(long hostId, long availableBytes) {}

  /**
   * Marks the given host as either healthy or unhealthy. Unhealthy hosts should not be considered
   * as valid candidates by pickHosts.
   */
  void updateHostHealth(long hostId, boolean isHealthy) {}

  /**
   * Marks the given rack as either healthy or unhealthy. If a rack is unhealthy, then all the hosts
   * in that rack should be considered unhealthy.
   */
  void updateRackHealth(long rackId, boolean isHealthy) {}

  /**
   * Marks the given datacenter as either healthy or unhealthy. If a datacenter is unhealthy, then
   * all the racks in that datacenter should be considered unhealthy.
   */
  void updateDatacenterHealth(long datacenterId, boolean isHealthy) {}

  @Override
  public String toString() {
    return "HostPicker [dcMap=" + dcMap + "]";
  }
}
