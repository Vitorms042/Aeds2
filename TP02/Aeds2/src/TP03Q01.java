import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class TP03Q01{
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        Jogador jogador = new Jogador();
        FilaArray lista= new FilaArray(3922);
        String acharId=teclado.nextLine();

        while(!acharId.equals("FIM")){
            jogador=new Jogador();
        jogador.ler(acharId,lista);
        lista.InserirFinal(jogador);
        acharId=teclado.nextLine();
        }
        //Após o usuário digitar FIM, ele digita n e vai para o próximo loop
       int n= teclado.nextInt();
        teclado.nextLine();
       while(n!=0){
        String comando=teclado.nextLine();
        String comandos[]=comando.split(" "); //Split no comando por espaço em branco para poder processar os elementos recebidos e conseguir fazer as devidas operações.
        if(comandos[0].equals("II")){
             jogador=new Jogador();
             jogador.ler(comandos[1], lista);
            lista.InserirInicio(jogador);
        }
        else if(comandos[0].equals("IF")){
            jogador= new Jogador();
            jogador.ler(comandos[1], lista);
            lista.InserirFinal(jogador);
        }
        else if(comandos[0].equals("RI")){
           Jogador removido=lista.RemoverInicio();
            System.out.println("(R) " + removido.getNome());
        }
         else if(comandos[0].equals("RF")){
            Jogador removido=lista.RemoverFinal();
             System.out.println("(R) " + removido.getNome());
        }
        else if(comandos[0].equals("I*")){
            jogador=new Jogador();
            jogador.ler(comandos[2], lista);
            int pos=Integer.parseInt(comandos[1]);
            lista.InserirPos(jogador, pos);
        }
        else if(comandos[0].equals("R*")){
            int pos= Integer.parseInt(comandos[1]);
           Jogador removido=lista.RemoverPos(pos);
            System.out.println("(R) " + removido.getNome());
        }
        n--;
       }
       Jogador.Imprimir(lista);
        teclado.close();
    }

}

class Jogador{
    private int id;
    private String nome;
    private int altura; //Atributos da classe Jogador.
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
    //Costrutor vazio.
}

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento){
        this.id= id;
        this.nome=nome;
        this.altura=altura;//Construtor preenchido
        this.peso=peso;
        this.universidade=universidade;
        this.anoNascimento=anoNascimento;
        this.cidadeNascimento=cidadeNascimento;
        this.estadoNascimento=estadoNascimento;
    }

    public int getId(){
        return id; //Getters
    }

     public String getNome(){
        return nome;
    }

    public int getAltura(){
        return altura;
    }

     public int getPeso(){
        return peso;
    }

    public String getUniversidade(){
        return universidade;
    }

     public int getAnoNascimento(){
        return anoNascimento;
    }

    public String getCidadeNascimento(){
        return cidadeNascimento;
    }

    public String getEstadoNascimento(){
        return estadoNascimento;
    }

    public void setId(int id){
    this.id = id; //Setters.
}

public void setNome(String nome){
    this.nome = nome;
}

public void setAltura(int altura){
    this.altura = altura;
}

public void setPeso(int peso){
    this.peso = peso;
}

public void setUniversidade(String universidade){
    this.universidade = universidade;
}

public void setAnoNascimento(int anoNascimento){
    this.anoNascimento = anoNascimento;
}

public void setCidadeNascimento(String cidadeNascimento){
    this.cidadeNascimento = cidadeNascimento;
}

public void setEstadoNascimento(String estadoNascimento){
    this.estadoNascimento = estadoNascimento;
}

public Jogador clone() {
    return new Jogador(id, nome, altura, peso, universidade, anoNascimento, cidadeNascimento, estadoNascimento);
}//Método clonagem.


public static void Imprimir(FilaArray lista){
    int tamanho=lista.GetTamanho();
 for(int i=0; i<tamanho; i++){
            System.out.println("[" + i + "]" + " ## " + lista.getElemento(i).getNome() +" ## "+ lista.getElemento(i).getAltura() +" ## "+ lista.getElemento(i).getPeso() +" ## "+ lista.getElemento(i).getAnoNascimento() +" ## "+ lista.getElemento(i).getUniversidade() +" ## "+ lista.getElemento(i).getCidadeNascimento() +" ## "+ lista.getElemento(i).getEstadoNascimento());
    }
}


public static void InsertionSort(ArrayList<Jogador> time) {
   
}



public Jogador ler(String acharId, FilaArray lista) {
    String arquivoCSV = "/tmp/players.csv";
    try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
        String linha = "";
        int findId = Integer.parseInt(acharId);

        br.readLine(); // Lê a primeira linha manualmente para pular o cabeçalho
        while ((linha = br.readLine()) != null) {
            String palavras[] = linha.split(",", 8);
            for (int i = 0; i < palavras.length; i++) {
                if (palavras[i].equals(",")) {
                    if (palavras[i + 1].equals(",")) {
                        palavras[i].replace(palavras[i], "nao informado");
                    }
                }
            }
            setId(Integer.parseInt(palavras[0]));
            setNome(palavras[1]);
            setAltura(Integer.parseInt(palavras[2]));
            setPeso(Integer.parseInt(palavras[3]));
            setUniversidade(palavras[4]);
            setAnoNascimento(Integer.parseInt(palavras[5]));
            setCidadeNascimento(palavras[6]);
            setEstadoNascimento(palavras[7]);

            Jogador jogador = new Jogador(getId(), getNome(), getAltura(), getPeso(), getUniversidade(), getAnoNascimento(), getCidadeNascimento(), getEstadoNascimento());
            
            if (jogador.getId() == findId) {
                return jogador;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }
    
    // Retorna null se não encontrar o jogador com o ID especificado
    return null;
}
}

 class FilaArray{

private Jogador[] array; //Atributos
private int n;

    public FilaArray(int size) {
        array = new Jogador[size]; //Construtor
        n = 0;
    }

public void InserirInicio(Jogador x) {
    if (n < array.length) {
        for (int i = n - 1; i >= 0; i--) {
            array[i + 1] = array[i];
        } //Modificado para contornar um erro de index out of bounds, começa da penúltima posição até a primeira, jogando todas os elementos "uma casa para frente", até conseguir inserir na posição 0.
        array[0] = x;
        n++;
    }
}


    public void InserirFinal(Jogador x) {
        if (n < array.length) {
            array[n] = x;
            n++; //Insere na posição final dos elementos presentes
        }
    }

    

public void InserirPos(Jogador x, int pos){
if(n<array.length){
    for(int i=n; i>pos; i--){
        array[i]= array[i-1];
    } //Insere na posição indicada
    array[pos]=x;
    n++;
}
 else{
        System.out.println("A fila está cheia, não é possível inserir mais elementos.");
    }
}

public Jogador RemoverInicio(){
    Jogador remover=array[0];
    for(int i=0; i<n; i++){
        array[i]=array[i+1];
    } //Remove do início do array
    n--;
    return remover;
}


public Jogador RemoverFinal(){
    return array[--n];
}

public Jogador RemoverPos( int pos) {
    Jogador remover = array[pos];
    for (int i = pos; i < array.length - 1; i++) {
        array[i] = array[i + 1];
    } //Remove de uma posição específica
    n--;
    return remover;
}

public int GetTamanho(){
    return n; //Retorna o tamanho do array/número de elementos presentes
}

public Jogador getElemento(int index) {
    if (index >= 0 && index < n) {
        return array[index];
    } 
    else{   //Acessa um jogador em um índice específico e retorna.
        return null;
    }
}

}