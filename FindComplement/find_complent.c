#include <stdio.h>
#include <stdlib.h>

int
find_complement(int n) {
    int mask = ~0;

    while (n & mask) {
        mask <<= 1;
    }
    return ~n & ~mask;
}

int
main(int argc, char *argv[]) {
    printf("complement(5) = %d\n", find_complement(5));
    printf("complement(10) = %d\n", find_complement(10));
}
