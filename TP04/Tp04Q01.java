
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Tp04Q01{
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        Jogador jogador = new Jogador();
        BST arvore= new BST();
        String acharId=teclado.nextLine();

        while(!acharId.equals("FIM")){
            jogador=new Jogador();
        jogador.ler(acharId,arvore);
        arvore.InserirValor(jogador);
        acharId=teclado.nextLine();
        }
        //Após o usuário digitar FIM, ele digita n e vai para o próximo loop
        
       String nome=teclado.nextLine();
       while(!nome.equals("FIM")){
       arvore.pesquisa(nome);
       nome=teclado.nextLine();
       }
       
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


public Jogador ler(String acharId, BST lista) {
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

class BST{
    Node raiz;
class Node{
    Node dir;
    Node esq;
    Jogador jogador;
    
Node(Jogador jogador){
this.jogador=jogador;
}
}

public void InserirValor(Jogador valor){
    raiz = InserirHelper(raiz, valor);
}

private Node InserirHelper(Node raiz, Jogador valor){
    if(raiz == null){
        return new Node(valor);
    }

    if(valor.getNome().compareTo(raiz.jogador.getNome()) < 0){
        raiz.esq = InserirHelper(raiz.esq, valor);
    } 
    else if(valor.getNome().compareTo(raiz.jogador.getNome()) > 0){
        raiz.dir = InserirHelper(raiz.dir, valor);
    }

    return raiz;
}

public void PreOrdem(){
PreOrdemHelper(raiz);
}

private void PreOrdemHelper(Node raiz){
    if(raiz!=null){
    System.out.println(raiz.jogador.getNome());
    PreOrdemHelper(raiz.esq);
    PreOrdemHelper(raiz.dir);
    }
}

public void InOrder(){
InOrderHelper(raiz);
}

private void InOrderHelper(Node raiz){
    if(raiz!=null){
    InOrderHelper(raiz.esq);
    System.out.println(raiz.jogador.getNome());
    InOrderHelper(raiz.dir);
    }
}

public void PosOrdem(){
PosOrdemHelper(raiz);
}

private void PosOrdemHelper(Node raiz){
    if(raiz!=null){
    PosOrdemHelper(raiz.esq);
    PosOrdemHelper(raiz.dir);
    System.out.println(raiz.jogador.getNome());
    }
}

public boolean pesquisa(String valor) {
    return pesquisaHelper(raiz, valor);
}

private boolean pesquisaHelper(Node raiz, String valor) {
    StringBuilder caminho = new StringBuilder(); //Criação de um stringbuilder para guardar o caminho.
    caminho.append(valor + " "); //Antes mesmo de começar o método, é registrado o nome do jogador, e a palavra raiz.
    caminho.append("raiz" + " ");
    return pesquisaRecursiva(raiz, valor, caminho);
}

private boolean pesquisaRecursiva(Node raiz, String valor, StringBuilder caminho) {
    if (raiz == null) {
        caminho.append("NAO"); //Se fôr nulo, não está na árvore. logo, é encerrado os loops.
        System.out.println(caminho);
        return false;
    } else if (valor.equals(raiz.jogador.getNome())) {
        caminho.append("SIM"); //Foi encontrado o nome desejado.
        System.out.println(caminho);
        return true;
    } else if (valor.compareTo(raiz.jogador.getNome()) < 0) {
        caminho.append("esq" + " "); //Se fôr menor, retorna para a esquerda da árvore.
        return pesquisaRecursiva(raiz.esq, valor, caminho);
    } else {
        caminho.append("dir"+" ");//Se fôr maior, retorna para a direita da árvore.
        return pesquisaRecursiva(raiz.dir, valor, caminho);
    }
}






public Jogador Remover(Jogador valor){
Node remover=RemoverHelper(valor, raiz);
return remover.jogador;
}

private Node RemoverHelper(Jogador valor, Node raiz){
if(raiz==null){
    return raiz;
}
else if(raiz.jogador.getNome().compareTo(valor.getNome())<0){
    raiz.esq= RemoverHelper(valor, raiz.esq);
}
else if(raiz.jogador.getNome().compareTo(valor.getNome())>0){
    raiz.dir= RemoverHelper(valor, raiz.dir);
}

else{
    if(raiz.esq==null && raiz.dir==null){
    raiz=null;
    }
    else if(raiz.esq==null){
    raiz.jogador=predecessor(raiz);
    raiz.esq=RemoverHelper(valor, raiz);
    }
    else if(raiz.dir==null){
    raiz.jogador=sucessor(raiz);
    raiz.dir=RemoverHelper(valor, raiz);
    }
}
return raiz;
}

private Jogador sucessor(Node raiz){ //Menor dos da direita
raiz=raiz.dir;
while(raiz.esq!=null){
    raiz=raiz.esq;
}
return raiz.jogador;
}

private Jogador predecessor(Node raiz){ //Maior do da esquerda
raiz=raiz.esq;
while(raiz.dir!= null){
    raiz=raiz.dir;
}
return raiz.jogador;
}

}


