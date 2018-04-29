import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyCalendar2 {
   class Range {
    int s;
    int e;
    
    public Range(int st, int en) {
      this.s = st;
      e = en;
    }
    
    public Range overlaps(Range c) {
      if (c.s <= s && c.e >= s && c.e <= e) {
        return new Range(s, c.e);
      }
      if (c.s >= s && c.e <= e) {
        return new Range(c.s, c.e);
      }
      if (c.s >= s && e <= c.e) {
        return new Range(s, e);
      }
      return null;
    }
   }
   List<Range> doubleBooked = new LinkedList<>();
   List<Range> allBooked = new LinkedList<>();
  private Integer next;
   
   public MyCalendar2() {
   }
  
   public boolean book(int start, int end) {
     /* first find if this overlaps in the double booked */
     if (alreadyDoubleBooked(start, end) == true) {
       return false;
     }
   }
  
   private boolean alreadyDoubleBooked(int start, int end) {
     Range r = new Range(start, end);
     for (Range dbR : doubleBooked) {
       Range nr = dbR.overlaps(r);
       if (nr != null) {
         /* already bouble booked */
         System.out.println(r + " double booked with " + dbR);
         return false;
       }
     }
     
     for (Range dbR : allBooked) {
       Range nr = dbR.overlaps(r);
       if (nr != null) {
         /* add to bouble booked */
         insertToBookedList(doubleBooked, nr);
       }
     }
     insertToBookedList(allBooked, r);

     return false;
   }
   
   public void insertToBookedList(List<Range> bookingList, Range nr) {
     int insertAfter = -1;
     for (int i = 0; i < bookingList.size();) {
       Range or = bookingList.get(i).overlaps(nr);
       if (or == null) {
         if (nr.s < bookingList.get(i).s) {
           break;
         }
         insertAfter = i;
       } else {
         /* 
          * there is an overlap, in which case we need to concatenate all the
          * entries
          */
         if (or.s < bookingList.get(i).s) {
           bookingList.get(i).s = or.s;
         }
         if (or.e <= bookingList.get(i).e) {
           /* nothing to do */
           break;
         }
         
         List<Integer>  delIndex = new LinkedList<>();
         int j = i + 1;
         for (;
             j < bookingList.size() && or.e >  bookingList.get(i).e;
             ) {
           delIndex.add(j);
         }
         
         if (delIndex.isEmpty()) {
           bookingList.get(i).e = or.e;
           break;
         }
         
         if (j >= bookingList.size()) {
           bookingList.get(i).e = or.e;
           break;
         }
         
         bookingList.get(i).e = bookingList.get(j-1).e;
         
         Iterator<Integer> itr = delIndex.iterator();
         while (itr.hasNext()) {
           int next = itr.next();
           bookingList.remove(next);
         }
         
         break;
       }
     }
     bookingList.add(insertAfter + 1, nr);
   }
   
   public static void main(String[] arg) {
     
   }
}

