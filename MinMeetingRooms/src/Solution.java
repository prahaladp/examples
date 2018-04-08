import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
  class Interval {
    int start;
    int end;
    public Interval(int s, int e) {
      start = s;
      end = e;
    }
    
    @Override
    public String toString() {
      return "Interval [start=" + start + ", end=" + end + "]";
    }
  }
  
  List<Interval> intervals = new LinkedList<>();
  
  public void addInterval(int s, int e) {
    Interval n = new Interval(s, e);
    intervals.add(n);
  }
    
  public int minMeetingRooms() {
    int count = 0;
    PriorityQueue<Interval> roomQueue = new PriorityQueue<>(20, new Comparator<Interval>() {
      public int compare(Interval a, Interval b) {
        return 1;
      }
    });
    
    if (intervals == null || intervals.size() == 0) {
      return count;
    }

    /*
     * Sort the array based on the end time
     */
    intervals.sort(new Comparator<Interval>( ) {
      public int compare(Interval i1, Interval i2) {
        return i1.end - i2.end;
      }
    });
    
    System.out.println("Sorted Intervals " + intervals);
    
    for (Interval c : intervals) {
      if (roomQueue.size() == 0) {
        Interval ni = new Interval(c.start, c.end);
        System.out.println("adding new room : " + ni);
        roomQueue.offer(ni);
      } else {
        List<Interval> roomIntervals = new LinkedList<>();
        Interval ri;
        
        while ((ri = roomQueue.poll()) != null) {
          if (c.start > ri.start) {
            ri.start = c.start;
            ri.end = c.end;
            System.out.println("updating room interval : " + ri);
            roomQueue.offer(ri);
            break;
          } else {
            System.out.println("queueing room list : " + ri);
            roomIntervals.add(ri);
          }
        }
        
        if (ri == null) {
          Interval ni = new Interval(c.start, c.end);
          System.out.println("adding new room since all rooms booked: " + ni);
          roomQueue.offer(ni);
        }
        System.out.println("Updating the rooms from the queue");
        roomIntervals.stream().forEach(rie -> {
          System.out.println("adding back from queue : " + rie);
          roomQueue.add(rie);
        });
      }
    }
    return roomQueue.size();
  }
  
  public static void main(String[] args) {
    Integer[] rint = {1,5,2,3,1,4,1,3,4,5};
    Solution s = new Solution();
    for (int i = 0; i < rint.length; i += 2) {
      s.addInterval(rint[i],  rint[i+1]);
    }
    System.out.println("Min Rooms requires = " + s.minMeetingRooms());
  }
}
