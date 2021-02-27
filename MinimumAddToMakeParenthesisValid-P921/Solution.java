class Solution {
    public int minAddToMakeValid(String S) {
        Stack<Character> s = new Stack<>();
        
        for (char c : S.toCharArray()) {
            if (c == ')') {
                if (s.size() != 0 && s.peek() == '(') {
                    s.pop();
                } else {
                    s.push(c);
                }
            } else {
                s.push(c);
            }
        } 
        return s.size();
    }
}
