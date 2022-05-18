#include <stdio.h>
#include <signal.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

pid_t pidPing;


void manejadorPing(int signal)
{
    printf("Ping OK\n");
}

void manejadorRetraso(int signal)
{
    sleep(5);
    printf("Retraso finalizado\n");
}

int main()
{
    FILE *fifo;    
    int accion = -1;

    while (accion != 4)
    {
        printf("1-PING //2-Retraso de 5s//3- Mostrar info//4- Salir\n");
        scanf("%d", &accion);
        fifo = fopen("FIFOSignal", "r");
        fscanf(fifo, "%d", &pidPing);
        switch (accion)
        {
        case 1:
            printf("Solicitando ping a PID %d...\n", pidPing);
            fclose(fifo);
            fifo = fopen("FIFOSignal", "w");
            fprintf(fifo, "%d\n", getpid());

            fclose(fifo);
            kill(pidPing, SIGUSR1);
            signal(SIGUSR1, manejadorPing);
            int pause(void);
            
            system("./consumerS &");
            break;
        case 2://No se por qué no funciona
            fclose(fifo);
            kill(pidPing, SIGUSR2);
            signal(SIGUSR2, manejadorRetraso);
            while(1){}
            system("./consumerS &");
            break;
        case 3:
            printf("\nProducer PID: %d\nConsumer PID: %d\n", getpid(), pidPing);

            break;
        case 4:
            kill(pidPing, SIGTERM);
            exit(0);
            break;
        }
    }
    return 0;
}
