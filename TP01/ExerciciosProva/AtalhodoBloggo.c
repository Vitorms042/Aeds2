#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char letra, palavra[60];
    int tam, italico = 0, bold = 0, i;
    while (fgets(palavra, 50, stdin)){
        tam = strlen(palavra);
        for (int i = 0; i < tam; i++){
            letra = texto[i];
            if (letra == '_'){
                italico++;
                if (italico % 2)
                {
                    printf("<i>");
                }
                else
                {
                    printf("<\i>");
                }
            }else if (letra == '*')
            {
                bold++;
                if (bold % 2){
                    printf("<b>");
                }
                else{
                    printf("<\b>");
                }
            }else
            {
                printf("%c", letra);
            }
        }
    }
}
/*
import java.util.Scanner;

public class FormataTexto {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder textoFormatado = new StringBuilder();
        int italico = 0, bold = 0;

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            int tam = linha.length();

            for (int i = 0; i < tam; i++) {
                char letra = linha.charAt(i);

                if (letra == '_') {
                    italico++;
                    if (italico % 2 == 1) {
                        textoFormatado.append("<i>");
                    } else {
                        textoFormatado.append("</i>");
                    }
                } else if (letra == '*') {
                    bold++;
                    if (bold % 2 == 1) {
                        textoFormatado.append("<b>");
                    } else {
                        textoFormatado.append("</b>");
                    }
                } else {
                    textoFormatado.append(letra);
                }
            }
            textoFormatado.append("\n");
        }
        System.out.print(textoFormatado.toString());
        scanner.close();
    }
}
*/