#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *
reorganize(char *str) {
    int     chcount[26];
    char    *retstr;
    int     unique = 0;
    int     retcnt = 0;
    int     i;

    bzero(chcount, sizeof (chcount));
    for (i = 0; i < strlen(str); i++) {
        chcount[str[i] - 'a']++;
        if (chcount[str[i] - 'a'] == 1) {
            unique++;
        }
    }

    retstr = (char *)malloc(strlen(str));
    retcnt = 0;
    while (unique) {
        for (i = 0; i < 26; i++) {
            if (chcount[i]) {
                if (retcnt && *(retstr + retcnt - 1) == 'a' + i) {
                    *retstr = '\0';
                    return retstr;
                }
                *(retstr + retcnt) = 'a' + i;
                retcnt++;

                chcount[i]--;
                if (chcount[i] == 0) {
                    unique--;
                }
            }
        }
    }

    *(retstr + retcnt) = '\0';
    return retstr;
}

int
main(int argc, char *argv[]) {
    if (argc <= 1) {
        exit(1);
    }
    printf("result : %s\n", reorganize(argv[1]));
}
