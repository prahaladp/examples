#include <stdio.h>

void
print_char(char ch, int cnt) {
	while (cnt--) {
		printf("%c", ch);
	}
}

void
print_star(int s, int space) {
	print_char(' ', space);
	print_char('*', s);
	print_char(' ', space);
	print_char('\n', 1);
}

void
print(int n) {
	int	s = 1;

	if (n % 2 == 0 ||
	    n < 1) {
		printf("Incorrect input\n");
		return;
	}

	while (s <= n) {
		print_star(s, (n - s)/2);
		s += 2;
	}

	s = n - 2;
	while (s >= 1) {
		print_star(s, (n - s)/2);
		s -= 2;
	}
}

int
main(int argc, char *argv[]) {
	print(1);
	print(5);
	print(15);
	print(-1);
	print(10);
}
