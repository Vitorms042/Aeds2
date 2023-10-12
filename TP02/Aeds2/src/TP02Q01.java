import java.io.RandomAccessFile;

class Jogador {
  
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        id = 0; 
        altura = 0;
        peso = 0;
        anoNascimento = 0;
        nome = "";
        universidade = "";
        cidadeNascimento = "";
        estadoNascimento = "";
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

   public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAltura() {
        return altura;
    }

    public int getPeso() {
        return peso;
    }
    
    public String getUniversidade() {
        return universidade;
    }

     public int getAnoNascimento() {
        return anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void imprimirDados() {
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## " 
        + getPeso() + " ## " + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
    }

    public Jogador clone() {
        Jogador clone = new Jogador(getId(), getNome(), getAltura(), getPeso(), getUniversidade(), getAnoNascimento(), getCidadeNascimento(), getEstadoNascimento());
        return clone;
    }

    public void lerDados(String id) throws Exception {

      RandomAccessFile arqJogador = new RandomAccessFile("/tmp/players.csv", "r");

      String linha;

      arqJogador.seek(60);

      while((linha = arqJogador.readLine()) != null){
        String[] array = linha.split(",");

        while(array.length < 8){
            linha += ", ";
            array = linha.split(",");
        }

      if(array.length >= 8 && array[0].equals(id)){
        setId(Integer.parseInt(id));
        setNome((array[1]));
        setAltura(Integer.parseInt(array[2]));
        setPeso(Integer.parseInt(array[3]));
        setAnoNascimento(Integer.parseInt(array[5]));

        if (array[6].isEmpty() || array[6].equals("")) {
            setCidadeNascimento("nao informado");
        } else {
            setCidadeNascimento(array[6]);
        }

        if (array[7].isEmpty() || array[7].equals("")) {
            setEstadoNascimento("nao informado");
        } else {
            setEstadoNascimento(array[7]);
        }

        if (array[4].isEmpty() || array[4].equals("")) {
            setUniversidade(("nao informado"));
        } else {
            setUniversidade(array[4]);
        }

        imprimirDados();
        break;
      }
    }

      arqJogador.close();
    }
}

public class TP02Q01 {
    public static void main(String[] args) throws Exception {
    
        Jogador jogadores[] = new Jogador[3500];
        String id = "";
        int i = 0;

        while(!comparaString(id, "FIM")){
            jogadores[i] = new Jogador();
            id = MyIO.readLine();
            jogadores[i].lerDados(id);

            i++;
        }
    }

public static boolean comparaString(String palavra1, String palavra2){
    if (palavra1.length() != palavra2.length()) {
        return false;
    }

    for(int i = 0; i < palavra2.length(); i++) {
        if(palavra1.charAt(i) != palavra2.charAt(i)){
            return false;
        }
    }
   return true;
}
}