
static inline void spinlock(volatile int *lock)
{
    while(!__sync_bool_compare_and_swap(lock, 0, 1))
    {
    }
}

int main(int argc, char *argv[]) {
}
