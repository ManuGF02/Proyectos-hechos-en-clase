#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){
    int a = mknod("FIFO", S_IFIFO|06666,0);
    int numeros[99];

    int posicion = 0;
    char numActual[10];
    int b = open("FIFO", 0);
    for(;;){
        
        read(b, numActual, 10);

        if(strchr(numActual, "*")){
            break;
        }

        numeros[posicion] = atoi(numActual);
        printf("%s", numActual);
    }
    close(b);
}