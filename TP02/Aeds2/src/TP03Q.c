#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

typedef struct Jogador
{
    int id;
    char nome[31];
    int altura;
    int peso;
    char universidade[31];
    int anoNascimento;
    char cidadeNascimento[31];
    char estadoNascimento[31];
} Jogador;

void imprimir(Jogador jogador)
{
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
}

Jogador ler(char *linha, int tam)
{
    Jogador jogador;
    int index[7];
    int virg = 0;
    for (int i = 0; i < tam; i++)
    {
        if (linha[i] == ',')
        {
            index[virg] = i;
            virg++;
        }
    }
    int i = 0; // index da linha
    int j = 0; // index do vetor

    // armazenar o id
    char id[5];
    while (i < index[0])
    {
        id[j] = linha[i];
        j++;
        i++;
    }
    id[j] = '\0';
    int id_integer = atoi(id);
    jogador.id = id_integer;

    // armazenar nome
    i = index[0] + 1;
    j = 0;
    char nome[21];
    while (i < index[1])
    {
        nome[j] = linha[i];
        j++;
        i++;
    }
    nome[j] = '\0';
    strcpy(jogador.nome, nome);

    // armazenar a altura
    i = index[1] + 1;
    j = 0;
    char altura[4];
    while (i < index[2])
    {
        altura[j] = linha[i];
        j++;
        i++;
    }
    altura[j] = '\0';
    int altura_integer = atoi(altura);
    jogador.altura = altura_integer;

    // armazenar peso
    i = index[2] + 1;
    j = 0;
    char peso[4];
    while (i < index[3])
    {
        peso[j] = linha[i];
        j++;
        i++;
    }
    peso[j] = '\0';
    int peso_integer = atoi(peso);
    jogador.peso = peso_integer;

    // armazenar universidade
    i = index[3] + 1;
    j = 0;
    char universidade[31];

    if ((index[4] - 1) - index[3] + 1 != 1)
    {
        while (i < index[4])
        {
            universidade[j] = linha[i];
            j++;
            i++;
        }
        universidade[j] = '\0';
    }
    else
    {
        strcpy(universidade, "nao informado");
    }
    strcpy(jogador.universidade, universidade);

    // armazenar ano nascimento
    i = index[4] + 1;
    j = 0;
    char anoNascimento[5];
    while (i < index[5])
    {
        anoNascimento[j] = linha[i];
        j++;
        i++;
    }
    anoNascimento[j] = '\0';
    int anoNascimento_integer = atoi(anoNascimento);
    jogador.anoNascimento = anoNascimento_integer;

    i = index[5] + 1;
    j = 0;
    char cidadeNascimento[31];

    if (index[6] - index[5] + 1 != 2)
    {
        while (i < index[6])
        {
            cidadeNascimento[j] = linha[i];
            j++;
            i++;
        }
        cidadeNascimento[j] = '\0';
    }
    else
    {
        strcpy(cidadeNascimento, "nao informado");
    }
    strcpy(jogador.cidadeNascimento, cidadeNascimento);

    i = index[6] + 1;
    j = 0;
    char estadoNascimento[31];
    if ((tam - 3) - index[6] + 1 != 0)
    {
        while (i < tam - 1)
        {
            estadoNascimento[j] = linha[i];
            j++;
            i++;
        }
        estadoNascimento[j] = '\0';
    }
    else
    {
        strcpy(estadoNascimento, "nao informado");
    }
    strcpy(jogador.estadoNascimento, estadoNascimento);

    return jogador;
}
//---------------------------- FILA CIRCULAR----------------------------------------------------
#define FILA_TAMANHO 6

typedef struct FilaCircular {
    Jogador*arr;
    int primerio, ultimo;
    int n;
}FilaCircular;

//----------------------------FUNÇÕES FILA CIRCULAR ----------------------------------------------------
FilaCircular* criarFilaCircular(){
    FilaCircular* novaFilaCircular = malloc(sizeof(FilaCircular));
    novaFilaCircular->arr = malloc((FILA_TAMANHO)*sizeof(Jogador));
    novaFilaCircular->primerio = 0;
    novaFilaCircular->ultimo = 0;
    novaFilaCircular->n = 0;
    return novaFilaCircular;
}

void mostrarFila(FilaCircular* fila) {
    int tam = fila->n;
    for(int i = 0; i < tam; i++) {
        imprimir(fila->arr[i]);
    }
}

Jogador remover(FilaCircular* fila) {
    Jogador resp;
    if(fila->primerio == fila->ultimo) {
        printf("FILA VAZIA JA!\n");
        return resp;
    }
    resp = fila->arr[fila->primerio];
    fila->primerio = (fila->primerio + 1) % FILA_TAMANHO;
    fila->n--;
    return resp;
}

void inserir(FilaCircular* fila, Jogador x) {
    if( ((fila->ultimo + 1) % FILA_TAMANHO) == fila->primerio)
    {
        fila->primerio = (fila->primerio + 1) % FILA_TAMANHO;
        fila->arr[fila->ultimo] = x;
        fila->ultimo = (fila->ultimo + 1) % FILA_TAMANHO;
    }else{
        fila->arr[fila->ultimo] = x;
        fila->ultimo = (fila->ultimo + 1) % FILA_TAMANHO;
        fila->n++;
    }
}
// ------------------- MEDIA ALTURAS -------------------------------------
int mediaAltura(FilaCircular* fila){

    int alturas = 0;
    int numJogadores = fila->n;

    int i = fila->primerio;

    while(i != fila->ultimo)
    {
        alturas += fila->arr[i].altura;
        i = (i+1) % FILA_TAMANHO;
    }

    float tmp = ((float)alturas/(float)numJogadores);
    float parte_decimal = fmod(tmp, 1.0);

    if(parte_decimal > 0.5)
    {
        return (alturas/numJogadores)+1;
    }
    return alturas/numJogadores;
}
void alterarIdParaPosicao(FilaCircular*fila){
    int tam = fila->n;
    for(int i = 0; i <= tam-1; i++){
        fila->arr[i].id = i;
    }
}
//----------------------------  MAIN    --------------------------------------------------------------------------
Jogador procurarJogadorPorId(Jogador*arr, int tam, int id) {
    for(int i = 0; i < tam; i++) {
        if(arr[i].id == id) {
            return arr[i];
        }
    }
    printf("Jogador nao encontrado!\n");
    Jogador error;
    error.id = -1;
    return error;
}
void imprimirArrayDeJogadores(Jogador* arr, int tam){
    for(int i = 0; i < tam; i++){
        imprimir(arr[i]);
    }
}
char* realocar(char*str, int newTam){
    str = realloc(str, (newTam + 1)*sizeof(char));
    if (str == NULL) {
        fprintf(stderr, "Erro na alocação de memória.\n");
        exit(1);
    }
    return str;
}
// ----------------------------- FORMATAÇÃO ----------------------------------------------------
int extrairN2(char*linha, int tam){
    char pos[31];
    int index = 0;
    for(int i = 2; i <= tam-1; i++){
        pos[index++] = linha[i];
    }
    return atoi(pos);
}

int main() {
// .......................................PARTE 1.....................................
    FILE * arq = fopen("/tmp/players.csv", "r");
    //FILE * arq = fopen("D:\\TP03\\anunciado\\players.csv", "r");
    //FILE * arq = fopen("J:\\TP03\\anunciado\\players.csv", "r");
    if(arq == NULL){
        printf("Falha ao abrir arquivo!\n");
        return 1;
    }
    Jogador* totalJogadores = malloc(3922*sizeof(Jogador));
    int index = 0;
    
    char linha[1001];
    fgets(linha, sizeof(linha), arq);

    while(fgets(linha, sizeof(linha), arq) != NULL) 
    {   
        totalJogadores[index++] = ler(linha, strlen(linha));
    }
    totalJogadores = realloc(totalJogadores, index*sizeof(Jogador));

// .....................................PARTE 2......................................

    FilaCircular* filaCircular = criarFilaCircular();

    char* entrada = malloc(55*sizeof(char));
    while(strcmp(entrada, "FIM") != 0){         
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0){
            int id = atoi(entrada);
            inserir(filaCircular, procurarJogadorPorId(totalJogadores, index, id));
            printf("%d\n", mediaAltura(filaCircular));
        }
    }
    free(entrada);

// .....................................PARTE 3......................................
    int n;
    scanf("%d", &n);

    for(int i = 0; i < n; i++) 
    {
        char* entrada = malloc(55*sizeof(char));
        scanf(" %[^\n]", entrada);
        int entradaTam = strlen(entrada);
        entrada = realocar(entrada, entradaTam);
    
        if(entrada[0] == 'R')
        {
           printf("(R) %s\n", remover(filaCircular).nome);
        }else if(entrada[0] == 'I')
        {
            int id = extrairN2(entrada, strlen(entrada));
            inserir(filaCircular, procurarJogadorPorId(totalJogadores, index, id));
            printf("%d\n", mediaAltura(filaCircular));
        }
        free(entrada);
    }

    
    mostrarFila(filaCircular);

  // desalocamento de memória
    free(totalJogadores);
    fclose(arq);
    return 0;
}