#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <limits.h>
#include <stdbool.h>

void assign_pts(int a, int b, int *ap, int *bp)
{
    printf("%d %d\n", a, b);
    if (a == b) {
        return;
    }
    
    if (a > b) {
	printf("a > b\n");
        (*ap)++;
        return;
    }
    
    (*bp)++;
    return;
}
int main(){
    int ap = 0, bp = 0;
    int a0; 
    int a1; 
    int a2; 
    scanf("%d %d %d",&a0,&a1,&a2);
    printf("%d %d %d\n", a0, a1, a2);
    int b0; 
    int b1; 
    int b2; 
    scanf("%d %d %d",&b0,&b1,&b2);

    assign_pts(a0, b0, &ap, &bp);
    printf("%d %d\n", ap, bp);
    assign_pts(a1, b1, &ap, &bp);
    assign_pts(a2, b2, &ap, &bp);
    printf("%d %d\n", ap, bp);
}

