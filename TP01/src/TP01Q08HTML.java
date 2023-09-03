import java.io.*;
import java.net.*;

public class TP01Q08HTML {
    public static void main(String[] args) {
        String tituloSite, html;
        boolean end = false;
        while (!end) {
            tituloSite = MyIO.readLine();
            end = isFim(tituloSite);
            if (!end) {
                html = MyIO.readLine();
                html = getHtml(html);
                stringSaida(tituloSite, html);
            }
        }
    }

    public static void stringSaida(String tituloSite, String html) {
        int[] contCaracter = new int[25];
        
        for (int i = 0; i < contCaracter.length; i++) {
            contCaracter[i] = 0;
        }
        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == 'a')
                contCaracter[0]++;
            else if (html.charAt(i) == 'e')
                contCaracter[1]++;
            else if (html.charAt(i) == 'i')
                contCaracter[2]++;
            else if (html.charAt(i) == 'o')
                contCaracter[3]++;
            else if (html.charAt(i) == 'u')
                contCaracter[4]++;
            else if (html.charAt(i) == '\u00E1')
                contCaracter[5]++;
            else if (html.charAt(i) == '\u00E9')
                contCaracter[6]++;
            else if (html.charAt(i) == '\u00ED')
                contCaracter[7]++;
            else if (html.charAt(i) == '\u00F3')
                contCaracter[8]++;
            else if (html.charAt(i) == '\u00FA')
                contCaracter[9]++;
            else if (html.charAt(i) == '\u00E0')
                contCaracter[10]++;
            else if (html.charAt(i) == '\u00E8')
                contCaracter[11]++;
            else if (html.charAt(i) == '\u00EC')
                contCaracter[12]++;
            else if (html.charAt(i) == '\u00F2')
                contCaracter[13]++;
            else if (html.charAt(i) == '\u00F9')
                contCaracter[14]++;
            else if (html.charAt(i) == '\u00E3')
                contCaracter[15]++;
            else if (html.charAt(i) == '\u00F5')
                contCaracter[16]++;
            else if (html.charAt(i) == '\u00E2')
                contCaracter[17]++;
            else if (html.charAt(i) == '\u00EA')
                contCaracter[18]++;
            else if (html.charAt(i) == '\u00EE')
                contCaracter[19]++;
            else if (html.charAt(i) == '\u00F4')
                contCaracter[20]++;
            else if (html.charAt(i) == '\u00FB')
                contCaracter[21]++;
            else if (consoante(html.charAt(i)))
                contCaracter[22]++;
            else if (i + 4 < html.length() && html.substring(i, i + 4).equals("<br>")) {
                i += 4;
                contCaracter[23]++;
            } else if (i + 7 < html.length() && html.substring(i, i + 7).equals("<table>")) {
                i += 7;
                contCaracter[24]++;
            }
        }
        MyIO.println("a(" + contCaracter[0] + ") e(" + contCaracter[1] + ") i(" + contCaracter[2] + ") o(" + contCaracter[3]
                + ") u(" + contCaracter[4] + ") á(" + contCaracter[5] + ") é(" + contCaracter[6] + ") í(" + contCaracter[7]
                + ") ó(" + contCaracter[8] + ") ú(" + contCaracter[9] + ") à(" + contCaracter[10] + ") è(" + contCaracter[11]
                + ") ì(" + contCaracter[12] + ") ò(" + contCaracter[13] + ") ù(" + contCaracter[14] + ") ã(" + contCaracter[15]
                + ") õ(" + contCaracter[16] + ") â(" + contCaracter[17] + ") ê(" + contCaracter[18] + ") î(" + contCaracter[19]
                + ") ô(" + contCaracter[20] + ") û(" + contCaracter[21] + ") consoante(" + contCaracter[22] + ") <br>("
                + contCaracter[23] + ") <table>(" + contCaracter[24] + ") " + tituloSite);
    }

    public static boolean consoante(char letra) {
        if (letra >= 'a' && letra <= 'z') {
            if (letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o'
                    || letra == 'u') {
                return false;
            }
            return true;
        }
        return false;
    }

    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
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
        return s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M';
    }
}