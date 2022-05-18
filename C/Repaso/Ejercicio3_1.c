#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <signal.h>

void main(){

    char carpeta[50], archivo[50], crearCarpeta[50] = "mkdir ~/Escritorio/Repaso/";
    char crearArchivo[100] = "touch ~/Escritorio/Repaso/";
    int numArchivos;

    printf("\nNombre de la Carpeta\n");
    scanf("%s", carpeta);
    printf("\nNúmero de Archivos a Crear\n");
    scanf("%d", &numArchivos);

    if(numArchivos > 10){
        exit(0);
    }

    printf("\nNombre del Archivo\n");
    scanf("%s", archivo);

    strcat(crearCarpeta, carpeta);

    system(crearCarpeta);

    
    strcat(crearArchivo, carpeta);
    strcat(crearArchivo, "/");
    strcat(crearArchivo, archivo);  

    for(int i = 0; i < numArchivos; i++){
        strcat(crearArchivo, "_");
        system(crearArchivo);
    }
}