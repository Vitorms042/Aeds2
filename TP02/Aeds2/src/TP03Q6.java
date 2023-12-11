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

class Stack {

    private Celula top;

    public Stack() {
        top = null;
    }

    public void insert(Jogador x) {
        Celula tmp = new Celula(x);
        tmp.prox = top;
        top = tmp;
        tmp = null;
    }

    public Jogador remove() throws Exception {
        if (top == null) {
            throw new Exception("Can't remove from empty Stack!");
        }
        Jogador res = top.jogador;
        Celula tmp = top;
        top = top.prox;
        tmp.prox = null;
        tmp = null;
        return res;
    }

    public boolean isEmpty() {
        if (top == null) {
            return true;
        }
        return false;
    }

    public void show() {
        Stack reversed = new Stack();
    while (!isEmpty()) {
        try {
            Jogador jogador = remove();
            reversed.insert(jogador);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // Reconstroi a pilha original
    int k = 0;
    while (!reversed.isEmpty()) {
        try {
            Jogador a = reversed.remove();
            a.imprimir(k);
            k++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    }

    public void showRemoved(Jogador jogador) {
        System.out.println("(R) " + jogador.getNome());
    }

}

public class TP03Q6 {

    public static final String inserir = "I";
    public static final String remover = "R";

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

        Stack stack = new Stack();
        String id = sc.nextLine();
        while (!id.equals("FIM")) {
            stack.insert(jogadores.get(Integer.parseInt(id)));
            id = sc.nextLine();
        }
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            String comando = sc.nextLine();
            String[] str = comando.split(" ");
            if (str[0].equals(inserir)) {
                stack.insert(jogadores.get(Integer.parseInt(str[1])));
            } else if (str[0].equals(remover)) {
                Jogador jogador = stack.remove();
                stack.showRemoved(jogador);

            }
        }

        stack.show();

        sc.close();
    }
}
