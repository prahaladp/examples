
public class LongInt {
  String numb1;
  String numb2;
  
  public LongInt(String num1, String num2) {
    numb1 = num1;
    numb2 = num2;
  }
  
  void multiply(StringBuffer res, int curr, char j, char i) {
    int newVal = (i - '0') * (j - '0') + (res.charAt(curr) - '0');
    
    if (newVal < 10) {
      res.setCharAt(curr, (char)('0' + newVal));
      return;
    }
    
    /* handle carryover */
    int carryOver = newVal/10;
    
    newVal %= 10;
    res.setCharAt(curr, (char)('0' + newVal));
    curr--;
    
    while (carryOver != 0) {
      newVal = (res.charAt(curr) - '0') + carryOver;
      carryOver = 0;
      if (newVal < 10) {
        res.setCharAt(curr, (char)('0' + newVal));
      } else {
        carryOver = newVal/10;
        res.setCharAt(curr, (char)('0' + (newVal % 10)));
      }
      curr--;
    }
  }
  
  public String mult() {
    StringBuffer res = new StringBuffer();
    int length = numb1.length() + numb2.length() + 2;
    res.setLength(length);

    String i = numb1;
    String j = numb2;
    
    if (numb2.length() > numb1.length()) {
      i = numb2;
      j = numb1;
    }

    for (int indx = 0; indx < res.length(); indx++) {
      res.setCharAt(indx, '0');
    }
    
    int offset = 0;
    for(int ind = j.length() - 1; ind >= 0 ; ind--) {
      int curr = length - offset - 1;
      for (int m = i.length() - 1; m >= 0; m--) {
        multiply(res, curr, j.charAt(ind), i.charAt(m));
        curr--;
      }
      System.out.println("intermediate (" + ind + ") : " + res.toString());
      offset++;
    }
    return res.toString();
  }
  
  public static void main(String[] args) {
    LongInt t = new LongInt("100", "2");
    System.out.println("mult = " + t.mult());
    
    t = new LongInt("100", "220");
    System.out.println("mult = " + t.mult());

    t = new LongInt("89", "45");
    System.out.println("mult = " + t.mult());
    

    t = new LongInt("89123123121241241", "4523423415115");
    System.out.println("mult = " + t.mult());


  }
}
