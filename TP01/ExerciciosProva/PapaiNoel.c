#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* carta(char* nome1, char* nome2){
    char* cartinha = (char*)malloc(sizeof(char) * 40);

    int tamTotal = strlen(nome1) + strlen(nome2);

    for(int i = 0, j = 0, z = 0; i < tamTotal; i += 4, j+=2, z+=2){
         cartinha[i] = nome1[j];
         cartinha[i + 1] = nome1[j + 1];
         cartinha[i + 2] = nome2[z];
         cartinha[i + 3] = nome2[z + 1];
    }

    return cartinha;
}

int main(){
    int casos;
    scanf("%d", &casos);

    for(int i = 0; i < casos; i++){
       char nome1[20];
       char nome2[20];
       scanf(" %[^\n]", nome1);
       scanf(" %[^\n]", nome2);
       char* cartinha = carta(nome1, nome2);
       printf("%s\n" , cartinha);
       free(cartinha);
    }
}