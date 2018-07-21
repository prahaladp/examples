#include<stdio.h>
#include<string.h>

typedef int bool;
#define true 1
#define false 0

bool
isSubsequenceImpl(char* s, char* t, int lens, int lent, int inds, int indt) {

    while (indt < lent) {
        if (s[inds] != t[indt]) {
 //           printf("......match not found for  (%d, %d)\n", inds, indt);
            indt++;
            continue;
        }
//        printf("match found for %c at (%d, %d)\n", s[inds], inds, indt);

        if (inds + 1 >= lens) {
            return true;
        }

        bool val = isSubsequenceImpl(s, t, lens, lent, inds + 1, indt + 1);
        if (val) {
            return true;
        }
        indt += 1;
    }

    return false;
}

bool isSubSequence(char *s, char *t) {
    int lens = strlen(s);
    int lent = strlen(t);

    return isSubsequenceImpl(s, t, lens, lent, 0, 0);
}

int
main(int argc, char *argv[]) {
    char *s = "abc", *t = "ahbgdc";
    printf("val = %d\n", isSubSequence(s, t));
    s = "axc", t = "ahbgdc";
    printf("val = %d\n", isSubSequence(s, t));
}
