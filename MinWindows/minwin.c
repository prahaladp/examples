#include<stdio.h>
#include<stdlib.h>
#include<string.h>

char*
minWindow(char* s, char* t) {
    char    *c = s;
    char    *subs = NULL;
    int     *ind = (int *)malloc(sizeof (int) * strlen(t));
    int     ts = 0;
    char    *first = NULL;
    char    *second = NULL;
    char    *minfirst = NULL;
    char    *minlast = NULL;
    char    *last = NULL;

    bzero(ind, sizeof (int) * strlen(t));
    while (*c) {
        char    *search = t;
        subs = strchr(search, *c);
        while ((subs = strchr(search, *c)) != NULL)  {
            // printf("--- found match for %c\n", *c);
            if (ind[subs - t] == 0) {
                ind[subs - t] = 1;
                ts++;

                if (first == NULL) {
                    first = c;
                } else {
                    if (*first == *c) {
                        first = c;
                    } if (!second) {
                        second = c;
                    }
                }
                last = c;
                break;
            } else {
                if (second == NULL && *first == *c) {
                    first = c;
                }
                search = subs + 1;
            }
        }
        if (ts == strlen(t)) {
            // printf("---found all matches\n");
            /* found all the matches */
            if (!minfirst) {
                minfirst = first;
                minlast = last;
            } else if (first - last + 1 < minlast - minfirst + 1) {
                minfirst = first;
                minlast = last;
            }

            if (ts == 1) {
                break;
            }

            /* reset the counter */
            if (second) {
                c = second;
            } else {
                c = last;
            }

            // printf("restarting at : %s\n", c);
            bzero(ind, sizeof (int) * strlen(t));
            first = NULL;
            second = NULL;
            ts = 0;
            continue;
        }
        c++;
    }

    // printf("done searching %s\n", minlast);
    if (minfirst) {
        char    *res = (char *) malloc(sizeof (char) * (minlast - minfirst + 2));
        int     diff = minlast - minfirst + 1;
        *res = '\0';
        strncpy(res, minfirst, minlast - minfirst + 1);
        *(res + diff) = '\0';
        return res;
    }

    return "";
}

int main(int argc, char *argv[]) {
    printf("minwindow = %s\n", minWindow("ADOBECODEBANC", "ABC"));
    printf("minwindow = %s\n", minWindow("ADOBECODEBANCA", "ABC"));
    printf("minwindow = %s\n", minWindow("a", "a"));
    printf("minwindow = %s\n", minWindow("bba", "ab"));

}
