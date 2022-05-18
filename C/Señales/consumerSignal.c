#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>

pid_t pid_producer;

FILE *fifo;
void manejador(int signal)
{
    fifo = fopen("FIFOSignal", "r");
    fscanf(fifo, "%d", &pid_producer);
    fclose(fifo);     
    kill(pid_producer, SIGUSR1);
}

int main()
{
    pid_t pid = getpid();
    fifo = fopen("FIFOSignal", "w");
    fprintf(fifo, "%d", pid);
    fclose(fifo);

    signal(SIGUSR1, manejador);
    signal(SIGUSR2, manejador);
    pause();

    return 0;
}