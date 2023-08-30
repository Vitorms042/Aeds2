import java.util.Scanner;

public class TP01Q03{
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String in = new String();
        String out = new String("");
        in = sc.nextLine();
        while(in.equals("FIM") == false){
            for(int i = 0; i < in.length();i++){
                //Passa a entrada char para um inteiro(ASCII)
                int a = (int) in.charAt(i);
                //Trata problema de um caracter especial
                if(a == 65533){
                    out = out + (char) 65533;
                }
                //Realiza a conversao do ciframento
                else{
                a = a+3;
                char b = (char)a;
                out = out + b;
                }
            }
        System.out.println(out);
        in = "";
        out = "";
        in = sc.nextLine();
        }
        sc.close();
    }
}