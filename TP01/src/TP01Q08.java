import java.io.*;
import java.net.*;

public class TP01Q08 {

    public static void main(String[] args) {

        String tituloPagina = MyIO.readLine();

        while(!isFim(tituloPagina)){
            String endereco = MyIO.readLine();
            String html = getHtml(endereco);

            int[] cont = new int[25];

            for(int i = 0; i < html.length(); i++){
               if(html.charAt(i) == 'a'){
                 cont[0]++;
               }else if(html.charAt(i) == 'e'){
                cont[1]++;
               }else if(html.charAt(i) == 'i'){
                cont[2]++;
               }else if(html.charAt(i) == 'o'){
                cont[3]++;
               }else if(html.charAt(i) == 'u'){
                cont[4]++;
               }else if(html.charAt(i) == '\u00E1'){
                cont[5]++;
               }else if(html.charAt(i) == '\u00E9'){
                cont[6]++;
               }else if(html.charAt(i) == '\u00E9'){
                cont[7]++;
               }else if(html.charAt(i) == '\u00F3'){
                cont[8]++;
               }else if(html.charAt(i) == '\u00FA'){
                cont[9]++;
               }else if(html.charAt(i) == '\u00E0'){
                cont[10]++;
               }else if(html.charAt(i) == '\u00E8'){
                cont[11]++;
               }else if(html.charAt(i) == '\u00EC'){
                cont[12]++;
               }else if(html.charAt(i) == '\u00F2'){
                cont[13]++;
               }else if(html.charAt(i) == '\u00F9'){
                cont[14]++;
               }else if(html.charAt(i) == '\u00E3'){
                cont[15]++;
               }else if(html.charAt(i) == '\u00F5'){
                cont[16]++;
               }else if(html.charAt(i) == '\u00E2'){
                cont[17]++;
               }else if(html.charAt(i) == '\u00EA'){
                cont[18]++;
               }else if(html.charAt(i) == '\u00EE'){
                cont[19]++;
               }else if(html.charAt(i) == '\u00F4'){
                cont[20]++;
               }else if(html.charAt(i) == '\u00FB'){
                cont[21]++;
               }else if(isConsoante(html.charAt(i))){
                cont[22]++;
               }else if(i + 4 < html.length() && html.substring(i, i + 4).equals("<br>")){
                 i += 4;
                cont[23]++;
               }else if(i + 4 < html.length() && html.substring(i, i + 7).equals("<table>")){
                i += 7;
                cont[24]++;
               }

               MyIO.println("a(" + cont[0] + ") " + "e(" + cont[1] + ") " + "i(" + cont[2] + ") " + "o(" + cont[3] + ") " + "u(" + cont[4] + ") " + "á(" + cont[5] + ") " + "é(" + cont[6] + ") " + "í(" + cont[7] + ") " + "ó(" + cont[8] + ") " + "ú(" + cont[9] + ") " + "à(" + cont[10] + ") " + "è(" + cont[11] + ") " + "ì(" + cont[12] + ") " + "ò(" + cont[13] + ") " + "ù(" + cont[14] + ") " + "ã(" + cont[15] + ") " + "õ(" + cont[16] + ") " + "â(" + cont[17] + ") " + "ê(" + cont[18] + ") " + "î(" + cont[19] + ") " + "ô(" + cont[20] + ") " + "û(" + cont[21] + ") " + "consoante(" + cont[22] + ") " + "<br>(" + cont[23] + ") " + "<table>(" + cont[24] + ") " + tituloPagina);
        }

        for(int i = 0; i < cont.length; i++){
            cont[i] = 0;
        } 
       tituloPagina = MyIO.readLine();        
     }
   }
   
    public static boolean isConsoante(char s){
           char letra = Character.toLowerCase(s);
            if((letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u')){
                return false;
        }
         return true;
    }

    public static String getHtml(String endereco){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
  
        try {
           url = new URL(endereco);
           is = url.openStream();  // throws an IOException
           br = new BufferedReader(new InputStreamReader(is));
  
           while ((line = br.readLine()) != null) {
              resp += line + "\n";
           }
        } catch (MalformedURLException mue) {
           mue.printStackTrace();
        } catch (IOException ioe) {
           ioe.printStackTrace();
        } 
  
        try {
           is.close();
        } catch (IOException ioe) {
           // nothing to see here
  
        }
  
        return resp;
    }

    public static boolean isFim(String s) {
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
}
