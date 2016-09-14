bool MatchingParentheses(std::string para) {
    char *stack = (char *)malloc(sizeof (char) * para.length());
    int  indx = 0;
    bool val = false;
    
    for(char& c : para) {
        if (c == '(' || c == '{' || c == '[') {
            stack[indx++] = c;
        } else {
            if (c == ')') {
                val = (stack[indx - 1] != '(') ? false : true;
                if (!val) {
                    return (val);
                }
                indx--;
            }
            if (c == '}') {
                val = (stack[indx - 1] != '{') ? false : true;
                if (!val) {
                    return (val);
                }
                indx--;
            }
            if (c == ']') {
                val = (stack[indx - 1] != '[') ? false : true;
                if (!val) {
                    return (val);
                }
                indx--;
            }
        }
    }
    
    return (true);
}
