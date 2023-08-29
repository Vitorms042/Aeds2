public class TP01Q03 {
    //Metodo que acrescenta 3 posicoes para cada caracter da string
    public static String ciframentoCesar(String s){
         String bStrings = "";
         
         for(int i = 0; i < s.length(); i++){
           int a = (int) s.charAt(i);
           if(a == 65533){
            bStrings += (char)a; 
           }else{
             a += 3;
             bStrings += (char)a;
           }
         }
         return bStrings;
     } 
     //Metodo que retorna se a string é a palavra FIM
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
    public static void main(String[] args) throws Exception {
        
        String aString = new String();

        aString = MyIO.readLine(null);

        while(isFim(aString)){
                System.out.println(ciframentoCesar(aString));
                aString = MyIO.readLine(null);
            }       
          }
        }