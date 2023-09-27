import java.util.*;

public class App{

    public static String cartinha(String nome1, String nome2){
        int tam = nome1.length() + nome2.length() - 1;
        String nome3 = "";
        int j = 0, z = 0, temp = 0;

        for(int i = 0; i < tam; i +=2, temp += 1){
            if(temp % 2 == 0){
               char a = nome1.charAt(j);
               nome3 += a;
               char b = nome1.charAt(j+1);
               nome3 += b;
               j +=2;
            }else{
                char c = nome2.charAt(z);
                nome3 += c;
                char d = nome2.charAt(z + 1);
                nome3 += d;
                z += 2;
            }
        }

        return nome3.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int casos = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0; i < casos; i++){
            String nome1 = scanner.nextLine();
            String nome2 = scanner.nextLine();
            System.out.println(cartinha(nome1, nome2));
        }
       scanner.close();
    }
}