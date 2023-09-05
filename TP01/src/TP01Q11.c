#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<stdbool.h>

bool isPalindromo(char* s, int start, int end){

       if(start > end) {
            return true;
        }

       if(s[start] == s[end]){
          return isPalindromo(s, start + 1, end - 1);
       }else{
         return false;
       }
    }

int main(void){
    char texto[3000];

    scanf(" %[^\n]", texto);
    while(strcmp(texto, "FIM\0") != 0){
       if(isPalindromo(texto, 0, strlen(texto) - 1)){
          printf("SIM\n");          
       }else {
          printf("NAO\n");
       }
        scanf(" %[^\n]", texto);
    }
}