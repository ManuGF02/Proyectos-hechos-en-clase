#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>


void mostrarPid(int signal){
    printf("\nMI PID: %d, PID PADRE: %d\n", getpid(), getppid());
}


void main(){
    pid_t pid, pid2, pid3;
    int numProceso;

    pid = fork();

    switch(pid){
        case -1:
            printf("ERROR en la creacion del hijo");
        break;
        case 0:
            signal(10, mostrarPid);
            pause();
            kill(getppid(), 10);
        break;
        default:
            pid2 = fork();
            switch (pid2)
            {
            case -1:
                printf("ERROR en la creacion del hijo");
            break;
            case 0:
                signal(10, mostrarPid);
                pause();
                kill(getppid(), 10);
            break;
            default:
                pid3 = fork();
                switch(pid3){
                    case -1:
                        printf("ERROR en la creacion del hijo");
                    break;
                    case 0:
                        signal(10, mostrarPid);
                        pause();
                        kill(getppid(), 10);
                    break;
                    default:
                        printf("A QUÉ PROCESO QUIERES LLAMAR?");
                        scanf("%d", &numProceso);

                        switch(numProceso){
                            case 1:
                                printf("Mandando señal...");
                                kill(pid, 10);
                            break;
                            case 2:
                                printf("Mandando señal...");
                                kill(pid2, 10);
                            break;
                            case 3:
                                printf("Mandando señal...");
                                kill(pid3, 10);
                            break;
                            pause();
                        }
                    break;
                }
            break;
            }
        break;
    }
}