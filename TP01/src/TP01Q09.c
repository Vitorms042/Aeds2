#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    double entrada;
    File *arquivo = fopen("entrada.txt", "rw");
    int size;
    scanf("%d", &size);

    for(int i = 0; i < size; i++){
       scanf("%lf", &entrada);
       fprintf(arquivo, "%lf\n", entrada);
    }

    for(int i = (size -1); i >= 0; i--){
        double resp;
        fscanf(arquivo, "%lf\n", &resp);
    }


}