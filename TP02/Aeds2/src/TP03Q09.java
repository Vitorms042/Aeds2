
import java.util.Scanner;
public class TP03Q09{
 public static void main(String[] args) {
    Scanner teclado = new Scanner(System.in);
    int n = teclado.nextInt();
    teclado.nextLine();

    while(n!=0){
        int l1 = teclado.nextInt();
        int c1 = teclado.nextInt();
        teclado.nextLine();

        Matriz matriz1 = new Matriz(l1, c1);
        matriz1.lerMatriz(teclado);

        int l2 = teclado.nextInt();
        int c2 = teclado.nextInt();
        teclado.nextLine();

        Matriz matriz2 = new Matriz(l2, c2);
        matriz2.lerMatriz(teclado);

        // Imprimir diagonais da primeira matriz
        matriz1.imprimirDiagonais();

        // Imprimir matriz de soma
        Matriz matrizSoma = matriz1.soma(matriz2);
        if (matrizSoma != null) {
            matrizSoma.imprimirMatriz();
        } 

        // Imprimir matriz de multiplicação
        Matriz matrizMultiplicacao = matriz1.multiplicacao(matriz2);
        if (matrizMultiplicacao != null) {
            matrizMultiplicacao.imprimirMatriz();
        } 
    n--;
        
    }
    teclado.close();
}
}





class NodeMatriz {
    NodeMatriz dir;
    NodeMatriz esq;
    NodeMatriz cima;
    NodeMatriz baixo;
    int valor; //Ponteiros da matriz 

    NodeMatriz(){
        this(0);
    }

    NodeMatriz(int valor) {
        this.valor = valor;
        cima = dir = baixo = esq = null;
    }
}

 class Matriz {
    private NodeMatriz head;
    private int linha, coluna;

    public Matriz(){
        this(3,3);
    }

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;

        head = new NodeMatriz(0); // Criação do primeiro elemento da matriz, canto superior esquerdo
        NodeMatriz i = head; // Anda pelas linhas
        NodeMatriz j = head; // Anda pelas colunas
        NodeMatriz iAnt = head; //Anda na linha de cima
        int jcont = 0; //Contador

        for (int k = 1; k <= this.linha; k++) {
            for (int l = 1; l < this.coluna; l++,  iAnt = i.cima) {
                i.dir = new NodeMatriz();
                i.dir.esq = i;
                if(jcont != 0){
                    i.dir.cima = iAnt.dir;
                    iAnt.dir.baixo = i.dir;
                }
                i = i.dir;
            } //São criados 3 ponteiros: i, j e Iant. I anda pelas linhas, k pelas colunas, e Iant liga os elementos entre as linhas e colunas.
            jcont++;
            if(jcont == this.linha){
                i = j = iAnt = null;
                break;
            }
            j.baixo = new NodeMatriz();
            j.baixo.cima = j;
            j = j.baixo;
            i = j;
            iAnt = i.cima;
        }
    }


    public void lerMatriz(Scanner teclado) {
        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < this.coluna; j++) {
                int valor = teclado.nextInt();
                this.atualizarElemento(i, j, valor);
            }
            teclado.nextLine(); // Avança para a próxima linha
        }
    }


public void imprimirDiagonais() {
     
        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, i) + " ");
        } 
        System.out.println();

   
        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, this.coluna - 1 - i) + " ");
        } //Imprime as diagonais.
        System.out.println();
    }

    public void imprimirMatriz() {
      
        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < this.coluna; j++) {
                System.out.print(this.obterElemento(i, j) + " ");
            }
            System.out.println(); // Avança para a próxima linha
        }
    } //Imprime a matriz completa

    public Matriz soma(Matriz outraMatriz) {
        if (this.linha != outraMatriz.linha || this.coluna != outraMatriz.coluna) {
            return null;
        }

        Matriz resultado = new Matriz(this.linha, this.coluna);

        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < this.coluna; j++) {
                int valorSoma = this.obterElemento(i, j) + outraMatriz.obterElemento(i, j);
                resultado.atualizarElemento(i, j, valorSoma);
            }
        } //Imprime a soma das matrizes

        return resultado;
    }

     public Matriz multiplicacao(Matriz outraMatriz) {
        if (this.coluna != outraMatriz.linha) {
           
            return null;
        }

        Matriz resultado = new Matriz(this.linha, outraMatriz.coluna);

        for (int i = 0; i < this.linha; i++) {
            for (int j = 0; j < outraMatriz.coluna; j++) {
                int valorMultiplicacao = 0;
                for (int k = 0; k < this.coluna; k++) {
                    valorMultiplicacao += this.obterElemento(i, k) * outraMatriz.obterElemento(k, j);
                }
                resultado.atualizarElemento(i, j, valorMultiplicacao);
            } //Multiplica as matrizs
        }

        return resultado;
    }

    public void mostrarDiagonalPrincipal() {
        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, i) + " ");
        } //Mostra a diagonal principal
        System.out.println();
    }

    public void mostrarDiagonalSecundaria() {
        for (int i = 0; i < this.linha; i++) {
            System.out.print(this.obterElemento(i, this.coluna - 1 - i) + " ");
        } //Mostra a diagonal secundária
        System.out.println();
    }

public int obterElemento(int linha, int coluna) {
        if (linha < 0 || linha >= this.linha || coluna < 0 || coluna >= this.coluna) {
           
            return 0;
        }

        NodeMatriz node = getNode(linha, coluna);
        return (node != null) ? node.valor : 0;
    }//Retorna o valor de uma determinada coordenada

    public void atualizarElemento(int linha, int coluna, int valor) {
        if (linha < 0 || linha >= this.linha || coluna < 0 || coluna >= this.coluna) {
          
            return;
        }

        NodeMatriz node = getNode(linha, coluna);
        if (node != null) {
            node.valor = valor;
        }
    }


    private NodeMatriz getNode(int linha, int coluna) {
        if (linha < 0 || linha >= this.linha || coluna < 0 || coluna >= this.coluna) {
            
            return null;
        }
    //Retorna um node específico
        NodeMatriz node = head;
        for (int i = 0; i < linha; i++) {
            node = node.baixo;
        }
        for (int j = 0; j < coluna; j++) {
            node = node.dir;
        }
        return node;
    }
}
