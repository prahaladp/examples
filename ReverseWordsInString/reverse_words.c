#include<stdio.h>
#include<stdlib.h>
#include<string.h>

void
reverse_parts(char *s, char *e) {
    while (s <= e) {
        char    t = *s;
        *s = *e;
        *e = t;
        s++;
        e--;
    }
}

char *
remove_spaces(char *s) {
    while (*s && *s == ' ') { s++; }
    return s;
}

char *
find_next_space(char *s) {
    while (*s && *s != ' ') { s++; }
    return s;
}

char *
reverse(char *s) {
    int len = strlen(s);
    char    *e = s + len;
    char    *ws = s;
    char    *we = s;

    s = remove_spaces(s);
    ws = s;
    we = s;
    printf("trimmed = %s<\n", s);
    reverse_parts(s, e - 1);
    printf("reversed = %s<\n", s);

    /* start breaking */
    while (ws < e) {
        ws = remove_spaces(ws);
        if (ws >= e) {
            break;
        }
        we = find_next_space(ws + 1);
        reverse_parts(ws, we - 1);
        ws = remove_spaces(we);
    }
    return s;
}

int
main(int argc, char *argv[]) {
    char    str[80];
    strcpy(str, "the sky is blue");
    printf("reversing : the sky is blue : %s\n", reverse(str));
    str[0] = '\0';
    strcpy(str, "simple");
    printf("reversing : simple : %s\n", reverse(str));
    str[0] = '\0';
    strcpy(str, "two words");
    printf("reversing : two words : %s\n", reverse(str));
    str[2] = '\0';
    str[1] = '1';
    str[0] = ' ';
    printf("reversing :   : %si<\n", reverse(str));
}
