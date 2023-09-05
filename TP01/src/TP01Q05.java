import java.util.Scanner;

public class TP01Q05 {
// FUNCIONA
    public static String space(String line){
        String str = new String();
        for(int i = 0; i<line.length();i++){
            if(line.charAt(i) != ' '){
                str += line.charAt(i);
            }
        }
        return str;
    }

// FUNCIONA
    public static String toReplace(String line){
        String str = new String();
        if((line.charAt(0)-48) == 2){
            int a = (line.charAt(1)-48);    // -48 -> para pegar o valor 0 ou 1
            int b = (line.charAt(2)-48);
            for(int i = 3; i<line.length();i++){
                if(line.charAt(i) == 'A'){
                    str += a;
                } else if(line.charAt(i) == 'B'){
                    str += b;
                }else{
                    str += line.charAt(i);
                }
            }
        } else {
            int a = (line.charAt(1)-48);    // -48 -> para pegar o valor 0 ou 1
            int b = (line.charAt(2)-48);
            int c = (line.charAt(3)-48);
            for(int i = 4; i<line.length();i++){
                if(line.charAt(i) == 'A'){
                    str += a;
                } else if(line.charAt(i) == 'B'){
                    str += b;
                }else if(line.charAt(i) == 'C'){
                    str += c;
                }else{
                    str += line.charAt(i);
                }
                }
            }
            return str;
        }
        
        public static boolean result(String line, int position, int position2){
            if(line.charAt(position-1) == 'd'){
                for(int i = position+1; i<position2;i++){
                    if(line.charAt(i) == '0'){
                        return false;
                    }
                }
                return true;
            }
            if(line.charAt(position-1) == 'r'){
                for(int i = position+1; i<position2;i++){
                    if(line.charAt(i) == '1'){
                        return true;
                    }
                }
                return false;
            }
            if(line.charAt(position-1) == 't'){
                if(line.charAt(position+1) == '0'){
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        // FUNCIONA
        public static String decrease(String line, int position, int position2, boolean result){
            String str = new String();
            if(line.charAt(position-1) == 'd' || line.charAt(position-1) == 't'){
                for(int i = 0; i<line.length();i++){
                    if(i < position-3 || i >position2){
                        str += line.charAt(i);
                    } else if(result == true){
                        str += '1';
                        i = position2;
                    } else {
                        str += '0';
                        i = position2;
                    }
                }
            }else{
                for(int i = 0; i<line.length();i++){
                    if(i < position-2 || i > position2){
                        str += line.charAt(i);
                    } else if(result == true){
                        str += '1';
                        i = position2;
                    } else {
                        str += '0';
                        i = position2;
                    }
                }
            }
            return str;
        }
        
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String line, str;   // uma p ler entrada e outra p manipular
        int position = 0, position2 = 0;
        boolean result;
        str = sc.nextLine();
        while(str.charAt(0) != '0'){
            line=str;
            line = space(line);
            line = toReplace(line);
            // pegar ultimo '('
            while(line.length() != 1){

                for(int i = 0; i<line.length();i++){
                    if(line.charAt(i) == '('){
                        position = i;
                    }
                }
                // pegar primeiro ')'
                for(int i = 0; i<line.length();i++){
                    if(line.charAt(i) == ')' && i > position){
                        position2 = i;
                        i = line.length();
                    }
                }
                result = result(line,position,position2);
                line = decrease(line, position, position2,result);   
            }
            System.out.println(line);
            str = sc.nextLine();
        }
        sc.close();
    }
}