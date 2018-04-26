#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <stdio.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/stat.h>
#define SIZE 100

static double s_time;
void starttime() {
//      s_time=1.0*gethrtime();
}

void endtime(long its) {
//  double e_time=1.0*gethrtime();
//  printf("Time per iteration %5.2f MB/s\n", (1.0*its)/(e_time-s_time*1.0)*1000);
//  s_time=1.0*gethrtime();
}

void test_write()
{
    starttime();
    int file = open("/tmp/random",O_WRONLY|O_CREAT,S_IWGRP|S_IWOTH|S_IWUSR);
    for (int i=0; i<SIZE; i++) {
        int ret = write(file,"a",1);
        {
            printf("write returned (%d, %d)\n", i, ret);
        }
        sleep(1);
    }
    close(file);
    endtime(SIZE);
}

int
main(int argc, char *argv[]) {
    test_write();
}
