import java.util.*;

public class Espacos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int casos = scanner.nextInt(0);

        for(int i = 0; i < casos; i++){
           String texto = scanner.nextLine();
           String[] palavras = texto.split(" ");
           StringBuilder senha = new StringBuilder();

          for(int j = 0; j < palavras.length; j++){
            if(!palavras[j].isEmpty()){
             senha.append((palavras[j].charAt(0)));
            }
          } 
         System.out.println(senha);
        }
        scanner.close();
    }
}