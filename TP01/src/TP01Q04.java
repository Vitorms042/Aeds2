import java.util.Random;
import java.util.Scanner;
public class TP01Q04 {

    public static String alteracaoAleatoria(String s) {
        Random gerador = new Random();
        gerador.setSeed(4);
        //Gera duas letras aleatorias
        char letra1 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
        char letra2 = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
        String resp = "";

        for (int i = 0; i < s.length(); i++) {
            //Compara o caracter da string com a primeira letra gerada
            if(s.charAt(i) == 65533){
                resp += (char)65533;
            }
            else if (s.charAt(i) == letra1) {
                resp += letra2;
            } else {
                resp += s.charAt(i);
            }
        }
        return resp;
    }

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String string = "";
        string = sc.nextLine();
        while (!isFim(string)) {
            System.out.println(alteracaoAleatoria(string));
            string = sc.nextLine();
        }
        sc.close();
    }
}