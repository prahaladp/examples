
public class Solution {
  public int addDigits(int num) {
    while (num >= 10) {
      String s = Integer.toString(num);

      int ns = 0;
      for (char c : s.toCharArray()) {
        ns += c - '0';
      }

      num = ns;
    }

    return num;
  }

  void main(String[] args) {
    Solution s = new Solution();

    System.out.println("AddDigits (38) = %d" + s.addDigits(38));
  }
}
