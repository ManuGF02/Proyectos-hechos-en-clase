#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>


void manejador(int signal){
    printf("\nsEÑAL RECIBIDA\n");
}


void main(){
    char buffer[50];
    int pidProducer;
    int a = mknod("FIFOX", S_IFIFO, 0);

    int b = open("FIFOX", 0);
    read(b, buffer, 50);
    close(b);

    pidProducer = atoi(buffer);
    printf("\nPID PRODUCER: %d\n", pidProducer);

    sprintf(buffer, "%d", getpid());
    int c = open("FIFOX", 1);
    write(c, buffer, sizeof(buffer));
    close(c);

    signal(SIGUSR1, manejador);
    pause();
}