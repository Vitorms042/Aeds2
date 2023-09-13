import java.util.*;

public class Fracionario {
     public static void main(String[] args) {
          
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
          String num = scanner.nextLine();
          String cutoff = scanner.nextLine();

          double d = Double.parseDouble(num);
          int inteira = (int) d;

          double parteDouble = (d - inteira);
          
          double corte = Double.parseDouble(cutoff);

          if(parteDouble >= corte){ 
             int valor = (int) d;
             System.out.println(valor + 1);
        }else{
             int valor = (int) d;
             System.out.println(valor);
        }
     }
       scanner.close();
  }
}