#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){

    pid_t pid;

    printf("\nPID: %d\n", getpid());
    printf("\nPID PADRE: %d\n", getppid());

    system("sleep 2");

    system("ps");

    system("sleep 2");

    printf("\nSe va a crear un proceso Hijo\n");

    pid = fork();

    if(pid < 0){
        printf("\nERROR en el fork\n");
    }else if(pid == 0){
        printf("\nPID HIJO: %d\n", getpid());
        printf("\nPID DE PADRE DE HIJO: %d\n", getppid());
        system("sleep 2");

        system("ps");
    }else{
        wait(NULL);
    }
}