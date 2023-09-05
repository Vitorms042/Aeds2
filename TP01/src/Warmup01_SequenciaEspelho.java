public class Warmup01_SequenciaEspelho {

    public static String isEspelho(int B, int E){  
        StringBuilder sequencia = new StringBuilder();
        StringBuilder sequenciaInvertida = new StringBuilder();
         for(int i = B; i <= E; i++){
            sequencia.append(i);
         }

         for(int i = sequencia.length() - 1; i >= 0; i--){
            sequenciaInvertida.append(sequencia.charAt(i));
         }

           return sequencia.toString() + sequenciaInvertida.toString();
        }
  
   public static boolean isFim(int B, int E){
      return (B >= 1 && E <= 12221 && B <= E);
  }
  public static void main(String[] args) throws Exception {
      int B, E, casos = 3;
      for(int i = 0; i < casos; i++){
         B = MyIO.readInt();
         E = MyIO.readInt();
         if(!isFim(B, E)){
          break;
         }else{
             System.out.println(isEspelho(B, E)); 
          }
      }        
    }
  }