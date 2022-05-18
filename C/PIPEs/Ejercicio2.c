#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <unistd.h>
#include <wait.h>

void main(){
	pid_t pid;
	int fd[2];
	char cadena[10];
	
	pipe(fd);
	
	pid = fork();
	
	if(pid == 1){
		printf("PADRE----PID = %d\n", getpid());
		close(fd[0]);
		write(fd[1], "Hola Papa", 9);
		
	}if(pid == 0){
		printf("HIJO----PID = %d\n", getpid());
		close(fd[1]);
		read(fd[0], cadena, sizeof(cadena));
		printf("MENSAJE de Papá: %s \n", cadena);
		exit(-1);
		
	}else{
		printf("Falló el fork\n");
		exit(-1);  
	}
}
