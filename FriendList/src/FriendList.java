import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FriendList {
  
  
  List<Integer> getFriends(Iterator<List<Integer>> relations,int id) {
    Set<Integer> backLog = new HashSet<>();
    Map<Integer, Set<Integer>> friendMap = new HashMap<>();
    List<Integer> friendsOfId = new LinkedList<>();
    
    while (relations.hasNext()) {
      List<Integer> cList = (List<Integer>)relations.next();
      Set<Integer> fList = friendMap.computeIfAbsent(cList.get(0), f -> new HashSet<>());
      fList.add(cList.get(1));
    }
    
    backLog = friendMap.get(id);
    
    if (backLog == null) {
      return friendsOfId;
    }
    
    while (backLog.isEmpty() == false) {
      int nextFriend = (Integer)backLog.iterator().next();
      backLog.remove(nextFriend);
      
      if (friendsOfId.contains(nextFriend) == true) {
        // ignore
        continue;
      }
      
      if (friendMap.get(nextFriend) != null) {
        backLog.addAll(friendMap.get(nextFriend));
      }
      friendsOfId.add(nextFriend);
    }
    
    return friendsOfId;
  }
  
  public static void main(String[] args) {
    FriendList fl = new FriendList();
    
    List<List<Integer>> friendsList = new LinkedList<>();
    friendsList.add(Arrays.asList(1, 2));
    friendsList.add(Arrays.asList(1, 3));
    friendsList.add(Arrays.asList(2, 4));
    friendsList.add(Arrays.asList(5, 2));

    // System.out.println("Friends Map1 " + fl.getFriends(friendsList.iterator(), 1));
    
    friendsList.clear();
    friendsList.add(Arrays.asList(4, 2));
    friendsList.add(Arrays.asList(1, 2));
    friendsList.add(Arrays.asList(1, 3));
    friendsList.add(Arrays.asList(2, 4));
    friendsList.add(Arrays.asList(5, 2));

    System.out.println("Friends Map2 " + fl.getFriends(friendsList.iterator(), 1));
  }

}
