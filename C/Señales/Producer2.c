#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void manejadorPING(int signal){
    printf("PING OK :-)");
}

void manejador2(int signal){
    printf("\nRETRASO DE 5 SEG\n");
}

void main(){
    //Declaracion de variables
    char opcion[50];
    char pidOtro[50], buffer[50];
    int pidConsumer, opcionInt;
    
    //Mando mi PID a Consumer
    int a = open("FIFO", 1);
    sprintf(buffer, "%d", getpid());
    write(a, buffer, 50);
    close(a);
    printf("MANDANDO PID DE PRODUCER...");

    //recibo y Muestro el PID de consumer
    int b = open("FIFO", 0);
    read(b, buffer, 50);
    pidConsumer = atoi(buffer);
    close(b);

    printf("\nPID DE CONSUMER: %d\n", pidConsumer);

    //Cuerpo del programa
    printf("\nElige una de estas Opciones:\n");
    printf("\n1-PING a CONSUMER\n");
    printf("\n2-Retraso de 5 Seg.\n");
    printf("\n3-Mostrar Info\n");
    printf("\n4-Salir\n");
    scanf("%s", opcion);
    printf("\nPRODUCER--Opción elegida: %s\n", opcion);

    //Casteo El PID de Consumer a int
    opcionInt =  atoi(opcion);
    
    switch(opcionInt){
        case 1:
            printf("\nSolicitando PING a PID-%d\n", pidConsumer);
            kill(pidConsumer, SIGUSR1);
            int c = open("FIFO", 1);
            write(c, opcion, sizeof(opcion));
            close(c);
            signal(SIGUSR1, manejadorPING);
            pause();
            
        break;
        case 2:
            printf("\nEnviando Señal a CONSUMER...\n");
            kill(pidConsumer, SIGUSR1);
            signal(SIGUSR1, manejador2);
            pause();
        break;
        case 3:
            system("ps\n");
            printf("PRODUCER--PID: %d,\b NOMBRE: Producer2_exe\n", getpid());
            printf("CONSUMER--PID: %d,\b NOMBRE: Consumer2_exe\n", pidConsumer);
            kill(pidConsumer, SIGKILL);
        break;
        case 4:
            kill(pidConsumer, SIGKILL);
            exit(-1);
        break;
        default:
            printf("ERROR ELIGIENDO UNA OPCIÓN");
            kill(pidConsumer, SIGKILL);
            exit(-1);
        break;
        kill(pidConsumer, SIGUSR1);
        pause();
    }
}