class Solution {
    public String maximumTime(String time) {
        StringBuilder s = new StringBuilder();
        
        for (int i = 0; i < time.length(); i++) {
            if (time.charAt(i) != '?') {
                s.append(time.charAt(i));
                continue;
            }    
            switch (i) {
                case 0:
                    if (time.charAt(1) == '?') {
                        s.append('2');
                    } else if (time.charAt(1) >= '0' && time.charAt(1) <= '3') {
                        s.append('2');
                    } else {
                        s.append('1');
                    }
                    break;
                case 1:
                    if (s.charAt(0) == '0') {
                        s.append('9');
                    } else if (s.charAt(0) == '1') {
                        s.append('9');   
                    } else if (s.charAt(0) == '2') {
                        s.append('3');
                    }
                    break;
                case 3:
                    s.append('5');
                    break;
                case 4:
                    s.append('9');
                    break;
                default:
                    break;
            }
        }   
        return s.toString();
    }
}
