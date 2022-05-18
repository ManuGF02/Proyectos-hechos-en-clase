#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){
    char buffer[50];
    int pidConsumer;


    int a = open("FIFOX", 1);
    sprintf(buffer, "%d", getpid());
    write(a, buffer, sizeof(buffer));
    close(a);

    int b = open("FIFOX", 0);
    read(b, buffer, 50);
    close(b);

    pidConsumer = atoi(buffer);

    printf("\nPID CONSUMER = %d\n", pidConsumer);

    system("sleep 2");

    kill(pidConsumer, SIGUSR1);
}