#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void main(){
	char passwd[10];
	int a[5], fd[2];
	pipe(fd);
	for(int i = 0; i < 5; i ++){
		a[i] = rand() % 10;
		printf("%d", a[i]);
	}
	printf("\n");
	pid_t pid;
	
	pid = fork();	
	if(pid < 0){
		printf("\nError\n");
		exit(-1);
	}else if(pid == 0){
		read(fd[0], passwd, sizeof(passwd));		
		printf("HIJO---PID: %d. CLAVBE: %d%d%d%d%d\n", getpid(), a[0]-1,a[1]-1,a[2]-1,a[3]-1,a[4]-1);
		
		printf("Contraseña del PADRE recibida %s\n", passwd);
			
	}else{
    	printf("PADRE---PID: %d. CLAVE: %d%d%d%d%d\n", getpid(), a[0]+1,a[1]+1,a[2]+1,a[3]+1,a[4]+1); 					
    	printf("Contraseña Padre");
		scanf("%s", passwd);
		write(fd[1], passwd, sizeof(passwd));
		wait(NULL);
	}
	
}
