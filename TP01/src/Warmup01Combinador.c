#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *stringCompactada(char *string1, char *string2){
    int size = strlen(string1) + strlen(string2);
    char *palavraCompactada = (char *)malloc(size * sizeof(char));
    int j = 0, k = 0;
    int size2 = strlen(string2);

    for (int i = 0; i < size; i++){
        if (i % 2 == 0)
        {
            palavraCompactada[i] = string1[k];
            k++;
        }else{
            if (j < size2){
                palavraCompactada[i] = string2[j];
                j++;
            }else{
                palavraCompactada[i] = string1[k];
                k++;
            }
        }
    }

    return palavraCompactada;
}

int main(){
    char palavra1[50];
    char palavra2[50];

    for(int i = 0; i < 3; i++){
        scanf("%s", palavra1);
        scanf("%s", palavra2);
        char *resultado = stringCompactada(palavra1, palavra2);
        printf("%s\n", resultado);  
        free(resultado);
    }
}