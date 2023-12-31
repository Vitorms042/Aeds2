public class TP01Q01 {
    public static boolean isPalindromo(String s){  
          int j = s.length() - 1;
          for(int i = 0; i <= (s.length() - 1) / 2; i++, j--){
             if(s.charAt(i) != s.charAt(j)){
                 return false;
             }
          }
        return true;
    }
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static void main(String[] args) throws Exception {
        String texto = MyIO.readLine();
        while (!isFim(texto)) {
            if (isPalindromo(texto)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            texto = MyIO.readLine();
        }
    }
}