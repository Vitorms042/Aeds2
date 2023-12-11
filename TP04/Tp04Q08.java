
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Tp04Q08{
    public static void main(String[] args) {
        Scanner teclado=new Scanner(System.in);
        Jogador jogador = new Jogador();
        Hash hash= new Hash();
        String acharId=teclado.nextLine();

        while(!acharId.equals("FIM")){
            jogador=new Jogador();
        jogador.ler(acharId,hash);
        hash.inserir(jogador);
        acharId=teclado.nextLine();
        }
        //Após o usuário digitar FIM, ele digita n e vai para o próximo loop
    
       String nome=teclado.nextLine();
       while(!nome.equals("FIM")){
       hash.pesquisar(nome);
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


public Jogador ler(String acharId, Hash lista) {
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

class Hash {
   Jogador tabela[];
   int m;
   final int NULO = -1;

   public Hash() {
      this(25);
   }

   public Hash(int m) {
      this.m = m;
      this.tabela = new Jogador [this.m];
      for (int i = 0; i < m; i++) {
         tabela[i] = null;
      }
   }

   public int h(int elemento) {
      return elemento % m;
   }

   public int reh(int elemento) {
      return ++elemento % m;
   }

   public boolean inserir(Jogador elemento) {
      boolean resp = false;
      if (elemento != null) {
         int pos = h(elemento.getAltura());
         if (tabela[pos] == null) {
            tabela[pos] = elemento;
            resp = true;
         } 
         else {
            pos = reh(elemento.getAltura());
            if (tabela[pos] == null) {
               tabela[pos] = elemento;
               resp = true;
            }
         }
      }
      return resp;
   }

    public void pesquisar(String elemento) {
    for (int i = 0; i < m; i++) {
        Jogador jogador = tabela[i];
        if (jogador != null && jogador.getNome().equals(elemento)) {
            System.out.println(elemento + " " + "SIM");
            return;
        }
    }
    System.out.println(elemento + " " + "NAO");
}
}
