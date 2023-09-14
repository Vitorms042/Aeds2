#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    int casos;

     while(1){
        scanf("%d", &casos);
        if(casos == 0){
            break;
        }

        for(int i = 0; i < casos; i++){
         char linha[100];
         scanf("%s", linha);

         int cont = 0;
        
         for(int j = 0; j < strlen(linha); j++){
            soma += linha[j] - '0';
            //sona += linha[j] - 'a'; 'A'
        }
       }
       printf("%d\n", soma);
    }
}

/*
import java.util.*;

public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);

    while(true){
        int casos = scanner.nextInt();
        if(casos == 0){
            break;
        }
        scanner.nextLine();
  
        for(int i = 0; i < casos; i++){
            String linha = scanner.nextLine();
            int cont = 0;

          for(int j = 0; j < linha.length(); i++){
            char c = linha.charAt(j);
            cont += c - '0';
          }
          Systrm.out.println(cont);  
        }      
    } 
    scanner.close();
 }
*/