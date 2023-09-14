/*
Desde que foi lançado oficialmente o Pomekon no Brasil, Dabriel está tentando realizar seu maior sonho: Ser um Mestre Pomekon. Sua meta é conquistar os 151 Pomekons disponíveis. Ele já conseguiu capturar muitos monstrinhos, porém em sua cidade aparecem muitos Pomekons repetidos, fazendo com que ele capture diversas vezes o mesmo Pomekon.

Vendo que sua mochila está bem cheia, Dabriel pediu para que você fizesse um programa de computador que informasse a ele quantos Pomekons faltam para completar a coleção.

Entrada
A primeira linha do caso de teste consiste de um inteiro N (1 ≤ N ≤ 10³), representando a quantidade de Pomekons que Dabriel já capturou.
As próximas N linhas consistem de uma string S (1 ≤ |S| ≤ 10³), representando o nome de cada Pomekons. O nome de cada Pomekons consiste apenas de letras maiúsculas e minúsculas.


Saída
Você deverá imprimir: "Falta(m) X pomekon(s).", onde X representa a quantidade Pomekons não capturados.
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(){
    int qtdCapturados, qtdPokemons = 151;
    
    scanf("%d",&qtdCapturados);    
    
    char NomePokemom [qtdCapturados][20];

    for(int i = 0; i < qtdCapturados; i++){
       scanf("%s", &NomePokemom[i]);
    }

    int cont = 0;
    for(int i = 0; i < qtdCapturados; i++){
        int unico = 1;
      for(int j = 0; j < i; j++){
        if(strcmp(NomePokemom[i], NomePokemom[j]) == 0){
            unico = 0;
            break;
        }
       }
       if(unico){
        cont++;
      }
    }

    printf("Falta(m) %d pomekon(s).", qtdPokemons - cont);
}

/*
contains
*/