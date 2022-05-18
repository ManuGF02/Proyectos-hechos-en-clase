#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main(){
  pid_t pid_hijo1,pid_hijo2,pid_hijo3;
  pid_hijo1=fork();
  if(pid_hijo1==0){
    printf("Soy el hijo 1, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
    sleep(2);
    exit(0);
  }
  pid_hijo2=fork();
  if(pid_hijo2==0){
    printf("Soy el hijo 2, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
    sleep(2);
    exit(0);
  }
  pid_hijo3=fork();
  if(pid_hijo3==0){
    printf("Soy el hijo 3, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());
    sleep(2);
    exit(0);
  }
  printf("Proceso PADRE = %d\n",getppid());
  exit(0);
}