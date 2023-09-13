#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

int numMin(char* A, char* B){
    int cont = 0, tam;
    tam = strlen(A);
    
    for(int i = 0; i < tam; i++){
        int tempA = A[i];
        int tempB = B[i];
        if(tempB >= tempA){
         int temp = abs(tempB - tempA);
         cont += temp;
        }else{
            int temp = abs(tempB - tempA);
            int resto = 26 - temp;
            cont += resto;
        }
    }

    return cont;
}

int main(){
    int casos;
    scanf("%d", &casos);

    for(int i = 0; i < casos; i++){
        char A[100], B[100];
        scanf("%s", &A);
        scanf("%s", &B);

        int nmrOperacoes = numMin(A, B);
        printf("%d\n", nmrOperacoes);
    }
}