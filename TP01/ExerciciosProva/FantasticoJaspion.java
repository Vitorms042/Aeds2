import java.util.*;

public class FantasticoJaspion{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for(int i = 0; i < T; i++){
            int M = scanner.nextInt();
            int N = scanner.nextInt();

            String[] japones = new String[M]; 
            String[] portugues = new String[M];
            
            for(int j = 0; j < M; j++){
                japones[j] = scanner.next();
                portugues[j] = scanner.nextLine().trim();
            }

            for(int k = 0; k < N; k++){
                String linha = scanner.nextLine();
                String[] palavras = linha.split(" ");

                for(int l = 0; l < palavras.length ; l++){
                    boolean encontrada = false;

                    for(int p = 0; p < M; p++){
                        if(palavras[l].equals(japones[p])){
                            System.out.print(portugues[p] + " ");               
                            encontrada = true;
                            break;
                        }
                    }

                    if(!encontrada){
                        System.out.print(palavras[l] + "");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        scanner.close();
    }
}