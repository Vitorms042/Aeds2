import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Tp04Q04{
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        Jogador jogador = new Jogador();
        BST arvore= new BST();
        String acharId=teclado.nextLine();

        while(!acharId.equals("FIM")){
            jogador=new Jogador();
        jogador.ler(acharId,arvore);
        arvore.inserir(jogador);
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
    NoAN raiz;
class NoAN {
  public boolean cor;
  public Jogador elemento;
  public NoAN esq, dir;


  public NoAN(Jogador elemento) {
    this(elemento, false, null, null);
  }

  public NoAN(Jogador elemento, boolean cor) {
    this(elemento, cor, null, null);
  }

  public NoAN(Jogador elemento, boolean cor, NoAN esq, NoAN dir) {
    this.cor = cor;
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

public void inserir(Jogador elemento){
      // Se a arvore estiver vazia
      if (raiz == null) {
         raiz = new NoAN(elemento);
      // Senao, se a arvore tiver um elemento
      } 
      else if (raiz.esq == null && raiz.dir == null) {
         if (elemento.getNome().compareTo(raiz.elemento.getNome())<0) {
            raiz.esq = new NoAN(elemento);
         } 
         else {
            raiz.dir = new NoAN(elemento);
         }

      // Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null) {
         if (elemento.getNome().compareTo(raiz.elemento.getNome())<0) {
            raiz.esq = new NoAN(elemento);
         } 
         else if (elemento.getNome().compareTo(raiz.dir.elemento.getNome())<0) {
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = elemento;
            
         } 
         else {
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = raiz.dir.elemento;
            raiz.dir.elemento = elemento;
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, se a arvore tiver dois elementos (raiz e esq)
      } 
      else if (raiz.dir == null) {
         if (elemento.getNome().compareTo(raiz.elemento.getNome())>0) {
            raiz.dir = new NoAN(elemento);
       

         } 
         else if (elemento.getNome().compareTo(raiz.esq.elemento.getNome())<0) {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = elemento;


         } 
         else {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = raiz.esq.elemento;
            raiz.esq.elemento = elemento;

         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, a arvore tem tres ou mais elementos
      } 
      else {
         inserir(elemento, null, null, null, raiz);
      }
      raiz.cor = false;
   }

   private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
      // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
      if (pai.cor == true) {
         // 4 tipos de reequilibrios e acoplamento
         if (pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0) { // rotacao a esquerda ou direita-esquerda
            if (i.elemento.getNome().compareTo(pai.elemento.getNome()) >0) {
               avo = rotacaoEsq(avo);
            } else {
               avo = rotacaoDirEsq(avo);
            }
         } else { // rotacao a direita ou esquerda-direita
            if (i.elemento.getNome().compareTo(pai.elemento.getNome()) <0) {
               avo = rotacaoDir(avo);
            } else {
               avo = rotacaoEsqDir(avo);
            }
         }
         if (bisavo == null) {
            raiz = avo;
         } else if (avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0) {
            bisavo.esq = avo;
         } else {
            bisavo.dir = avo;
         }
         // reestabelecer as cores apos a rotacao
         avo.cor = false;
         avo.esq.cor = avo.dir.cor = true;
      } // if(pai.cor == true)
   }


   private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i)  {
      if (i == null) {
         if (elemento.getNome().compareTo(pai.elemento.getNome()) < 0) {
            i = pai.esq = new NoAN(elemento, true);
         } 
         else {
            i = pai.dir = new NoAN(elemento, true);
         }
         if (pai.cor == true) {
            balancear(bisavo, avo, pai, i);
         }
      } 
      else {
         // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
         if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
            i.cor = true;
            i.esq.cor = i.dir.cor = false;
            if (i == raiz) {
               i.cor = false;
            } else if (pai.cor == true) {
               balancear(bisavo, avo, pai, i);
            }
         }
         if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
            inserir(elemento, avo, pai, i, i.esq);
         } 
         else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
            inserir(elemento, avo, pai, i, i.dir);
         }
      }
   }

   private NoAN rotacaoDir(NoAN no) {
     
      NoAN noEsq = no.esq;
      NoAN noEsqDir = noEsq.dir;

      noEsq.dir = no;
      no.esq = noEsqDir;

      return noEsq;
   }

   private NoAN rotacaoEsq(NoAN no) {
  
      NoAN noDir = no.dir;
      NoAN noDirEsq = noDir.esq;

      noDir.esq = no;
      no.dir = noDirEsq;
      return noDir;
   }

   private NoAN rotacaoDirEsq(NoAN no) {
      no.dir = rotacaoDir(no.dir);
      return rotacaoEsq(no);
   }

   private NoAN rotacaoEsqDir(NoAN no) {
      no.esq = rotacaoEsq(no.esq);
      return rotacaoDir(no);
   }

public void PreOrdem(){
PreOrdemHelper(raiz);
}

private void PreOrdemHelper(NoAN raiz){
    if(raiz!=null){
    System.out.println(raiz.elemento.getNome());
    PreOrdemHelper(raiz.esq);
    PreOrdemHelper(raiz.dir);
    }
}

public void InOrder(){
InOrderHelper(raiz);
}

private void InOrderHelper(NoAN raiz){
    if(raiz!=null){
    InOrderHelper(raiz.esq);
    System.out.println(raiz.elemento.getNome());
    InOrderHelper(raiz.dir);
    }
}

public void PosOrdem(){
PosOrdemHelper(raiz);
}

private void PosOrdemHelper(NoAN raiz){
    if(raiz!=null){
    PosOrdemHelper(raiz.esq);
    PosOrdemHelper(raiz.dir);
    System.out.println(raiz.elemento.getNome());
    }
}

public boolean pesquisa(String valor) {
    return pesquisaHelper(raiz, valor);
}

private boolean pesquisaHelper(NoAN raiz, String valor) {
    StringBuilder caminho = new StringBuilder(); //Criação de um stringbuilder para guardar o caminho.
    caminho.append(valor + " "); //Antes mesmo de começar o método, é registrado o nome do jogador, e a palavra raiz.
    caminho.append("raiz" + " ");
    return pesquisaRecursiva(raiz, valor, caminho);
}

private boolean pesquisaRecursiva(NoAN raiz, String valor, StringBuilder caminho) {
    if (raiz == null) {
        caminho.append("NAO"); //Se fôr nulo, não está na árvore. logo, é encerrado os loops.
        System.out.println(caminho);
        return false;
    } else if (valor.equals(raiz.elemento.getNome())) {
        caminho.append("SIM"); //Foi encontrado o nome desejado.
        System.out.println(caminho);
        return true;
    } else if (valor.compareTo(raiz.elemento.getNome()) < 0) {
        caminho.append("esq" + " "); //Se fôr menor, retorna para a esquerda da árvore.
        return pesquisaRecursiva(raiz.esq, valor, caminho);
    } else {
        caminho.append("dir"+" ");//Se fôr maior, retorna para a direita da árvore.
        return pesquisaRecursiva(raiz.dir, valor, caminho);
    }
}






public Jogador Remover(Jogador valor){
NoAN remover=RemoverHelper(valor, raiz);
return remover.elemento;
}

private NoAN RemoverHelper(Jogador valor, NoAN raiz){
if(raiz==null){
    return raiz;
}
else if(raiz.elemento.getNome().compareTo(valor.getNome())<0){
    raiz.esq= RemoverHelper(valor, raiz.esq);
}
else if(raiz.elemento.getNome().compareTo(valor.getNome())>0){
    raiz.dir= RemoverHelper(valor, raiz.dir);
}

else{
    if(raiz.esq==null && raiz.dir==null){
    raiz=null;
    }
    else if(raiz.esq==null){
    raiz.elemento=predecessor(raiz);
    raiz.esq=RemoverHelper(valor, raiz);
    }
    else if(raiz.dir==null){
    raiz.elemento=sucessor(raiz);
    raiz.dir=RemoverHelper(valor, raiz);
    }
}
return raiz;
}

private Jogador sucessor(NoAN raiz){ //Menor dos da direita
raiz=raiz.dir;
while(raiz.esq!=null){
    raiz=raiz.esq;
}
return raiz.elemento;
}

private Jogador predecessor(NoAN raiz){ //Maior do da esquerda
raiz=raiz.esq;
while(raiz.dir!= null){
    raiz=raiz.dir;
}
return raiz.elemento;
}

}