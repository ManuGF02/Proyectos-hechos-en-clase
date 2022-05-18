#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){
    FILE *f;
    char cadena[100];



    printf("\nescriba algo en el fichero:\n");
    scanf("%s", cadena);


    f = fopen("fichero.txt", "w+");
    fwrite(cadena, sizeof(char), sizeof(cadena), f);
    fclose(f);

    int a = open("fichero.txt", 0);
    read(a, cadena, sizeof(cadena));
    close(a);

    printf("\nCADENA: %s\n", cadena);
}