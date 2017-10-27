#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef int bool;
#define true 1
#define false 0

bool isInterleaveImpl(char *s1, int ind1, int len1,
        char *s2, int ind2, int len2,
        char *s3, int ind3, int len3) {
    bool val;
    
    //printf("s1 : %d, s2 : %d, s3 : %d\n", ind1, ind2, ind3);
    if (ind3 >= len3) {
        if (ind1 < len1 || ind2 < len2) {
            return false;
        }
        return true;
    }
    if (ind1 < len1) {
        if (s1[ind1] == s3[ind3]) {
            val = isInterleaveImpl(s1, ind1 + 1, len1,
                                   s2, ind2, len2,
                                   s3, ind3 + 1, len3);
            if (val == true) {
                return val;
            }
        }
    }
    if (ind2 < len2) {
        if (s2[ind2] == s3[ind3]) {
            val = isInterleaveImpl(s1, ind1, len1,
                                   s2, ind2 + 1, len2,
                                   s3, ind3 + 1, len3);
            if (val == true) {
                return val;
            }
        }
    }
    
    return false;
}

bool isInterleave(char* s1, char* s2, char* s3) {
    int ind1 = 0;
    int ind2 = 0;
    int ind3 = 0;
    int len1 = strlen(s1);
    int len2 = strlen(s2);
    int len3 = strlen(s3);
 
    //printf("Strings : %s, %s, Checking %s\n", s1, s2, s3);
    return (isInterleaveImpl(s1, ind1, len1,
                             s2, ind2, len2,
                             s3, ind3, len3));
}

int main(int argc, char **argv) {
    printf("Is Interleaved %d\n", isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    printf("Is Interleaved %d\n", isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    printf("Is Interleaved %d\n", isInterleave("", "", "aadbbcbcac"));
    printf("Is Interleaved %d\n", isInterleave("bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
        "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
                                               "babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab"));

}
