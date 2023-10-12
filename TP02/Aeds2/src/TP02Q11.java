import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

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

    public void lerDados(String dadosJogador) {
        String[] posicao = new String[8];
        int tmp = 0;
        int j = 0;
        for (int i = 0; i < dadosJogador.length(); i++) {
            if (dadosJogador.charAt(i) == ',') {
                posicao[j] = dadosJogador.substring(tmp, i);
                tmp = i + 1;
                if (posicao[j].intern() == "") {
                    posicao[j] = "nao informado";
                }
                j++;
            }
        }
        posicao[7] = dadosJogador.substring(tmp, dadosJogador.length());

        if (posicao[7] == "") {
            posicao[7] = "nao informado";
        }
        setId(Integer.parseInt(posicao[0]));
        setNome(posicao[1]);
        setAltura(Integer.parseInt(posicao[2]));
        setPeso(Integer.parseInt(posicao[3]));
        setUniversidade(posicao[4]);
        setAnoNascimento(Integer.parseInt(posicao[5]));
        setCidadeNascimento(posicao[6]);
        setEstadoNascimento(posicao[7]);
    }
}

public class TP02Q11 {
    public static void main(String[] args) {

        long tempoIni = System.currentTimeMillis();
        ArrayList<Jogador> jogadores = new ArrayList<>();
        
        try{
            FileReader fileReader = new FileReader("/tmp/players.csv");
            BufferedReader arquivo = new BufferedReader(fileReader);   
            arquivo.readLine();
            while (arquivo.ready()) {
                Jogador jogador = new Jogador();
                jogador.lerDados(arquivo.readLine());
                jogadores.add(jogador);
                }
                arquivo.close();
            }catch(Exception e){
               System.out.println(e.getMessage() + "\n" + e.getLocalizedMessage());
            }
        ArrayList<Jogador> JogadorSelecionado = new ArrayList<>();
		Scanner scanner = new Scanner(System.in);
        String id = scanner.nextLine();
        while (!id.equals("FIM")) {
            int n = Integer.parseInt(id);
            if(n >= 0 && n < jogadores.size()){
            JogadorSelecionado.add(jogadores.get(Integer.parseInt(id)));
            id = scanner.nextLine();
            }else{
              System.out.println("Indice elevado");
              id = scanner.nextLine();
            }
        }

        int contador[] = coutingSort(JogadorSelecionado);

        long tempoFim = System.currentTimeMillis();
        try{
            FileWriter fileWriter = new FileWriter("matricula_countingsort.txt");
            BufferedWriter arqWriter = new BufferedWriter(fileWriter);
            arqWriter.write("Matricula: 808664\tTempo: " +(tempoFim - tempoIni) / 1000d + "\tComparacoes: "+ contador[0] + "\tMovimentacoes: " + contador[1]);
            arqWriter.close();
        }catch(Exception e){
            System.out.println(e.getMessage() + "\n" + e.getLocalizedMessage());
        }
        scanner.close();
    }

    public static int getMaior(ArrayList<Jogador> array) {
        int maior = array.get(0).getAltura();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getAltura() > maior)
                maior = array.get(i).getAltura();
        }
        return maior;
    }

   public static int[] coutingSort(ArrayList<Jogador> array1){
    int Movimentacoes = 0;
    int Comparacoes = 0;
    int[] array2 = new int[array1.size()];
    int[] array3 = new int[getMaior(array1)];
    ArrayList<Jogador> ordenado = new ArrayList<>();

    for(int i = 0; i < array1.size(); i++){
        array3[array1.get(i).getAltura() - 1] += 1;
    }

    for(int i = 1; i < array3.length; i++){
        array3[i] += array3[i - 1];
    }

    for (int i = array1.size() - 1; i >= 0; i--) {
        array2[array3[array1.get(i).getAltura() - 1] - 1] = i;
        array3[array1.get(i).getAltura() - 1] -= 1;
    }

    for (int i = 0; i < array2.length; i++) {
        ordenado.add(array1.get(array2[i]));
        Movimentacoes++;
    }

    for (int i = 0; i < ordenado.size(); i++) {
        for (int j = i + 1; j < ordenado.size(); j++) {
            if (ordenado.get(i).getAltura() == ordenado.get(j).getAltura()) {
                Comparacoes++;
                if (ordenado.get(i).getNome().compareToIgnoreCase(ordenado.get(j).getNome()) > 0) {
                    Comparacoes++;
                    Jogador temp = ordenado.get(i);
                    ordenado.set(i, ordenado.get(j));
                    ordenado.set(j, temp);
                    Movimentacoes += 3;
                }
            }
        }
    }

    for (int i = 0; i < ordenado.size(); i++) {
        ordenado.get(i).imprimirDados();
    }
    return new int[] {Comparacoes, Movimentacoes};
  }
 }