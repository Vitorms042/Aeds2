#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct {
  char id[10];
  char nome[150];
  char altura[10];
  char peso[10];
  char universidade[150];
  char anoNascimento[10];
  char cidadeNascimento[150];
  char estadoNascimento[150];
}Jogador;

int mov = 0, comp = 0;


Jogador clone(Jogador *jogador)
{
    Jogador novo;
    strcpy(novo.id, jogador->id);
    strcpy(novo.nome, jogador->nome);
    strcpy(novo.altura, jogador->altura);
    strcpy(novo.peso, jogador->peso);
    strcpy(novo.universidade, jogador->universidade);
    strcpy(novo.anoNascimento, jogador->anoNascimento);
    strcpy(novo.cidadeNascimento, jogador->cidadeNascimento);
    strcpy(novo.estadoNascimento, jogador->estadoNascimento);
    return novo;
}

void imprimir(Jogador *jogador){
  printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);

}

void ler(Jogador *jogador, char linha[]){
  int y = 0;
  char c = ',';
  int virgula[8];
  char str0[100], str1[100], str2[100], str3[100], str4[100], str5[100], str6[100], str7[100];

  memset(str0, '\0', sizeof(str0));
  memset(str1, '\0', sizeof(str1));
  memset(str2, '\0', sizeof(str2));
  memset(str3, '\0', sizeof(str3));
  memset(str4, '\0', sizeof(str4));
  memset(str5, '\0', sizeof(str5));
  memset(str6, '\0', sizeof(str6));
  memset(str7, '\0', sizeof(str7));
  
	for(int i=0; i<strlen(linha); i++){
		if(linha[i] == c) {
            virgula[y++] = i;
		}
    }
    y=0;
  
    for(int i=0; i<virgula[0]; i++){
        str0[y++] = linha[i];
    }
    y=0;

      for(int i=virgula[0]+1; i<virgula[1]; i++){
        str1[y++] = linha[i];
    }
    y=0;

    for(int i=virgula[1]+1; i<virgula[2]; i++){
        str2[y++] = linha[i];
    }
    y=0;

    for(int i=virgula[2]+1; i<virgula[3]; i++){
        str3[y++] = linha[i];
    }
    y=0;

    for(int i=virgula[3]+1; i<virgula[4]; i++){
        str4[y++] = linha[i];
    }
    y=0;

    for(int i=virgula[4]+1; i<virgula[5]; i++){
        str5[y++] = linha[i];
    }
    y=0;

    for(int i=virgula[5]+1; i<virgula[6]; i++){
        str6[y++] = linha[i];
    }
    y=0;

    for(int i=virgula[6]+1; i<strlen(linha)-1; i++){
        str7[y++] = linha[i];
    }
    y=0;

    if(strcmp(str0, "") == 0 || str0[0] == '\0'){
            strcpy(str0, "nao informado");
        }
    if(strcmp(str1, "") == 0 || str1[0] == '\0'){
            strcpy(str1, "nao informado");
        }
    if(strcmp(str2, "") == 0 || str2[0] == '\0'){
            strcpy(str2, "nao informado");
        }
    if(strcmp(str3, "") == 0 || str3[0] == '\0'){
            strcpy(str3, "nao informado");
        }
    if(strcmp(str4, "") == 0 || str4[0] == '\0'){
            strcpy(str4, "nao informado");
        }
    if(strcmp(str5, "") == 0 || str5[0] == '\0'){
            strcpy(str5, "nao informado");
        }
    if(strcmp(str6, "") == 0 || str6[0] == '\0'){
            strcpy(str6, "nao informado");
        }
    if(strcmp(str7, "") == 0 || str7[0] == '\0'){
            strcpy(str7, "nao informado");
        }
    
    strcpy(jogador->id, str0);
    strcpy(jogador->nome, str1);
    strcpy(jogador->altura, str2);
    strcpy(jogador->peso, str3);
    strcpy(jogador->universidade, str4);
    strcpy(jogador->anoNascimento, str5);
    strcpy(jogador->cidadeNascimento, str6);
    strcpy(jogador->estadoNascimento, str7);
}

void swap(Jogador *jogador1, Jogador *jogador2){
    Jogador temp = *jogador1;
    *jogador1 = *jogador2;
    *jogador2 = temp;
}

int anoInt(Jogador *jogador){
    int ano = atoi(jogador->anoNascimento);
    return ano;
}

void bolha(Jogador *jogadores, int n){
    int i, j;
    for (int i = (n - 1); i > 0; i--) {
        for (int j = 0; j < i; j++) {
            if(anoInt(&jogadores[j]) > anoInt(&jogadores[j+1])){
                swap(&jogadores[j], &jogadores[j+1]);
                mov += 3;
            } else if(anoInt(&jogadores[j]) == anoInt(&jogadores[j+1])){
                if(strcmp(jogadores[j].nome, jogadores[j+1].nome) > 0){
                    swap(&jogadores[j], &jogadores[j+1]);
                    mov += 3;
                }
                comp += 2;
            }
            comp++;
        }
    }
}

int main(){
    clock_t t;
    t = clock();

    FILE *arq;
    arq = fopen("/tmp/players.csv", "r");
    char linha[1000];
    fgets(linha, 1000, arq);

    Jogador jogadores[3923];
    int i = 0;
    while(fgets(linha, 1000, arq)){
        ler(&jogadores[i], linha);
        i++;
    }
    fclose(arq);
    
    Jogador escolhidos[3923];
    int k = 0;
    char id[100];
    scanf("%s", id);
    while(strcmp(id, "FIM") != 0){
        for(int j=0; j<3923; j++){
        if(atoi(id) == atoi(jogadores[j].id)){
            escolhidos[k++] = clone(&jogadores[j]);
        }
        }
        scanf("%s", id);
    }

    bolha(escolhidos, k);
    for(int i=0; i<k; i++){
        imprimir(&escolhidos[i]);
    }
    
    t = clock() - t;

    FILE *log;
    log = fopen("matrícula_bolha.txt", "w");
    fprintf(log, "Matricula: 808664\t Comparações: %d\t Movimentações: %d\t Execucao: %lfms", comp, mov, ((double)t)/((CLOCKS_PER_SEC/1000)));
    fclose(log);
    

    return 0;
}