#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int peep = 6;
int max_dish = 7;
int matrix[6][7] = {
	{ 1, 0, 0, 0, 1, 0, 0},
	{ 1, 0, 0, 0, 0, 1, 0},
	{ 1, 0, 0, 0, 0, 0, 1},
	{ 0, 1, 0, 0, 1, 0, 0},
	{ 0, 0, 1, 0, 0, 1, 0},
	{ 0, 0, 0, 1, 0, 0, 1}
};

int sel[7];
int users[6];

int result_dish[7];
int result_dish_cnt = 65535;

void
print_results()
{
	int	ii;

	printf("Results : \n");
	printf("Dish    : %d\n", result_dish_cnt);
	for (ii = 0; ii < max_dish; ii++) {
		if (result_dish[ii] == 1) {
			printf("Dish%d selected\n", ii);
		}
	}
}

void
mark_unmark_users(int x, int y, int mark)
{
	int	ii;

	for (ii = x; ii < peep; ii++) {
		if (matrix[ii][y] == 1) {
			users[ii] = mark;
		}
	}
}

int
all_users_selected()
{
	int	ii;

	for (ii = 0; ii < peep; ii++) {
		if (users[ii] == 0) {
			return (0);
		}
	}

	return (1);
}

void
copy_results(int dish) {
	if (dish < result_dish_cnt) {
		memcpy(result_dish, sel, sizeof (sel));
		result_dish_cnt = dish;
	}	
}

void
find_min_row(int x, int dish)
{
	int	y = 0;

	if (x >= peep) {
		/* nothing to do */
		return;
	}

	if (users[x] == 1) {
		/* already selected, go to next */
		find_min_row(x + 1, dish);
		return;
	}

	for (;y < max_dish; y++) {
		if (sel[y] == 1) {
			/* dish has been selected */
			continue;
		}
		if (matrix[x][y] == 1) {
			sel[y] = 1;
			mark_unmark_users(x, y, 1);
			dish++;
			if (all_users_selected()) {
				copy_results(dish);
			} else {
				find_min_row(x + 1, dish);
			}

			/* unmark and move to next */
			dish--;
			sel[y] = 0;
			mark_unmark_users(x, y, 0);
		}
	}
}

int
main(int argc, char *argv[])
{
	bzero(sel, sizeof (sel));
	bzero(users, sizeof (users));
	find_min_row(0, 0);
	print_results();
}
