#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

char opcion[50];
int opcionInt, pidProducer;

void manejador(int signal){
    printf("HOLA BRO");
    int a = open("FIFO", 0);
    read(a, opcion, 50);
    close(a);
    opcionInt = atoi(opcion);
    switch(opcionInt){
        case 1:
            printf("\nSEÑAL RECIBIDA\n");
            kill(pidProducer, SIGUSR1);
        break;
        case 2:
            kill(pidProducer, SIGUSR1);
        break;
        default:
            printf("\nERROR\n");
        break;
    }

}

void main(){
    //Declaracion del FIFO y otras variables
    int a = mknod("FIFO", S_IFIFO|06666,0);
    
    char pidS[50], buffer[50];
    int pidProducer;

    //Leo el PID de Producer
    int b = open("FIFO", 0);
    read(b, buffer, 50);
    pidProducer = atoi(buffer);
    close(b);
    printf("mandando PID de CONSUMER...");
    
    //Mando mi PID a producer
    int c = open("FIFO", 1);
    sprintf(buffer, "%d", getpid());
    write(c, buffer, 50);
    close(c);


    //Muestro el PID de PRODUCER
    printf("\nPID DE PRODUCER: %d\n", pidProducer);

    pause();

    signal(SIGUSR1, manejador);
    pause();
}