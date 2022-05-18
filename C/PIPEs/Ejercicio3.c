#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

void main() {
	int fd1[2], fd2[2],fd3[2],fd4[2];	
	pid_t pid, pidH;
	char passwd[20], fichero[20];	
	FILE *f;

	//Abro los pipes
	pipe(fd1);
	pipe(fd2);	
	pipe(fd3);
	pipe(fd4);	

	//Hago el fork
	pid = fork();
	if(pid == -1){
		printf("\nError\n");
	}else if(pid == 0){ //Hijo
		//Recibe la Contraseña
		read(fd1[0],passwd,sizeof(passwd));
		printf("Hijo PID es %d\n", getpid());
		printf("Contraseña: %s Viene del Padre con PID: %d\n",passwd,getppid());
		for(int i=0;i<strlen(passwd);i++){
			passwd[i] += 10;
		}				
		pidH = fork();	
		write(fd2[1], passwd, sizeof(passwd));			
		if(pidH == -1){
			printf("\nError\n");				
		}else if(pidH == 0){ //Nieto	
			//Recibe la contraseña
			read(fd2[0], passwd, sizeof(passwd));
			printf("Nieto PID es: %d\n", getpid());
			printf("Contraseña : %s Viene del Hijo con PID: %d\n",passwd,getppid());
			//Creación del Archivo
			f = fopen("Password","w");
			fwrite(passwd,1,sizeof(passwd),f);
			fclose(f);	
			//Enviamos el nombre de el fichero
			write(fd3[1], "Password", 20);	
		}else { //Hijo
			//Recibe el nombre del archivo
			read(fd3[0], fichero, sizeof(fichero));
			printf("Soy el Hijo y este es el nobre del fichero: %s\n", fichero);
			//Envia el nombre del archivo
			write(fd4[1], fichero, sizeof(fichero));						
		}
	}else { //Padre
		//Pide la Contraseña
		printf("Introduce una contraseña con maximo 20 caracteres: ");
		scanf("%s",passwd);
		//Envia la Contraseña
		write(fd1[1], passwd, sizeof(passwd));
		//Recibe el nombre del fichero			
		read(fd4[0], fichero, 20);
		printf("Soy el Abuelo y este es el nobre del fichero: %s\n", fichero);
	}	
}
