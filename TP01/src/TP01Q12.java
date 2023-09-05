import java.util.Scanner;

public class TP01Q12 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        while (!in.equals("FIM")) {
            String out = processInput(in, 0);
            System.out.println(out);
            in = sc.nextLine();
        }
        sc.close();
    }

    public static String processInput(String input, int index) {
        if (index >= input.length()) {
            return "";
        }

        int a = (int) input.charAt(index);

        if (a == 65533) {
            return (char) 65533 + processInput(input, index + 1);
        } else {
            a = a + 3;
            char b = (char) a;
            return b + processInput(input, index + 1);
        }
    }
}