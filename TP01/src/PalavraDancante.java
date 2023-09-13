import java.util.*;

public class PalavraDancante {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNextLine()) {
          String linha = scanner.nextLine();
          StringBuilder string = new StringBuilder();
          boolean maiuscula = true;

          for(int i = 0; i < linha.length(); i++){
            char c = linha.charAt(i);
            if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
               if(maiuscula){
                if(c >= 'a' && c <= 'z'){
                    c = (char) (c - ('a' - 'A'));
                }
                maiuscula = false;
               }else{
                if(c >= 'A' && c <= 'Z'){
                   c = (char) (c + ('a' - 'A'));
                }
                maiuscula = true;
               }
            }
            string.append(c);
          }
         System.out.println(string.toString());
        }
        scanner.close();
    }
}