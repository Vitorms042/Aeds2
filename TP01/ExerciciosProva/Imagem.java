import java.util.*;

public class Imagem{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        while(true){
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        if(N == 0 && M == 0){
            break;
        }

        char[][] Desenho = new char[N][M];
        for(int i = 0; i < N; i++){
            String linha = scanner.next();
            Desenho[i] = linha.toCharArray();
        }

        int A = scanner.nextInt();
        int B = scanner.nextInt();

        for(int i = 0; i < A; i ++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < B; k++){
                    System.out.print(Desenho[j][k % M]);
                }
                System.out.println();
            }
        }
         System.out.println();
        }
       scanner.close();
    }
}