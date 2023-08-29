#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<stdbool.h>


bool isPalindromo(char* s){
     int j = strlen(s) - 1;

     for(int i = 0; i <= strlen(s) / 2; i++, j--){
       if(s[i] != s[j]){
          return false;
       }
     }
    return true;
}

int main(void){
    char texto[3000];

    scanf(" %[^\n]", texto);
    while(strcmp(texto, "FIM\0") != 0){
       if(isPalindromo(texto)){
          printf("SIM\n");          
       }else {
          printf("NAO\n");
       }
        scanf(" %[^\n]", texto);
    }
}