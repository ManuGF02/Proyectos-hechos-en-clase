#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

void main(){
    int estado, p;
    char numAleatorios[3];

    p = mknod("FIFO2", S_IFIFO|0666,0);
    estado = open("FIFO2", 1);

    if(p == -1){
        printf("\nERROR\n");
        exit(0);
    }else{
        printf("\nCuántos Números quieres Producir\n");
        fgets(numAleatorios, 50, stdin);
        write(estado, numAleatorios, strlen(numAleatorios));
    }
    close(estado);
}