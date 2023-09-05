import java.util.Scanner;

public class TP01Q15 {
    //Verifica todos os caracteres até encontrar somente vogal ou não
    public static boolean isVogal(String s) {
        if (s.isEmpty()) {
            return true;
        }
        
        char letra = Character.toLowerCase(s.charAt(0));
        
        if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u' || 
            letra == '\u00E1' || letra == '\u00E0' || letra == '\u00E3' || letra == '\u00E2' || 
            letra == '\u00E9' || letra == '\u00E8' || letra == '\u00EA' || letra == '\u00ED' || 
            letra == '\u00F3' || letra == '\u00F2' || letra == '\u00F5' || letra == '\u00F4' || 
            letra == '\u00FA' || letra == '\u00F9') {
            return isVogal(s.substring(1));
        } else {
            return false;
        }
    }
    //Verifica os caracteres ate encontrar somente consoante ou não
    public static boolean isConsoante(String s) {
        if (s.isEmpty()) {
            return true;
        }
        
        char letra = Character.toLowerCase(s.charAt(0));
        
        if (Character.isLetter(letra) && letra != 'a' && letra != 'e' && letra != 'i' && letra != 'o' && letra != 'u') {
            return isConsoante(s.substring(1));
        } else {
            return false;
        }
    }
    //Verifica se os caracteres são inteiros ou não
    public static boolean isInteiro(String s) {
        if (s.isEmpty()) {
            return true;
        }
        
        char primeiro = s.charAt(0);
        
        if (Character.isDigit(primeiro)) {
            return isInteiro(s.substring(1));
        } else {
            return false;
        }
    }
    //Verifica se os caracteres são reais ou não
    public static boolean isReal(String s) {
        if (s.isEmpty()) {
            return false;
        }
        
        char primeiro = s.charAt(0);
        
        if (Character.isDigit(primeiro)) {
            return isReal(s.substring(1));
        } else if (primeiro == '.' || primeiro == ',') {
            // Verifica se há apenas um ponto ou vírgula
            int cont = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.' || s.charAt(i) == ',') {
                    cont++;
                }
            }
            return cont == 1 && isReal(s.substring(1));
        } else {
            return false;
        }
    }

    public static boolean isFim(String s) {
        return s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M';
    }

    public static void main(String[] args) {
        Scanner myIO = new Scanner(System.in);
        String string = "";
        string = myIO.nextLine();
        while (!isFim(string)) {
            String vogal = isVogal(string) ? "SIM" : "NAO";
            String consoante = isConsoante(string) ? "SIM" : "NAO";
            String inteiro = isInteiro(string) ? "SIM" : "NAO";
            String real = isReal(string) ? "SIM" : "NAO";
            System.out.println(vogal + " " + consoante + " " + inteiro + " " + real);
            string = myIO.nextLine();
        }
        myIO.close();
    }
}
