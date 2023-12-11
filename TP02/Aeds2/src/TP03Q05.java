import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

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
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        if (cidadeNascimento.length() > 0)
            this.cidadeNascimento = cidadeNascimento;
        else
            cidadeNascimento = "nao informado";
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public void imprimir(int i) {
        System.out.println("[" + i + "]" + " ## " + getNome() + " ## " + getAltura() + " ## " + getPeso() + " ## "
                + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## "
                + getEstadoNascimento() + " ## ");
    }

    public void ler(String linha) {
        String[] data = new String[8];
        int tmp = 0;
        int j = 0;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == ',') {
                data[j] = linha.substring(tmp, i);
                tmp = i + 1;
                if (data[j].intern() == "") {
                    data[j] = "nao informado";
                }
                j++;
            }

        }

        data[7] = linha.substring(tmp, linha.length());

        if (data[7] == "") {
            data[7] = "nao informado";
        }
        setId(Integer.parseInt(data[0]));
        setNome(data[1]);
        setAltura(Integer.parseInt(data[2]));
        setPeso(Integer.parseInt(data[3]));
        setUniversidade(data[4]);
        setAnoNascimento(Integer.parseInt(data[5]));
        setCidadeNascimento(data[6]);
        setEstadoNascimento(data[7]);

    }
}

class Celula {
    public Jogador jogador;
    public Celula prox;// aponta para objetos do tipo célula

    public Celula() {
    }

    public Celula(Jogador jogador) {
        this.jogador = jogador;
        this.prox = null;
    }
}

class Lista {
    private Celula primeiro, ultimo;

    Lista() {
        primeiro = new Celula();
        ultimo = primeiro;
    }

    public int tamanho() {
        int tamanho = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            tamanho++;
        }
        return tamanho;
    }

    public void inserirFim(Jogador jogador) {
        ultimo.prox = new Celula(jogador);
        ultimo = ultimo.prox;
        // jogador.imprimir(0);
    }

    public void inserirInicio(Jogador jogador) {
        Celula tmp = new Celula(jogador);
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
        // jogador.imprimir(0);
    }

    public void inserir(Jogador jogador, int pos) throws Exception {
        int tamanho = tamanho();
        if (pos < 0 || pos > tamanho)
            throw new Exception("Invalid index!");
        else if (pos == 0)
            inserirInicio(jogador);
        else if (pos == tamanho)
            inserirFim(jogador);
        else {
            Celula i = primeiro;
            // percorre até anterior a posição onde se quer inserir elemento
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            Celula tmp = new Celula(jogador);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
            // jogador.imprimir(0);

        }
    }

    // remove fisicamente nó cabeça, primeiro elemento inserido vira cabeça
    public Jogador removerInicio() throws Exception {// igual fila
        if (primeiro == ultimo)
            throw new Exception("Can't remove from an empty queue");
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        Jogador resp = primeiro.jogador;
        tmp.prox = null;
        tmp = null;
        // resp.imprimir(0);
        return resp;
    }

    public Jogador removerFim() throws Exception {
        // ultimo elemento passa a ser penúltimo(ultimo = i), remove referencias para
        // antigo ultimo
        if (primeiro == ultimo)
            throw new Exception("Array is empty!");
        Celula i;
        // percorre array até penultimo elemento
        for (i = primeiro; i.prox != ultimo; i = i.prox)
            ;
        Jogador resp = ultimo.jogador;
        ultimo = i;
        i = ultimo.prox = null;
        // resp.imprimir(0);
        return resp;
    }

    public Jogador remover(int pos) throws Exception {
        Jogador resp;
        int tamanho = tamanho();
        if (primeiro == ultimo || pos < 0 || pos >= tamanho)
            throw new Exception("Empty Array/ Invalid index!");
        else if (pos == 0)
            resp = removerInicio();
        else if (pos == tamanho - 1)
            resp = removerFim();
        else {
            Celula i = primeiro;
            // percorre até posição anterior do resp que será removido
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            Celula tmp = i.prox;
            resp = tmp.jogador;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }
        // resp.imprimir(0);
        return resp;
    }

    public void mostrar() {
        // começa no topo, se i não apontar para NULL imprime
        // aponta para NULL quando for fim da pilha
        // i.prox do ultimo aponta para NULL
        int k = 0;
        for (Celula i = primeiro.prox; i != null; i = i.prox) {
            i.jogador.imprimir(k);
            k++;
        }
    }

    public void imprimirRemovido(Jogador jogador) {
        System.out.println("(R) " + jogador.getNome());
    }
}

public class TP03Q05 {

    public static final String inserirInicio = "II";
    public static final String inserir = "I*";
    public static final String inserirFim = "IF";
    public static final String removerInicio = "RI";
    public static final String remover = "R*";
    public static final String removerFim = "RF";

    public static void main(String[] args) throws NumberFormatException, Exception {
        Scanner sc = new Scanner(System.in);
        ArrayList<Jogador> jogadores = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader("/tmp/players.csv");
            BufferedReader arq = new BufferedReader(fileReader);
            arq.readLine();
            while (arq.ready()) {
                Jogador jogador = new Jogador();
                jogador.ler(arq.readLine());
                jogadores.add(jogador);
            }
            arq.close();
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getLocalizedMessage());
        }

        Lista lista = new Lista();
        String id = sc.nextLine();
        while (!id.equals("FIM")) {
            lista.inserirFim(jogadores.get(Integer.parseInt(id)));
            id = sc.nextLine();
        }
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String comando = sc.nextLine();
            String[] str = comando.split(" ");
            if (str[0].equals(inserirInicio)) {
                lista.inserirInicio(jogadores.get(Integer.parseInt(str[1])));
            } else if (str[0].equals(inserirFim)) {
                lista.inserirFim(jogadores.get(Integer.parseInt(str[1])));
            } else if (str[0].equals(inserir)) {
                int pos = Integer.parseInt(str[1]);
                lista.inserir(jogadores.get(Integer.parseInt(str[2])), pos);
            } else if (str[0].equals(removerInicio)) {
                Jogador jogador = lista.removerInicio();
                lista.imprimirRemovido(jogador);
            } else if (str[0].equals(removerFim)) {
                Jogador jogador = lista.removerFim();
                lista.imprimirRemovido(jogador);
            } else if (str[0].equals(remover)) {
                int pos = Integer.parseInt(str[1]);
                Jogador jogador = lista.remover(pos);
                lista.imprimirRemovido(jogador);
            }
        }

        lista.mostrar();

        sc.close();
    }
}
