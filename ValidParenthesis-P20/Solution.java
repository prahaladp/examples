class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        
        for (int i =0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '}') {
                if (st.size() == 0 || st.pop() != '{') {
                    return false;
                }
                continue;
            }
            if (c == ']') {
                if (st.size() == 0 || st.pop() != '[') {
                    return false;
                }
                continue;
            }
            if (c == ')') {
                if (st.size() == 0 || st.pop() != '(') {
                    return false;
                }
                continue;
            }
            st.push(c);
        }
        if (st.size() != 0) {
            return false;
        }
        return true;
    }
}
