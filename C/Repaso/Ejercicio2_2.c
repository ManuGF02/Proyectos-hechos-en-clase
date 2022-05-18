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
    int fd[2], fd2[2];
    char passwd[10];
    pipe(fd);
    pipe(fd2);

    printf("\nIntroduce Contraseña para HIJO:\n");
    scanf("%s", passwd);
    system("sleep 1");
    write(fd[1], passwd, sizeof(passwd));

    pid = fork();

    if(pid < 0){
        printf("ERROR");
    }else if(pid == 0){
        read(fd[0], passwd, sizeof(passwd));
        printf("\nHIJO-Leyendo Contraseña de PAdre...\n");
        system("sleep 1");
        printf("\nContraseña: %s\n", passwd);
        system("sleep 1");
        printf("\nIntroduce Contraseña para PADRE:\n");
        scanf("%s", passwd);
        write(fd2[1], passwd, sizeof(passwd));
    }else{
        wait(NULL);
        read(fd2[0], passwd, sizeof(passwd));
        printf("\nLeyendo Contraseña de HIJO...\n");
        system("sleep 1");
        printf("\nContraseña de HIJO: %s\n", passwd);
    }
}