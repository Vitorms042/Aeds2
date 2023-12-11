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

int getMax(int *lista, int n){
    int maior = lista[0];

    for (int i = 1; i < n; i++){
        if (maior < lista[i]){
            maior = lista[i];
        }
    }
    return maior;
}

void radixSort(Jogador *lista, int n){
    int maxId = 0;

    for (int i = 0; i < n; i++){
        int id = atoi(lista[i].id);
        if (id > maxId){
            maxId = id;
        }
    }

    for (int exp = 1; maxId / exp > 0; exp *= 10){
        radixCountingSort(lista, n, exp);
    }
}

void radixCountingSort(Jogador *lista, int n, int exp){
    Jogador saida[n];
    int contador[10] = {0};

    for (int i = 0; i < n; i++){
        int id = atoi(lista[i].id);
        contador[(id / exp) % 10]++;
    }

    for (int i = 1; i < 10; i++){
        contador[i] += contador[i - 1];
    }

    for (int i = n - 1; i >= 0; i--){
        int id = atoi(lista[i].id);
        int posicao = (id / exp) % 10;
        saida[contador[posicao] - 1] = lista[i];
        contador[posicao]--;
    }

    for (int i = 0; i < n-1; i++){
        lista[i] = saida[i];
    }
}

void ler(Jogador *jogador, char linha[1000]){
    int pos[8];
    int virgulas = 0;
    for (int i = 0; i < strlen(linha); i++){
        if (linha[i] == ','){
            pos[virgulas] = i;
            virgulas++;
        }
    }

    int cont = 0;
    char id[100];
    char nome[100];
    char peso[100];
    char altura[100];
    char universidade[100];
    char anoNascimento[100];
    char cidadeNascimento[100];
    char estadoNascimento[100];

    if (pos[0] - 0 != 0){
        for (int i = 0; i < pos[0]; i++){
            id[cont++] = linha[i];
        }
        id[cont] = '\0';
        strcpy(jogador->id, id);
    }else{
        strcpy(jogador->id, "nao informado");
    }

    cont = 0;

    if (pos[1] - (pos[0]) != 1){
        for (int j = pos[0] + 1; j < pos[1]; j++){
            nome[cont++] = linha[j];
        }
        nome[cont] = '\0';
        strcpy(jogador->nome, nome);
    }else{
        strcpy(jogador->nome, "nao informado");
    }
    cont = 0;

    if (pos[2] - (pos[1]) != 1){
        for (int k = pos[1] + 1; k < pos[2]; k++){
            altura[cont++] = linha[k];
        }
        altura[cont] = '\0';
        strcpy(jogador->altura, altura);
    }else{
        strcpy(jogador->altura, "nao informado");
    }

    cont = 0;

    if (pos[3] - (pos[2]) != 1){
        for (int l = pos[2] + 1; l < pos[3]; l++){
            peso[cont++] = linha[l];
        }
        peso[cont] = '\0';
        strcpy(jogador->peso, peso);
    }else{
        strcpy(jogador->peso, "nao informado");
    }

    cont = 0;
    if (pos[4] - (pos[3]) != 1){
        for (int m = pos[3] + 1; m < pos[4]; m++){
            universidade[cont++] = linha[m];
        }
        universidade[cont] = '\0';
        strcpy(jogador->universidade, universidade);
    }else{
        strcpy(jogador->universidade, "nao informado");
    }

    cont = 0;

    if (pos[5] - (pos[4]) != 1){
        for (int n = pos[4] + 1; n < pos[5]; n++){
            anoNascimento[cont++] = linha[n];
        }
        anoNascimento[cont] = '\0';
        strcpy(jogador->anoNascimento, anoNascimento);
    }else{
        strcpy(jogador->anoNascimento, "nao informado");
    }

    cont = 0;

    if (pos[6] - (pos[5]) != 1){
        for (int o = pos[5] + 1; o < pos[6]; o++){
            cidadeNascimento[cont++] = linha[o];
        }
        cidadeNascimento[cont] = '\0';

        strcpy(jogador->cidadeNascimento, cidadeNascimento);
    }else{
        strcpy(jogador->cidadeNascimento, "nao informado");
    }

    cont = 0;

    if ((strlen(linha) - 1) - (pos[6]) != 1){
        for (int p = pos[6] + 1; p < strlen(linha) - 1; p++){
            estadoNascimento[cont++] = linha[p];
        }
        estadoNascimento[cont] = '\0';
        strcpy(jogador->estadoNascimento, estadoNascimento);
    }else{
        strcpy(jogador->estadoNascimento, "nao informado");
    }
    cont = 0;
}

int main (){
    clock_t inicio, fim;
    char dados[1000];
    FILE* arquivo = fopen("/tmp/players.csv","r");
    Jogador jogador[3922];
    char id[100];
    char nome[100];
    Jogador busca[1000];
    int contador = 0;
    
    fgets (dados, sizeof(dados), arquivo); 
    int i = 0; 
    while (fgets (dados, 1000, arquivo) && i < 3922){
        ler(&jogador[i], dados); 
        i++;
    }
    
    scanf("%s", id);
    while (strcmp(id, "FIM") != 0){
        for (int j = 0; j < 3923; j++){
            if(strcmp(jogador[j].id,id) == 0) {
                busca[contador++] = clone(&jogador[j]);
            }
        }
        scanf("%s", id);
    }
 
    inicio = clock();

    radixSort(busca, contador - 1);

    for(int i = 0; i < contador; i++) {
      imprimir(&busca[i]); 
   }

    fim = clock();

    FILE *arquivoLog;
    char nomeArquivoLog[] = "matricula_radixSort.txt";

    arquivoLog = fopen(nomeArquivoLog, "w");

    if (arquivo == NULL) {
        perror("Erro ao abrir o arquivo");
        return 1;
    }

    int matricula = 808664;
    double tempoExecucao = (double)(fim - inicio) / CLOCKS_PER_SEC; 

    fprintf(arquivo, "Matricula: %d\tTempo: %.2f\tComparacoes: %d\tMovimentacoes: %d\n",
            matricula, tempoExecucao, comparacoes, movimentacoes);

    fclose(arquivoLog);

    fclose(arquivo);
    return 0;
}