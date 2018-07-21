
public class Host {
  private Long hId;
  private Long rId;
  private Long dId;
  private Long aBytes;
  private boolean healthState = true;

  public Host(Long hostId, Long rackId, Long dcId, Long availableBytes) {
    hId = hostId;
    rId = rackId;
    dId = dcId;
    aBytes = availableBytes;
  }

  public void setHealth(boolean state) {
    healthState = state;
  }

  public boolean getHealth() {
    return healthState;
  }

  public Long getHostId() {
    return hId;
  }

  public Long getRackId() {
    return rId;
  }

  public Long getDcId() {
    return dId;
  }

  public Long getBytes() {
    return aBytes;
  }

  public void setBytes(Long bytes) {
    aBytes = bytes;
  }

  @Override
  public String toString() {
    return hId + ", " + rId + ", " + dId + ", " + aBytes;
  }
}
