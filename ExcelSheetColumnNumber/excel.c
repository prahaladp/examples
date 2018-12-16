#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int titleToNumber(char* s) {
    int i;
    int indx = 0;
    int pow = 1;
    int blk = 0;
    
    for (i = strlen(s) - 1; i >= 0; i--) {
        printf(" indx = %d, pow = %d, blk = %d, %c-A = %d\n", indx, pow, blk, s[i], s[i] - 'A');
        if (i == strlen(s) - 1) {
            indx = (pow * (s[i] - 'A'));
        } else {
            indx += (pow * (s[i] - 'A' + 1));
        }
        pow *= 26;
        blk += pow;
    }
    
    return indx + 1;
}

int
main(int argc, char *argv[]){
    printf("index (B) = %d\n", titleToNumber("B"));
    printf("index (AA) = %d\n", titleToNumber("AA"));
    printf("index (AB) = %d\n", titleToNumber("AB"));
    printf("index (BA) = %d\n", titleToNumber("BA"));
    printf("index (ZY) = %d\n", titleToNumber("ZY"));
    printf("index (AAA) = %d\n", titleToNumber("AAA"));
    printf("index (ZAC) = %d\n", titleToNumber("ZAC"));
}
