#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>

void manejador1(int signal)
{
    printf("\nSoy el proceso %d. Mi padre es %d\n", getpid(), getppid());
}

void manejadorPadre(int signal){
    printf("Terminando el programa\n");
}

int main()
{
    int proceso;
    pid_t pid, pid2, pid3;

    pid = fork();

    switch (pid)
    {
    case -1:
        printf("Error al crear al hijo\n");
        break;
    case 0:
        signal(SIGUSR1, manejador1);
        pause();
        kill(getppid(), SIGUSR1);
        break;
    default:
        pid2 = fork();
        switch (pid2)
        {
        case -1:
            printf("Error al crear al hijo\n");
            break;
        case 0:
            signal(SIGUSR1, manejador1);
            pause();
            kill(getppid(), SIGUSR1);
            break;

        default:
            pid3 = fork();
            switch (pid3)
            {
            case -1:
                printf("Error al crear al hijo\n");
                break;
            case 0:
                signal(SIGUSR1, manejador1);
                pause();
                kill(getppid(), SIGUSR1);
                break;

            default:
                printf("Escribe un número del 1 al 3\n");
                scanf("%d", &proceso);

                switch (proceso)
                {
                case -1:
                    break;
                case 1:
                    kill(pid, SIGUSR1);
                    break;
                case 2:
                    kill(pid2, SIGUSR1);
                    break;
                case 3:
                    kill(pid3, SIGUSR1);
                    break;

                default:
                    printf("Escribe un número del 1 al 3\n");                   
                    break;
                }
                signal(SIGUSR1, manejadorPadre);
                pause();
            }
            break;
        }
        break;
    }

    return 0;
}
