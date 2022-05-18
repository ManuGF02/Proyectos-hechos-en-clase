#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){
    int array[5];
    pid_t pid;
    int fd[2];
    char passwd[10];
    pipe(fd);

    for(int i = 0; i < 5; i++){
        array[i] = rand() % 10;
        printf("\nPosicion ");
        printf("%d", i);
        printf(": ");
        printf("%d", array[i]);
        printf("\n");
        system("sleep 1");
    }

    
    pid = fork();
    if(pid < 0){
        printf("\nERROR\n");
    }else if(pid == 0){
        read(fd[0], passwd, sizeof(passwd));
        printf("\nCONTRASEÑA: %s\n", passwd);
        
        
        printf("Restando 1 a los elementos del array...");
        system("sleep 1");

        for(int i = 0; i < 5; i++){
            array[i] = array[i] - 1;
            printf("\nPosicion ");
            printf("%d", i);
            printf(": ");
            printf("%d", array[i]);
            printf("\n");
            system("sleep 1");
        }
    }else{
        printf("\nSumando 1 a los elementos del array...\n");
        system("sleep 1");

        for(int i = 0; i < 5; i++){
            array[i] = array[i] + 1;
            printf("\nPosicion ");
            printf("%d", i);
            printf(": ");
            printf("%d", array[i]);
            printf("\n");
            system("sleep 1");
        }
        printf("\nEscribe una contraseña:\n");
        scanf("%s", passwd);
        write(fd[1], passwd, sizeof(passwd));
    }
}