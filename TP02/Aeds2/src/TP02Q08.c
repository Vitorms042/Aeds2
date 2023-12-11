#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

int comparacoes = 0;
int movimentacoes = 0;

typedef struct Jogador{
    char id[100];
    char nome[100];
    char peso[100];
    char altura[100];
    char universidade[100];
    char anoNascimento[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];
} Jogador;

Jogador clone(Jogador *jogador){
    Jogador novo;
    strcpy(novo.id, jogador->id);
    strcpy(novo.nome, jogador->nome);
    strcpy(novo.altura, jogador->altura);
    strcpy(novo.peso, jogador->peso);
    strcpy(novo.anoNascimento, jogador->anoNascimento);
    strcpy(novo.cidadeNascimento, jogador->cidadeNascimento);
    strcpy(novo.estadoNascimento, jogador->estadoNascimento);
    strcpy(novo.universidade, jogador->universidade);
    return novo;
}

void imprimir(Jogador *jogador){
    printf("[%s ## %s ## %s ## %s ## %s ## %s ## %s ## %s]\n", jogador->id, jogador->nome, jogador->altura, jogador->peso, jogador->anoNascimento, jogador->universidade, jogador->cidadeNascimento, jogador->estadoNascimento);
}

int comparar(const void *a, const void *b){
    Jogador *jogadorA = (Jogador *)a;
    Jogador *jogadorB = (Jogador *)b;

    int resultadoPeso = atoi(jogadorA->peso) - atoi(jogadorB->peso);

    if (resultadoPeso == 0){
        return strcmp(jogadorA->nome, jogadorB->nome);
    }else{
        return resultadoPeso;
    }
}

void shellsort(Jogador *jogador, int n){
    int h = 1;
    while (h < n / 3){
        h = 3 * h + 1;
    }
    while (h >= 1){
        for (int i = h; i < n; i++){
            for (int j = i; j >= h; j -= h){
                comparacoes++;
                if (comparar(&jogador[j], &jogador[j - h]) < 0){
                    Jogador temp = jogador[j];
                    jogador[j] = jogador[j - h];
                    jogador[j - h] = temp;
                    movimentacoes+=3;
                }
            }
        }
        h /= 3;
    }
}

void ler (Jogador *jogador, char linha[1000]){

    int posicao[7];
    int virgulas = 0;
    for (int i = 0; i < strlen(linha); i++){
        if(linha[i] == ','){
            posicao[virgulas] = i;
            virgulas++;
        }
    }
    
    int count = 0;
    char id[100];
    char nome[100];
    char peso[100];
    char altura[100];
    char universidade[100];
    char anoNascimento[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];

    if (posicao[0] - 0 != 0){
        for(int i = 0; i < posicao[0]; i++){
          id[count++] = linha[i];
        }
        id[count] = '\0';
        strcpy(jogador->id,id);
    } else{
        strcpy(jogador->id,"nao informado");  
    }
   
    count = 0;

    if (posicao[1] - (posicao[0]) != 1){
        for(int j = posicao[0] + 1; j < posicao[1]; j++){
        nome[count++] = linha[j];
    }
    nome[count] = '\0';
    strcpy(jogador->nome,nome);
    } else{
    strcpy(jogador->nome,"nao informado");   
    }
    count = 0;

    if (posicao[2] - (posicao[1]) != 1){
        for (int k = posicao[1] + 1; k < posicao[2]; k++){
            altura[count++] = linha[k];
        }
        altura[count] = '\0';
        strcpy(jogador->altura,altura);
    } else{
    strcpy(jogador->altura,"nao informado");
    }
    count = 0;

    if (posicao[3] - (posicao[2]) != 1){
        for (int l = posicao[2] + 1; l < posicao[3]; l++){
            peso[count++] = linha[l];
        }
        peso[count] = '\0';
        strcpy(jogador->peso,peso);
    } else{
    strcpy(jogador->peso,"nao informado");
    }
    
    count = 0;
    if (posicao[4] - (posicao[3]) != 1){
        for (int m = posicao[3] + 1; m < posicao[4]; m++){
            universidade[count++] = linha[m];
        }
        universidade[count] = '\0';
        strcpy(jogador->universidade,universidade);
    } else{
    strcpy(jogador->universidade,"nao informado");
    }
    
    count = 0;

    if (posicao[5] - (posicao[4]) != 1){
        for (int n = posicao[4] + 1; n < posicao[5]; n++){
            anoNascimento[count++] = linha[n];
        }
        anoNascimento[count] = '\0';
        strcpy(jogador->anoNascimento,anoNascimento);
    } else{
    strcpy(jogador->anoNascimento,"nao informado");
    }
    
    count = 0;

    if (posicao[6] - (posicao[5]) != 1){
        for(int o = posicao[5] + 1; o < posicao[6]; o++){
            cidadeNascimento[count++] = linha[o];
        }
        cidadeNascimento[count] = '\0';
       
        strcpy(jogador->cidadeNascimento,cidadeNascimento);
    } else{
    strcpy(jogador->cidadeNascimento,"nao informado");
    }
    
    count = 0;

     if ((strlen(linha) - 1) - (posicao[6]) != 1){
        for(int p = posicao[6] + 1; p < strlen(linha) - 1; p++){
            estadoNascimento[count++] = linha[p];
        }
        estadoNascimento[count] = '\0';
        strcpy(jogador->estadoNascimento,estadoNascimento);
     } else{
    strcpy(jogador->estadoNascimento,"nao informado");
    }
    count = 0;
}

int main (){
    clock_t inicio, fim;
    inicio = clock();
    char dados[1000];
    FILE* arquivo = fopen("/tmp/players.csv","r");
    Jogador jogador[3922];
    char id[100];
    char nome[100];
    Jogador busca[1000];
    int contador = 0;
    
    fgets (dados, sizeof(dados), arquivo); 
    int i = 0; 
    while (fgets (dados, 1000, arquivo)){
        ler(&jogador[i], dados); 
        i++;
    }
    
    scanf("%s", id);
    while (strcmp(id, "FIM") != 0){
        busca[contador++] = clone(&jogador[atoi(id)]);
        scanf("%s", id);
    }
 
    shellsort(busca, contador);

    for(int i = 0; i < contador; i++) {
      imprimir(&busca[i]); 
   }

    fim = clock();

    FILE *arquivoLog;
    char nomeArquivoLog[] = "matricula_shellsort.txt";
    arquivoLog = fopen(nomeArquivoLog, "w");

    int matricula = 808664;
    double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC; 

    fprintf(arquivo, "Matricula: %d\tTempo: %.2f\tComparacoes: %d\tMovimentacoes: %d\n",matricula, tempoExecucao, comparacoes, movimentacoes);

    fclose(arquivoLog);
    fclose(arquivo);
    return 0;
}