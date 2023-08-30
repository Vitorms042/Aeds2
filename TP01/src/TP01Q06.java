import java.util.Scanner;

public class TP01Q06 {

    public static boolean isVogal(String s){
         for(int i = 0; i < s.length(); i++){
           char letra = Character.toLowerCase(s.charAt(i));
            if(letra != 'a' || letra != 'e' || letra != 'i' || letra != 'o' || letra != 'u' || letra != '\u00E1' || letra != '\u00E0' || letra != '\u00E3' || letra != '\u00E2' || letra != '\u00E9' || letra != '\u00E8' || letra != '\u00EA' || letra != '\u00ED' || letra != '\u00F3' || letra != '\u00F2' || letra != '\u00F5' || letra != '\u00F4' || letra != '\u00FA' || letra != '\u00F9'){
                return false;
         }
       }
         return true;
    }

    public static boolean isConsoante(String s){
        for(int i = 0; i < s.length(); i++){
           char letra = Character.toLowerCase(s.charAt(i));
            if((Character.isLetter(letra) == false) || (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u')){
                return false;
        }
     }
         return true;
    }

    public static boolean isInteiro(String s){
       for(int i = 0; i < s.length(); i++){
        if(((Character.isDigit(s.charAt(i)) == false))){
            return false;
        }
       }
       return true;
    }

    public static boolean isReal(String s){
        int cont = 0;
       for(int i = 0; i < s.length(); i++){
           if(s.charAt(i) == '.' || s.charAt(i) == ','){
             cont++;
           }              
           else if((Character.isDigit(s.charAt(i)) == false)){
            return false;
          }     
     }
        if(cont != 1){
            return false;
        }
    return true;
}

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static void main(String[] args) {
        Scanner myIO = new Scanner(System.in);
        String string = "";
        string = myIO.nextLine();
        while(!isFim(string)){
            String vogal = isVogal(string) ? "SIM" : "NAO";
            String consoante = isConsoante(string) ? "SIM" : "NAO";
            String inteiro = isInteiro(string) ? "SIM" : "NAO";
            String real = isReal(string) ? "SIM" : "NAO";
            System.out.println(vogal +" "+ consoante +" "+ inteiro +" "+ real);
            string = myIO.nextLine();
        }
        myIO.close();
    }
}