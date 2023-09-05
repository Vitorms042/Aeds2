#include <stdio.h>
#include <stdlib.h>

int main() {
    double input;
    FILE *file = fopen("entrada.txt", "wb");
    int size;

    if (file == NULL) {
        return 1;
    }

    scanf("%d", &size);

    // Leitura de valores Double e gravação no arquivo binário
    for (int i = size; i > 0; i--) {
        scanf("%lf", &input);
        fwrite(&input, sizeof(double), 1, file);
    }

    fclose(file); // Fecha o arquivo após a gravação

    file = fopen("entrada.txt", "rb"); // Abre o arquivo binário para leitura

    if (file == NULL) {
        return 1;
    }

    // Seleciona a posição de bytes para acessar de trás para frente todos os valores de Double lidos anteriormente
    for (int i = size - 1; i >= 0; i--) {
        fseek(file, i * sizeof(double), SEEK_SET);
        double resp;
        fread(&resp, sizeof(double), 1, file);
        int x = (int)resp;
        if (x == resp) {
            printf("%d\n", x);
        } else {
            printf("%.3lf\n", resp);
        }
    }

    fclose(file); // Fecha o arquivo após a leitura

    return 0;
}
