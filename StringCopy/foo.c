#include <stdio.h>
#include <stdint.h>

void foo(void *a, void *b, int c)
{
	while (c) {
		switch (c % 4) {
		case 3:
			*((uint8_t *)a = *((uint8_t *)b);
			a += 1;
			b += 1;
			c -= 1;
			break;
		case 2:
			*((uint16_t *)a) = *((uint16_t *)b);
			a += 2;
			b += 2;
			c -= 2;
			break;
		case 1:
			*((uint8_t *)a) = *((uint8_t *)b);
			a += 1;
			b += 1;
			c -= 1;
			break;
		case 0:
			*((uint32_t *)a) = *((uint32_t *)b);
			a += 4;
			b += 4;
			c -= 4;
			break;
		}
	}
}

void
main(int argc, char *argv) {
}
