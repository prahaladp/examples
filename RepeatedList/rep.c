# include <stdio.h>
# include <string.h>
# include <stdio.h>
# include <stdlib.h>

char *test_list[3][4];

void
print_input(int num_list, int max_items)
{
	int	i = 0;
	int	j = 0;

	for (; i < num_list; i++) {
		for (j = 0; j < max_items; j++) {
			if (test_list[i][j] == NULL) {
				break;
			}
			printf("%s ", test_list[i][j]); 
		}
		printf("\n");
	}
}

int
valid_counters(int *counters, int num_list, int max_items)
{
	int	i = 0;

	for (; i < num_list; i++) {
		/*
		printf("validating list %d %d %p %s\n",
			i, counters[i],
			test_list[i][counters[i]],
			test_list[i][counters[i]]);
		*/
		/* if there is an item and it has a string */
		if (counters[i] < max_items &&
		    test_list[i][counters[i]] &&
		    strlen(test_list[i][counters[i]])) {
			return i;
		}
	}
	return -1;
}

void
set_next_counter(int *counters, int lowest, int max_items)
{
	int	i = counters[lowest] + 1;

	for (; i < max_items; i++) {
		if (test_list[lowest][i] != NULL &&
		    strlen(test_list[lowest][i]) != 0 &&
		    strcmp(test_list[lowest][counters[lowest]],
			test_list[lowest][i]) == 0) {
			continue;
		}
		break;
	}
	counters[lowest] = i;
}

void
compare_across_lists(int *counters,
	int num_list,
	int max_items,
	int lowest)
{
	int	i;
	int	atleast_one_match = 0;

	/* printf("Starting index = %d\n", lowest); */

	for (i = lowest + 1; i < num_list; i++) {
		if (test_list[i][counters[i]] &&
		    strlen(test_list[i][counters[i]])) {
			/*
			printf("Comparing %s with %s\n",
			    test_list[lowest][counters[lowest]],
			    test_list[i][counters[i]]);
			*/

			int cmp_index = strcmp(
					test_list[lowest][counters[lowest]],
					test_list[i][counters[i]]);
			if (cmp_index == 0) {
				atleast_one_match = 1;
				counters[i]++;
				continue;
			}
			if (cmp_index > 0) {
				lowest = i;
			}
		}
	}

	if (atleast_one_match) {
		set_next_counter(counters, lowest, max_items);
		return;
	}

	/* print the lowest and increment the index */
	printf("=> %s ", test_list[lowest][counters[lowest]]);
	set_next_counter(counters, lowest, max_items);
}

void
test_case_impl(int *counters, int num_lists, int max_items)
{
	int next_index = 0;

	bzero(counters, sizeof (int) * num_lists);
	print_input(num_lists, max_items);

	while ((next_index = valid_counters(counters, num_lists, max_items)) >= 0) {
		compare_across_lists(counters, num_lists, max_items, next_index);
	}
	printf("\n");
}

void
test_case1(int *counters, int num_lists, int max_items)
{
	bzero((void *)test_list, num_lists * max_items);
	test_list[0][0] = "aaa";
	test_list[0][1] = "bbb";
	test_list[0][2] = "ddd";
	test_list[0][3] = "xyxz";
	test_list[1][0] = "bbb";
	test_list[2][0] = "ddd";
	test_case_impl(counters, num_lists, max_items);	
}

void
test_case2(int *counters, int num_lists, int max_items)
{
	test_list[0][0] = "aaa";
	test_list[0][1] = "bbb";
	test_list[0][2] = "ddd";
	test_list[0][3] = "xyxz";
	test_list[1][0] = "bbb";
	test_list[2][0] = "ddd";
	test_list[2][1] = "xyxz";
	test_case_impl(counters, num_lists, max_items);	
}

void
test_case3(int *counters, int num_lists, int max_items)
{
	test_list[0][0] = "aaa";
	test_list[0][1] = "bbb";
	test_list[0][2] = "ddd";
	test_list[0][3] = "xyxz";
	test_list[1][0] = "bbb";
	test_list[2][0] = "bbb";
	test_list[2][1] = "Xyxz";
	test_case_impl(counters, num_lists, max_items);	
}

void
test_case4(int *counters, int num_lists, int max_items)
{
	test_list[0][0] = "aaa";
	test_list[0][1] = "bbb";
	test_list[0][2] = "bbb";
	test_list[0][3] = "ddd";
	test_list[0][3] = "xyxz";
	test_list[1][0] = "bbb";
	test_list[2][0] = "bbb";
	test_list[2][1] = "Xyxz";
	test_list[2][2] = "Xyxz";
	test_case_impl(counters, num_lists, max_items);	
}

void
test_cases()
{
	int num_lists = 3;
	int *counters = NULL;
	int max_items = 4;

	counters = (int *)malloc(sizeof (int) * num_lists);

	test_case1(counters, num_lists, max_items);
	test_case2(counters, num_lists, max_items);
	test_case3(counters, num_lists, max_items);
	test_case4(counters, num_lists, max_items);
}

int
main(int argc, char *argv[])
{
	test_cases();
}
