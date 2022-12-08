import java.io.*;
import java.text.*;
import java.util.*;

// classe de execução
class TP05q04{    
    /**
     * Le ids dos games da entrada padrao ate achar FIM, procura id no csv,
     * faz o parse do game para objeto e salva em uma RedBlack.
     * Depois le qtd de insercoes e remocoes que serao feitas, le palavras de comando
     * e insere jogos da RedBlack de acordo com parametros da entrada. 
     * Por fim, pesquisa elementos na arvore, mostrando caminhos de pesquisa.
     */
    public static void main(String[] args) throws Exception{
        String file = "/tmp/games.csv";
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        RedBlack arvoreGames = new RedBlack();
        String gameID = input.readLine(); 
        int id;
        
        while(!isFIM(gameID)){
            id = Integer.parseInt(gameID);
            
            Game game = new Game();
            game.readFromFileID(file, id);

            // salvar na RedBlack
            arvoreGames.inserir(game);

            gameID = input.readLine(); 
        }

        // qtd de objetos a serem inseridos/removidos
        int qtd = Integer.parseInt(input.readLine());
        String comando, name;
        
        // le insercoes/remocoes e mostra nome do jogo quando remover
        for(int i = 0; i < qtd; i++){
            Game game = new Game();
            comando = input.readLine();
            
            // interpreta e realiza comandos
            if(comando.charAt(0) == 'I'){ // inserir
                id = Integer.parseInt( comando.substring(2, comando.length()) );
                
                game.readFromFileID(file, id);
                arvoreGames.inserir(game);
            } else if(comando.charAt(0) == 'R'){ // remover
                name = comando.substring(2, comando.length());
                
                // arvoreGames.remover(name);
            }
        }

        long start = System.currentTimeMillis(); // tempo de inicio
        FileWriter logFile = new FileWriter("matricula_avinegra.txt");
        
        // realiza pesquisas e mostra caminhos e resultado
        name = input.readLine();

        while(!isFIM(name)){
            System.out.println(name);

            if(arvoreGames.pesquisar(name))
                System.out.println("SIM");
            else
                System.out.println("NAO");

            name = input.readLine();
        }

        long end = System.currentTimeMillis(); // tempo de fim
        float time = (end - start); // calcula tempo de execucao
        int comp = arvoreGames.getComp();
        logFile.write("761400\t"+time+"\t"+comp);
        logFile.close();
    }

    // verifica se string é igual a "FIM"
    public static boolean isFIM(String str){
        return (str.equals("FIM"));
    }
}

// classe No de Game
class No{
    public Game elemento; // game do nó
    public No esq, dir;  // filhos da esq e dir
    public boolean cor; // cor do no (com ou sem) 

    // construtores
    public No(Game elemento){
        this(elemento, null, null, false);
    }
    public No(Game elemento, boolean cor){
        this(elemento, null, null, cor);
    }
    public No(Game elemento, No esq, No dir, boolean cor){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }
}

// classe RedBlack de Game
class RedBlack{
    private No raiz; // nó raiz da arvore binaria
    private int comp;

    // construtor
    public RedBlack(){
		raiz = null;
        comp = 0;
	}

    // gets
    public No getRaiz(){
        return raiz;
    }
    public int getComp(){
        return comp;
    }
    
    // metodos
    /**
     * Insere um game na arvore RB, se arvore ja tiver 3 ou mais elementos, chama
     * funcao recursiva
     * @param elemento Game a ser inserido
     * @throws Exception se o elemento ja estiver na arvore
     */
    public void inserir(Game elemento) throws Exception{
        if(raiz == null){ // arvore vazia
            raiz = new No(elemento); // cria raiz
        } else if(raiz.esq == null && raiz.dir == null){ // 1 elemento (raiz)
            if(elemento.getName().compareTo(raiz.elemento.getName()) < 0){
                raiz.esq = new No(elemento);
            } else{
                raiz.dir = new No(elemento);
            }
        } else if(raiz.esq == null){ // 2 elementos (raiz e dir)
            if(elemento.getName().compareTo(raiz.elemento.getName()) < 0){
                raiz.esq = new No(elemento);
            } else if(elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0){
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = elemento;
            } else{
                raiz.esq = new No(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;
        } else if(raiz.dir == null){ // 2 elementos (raiz e esq)
            if(elemento.getName().compareTo(raiz.elemento.getName()) > 0){
                raiz.dir = new No(elemento);
            } else if(elemento.getName().compareTo(raiz.esq.elemento.getName()) > 0){
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = elemento;
            } else{
                raiz.dir = new No(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }

            raiz.esq.cor = raiz.dir.cor = false;
        } else{ // 3 ou mais elementos 
            inserir(elemento, null, null, null, raiz);
        }

        raiz.cor = false;
    }
    /**
     * Insere game na arvore RB quando ja tem 3 ou mais elementos, procurando posicao
     * e ajustando cores/balanceamento
     * @param elemento Game a ser inserido
     * @param bisavo No bisavo do no em analise
     * @param avo No avo do no em analise
     * @param pai No pai do no em analise
     * @param i No em analise
     * @throws Exception se o elemento ja tiver sido inserido
     */
    private void inserir(Game elemento, No bisavo, No avo, No pai, No i) throws Exception{
        if(i == null){ // posicao de insercao
            if(elemento.getName().compareTo(pai.elemento.getName()) < 0){
                i = pai.esq = new No(elemento, true);
            } else{
                i = pai.dir = new No(elemento, true);
            }

            // balanceia se necessario
            if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
            }
        } else{ 
            // no do tipo 4: precisa fragmentar e reequilibrar a arvore
            if(     i.esq != null && i.dir != null && 
                i.esq.cor == true && i.dir.cor == true ){

                i.cor = true;
                i.esq.cor = i.dir.cor = false;

                if(i == raiz){
                    i.cor = false;
                } else if(pai.cor == true){
                    balancear(bisavo, avo, pai, i);
                }
            }

            // procura posicao de insercao
            if(elemento.getName().compareTo(i.elemento.getName()) < 0){
                inserir(elemento, avo, pai, i, i.esq);
            } else if(elemento.getName().compareTo(i.elemento.getName()) > 0){
                inserir(elemento, avo, pai, i, i.dir);
            } else{
                throw new Exception("Erro ao inserir! Elemento ja inserido");
            }
        }
    }
    /**
     * Chama função recursiva que realiza pesquisa na arvore
     * @param name nome do Game a ser pesquisado
     * @return true se achar nome ou false caso contrario
     */
    public boolean pesquisar(String name){
        comp = 0;
        System.out.print("raiz ");
        return pesquisar(name, raiz);
    }
    /**
     * Realiza pesquisa na arvore recursivamente
     * @param name nome do Game a ser pesquisado
     * @param i No em analise
     * @return true se achar nome ou false caso contrario
     */
    private boolean pesquisar(String name, No i){
        boolean achou = false;

        if(i == null){ // chegou ao ultimo no possivel e nao encontrou elemento
            achou = false;
        } else if( name.equals(i.elemento.getName()) ){ // encontrou nome do game procurado
            comp++;
            achou = true;
        } else if( name.compareTo(i.elemento.getName()) > 0 ){
            // nome do game a pesquisar vem antes do elemento do nó i => direita
            comp+=2;
            System.out.print("dir ");
            achou = pesquisar(name, i.dir);
        } else if( name.compareTo(i.elemento.getName()) < 0 ){
            // nome do game a pesquisar vem depois do elemento do nó i => esquerda
            comp+=3;
            System.out.print("esq ");
            achou = pesquisar(name, i.esq);
        }

        return achou;
    }
    /**
     * Balanceia arvore nas insercoes e remocoes, chamando funcoes de rotacao
     * dependendo dos valores de fator de balanceamento
     * @param no No em analise
     */
    private void balancear(No bisavo, No avo, No pai, No i){
        if(pai.cor == true){ // se pai tem cor, reequilibrar a arvore, rotacionando o avo
            // rotacionar a esquerda ou direita-esquerda
            if(pai.elemento.getName().compareTo(avo.elemento.getName()) > 0){ 
                if(i.elemento.getName().compareTo(pai.elemento.getName()) > 0 ){
                    avo = rotacionarEsq(avo);
                } else{
                    avo = rotacionarDirEsq(avo);
                }
            } else{ // rotacionar a direita ou esquerda-direita
                if(i.elemento.getName().compareTo(pai.elemento.getName()) < 0){
                    avo = rotacionarDir(avo);
                } else{
                    avo = rotacionarEsqDir(avo);
                }
            }

            if(bisavo == null){
                raiz = avo;
            } else if(avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0){
                bisavo.esq = avo;
            } else{
                bisavo.dir = avo;
            }

            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
	}
    /**
     * Rotaciona nos para a direita, trocando ponteiros necessarios
     * @param no No pivo do desbalanceamento
     * @return No substituido
     */
	private No rotacionarDir(No no){
        No noEsq = no.esq;
        No noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
	}
    /**
     * Rotaciona nos para a esquerda, trocando ponteiros necessarios
     * @param no No pivo do desbalanceamento
     * @return No substituido
     */
	private No rotacionarEsq(No no){
        No noDir = no.dir;
        No noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;

        return noDir;
	}
    /**
     * Faz duas rotacoes nos nós: primeiro pra direita, depois pra esquerda,
     * trocando ponteiros necessarios
     * @param no No pivo do desbalanceamento
     * @return No substituido
     */
    private No rotacionarDirEsq(No no){
        no.dir = rotacionarDir(no.dir);
        
        return rotacionarEsq(no);
    }
    /**
     * Faz duas rotacoes nos nós: primeiro pra esquerda, depois pra direita,
     * trocando ponteiros necessarios
     * @param no No pivo do desbalanceamento
     * @return No substituido
     */
    private No rotacionarEsqDir(No no){
        no.esq = rotacionarEsq(no.esq);
        
        return rotacionarDir(no);
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
    public Game(){ }
    public Game(int app_id, String name, Date release_date, String owners, int age, 
                float price, int dlcs, String[] languages, String website, boolean 
                windows, boolean mac, boolean linux, float upvotes, int avg_pt, 
                String developers, String[] genres){
        
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
    public int getApp_id(){
        return app_id;
    }
    public void setApp_id(int app_id){
        this.app_id = app_id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Date getRelease_date(){
        return release_date;
    }
    public void setRelease_date(Date release_date){
        this.release_date = release_date;
    }
    public String getOwners(){
        return owners;
    }
    public void setOwners(String owners){
        this.owners = owners;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public float getPrice(){
        return price;
    }
    public void setPrice(float price){
        this.price = price;
    }
    public int getDlcs(){
        return dlcs;
    }
    public void setDlcs(int dlcs){
        this.dlcs = dlcs;
    }
    public String[] getLanguages(){
        return languages;
    }
    public void setLanguages(String[] languages){
        this.languages = languages;
    }
    public String getWebsite(){
        return website;
    }
    public void setWebsite(String website){
        this.website = website;
    }
    public boolean getWindows(){
        return windows;
    }
    public void setWindows(boolean windows){
        this.windows = windows;
    }
    public boolean getMac(){
        return mac;
    }
    public void setMac(boolean mac){
        this.mac = mac;
    }
    public boolean getLinux(){
        return linux;
    }
    public void setLinux(boolean linux){
        this.linux = linux;
    }
    public float getUpvotes(){
        return upvotes;
    }
    public void setUpvotes(float upvotes){
        this.upvotes = upvotes;
    }
    public int getAvg_pt(){
        return avg_pt;
    }
    public void setAvg_pt(int avg_pt){
        this.avg_pt = avg_pt;
    }
    public String getDevelopers(){
        return developers;
    }
    public void setDevelopers(String developers){
        this.developers = developers;
    }
    public String[] getGenres(){
        return genres;
    }
    public void setGenres(String[] genres){
        this.genres = genres;
    }

    // clone (sobreescreve função da superclasse Object)
    @Override
    public Game clone(){
        Game novo = new Game(app_id, name, release_date, owners, age, price, dlcs, 
                     languages, website, windows, mac, linux, upvotes, avg_pt,
                     developers, genres);

        return novo;
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
        } else{
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
    public void getAttributes(String[] atr) throws Exception{
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
    public static String[] createArrayStrings(String str){
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
