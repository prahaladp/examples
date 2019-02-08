
public class Solution {
  class Tag {
    private int index;
    private int cindex;
    private int end;
    private String code;
    private String tagName;

    public Tag(String code, int i) {
      this.index = i;
      this.cindex = i;
      this.end = -1;
      this.code = code;
    }

    private boolean extractTagName() {
      cindex++;
      for (int i = cindex; i < code.length();) {
        char ch = code.charAt(i);
        if (ch == ' ') {
          i++;
        } else if (ch == '>') {
          tagName = code.substring(cindex, i);
          System.out.println("Extracted tag " + tagName);
          cindex = i + 1;
          return true;
        } else if (ch < 'A' || ch > 'Z') {
          return false;
        } else {
          i++;
        }
      }
      return false;
    }

    public boolean extractTagEndAndVerify() {
      if (cindex == code.length() - 1) {
        return false;
      }
      for (int i = cindex; i < code.length(); i++) {
        char ch = code.charAt(i);
        if (ch == '>') {
          String tag = code.substring(cindex, i);
          if (tag.compareTo(tagName) != 0) {
            System.out.println("Incorrect end tag name " + tag);
            return false;
          }
          System.out.println("Completed tag " + tagName);
          end = i + 2;
          return true;
        }
      }

      return false;
    }

    public int parse() {
      if (extractTagName() == false) {
        return -1;
      }
      for (; cindex < code.length();) {
        char ch = code.charAt(cindex);
        if (ch == ' ') {
          cindex++;
        } else if (ch == '<') {
          if (cindex == code.length() - 1) {
            System.out.println("index spilled over");
            return -1;
          }
          if (code.charAt(cindex + 1) == '/') {
            cindex += 2;
            if (extractTagEndAndVerify() == false) {
              return -1;
            }
            return end;
          } else {
            Tag nestedTag = new Tag(code, cindex);
            int nextIndex = nestedTag.parse();
            if (nextIndex == -1) {
              System.out.println("nested tag parse failed");
              return -1;
            }
            cindex = nextIndex;
          }
        }
      }
      end = cindex;
      return end;
    }
  }

  public boolean isValid(String code) {
    if (code.length() == 0) {
      return true;
    }

    code = code.trim();
    Tag currentTag = null;

    for (int i = 0; i < code.length();) {
      if (code.charAt(i) == '<') {
        currentTag = new Tag(code, i);
        i = currentTag.parse();
        if (i == -1) {
          return false;
        }
      } else if (code.charAt(i) == ' ') {
        i++;
      } else {
        System.out.println("Incorrect character " + code.charAt(i) + " at index " + i);
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Solution s = new Solution();
    s.isValid("<TAGNAME></TAGNAME>");
    s.isValid("<TAGNAME>   </TAGNAME>");
    s.isValid("<TAGNAME> </END> </TAGNAME>");
    s.isValid("<TAGNAME><TAG1> </TAG1></TAGNAME>");
  }
}
