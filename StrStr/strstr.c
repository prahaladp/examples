# include <stdio.h>

int strStr(char* haystack, char* needle) {
    char *tmpptr = needle;
    char *start = haystack;
    int  indx = -1;
    int  i = 0;
    
    if (haystack == NULL || needle == NULL) {
        return -1;
    }
    
    while (*haystack) {
    
        printf("%d %s -> %s (%d)\n", i, haystack, tmpptr, indx);

        if (*haystack == *tmpptr) {
            if (tmpptr == needle) {
                indx = i;
            }
            tmpptr++;
        } else {
            if (tmpptr != needle) {
                haystack = start + indx;
                i = indx;
            }
            tmpptr = needle;
            indx = -1;
        }
        if (*tmpptr == '\0') {
            return indx;
        }
        haystack++;
        i++;
    }
    return -1;
}

int main(int argc, char *argv[]) {
    printf("strStr : aaaaa, bba = %d\n", strStr("aaaaa", "bba"));
    printf("strStr : aaaaa, aa = %d\n", strStr("aaaaa", "aa"));
    printf("strStr : abaaa, ba = %d\n", strStr("abaaa", "ba"));
    printf("strStr : abbaa, ba = %d\n", strStr("abbaa", "ba"));


}
