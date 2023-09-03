import java.util.*;
import java.io.*;

public class TP01Q07 {
    public static void main(String[] args) throws IOException {

        double input;
        RandomAccessFile file = new RandomAccessFile("teste.txt", "rw");
        int size = MyIO.readInt();
        //Faz a leitura de todos os valores Double e salva no arquivo
        for(int i = size;i > 0;i--){
            input = MyIO.readDouble();
            file.writeDouble(input);
        }
        //Seleciona a posicao de bytes para acessar de tras para frente todos os valores de Double lidos anteriormente
        for(int i = (size-1); i >=0;i--){
            file.seek(i*8);
            double resp = file.readDouble();
            int x = (int) resp;
            if(x == resp) System.out.println(x);
            else System.out.println(resp);
        }
        file.close();
    }
}