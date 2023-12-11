import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class TP03Q011{
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        Jogador jogador = new Jogador();
        LL lista= new LL();
        String acharId=teclado.nextLine();

        while(!acharId.equals("FIM")){
            jogador=new Jogador();
        jogador.ler(acharId,lista);
        lista.InserirFinal(jogador);
        acharId=teclado.nextLine();
        }
        //Após o usuário digitar FIM, ele digita n e vai para o próximo loop
        int n = 0;
        if (teclado.hasNextInt()) {
            n = teclado.nextInt();
        }
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
       int tamanho=lista.GetTamanho();
       Jogador.QuickSort(lista, 0, tamanho);
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


public static void Imprimir(LL lista) {
    int tamanho=lista.GetTamanho();
    for(int i=0; i<tamanho; i++){
            System.out.println("[" + lista.getElemento(i).getId() + " ## " + lista.getElemento(i).getNome() +" ## "+ lista.getElemento(i).getAltura() +" ## "+ lista.getElemento(i).getPeso() +" ## "+ lista.getElemento(i).getAnoNascimento() +" ## "+ lista.getElemento(i).getUniversidade() +" ## "+ lista.getElemento(i).getCidadeNascimento() +" ## "+ lista.getElemento(i).getEstadoNascimento() +"]");
    }
}


public Jogador ler(String acharId, LL lista) {
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

public static void QuickSort(LL lista, int start, int end) {
    if (start < end) {
        int partitionIndex = partition(lista, start, end);

        QuickSort(lista, start, partitionIndex - 1);
        QuickSort(lista, partitionIndex + 1, end);
    }
}

public static int partition(LL lista, int start, int end) {
    int meio = (start + end) / 2;
    Jogador pivo = lista.getElemento(meio);

    int indiceEsq = start - 1;

    for (int indiceDir = start; indiceDir < end; indiceDir++) {
        int comparacaoEstados = lista.getElemento(indiceDir).getEstadoNascimento().compareTo(pivo.getEstadoNascimento());
        
        if (comparacaoEstados < 0 || (comparacaoEstados == 0 && lista.getElemento(indiceDir).getNome().compareTo(pivo.getNome()) <= 0)) {
            indiceEsq++;
            swap(lista, indiceEsq, indiceDir);
        }
    }

    swap(lista, indiceEsq + 1, end);
    return indiceEsq + 1;
}


public static void swap(LL lista, int index1, int index2) {
    if (lista.getNode(index1) != null && lista.getNode(index2) != null) {
        Jogador temp = lista.getNode(index1).valor;
        lista.getNode(index1).valor = lista.getNode(index2).valor;
        lista.getNode(index2).valor = temp;
    }
}





}


class LL {
    private Node head;
    private Node tail;
    private int tamanho;

      public LL() {
        head = null;
        tail = null;
        tamanho = 0;
    }


    public class Node {
        Node prox;
        Node ant;
        Jogador valor;

        Node(Jogador valor) {
            this.valor = valor;
        }
    }

    public void InserirInicio(Jogador valor) {
        Node celula = new Node(valor);
        celula.prox = head;
        head.ant=celula;
        head = celula;

        if (tail == null) {
            tail = head;
        }

        tamanho++;
    }

  public void InserirFinal(Jogador valor) {
    if (tail == null) {
        Node celula = new Node(valor);
        head = tail = celula;
        tamanho++;
    } else {
        Node celula = new Node(valor);
        tail.prox = celula;
        celula.ant = tail;
        tail = celula;
        tamanho++;
    }
}

    public void InserirPos(Jogador jogador, int pos) {
        if (pos < 0 || pos > tamanho) {
            // Posição inválida
            return;
        }

        if (pos == 0) {
            InserirInicio(jogador);
            return;
        }

        Node tmp = head;
        for (int i = 0; i < pos-1; i++) {
            tmp = tmp.prox;
        }

        Node celula = new Node(jogador);
        celula.prox = tmp.prox.prox;
        tmp.prox = celula;
        celula.ant=tmp;
        tmp.prox.prox.ant=celula;
        tamanho++;
    }

     public Jogador RemoverInicio(){
        Jogador remover=head.valor;
        head=head.prox;
        tamanho--;
        return remover;
    }

    public Jogador RemoverFinal(){
        Jogador remover=tail.valor;
        tail=tail.ant;
        tamanho--;
         return remover;
    }

    public Jogador RemoverPos(int pos){
        Node tmp=head;
    for(int i=0; i<pos; i++){
    tmp=tmp.prox;
    }
    Jogador remover=tmp.prox.valor;
    tmp.prox=tmp.prox.prox;
    tmp.prox.prox.ant=tmp;
    tamanho--;
    return remover;
    }

    

    public Jogador getElemento(int pos) {
        Node node = getNode(pos);
        return (node != null) ? node.valor : null;
    }

public Node getNode(int index) {
    Node tmp = head;
    for (int i = 0; i < index; i++) {
        if (tmp == null) {
            return null;
        }
        tmp = tmp.prox;
    }
    return tmp;
}

public int GetTamanho() {
        return tamanho;
    }
}
