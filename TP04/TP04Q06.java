
import java.io.File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Scanner;

class Arvore { //Arvore modelo Trie
    
    class Node {
        Node array[]; //array de Nodes
        char key;
        Node(char key) {
            this.key = key;
            this.array = new Node[60];
        }
        Node(){
            this.array = new Node[60];
        }
    }
    //atributos
    private Node root;
    Arvore() {
        this.root = new Node();  //raiz nao tem caractere
    }

       private boolean VerificarInsercao(String nome){ //metodo para ver se a palavra existe ou se é prefixo de algo
            boolean flag = false;
            Node tmp = this.root;
            for(int i = 0; i<nome.length() && !flag; i++){
                for(int j = 0; j<tmp.array.length && !flag ; j++){
                    if(tmp.array[j] == null){ // so retorna True caso a palavra faça algum redirecionamento
                        return true;
                    }
                    else if(nome.charAt(i) == tmp.array[j].key){
                        tmp = tmp.array[j]; //prosseguindo com a frase
                        break;
                    }
                }
            }
            return flag;
        }



    public void inserir(String nome) {
        if(!VerificarInsercao(nome)){
            return; // não quero um Else enorme aqui
        }
        for (int j = 0; j < this.root.array.length; j++) {
            if (this.root.array[j] == null) {
                this.root.array[j] = new Node(nome.charAt(0));
                inserir(this.root.array[j], nome, 1);
                break;
            }
            else if (nome.charAt(0) == this.root.array[j].key) {
                inserir(this.root.array[j], nome, 1);
                break;
            }
        }
    }
    private void inserir(Node node, String nome, int i) { // POR QUE KARALHOS EU FIZ POR RECURSAO? EU COMO MERDA POR ACASO? 
        if(i==nome.length()){ // não há mais letras para inserir
            return;
        }
        for (int j = 0; j < node.array.length; j++) {
                 if (node.array[j] == null) {
                    node.array[j] = new Node(nome.charAt(i)); //cria Node com a letra
                    inserir(node.array[j], nome, ++i);
                    break;
                }   
            
                else if (nome.charAt(i) == node.array[j].key) {
                    inserir(node.array[j], nome, ++i); //i incrementado quando a func  é chamada
                    break;
                }
            }
    }

    public void mostrar(){
        String buffer = "";
         for(int i = 0; i<this.root.array.length; i++){
            if (this.root.array[i]!= null) {
                 mostrar(buffer, this.root.array[i]);
            }
        }
    }
    private void mostrar(String buffer, Node tmp){
        buffer += tmp.key;
        if(tmp.array[0] == null){//é uma folha (palavra completa)
            System.out.println(buffer);
        }
        for(int i = 0; i<tmp.array.length; i++){
            if (tmp.array[i]== null) {
                break; //não há mais filhos
            }else{
                mostrar(buffer, tmp.array[i]);
            }
        }
    }
    public LinkedList<String> getAllNames(){
        LinkedList<String> list = new LinkedList<String>();
        String buffer = "";
        for(int i = 0; i<this.root.array.length; i++){
            if (this.root.array[i]==null) {
                 break;
            }else{
                getAllNames(this.root.array[i], buffer, list);
            }
        }
        return list;
    }

    private void getAllNames(Node temp, String buffer, LinkedList<String> list){
        buffer += temp.key;
        if(temp.array[0] == null){//é uma folha (palavra completa)
            list.add(buffer);
        }
        for(int i = 0; i<temp.array.length; i++){
            if (temp.array[i]== null) {
                break; //não há mais filhos
            }else{
                getAllNames(temp.array[i], buffer, list);
            }
        }

    }
    public boolean pesquisar(String nome){
        for(int i = 0; i<this.root.array.length; i++){
            if(this.root.array[i] == null){
                return false;
            }else if(this.root.array[i].key == nome.charAt(0)){
                return pesquisar(this.root.array[i], nome, 1);
            }
        }
        return false;

    }
    public boolean pesquisar(Node node,String nome, int i){
        if(i == nome.length()){
            return true;
        }
        for(int j = 0; j<node.array.length; j++){
            if(node.array[j] == null){
                return false;
            }else if(node.array[j].key == nome.charAt(i)){
                return pesquisar(node.array[j], nome, ++i);
            }
        }
        return false;
    }


 public static Arvore ConcatenarArvore(Arvore tree1, Arvore tree2) {
    LinkedList<String> list1 = tree1.getAllNames();
    LinkedList<String> list2 = tree2.getAllNames();

    Arvore tree3 = new Arvore();
    
    // Adiciona todos os elementos da primeira lista
    while (!list1.isEmpty()) {
        tree3.inserir(list1.removeFirst());
    }

    // Adiciona todos os elementos da segunda lista
    while (!list2.isEmpty()) {
        tree3.inserir(list2.removeFirst());
    }

    return tree3;
}
}

class TP04Q06 {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;
    public static int tamanho = 0;

    TP04Q06() {

    }

    TP04Q06(int id, String nome, int altura, int peso, String universidade, int anoNascimento, String cidadeNascimento,
            String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // getters
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    // setters
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

    // clone
    public TP04Q06 clone(TP04Q06 J) {
        TP04Q06 clonado = new TP04Q06();
        clonado.id = J.id;
        clonado.nome = J.nome;
        clonado.altura = J.altura;
        clonado.peso = J.peso;
        clonado.universidade = J.universidade;
        clonado.anoNascimento = J.anoNascimento;
        clonado.cidadeNascimento = J.cidadeNascimento;
        clonado.estadoNascimento = J.estadoNascimento;
        return clonado;

    }

    public static void imprimir(TP04Q06 J, int pos) {

        MyIO.println("[" + pos + "]" + " ## " + J.getNome() + " ## " + J.getAltura() + " ## " + J.getPeso() + " ## "
                + J.getAnoNascimento() + " ## " + J.getUniversidade() + " ## " + J.getCidadeNascimento() + " ## "
                + J.getEstadoNascimento() + " ##");
    }

    public TP04Q06 ler(TP04Q06 J) {
        int aux = MyIO.readInt();
        J.setId(aux);
        String aux2 = MyIO.readLine();
        J.setNome(aux2);
        aux = MyIO.readInt();
        J.setAltura(aux);
        aux = MyIO.readInt();
        J.setPeso(aux);
        aux2 = MyIO.readLine();
        J.setUniversidade(aux2);
        aux = MyIO.readInt();
        J.setAnoNascimento(aux);
        aux2 = MyIO.readLine();
        J.setCidadeNascimento(aux2);
        aux2 = MyIO.readLine();
        J.setEstadoNascimento(aux2);
        return J;
    }

    public static String[] handleString(String csv) {
        String novaString = "";
        for (int i = 0; i < csv.length(); i++) {
            if (csv.charAt(i) == ',' && i + 1 < csv.length() && csv.charAt(i + 1) == ',') {
                novaString += ",nao informado";

            } else if (csv.charAt(i) == ',' && i == csv.length() - 1) {
                novaString += ",nao informado";

            } else {
                novaString += csv.charAt(i);
            }
        }

        csv = novaString;

        return csv.split(",");
    }

    public static TP04Q06 getJogadorByID(int id, TP04Q06 array[]) {
        return array[id];
    }

    public static TP04Q06 getJogadorByName(String nome, TP04Q06 array[]) {
        TP04Q06 J = new TP04Q06();
        for (int i = 0; i < array.length; i++) {
            if (array[i].getNome().equals(nome)) {
                J = array[i];
                break;
            }
        }
        return J;
    }

    public static void main(String[] args) throws Exception {

        File arquivo = new File("/tmp/players.csv");
        TP04Q06 time[] = new TP04Q06[3923];

        try (RandomAccessFile raf = new RandomAccessFile(arquivo, "r")) {
            raf.readLine();

            String linha;
            int i = 0;
            
            while ((linha = raf.readLine()) != null) {
                String[] array = handleString(linha);

                TP04Q06 jogador = new TP04Q06();
                jogador.setId(Integer.parseInt(array[0]));
                jogador.setNome(array[1]);
                jogador.setAltura(Integer.parseInt(array[2]));
                jogador.setPeso(Integer.parseInt(array[3]));
                jogador.setUniversidade(array[4]);
                jogador.setAnoNascimento(Integer.parseInt(array[5]));
                jogador.setCidadeNascimento(array[6]);
                jogador.setEstadoNascimento(array[7]);

                time[i] = jogador;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Arvore tree1 = new Arvore();
        Arvore tree2 = new Arvore();

        while (true) {
            String entrada = MyIO.readLine();
            if (entrada.equals("FIM")) {
                break;
            } else {
                int id = Integer.parseInt(entrada);
                TP04Q06 J = getJogadorByID(id, time);
                tree1.inserir(J.getNome());
                MyIO.println(J.getId() + "\t" + J.getNome());
            }
        }

        while (true) {
            String entrada = MyIO.readLine();
            if (entrada.equals("FIM")) {
                break;
            } else {
                int id = Integer.parseInt(entrada);
                TP04Q06 J = getJogadorByID(id, time);
                tree2.inserir(J.getNome());
                MyIO.println(J.getId() + "\t" + J.getNome());
            }
        }

        
        Arvore tree3 = Arvore.ConcatenarArvore(tree1, tree2);
        
         while (true) {
         String entrada = MyIO.readLine();
         if (entrada.equals("FIM")) {
         break;
         }else{
            MyIO.print(entrada);
            if(tree3.pesquisar(entrada)){
                MyIO.print(" SIM\n");
            }else{
                MyIO.print(" NAO\n");
            }
         }
        
         }
    }
}
