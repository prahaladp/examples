import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NumberToWords {
  int   v;
  
  public NumberToWords(int val) {
    v = val;
  }
  
  public int findMax(int val) {
    int[] maxVal = {
        1000000000, 1000000, 1000, 100, 
        90, 80, 70, 60, 50, 40, 30, 20,
        19, 18, 17, 16, 15, 14, 13, 12, 11,
        9, 8, 7, 6, 5, 4, 3, 2, 1
    };
    
    for (int i = 0; i < maxVal.length; i++) {
      if (val >= maxVal[i]) {
        return maxVal[i];
      }
    }
    return 1;
  }
  
  public String convertToTextImpl(int cVal) {
    StringBuilder s = new StringBuilder();
    int temp = cVal;
    Map<Integer, String> numToText = new ConcurrentHashMap<>();
    numToText.put(1000000000, "billion");
    numToText.put(1000000, "million");
    numToText.put(1000, "thousand");
    numToText.put(100, "hundred");
    numToText.put(90, "ninety");
    numToText.put(80, "eighty");
    numToText.put(70, "seventy");
    numToText.put(60, "sixty");
    numToText.put(50, "fifty");
    numToText.put(40, "forty");
    numToText.put(30, "thirty");
    numToText.put(20, "twenty");
    numToText.put(19, "nineteen");
    numToText.put(18, "eighteen");
    numToText.put(17, "seventeen");
    numToText.put(16, "sixteen");
    numToText.put(15, "fifteen");
    numToText.put(14, "fourteen");
    numToText.put(13, "thirteen");
    numToText.put(12, "twelve");
    numToText.put(11, "eleven");
    numToText.put(10, "ten");
    numToText.put(9, "nine");
    numToText.put(8, "eight");
    numToText.put(7, "seven");
    numToText.put(6, "six");
    numToText.put(5, "five");
    numToText.put(4, "four");
    numToText.put(3, "three");
    numToText.put(2, "two");
    numToText.put(1, "one");
   
    while (temp > 0) {
      int t = findMax(temp);
      int r = temp/t;
      if (t >= 100) {
        s.append(convertToTextImpl(r));
      }
      s.append(numToText.get(t) + " ");
      temp = temp % t;
    }
    
    return s.toString();
  }
  
  public String convertToText() {
    return convertToTextImpl(v);
  }
  
  public static void main(String[] args) {
      List<Integer> nums = Arrays.asList(123, 12345, 1234567);
      
      nums.stream().forEach(n -> {
        NumberToWords s = new NumberToWords(n);
        System.out.println("Number : " + s.convertToText());
      });
  }

}
