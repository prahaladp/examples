#include <stdio.h>
#include <stdlib.h>
#include <strings.h>

typedef struct seq_info_s {
    struct seq_info_s   *next;
} seq_info_t;

typedef struct vector_s {
    int                 sum;
    int                 len;
    int                 nx;
    struct vector_s     *next;
} vector_t;

vector_t *
find_vector(int indx, int sum, vector_t *vec) {
    vector_t    *start = vec;

    printf("---- find_vector : (%d) (%d) (%p)\n", indx, sum, start);
    
    while (start && start->sum <= sum) {
        if (start->sum == sum) {
            printf("---- find_vector : return match for (%d)\n", sum);
            return start;
        }
        start = start->next;
    }
    return NULL;
}

char *
dump_vector(vector_t *vec, char *str) {

    str[0] = '\0';
    snprintf(str, sizeof (str),  " sum = %d, len = %d, nx = %d ", vec->sum, vec->len, vec->nx);
    return str;
}

void
add_to_sorted_vec(vector_t **vec, int indx, vector_t *new_vec) {
    vector_t    *start_vec = vec[indx];
    vector_t    *prev_vec = NULL;
    char        str[350];
   
    printf("---- add_to_sorted_vec : (%d) (%s)\n", indx, dump_vector(new_vec, str)); 
    if (!start_vec) {
        return;
    }
    
    while (start_vec && start_vec->sum <= new_vec->sum) {
        prev_vec = start_vec;
        start_vec = start_vec->next;
    }
    
    if (prev_vec == NULL) {
        printf("---- add_to_sorted_vec : adding at beginning \n");
        vec[indx] = new_vec;
    } else {
        prev_vec->next = new_vec;
    }
    new_vec->next = start_vec;
}

vector_t *
add_to_vec(int indx, int sum, int nx, int len, vector_t **vec) {
    vector_t    *new_vec = (vector_t *)malloc(sizeof(vector_t));
    bzero(new_vec, sizeof (vector_t));

    printf("---- add_to_vec : (%d)\n", indx);
    
    new_vec->sum = sum;
    new_vec->nx = nx;
    new_vec->len = len;
    
    add_to_sorted_vec(&vec[indx], indx, new_vec);
    return new_vec;
}

int
find_sub_index(int *arr, int indx, int len, int sum, vector_t **vec) {
    vector_t    *existing_vec = NULL;
    vector_t    *current_vec = NULL;
    int         seq_len = -1;
    int         i;
    
    if (indx >= len) {
        return -1;
    }
  
    printf("----  find_sub_index : (%d) (%d) (%d) \n", indx, sum, len);
    existing_vec = find_vector(indx, sum, vec[indx]);
    if (existing_vec) {
        printf("---- find_sub_index : found match\n");
        return existing_vec->nx;
    }
    
    for (i = indx; i < len; i++) {
        seq_len = find_sub_index(arr, indx + 1, len, sum - arr[indx], vec);
        if (seq_len != -1) {
            if (current_vec == NULL) {
                current_vec = add_to_vec(indx, (sum - arr[i]), i, seq_len, vec);
            } else if (current_vec->len > seq_len) {
                current_vec->nx = i;
            }
        }
    }
    
    if (current_vec == NULL) {
        return -1;
    }
    return current_vec->len;
}

void
init_vectors(int *arr, int indx, int len, vector_t *vec) {
    int     i;
    
    for (i = 0; i <= len; i++) {
        vec[i].sum = arr[i];
        vec[i].len = 1;
        vec[i].nx = i;
    }
}

void
print_result(int *arr, int len, int sum, vector_t **vec) {
    vector_t    *min_vector = NULL;
    int         min_seq_len = 0;
    int         min_indx = -1;
    vector_t    *curr_vec;
    int         i;

    printf("----- print_result -----\n");
    for (i = 0; i < len; i++) {
        curr_vec = find_vector(i, sum, vec[i]); 
        if (min_vector->len > min_seq_len) {
            min_seq_len = min_vector->len;
            min_vector = curr_vec; 
            min_indx = i;
        }
    }

    if (!min_vector) {
        printf("unable to find the starting vector\n");
        return;
    }

    while (min_vector) {
        printf("(%d)\n", arr[min_indx]);
        sum -= arr[min_indx];
        min_vector = find_vector(min_indx, sum, vec[min_indx]);
        if (min_vector) {
            min_indx = min_vector->nx;
        }
    }
}

void
find_longest_seq(int *arr, int len, int sum) {
    int         i = 0;
    int         seq_len = -1;
    vector_t    **vec = (vector_t **)malloc(sizeof(vector_t*) * len);
    bzero(vec, sizeof(vector_t *) * len);

    printf(" ------ find_longest_seq : (%d) (%d) \n", len, sum);
    for (i = 0; i < len; i++) {    
        seq_len = find_sub_index(arr, i, len, sum, vec);
    }
    if (seq_len == -1) {
        printf("unable to find a sequence number for %d", sum);
    } else {
        // print the sequence;
        print_result(arr, len, sum, vec);
    }
}

void
testcase1() {
    int arr[] = { 0, 1, -1, 2 };
    int sum = 1;

    find_longest_seq(arr, sizeof (arr)/ sizeof (int), sum);
}

int main(void) {
    testcase1();
	return 0;
}
