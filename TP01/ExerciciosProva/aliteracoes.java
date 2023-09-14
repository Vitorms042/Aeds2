import java.util.*;

public class aliteracoes{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            String[] palavras = linha.split(" ");
            int cont = 0;

            if(palavras.length > 1){
                char primeiraLetra = Character.toLowerCase(palavras[0].charAt(0));
            
            for(int i = 1; i <  palavras.length; i++){
               char PrimeiraLetraAtual = Character.toLowerCase(palavras[i].charAt(0));
            if(primeiraLetra == PrimeiraLetraAtual){
                cont++;
            }
            }
        }
        System.out.println(cont);
    }
    scanner.close();
 } 
}