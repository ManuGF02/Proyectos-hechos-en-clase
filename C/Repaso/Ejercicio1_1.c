
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){

    char num[10], funcion[10] = "sleep ";


    printf("\nDime un número\n");
    scanf("%s", num);
    printf("\nEl número Sleep es %s\n", num);
    strcat(funcion, num);
    system(funcion);
}
