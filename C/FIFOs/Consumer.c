#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

void main(){
    int estado, p, nBytes;
    char msg[50];

    if(p == -1){
        printf("\nERROR\n");
    }
    while(1){
        estado = open("FIFO2", 0);
        nBytes = read(estado, msg, 1);
        printf("\nObteniendo Mensage\n");
        while(nBytes != 0)
        {
            printf("%s", msg);
            nBytes = read(estado, msg, 1);
        }
    }
    close(estado);
}