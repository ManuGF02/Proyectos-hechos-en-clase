#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){
    int datos[4];
    char sleep[50] = "sleep ", var[10];    

    printf("\nCuántos Números Aleatorios quieres??\n");
    scanf("%d", &datos[0]);

    printf("\nIndica el Numero MINIMO\n");
    scanf("%d", &datos[1]);

    printf("\nIndica el NUMERO MAXIMO\n");
    scanf("%d", &datos[2]);

    printf("\nTIEMPO QUE TARDE EN ENVIAR EL SIGUIENTE NUMERO\n");
    scanf("%d", &datos[3]);

    int numAleatorios[datos[0]];
    
    sprintf(var, "%d", datos[3]);
    strcat(sleep, var);

    for(int i = 0; i < datos[0]; i++){
        numAleatorios[i] = rand() % (datos[2] - datos[1] + 1) + datos[1];
        printf("\n%d\n", numAleatorios[i]);
    }

    int a = open("FIFO", 1);

    for(int i = 0; i < sizeof(numAleatorios); i++){
        char numActual[50];
        sprintf(numActual, "%d", numAleatorios[i]);
        write(a, numActual, sizeof(numActual));
        printf("Enviando...");
        system(sleep);
    }
    write(a, "*", 10);

    close(a);
}