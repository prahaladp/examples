#include<stdio.h>
#include<stdlib.h>
#include<strings.h>

/*
 * See : https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
 */

char
nextGreatestLetter(char* letters, int lettersSize, char target) {
    int     i = 0;

    while (i < lettersSize) {
        if (letters[i] > target) {
            return letters[i];
        }
        i++;
    }

    return letters[0];
}

int
main(int argc, char *argv[]) {
    printf(" cfj, a = %c\n", nextGreatestLetter("cfj", 3, 'a'));
    printf(" cfj, c = %c\n", nextGreatestLetter("cfj", 3, 'c'));
    printf(" cfj, d = %c\n", nextGreatestLetter("cfj", 3, 'd'));
}
