import java.io.*;
import java.text.*;
import java.util.*;

// classe de execução
class TP05q06 {
    /**
     * Le ids dos games da entrada padrao ate achar FIM, procura id no csv,
     * faz o parse do game para objeto e salva no final de uma Lista.
     * Depois le nomes de jogos ate achar FIM e pesquisa eles na Lista de Games 
     */
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis(); // tempo de inicio
        FileWriter logFile = new FileWriter("matricula_hashRehash.txt");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        HashDiretoRehash hashGames = new HashDiretoRehash(21);
        String gameID = input.readLine(); 
        
        while(!isFIM(gameID)){
            int id = Integer.parseInt(gameID);
            
            Game game = new Game();
            game.readFromFileID("/tmp/games.csv", id);

            // salvar no Hash
            hashGames.inserir(game);

            gameID = input.readLine(); 
        }

        // TODO: pesquisa
        
        long end = System.currentTimeMillis(); // tempo de fim
        // calcula tempo de execucao
        float time = (end - start);
        float comp = hashGames.getComp();
        logFile.write("761400\t"+time+"\t"+comp+"\t");
        logFile.close();
    }

    // verifica se string é igual a "FIM"
    public static boolean isFIM(String str){
        boolean fim = false;

        if(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M')
            fim = true;
        
        return fim;
    } 
}

// classe HashDiretoRehash de Games
class HashDiretoRehash {
    private Game tabela[]; // tabela de hash 
    private int tamanho; // tamanho do hash
    private int comp;
 
    public HashDiretoRehash(){
        this(7);
    }
    public HashDiretoRehash(int tamanho){
        this.tamanho = tamanho;
        comp = 0;
       
        tabela = new Game[tamanho]; // cria um array de Game
        for(int i = 0; i < tamanho; i++){
            tabela[i] = null;
        }
    }
    
    public int getComp(){
        return this.comp;
    }

    // funcao hash
    public int hash(Game elemento){
       return elemento.valorASCIINome() % tamanho;
    }
    // funcao rehash
    public int rehash(Game elemento){
        return (elemento.valorASCIINome() % tamanho) % tamanho;
    }
    // pesquisa na tabela hash
    boolean pesquisar(Game elemento){
        comp = 0;
        boolean achou = false;
        
        int pos = hash(elemento);
     
        comp++;
        if(tabela[pos] == elemento) {
            achou = true;
        } else if (tabela[pos] != null) {
            pos = rehash(elemento);
            
            comp++;
            if (tabela[pos] == elemento) {
                achou = true;
            }
        }

        return achou;
    }
    // insere numa posicao da tabela hash
    public boolean inserir(Game elemento){
        boolean sucesso = false;

        if(elemento != null){
            // procura posicao do elemento pela funcao hash
            int pos = hash(elemento);
            
            if(tabela[pos] == null){ // se pos estiver vazia, insere
                tabela[pos] = elemento;
                sucesso = true;
            } else{ // conflito (elemento ja inserido na pos)
                // usa funcao de rehash p/achar nova pos
                pos = rehash(elemento);
                
                if(tabela[pos] == null){ // se pos estiver vazia, insere
                    tabela[pos] = elemento;
                    sucesso = true;
                }
            }
        }

        return sucesso;
    }
} 

// classe Lista de Games
class Lista {
    // atributos
    private Celula primeiro;
	private Celula ultimo;
    private int comp;
    
    /**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca)
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
        comp = 0;
	}
    
    public int getComp(){
        return this.comp;
    }

    /**
	 * Procura um game na Lista e retorna se ele existe
	 * @param x Game a pesquisar
	 * @return <code>true</code> se o game existir,
	 * <code>false</code> caso contrario
	 */
	public boolean pesquisar(Game x) {
		comp = 0;
        boolean achou = false;
        
        Celula i = primeiro.prox;
        while( i != null && !achou ) {
            comp++;
            if( i.elemento.equals(x) )
                achou = true;
            
            i = i.prox;
		}
		
        return achou;
	}

    /**
	 * Procura um game na Lista e retorna sua posicao
	 * @param x Game a pesquisar
	 * @return posicao do game (-1 se nao achar)
	 */
	public int pesquisarPos(Game x) throws Exception{
		comp = 0;
        boolean achou = false;
        int pos = 0;
        
        Celula i = primeiro.prox;
        while( i != null && !achou ) {
            comp++;
            if( i.elemento.equals(x) )
                achou = true;
            
            i = i.prox;
            pos++;
		}

        if(!achou)
            pos = -1;

        return pos;
	}
   
    /**
	 * Insere um elemento na primeira posicao da lista.
     * @param x Game elemento a ser inserido.
	 */
	public void inserirInicio(Game x) {
		Celula tmp = new Celula(x);
        
        tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		
        if(primeiro == ultimo) {                 
			ultimo = tmp;
		}
        
        tmp = null;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
     * @param x Game elemento a ser inserido.
	 */
	public void inserirFim(Game x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
     * @return resp Game elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Game removerInicio() throws Exception {
		if(primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

        Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Game resp = primeiro.elemento;
        
        tmp.prox = tmp = null;

		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
     * @return resp Game elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Game removerFim() throws Exception {
		if(primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

		// Caminhar ate a penultima celula:
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        Game resp = ultimo.elemento; 
        ultimo = i; 

        i = ultimo.prox = null;
      
		return resp;
	}

	/**
     * Insere um elemento em uma posicao especifica considerando que o 
     * primeiro elemento valido esta na posicao 0.
     * @param x Game elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
    public void inserir(Game x, int pos) throws Exception {
        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
            throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if(pos == 0){
            inserirInicio(x);
        } else if(pos == tamanho){
            inserirFim(x);
        } else{
            // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            
            tmp = i = null;
        }
    }

	/**
     * Remove um elemento de uma posicao especifica da lista
     * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
     * @return resp Game elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Game remover(int pos) throws Exception {
        Game resp;
        int tamanho = tamanho();

        if (primeiro == ultimo){
            throw new Exception("Erro ao remover (vazia)!");
        } else if(pos < 0 || pos >= tamanho){
            throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0){
            resp = removerInicio();
        } else if (pos == tamanho - 1){
            resp = removerFim();
        } else {
            // Caminhar ate a posicao anterior a remocao
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
        
            tmp.prox = i = tmp = null;
        }

        return resp;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
        int j = 0;

        for(Celula i = primeiro.prox; i != null; i = i.prox, j++) {
            System.out.print("["+j+"] ");
			i.elemento.print();
		}
	}

    /**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
    public int tamanho() {
        int tamanho = 0; 
        
        for(Celula i = primeiro; i != ultimo; i = i.prox){
            tamanho++;
        }
        
        return tamanho;
    }
}

// classe Celula de Game
class Celula {
	public Game elemento; // Elemento inserido na célula
	public Celula prox; // Aponta a celula prox

	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this( new Game() );
	}

	/**
	 * Construtor da classe.
	 * @param elemento Game inserido na celula.
	 */
	public Celula(Game elemento) {
        this.elemento = elemento;
        this.prox = null;
	}
}

// classe Game
class Game{
    // atributos
    private int app_id, age, dlcs, avg_pt;
    private String name, owners, website, developers;
    private Date release_date;
    private float price, upvotes;
    private String[] languages, genres;
    private boolean windows, mac, linux;
    
    // construtores
    public Game() { }
    public Game(int app_id, String name, Date release_date, String owners, int age, 
                float price, int dlcs, String[] languages, String website, boolean 
                windows, boolean mac, boolean linux, float upvotes, int avg_pt, 
                String developers, String[] genres) {
        
        this.app_id = app_id;
        this.name = name;
        this.release_date = release_date;
        this.owners = owners;
        this.age = age;
        this.price = price;
        this.dlcs = dlcs;
        this.languages = languages;
        this.website = website;
        this.windows = windows;
        this.mac = mac;
        this.linux = linux;
        this.upvotes = upvotes;
        this.avg_pt = avg_pt;
        this.developers = developers;
        this.genres = genres;
    }

    // gets e sets
    public int getApp_id() {
        return app_id;
    }
    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getRelease_date() {
        return release_date;
    }
    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }
    public String getOwners() {
        return owners;
    }
    public void setOwners(String owners) {
        this.owners = owners;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getDlcs() {
        return dlcs;
    }
    public void setDlcs(int dlcs) {
        this.dlcs = dlcs;
    }
    public String[] getLanguages() {
        return languages;
    }
    public void setLanguages(String[] languages) {
        this.languages = languages;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public boolean getWindows() {
        return windows;
    }
    public void setWindows(boolean windows) {
        this.windows = windows;
    }
    public boolean getMac() {
        return mac;
    }
    public void setMac(boolean mac) {
        this.mac = mac;
    }
    public boolean getLinux() {
        return linux;
    }
    public void setLinux(boolean linux) {
        this.linux = linux;
    }
    public float getUpvotes() {
        return upvotes;
    }
    public void setUpvotes(float upvotes) {
        this.upvotes = upvotes;
    }
    public int getAvg_pt() {
        return avg_pt;
    }
    public void setAvg_pt(int avg_pt) {
        this.avg_pt = avg_pt;
    }
    public String getDevelopers() {
        return developers;
    }
    public void setDevelopers(String developers) {
        this.developers = developers;
    }
    public String[] getGenres() {
        return genres;
    }
    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    // clone (sobreescreve função da superclasse Object)
    @Override
    public Game clone() {
        Game novo = new Game(app_id, name, release_date, owners, age, price, dlcs, 
                     languages, website, windows, mac, linux, upvotes, avg_pt,
                     developers, genres);

        return novo;
    }
    
    // pega valor ASCII do nome do Game
    public int valorASCIINome(){
        int val = 0;

        for(int i = 0; i < this.name.length(); i++){
            val += (int) name.charAt(i);
        }

        return val;
    }

    // imprimir
    public void print(){
        // formata a data
        SimpleDateFormat formatter = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);
        String date = formatter.format(release_date);

        // imprime atributos app_id, name, date, owners, age
        System.out.print(app_id + " " + name + " " + date + " " + owners + " " + 
                         age + " ");
        
        // imprime atributo price com duas casas decimais                          
        System.out.printf(Locale.ENGLISH, "%.2f", price);
        
        // imprime atributos dlcs, languages[] 
        System.out.print(" " + dlcs + " " + toStringArray(languages) + " ");
        
        // imprime atributo website, se for vazio imprime "null"
        if(website == "") System.out.print("null");
        else System.out.print(website);
        
        // imprime atributos windows, mac, linux, upvotes arredondado
        System.out.print(" " + windows + " " + mac + " " + linux  + " " + 
                         ((int)Math.round(upvotes)) + "% "); 
       
        // imprime atributo avg_pt em h e min, se for 0 imprime "null"
        if(avg_pt == 0){
            System.out.print("null ");
        } else {
            if((avg_pt / 60) != 0) System.out.print((avg_pt / 60)+"h ");
            if((avg_pt % 60) != 0) System.out.print((avg_pt % 60) +"m ");
        } 

        // imprime atributo website, se for vazio imprime "null"
        if(developers == "") System.out.print("null");
        else System.out.print(developers);

        // imprime atributo genres[]
        System.out.print(" " + toStringArray(genres));
        
        // nova linha
        System.out.println("");
    }

    // tranforma array de strings em string
    public String toStringArray(String[] array){
        String str = "[";
        int i = 0;
        
        while( array[i] != null ){
            str += array[i];

            if(array[i+1] != null)
                str += ", ";

            i++;
        }

        str += "]";

        return str;
    }

    // ler do arquivo e fazer parse de um jogo de ID específico 
    public void readFromFileID(String file, int id) throws Exception{
        BufferedReader input = new BufferedReader(new FileReader(file));
        String game = input.readLine();
        int arqID;
        boolean achou = false;
        
        do{
            // pega o ID da string game lida no arquivo 
            arqID = saveID(game);

            if(id == arqID){ // compara id do arquivo com id procurado (parametro)
                parseGame(game);
              
                achou = true;
            }
        } while( ((game = input.readLine()) != null) && (!achou) );
           
        input.close();
    }

    // faz o parse somente do ID da string game
    public int saveID(String game){
        String gameID = "";
        int id;
        int i = 0;

        while( game.charAt(i) != ',' ){
            gameID += game.charAt(i);

            i++;
        }

        id = Integer.parseInt(gameID);

        return id;
    }

    // faz o parse da string, separando atributos lidos
    public void parseGame(String game) throws Exception{
        String[] atr = new String[17]; 
        int first;
        int last;

        // cria array separando atributos 
        for(int i=0; i<atr.length; i++){
            first = 0;
            
            if(game.length() == 0){ 
                atr[i] = game;
            } else{
                if(i == 16 && game.charAt(first) != '"'){
                    last = game.length();
                } else{
                    last = findNextSep(game);
                }
            
                if(game.charAt(first) == '"'){
                    atr[i] = cutString(game, ++first, --last);
                    
                    first = last + 2;
                } else{
                    atr[i] = cutString(game, first, last);
    
                    first = last + 1;
                }
                
                game = cutString(game, first, game.length());
            }
        }

        // salva atributos
        getAttributes(atr);
    }

    // salva atributos no objeto
    public void getAttributes(String[] atr) throws Exception {
        // inteiros
        this.app_id = Integer.parseInt(atr[0]);
        this.age = Integer.parseInt(atr[4]);
        this.dlcs = Integer.parseInt(atr[6]);
        this.avg_pt = Integer.parseInt(atr[14]);
        
        // reais
        this.price = Float.parseFloat(atr[5]);
        this.upvotes = 100 * ( (float) Integer.parseInt(atr[12]) / ( Integer.parseInt(atr[12]) + Integer.parseInt(atr[13]) ) );
        
        // booleanos
        this.windows = Boolean.parseBoolean(atr[9]);
        this.mac = Boolean.parseBoolean(atr[10]);
        this.linux = Boolean.parseBoolean(atr[11]);
        
        // strings
        this.name = atr[1];
        this.owners = atr[3];
        this.website = atr[8];
        this.developers = atr[15];
        
        // arrays de strings
        this.languages = createArrayStrings(atr[7]);
        this.genres = createArrayStrings(atr[16]);
        
        // date
        SimpleDateFormat formatter;
        if(atr[2].charAt(6) == ',')
            formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        else if(atr[2].charAt(5) == ',')
            formatter = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
        else
            formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);

        this.release_date = formatter.parse(atr[2]);
    }

    // transforma string em array de strings
    public static String[] createArrayStrings(String str) {
        String[] array = new String[50];
        int i = 0, j = 0;
        array[0] = "";

        if(str.length() > 0){
            if(str.charAt(i) == '['){
                i++;

                while( (i<str.length()) && (str.charAt(i) != ']') ){
                    
                    if(str.charAt(i) == 39){ // char 39 = '
                        i++;
                        
                        if(str.charAt(i) == ','){
                            j++;
                            i+=2;
                            
                            array[j] = "";
                        }
                    } else{ 
                        array[j] += str.charAt(i);

                        i++;
                    }

                }
            } 
            else{
                while(i < str.length()){
                    array[j] += str.charAt(i);

                    i++;

                    if( (i != str.length()) && (str.charAt(i) == ',') ){
                        j++;
                        i++;

                        array[j] = "";
                    }
                }
            }
        }

        return array;
    }

    // acha separador de atributos da string do .csv
    public static int findNextSep(String strCsv){
        int index = -1;
        boolean sep = false;

        if(strCsv.charAt(0) == '"'){
            index += 2;

            while(strCsv.charAt(index) != '"'){
                index++;
            }
            
            index++;
            
            if((index != strCsv.length()) && (strCsv.charAt(index) == ','))
                    sep = true;

        } else{
            while(index < strCsv.length() && !sep){
                index++;
    
                if(strCsv.charAt(index) == ',') 
                    sep = true;
            }
        }
            
        return index;
    }

    // cria uma substring
    public static String cutString(String str, int first, int last){
        String res = "";

        for(int i=first; i<last; i++){
            res += str.charAt(i);
        }
        
        return res;
    }
}
