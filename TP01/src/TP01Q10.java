public class TP01Q10 {

    public static boolean isPalindromo(String s) {
        // Caso base: se a string for vazia ou contiver apenas um caractere, é um palíndromo
        if (s.length() <= 1) {
            return true;
        }
        // Verifica se o primeiro e o último caractere são iguais
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            // Chama recursivamente a função com a substring que exclui o primeiro e o último caractere
            return isPalindromo(s.substring(1, s.length() - 1));
        } else {
            // Se os caracteres não forem iguais, não é um palíndromo
            return false;
        }
    }

    public static boolean isFim(String s) {
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