import java.util.*;

public class Alfabeto {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int casos = scanner.nextInt();

        for(int i = 0; i < casos; i ++){
            String linha = scanner.nextLine();
            boolean[] letras = new boolean[26];
            int cont = 0;

         for(int j = 0; j < linha.length(); j++){
             char c = linha.charAt(i);
             if(c >= 'a' && c < 'z'){
                int indice = c - 'a';
                if(!letras[indice]){
                    letras[indice] = true;
                    cont++;
                }
             }
          }
          if(cont == 26){
            System.out.println("frase completa");
        }else if(cont >= 13){
            System.out.println("frase quase completa");
        }else{
            System.out.println("frase mal elaborada");
        }   
        } 
        scanner.close();  
    }
}