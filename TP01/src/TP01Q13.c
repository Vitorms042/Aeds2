#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

char *alteracaoAleatoria(char *s) {
    srand(4); // Semente fixa, produzirá a mesma sequência a cada vez
    char letra1 = (char) ('a' + (rand() % 26));
    char letra2 = (char) ('a' + (rand() % 26));
    char *resp = malloc(strlen(s) + 1);

    for (int i = 0; i < strlen(s); i++) {
        if (s[i] == letra1) {
            resp[i] = letra2;
        } else {
            resp[i] = s[i];
        }
    }
    resp[strlen(s)] = '\0'; // Adicionar o caractere nulo no final
    return resp;
}

int isFim(char *s) {
    return (strlen(s) >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
}

int main() {
    char string[3000];
    scanf(" %[^\n]", string);
    while (!isFim(string)) {
        char *resultado = alteracaoAleatoria(string);
        printf("%s\n", resultado);
        free(resultado); // Liberar a memória alocada
        scanf(" %[^\n]", string);
    }
    return 0;
}