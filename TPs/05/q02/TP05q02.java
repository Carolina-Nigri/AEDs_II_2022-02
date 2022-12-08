import java.io.*;
import java.text.*;
import java.util.*;

// classe de execução
class TP05q02 {    
    /**
     * Le ids dos games da entrada padrao ate achar FIM, procura id no csv,
     * faz o parse do game para objeto e salva em uma ArvoreDeArvore.
     * Depois le qtd de insercoes e remocoes que serao feitas, le palavras de comando
     * e insere/remove jogos da ArvoreDeArvore de acordo com parametros da entrada,
     * mostrando os removidos. Por fim, pesquisa elementos na arvore, mostrando 
     * caminhos de pesquisa.
     */
    public static void main(String[] args) throws Exception {
        String file = "/tmp/games.csv";
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        ArvoreDeArvore arvoreGames = new ArvoreDeArvore();
        String gameID = input.readLine(); 
        int id;
        
        while(!isFIM(gameID)){
            id = Integer.parseInt(gameID);
            
            Game game = new Game();
            game.readFromFileID(file, id);

            // salvar na ArvoreDeArvore
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
        FileWriter logFile = new FileWriter("matricula_arvoreArvore.txt");
        
        // realiza pesquisas e mostra caminhos e resultado
        name = input.readLine();

        while(!isFIM(name)){
            System.out.println(name);

            if(arvoreGames.pesquisar(name))
                System.out.println(" SIM");
            else
                System.out.println(" NAO");

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

// classe NoExt
class NoExt {
    public char letra; // primeira letra do nome do game
    public NoExt esq, dir;  // filhos da esq e dir
    public NoInt raizInt; // raiz da arvore interna

    // construtores
    public NoExt(char letra, NoInt raizInt){
        this(letra, null, null, raizInt);
    }
    public NoExt(char letra){
        this(letra, null, null, null);
    }
    public NoExt(char letra, NoExt esq, NoExt dir, NoInt raizInt){
        this.letra = letra;
        this.esq = esq;
        this.dir = dir;
        this.raizInt = raizInt;
    }
}

// classe NoInt
class NoInt {
    public Game elemento; // game do nó
    public NoInt esq, dir;  // filhos da esq e dir

    // construtores
    public NoInt(Game elemento){
        this(elemento, null, null);
    }
    public NoInt(Game elemento, NoInt esq, NoInt dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

// classe ArvoreDeArvore
class ArvoreDeArvore {
    private NoExt raizExt; // nó raiz da arvore externa
    private int comp;

    // construtor
    public ArvoreDeArvore() throws Exception{
		raizExt = null;
        comp = 0;

        // insere as 26 letras nos nós da árvore exteran
        preencheArvoreExt();
	}

    // gets
    public NoExt getRaizExt(){
        return raizExt;
    }
    public int getComp(){
        return comp;
    }
    
    // metodos
    /**
     * Preenche arvore externa, inserindo as 26 letras do alfabeto em 
     * ordem determinada
     * @throws Exception se letra ja tiver sido inserida
     */
    private void preencheArvoreExt() throws Exception{
        char[] letras = {'D','R','Z','X','V','B','F','P','U','I','G','E','J',
                         'L','H','T','A','W','S','O','M','N','K','C','Y','Q'};

        for(int i = 0; i < letras.length; i++){
            this.inserirLetra(letras[i]);
        }
    }
    /**
     * Chama função recursiva que insere uma letra na arvore binaria
     * @param letra char a ser inserido
     * @throws Exception se a letra ja estiver na arvore
     */
    public void inserirLetra(char letra) throws Exception{
        this.raizExt = inserirLetra(letra, this.raizExt);
    }
    /**
     * Insere letra na arvore, procurando posicao de insercao recursivamente
     * @param letra char a ser inserido
     * @param i NoExt em analise
     * @return NoExt em analise
     * @throws Exception se a letra ja estiver na arvore
     */
    private NoExt inserirLetra(char letra, NoExt i) throws Exception{
        if(i == null){ // chegou à posição certa de inserção da letra na árvore
            i = new NoExt(letra);
        } else if(letra < i.letra){ // letra tem que estar à esquerda (menor)
            // percorre esquerda da árvore recursivamente
            i.esq = inserirLetra(letra, i.esq); 
        } else if(letra > i.letra){ // letra tem que estar à direita (maior)
            // percorre direita da árvore recursivamente
            i.dir = inserirLetra(letra, i.dir);
        } else{
            throw new Exception("Erro ao inserir! Letra ja existente");
        }

		return i;
	}
    /**
     * Insere game na arvore de arvore, pesquisando primeiro na arvore externa o
     * nó que contém a primeira letra do nome do game para depois inserir o game
     * na arvore interna
     * @param game Game a ser inserido
     * @throws Exception se nao encontrar letra do nome ou se game ja estiver inserido
     */
    public void inserir(Game game) throws Exception{
        // pesquisa nó externo que contém primeira letra do nome do game 
        NoExt pos = pesquisarLetra(game.getName().charAt(0), this.raizExt);
        
        // insere game na arvore interna
        if(pos != null){
            pos.raizInt = inserir(game, pos.raizInt);
        } else{
            throw new Exception("Erro ao inserir Game! Primeira letra do nome nao encontrada");
        }
    }
    /** 
     * Insere game na arvore interna, procurando posicao de insercao recursivamente
     * @param game Game a ser inserido
     * @param i NoInt em analise
     * @return NoInt em analise
     * @throws Exception se o game ja estiver na arvore
     */
    private NoInt inserir(Game game, NoInt i) throws Exception{
        if(i == null){ // posicao de insercao
            i = new NoInt(game);
        } else if( game.getName().compareTo(i.elemento.getName()) > 0 ){
            // nome do game a inserir vem antes do elemento do nó i => direita
            i.dir = inserir(game, i.dir);
        } else if( game.getName().compareTo(i.elemento.getName()) < 0 ){
            // nome do game a inserir vem depois do elemento do nó i => esquerda
            i.esq = inserir(game, i.esq);
        } else{ // nome do game a inserir eh igual ao elemento do nó i => erro
            throw new Exception("Erro ao inserir! Game ja existente");
        }

        return i;
    }
    /**
     * Chama função recursiva que remove game da arvore interna
     * @param name nome do Game a ser removido
     * @throws Exception se nao encontrar letra do nome ou se percorrer arvore interna
     * e não encontrar game
     */
    public void remover(String name) throws Exception{
        // pesquisa nó externo que contém primeira letra do nome do game 
        NoExt pos = pesquisarLetra(name.charAt(0), this.raizExt);
        
        // remove game na arvore interna
        if(pos != null){
            pos.raizInt = remover(name, pos.raizInt);
        } else{
            throw new Exception("Erro ao remover Game! Primeira letra do nome nao encontrada");
        }
    }
    /**
     * Remove game da arvore interna recursivamente
     * @param name nome do Game a ser removido
     * @param i NoInt em analise
     * @return NoInt em analise
     * @throws Exception se percorrer parte da arvore e não encontrar game
     */
    private NoInt remover(String name, NoInt i) throws Exception{
        if(i == null){ // nao encontrou elemento
			throw new Exception("Erro ao remover! Game nao encontrado");
		} else if( name.compareTo(i.elemento.getName()) > 0 ){
			// nome do game a remover vem antes do elemento do nó i => direita
            i.dir = remover(name, i.dir);
		} else if( name.compareTo(i.elemento.getName()) < 0 ){
			// nome do game a remover vem depois do elemento do nó i => esquerda
            i.esq = remover(name, i.esq);
		} else if(i.dir == null){ // Sem no a direita
			i = i.esq;	
		} else if(i.esq == null){ // Sem no a esquerda
			i = i.dir;
		} else{ // No a esquerda e no a direita
			i.esq = maiorEsq(i, i.esq);
		}

		return i;
    }
    /**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda
	 * @param i NoInt que teve o elemento removido
	 * @param j NoInt da subarvore esquerda
	 * @return NoInt em analise, alterado ou nao
	 */
    private NoInt maiorEsq(NoInt i, NoInt j){
		if(j.dir == null){ // encontrou o maximo da subarvore esquerda
			i.elemento = j.elemento; // troca i com j
			j = j.esq; // troca j com j.esq
    	} else{ // existe no a direita
			j.dir = maiorEsq(i, j.dir); // caminha para direita
		}
        
		return j;
	}
    /**
     * Pesquisa letra na arvore externa, retornando nó em que está 
     * @param letra a ser pesquisada
     * @param i NoExt em analise
     * @return NoExt em analise 
     */
    public NoExt pesquisarLetra(char letra, NoExt i){
        NoExt pos = null;

        if(i != null){
            if(i.letra == letra){ // achou 
                pos = i;
            } else if(letra < i.letra){ // pesquisa à esquerda
                pos = pesquisarLetra(letra, i.esq);
            } else if(letra > i.letra){ // pesquisa à direita
                pos = pesquisarLetra(letra, i.dir);
            }
        }

        return pos;
    }
    /**
     * Realiza pesquisa na arvore de arvore
     * @param name nome do Game a ser pesquisado
     * @return true se achar nome ou false caso contrario
     */
    public boolean pesquisar(String name) throws Exception{
        comp = 0;
        System.out.print("raiz ");
        return mostrar(name, raizExt);
    }
    /**
     * Faz caminhamento na arvore externa e pesquisa na interna
     * @param name String nome do game a ser pesquisado
     * @param i NoExt em analise
     * @return true se achar, false se nao
     */
    public boolean mostrar(String name, NoExt i){
        boolean pesq = false;
        
        if(i != null){
            pesq = pesquisar(name, i.raizInt);
            
            if(!pesq){
                System.out.print(" ESQ ");
                pesq = mostrar(name, i.esq);
            } if(!pesq){
                System.out.print(" DIR ");
                pesq = mostrar(name, i.dir);
            }
        }

        return pesq;
    }
    /**
     * Realiza pesquisa na arvore interna recursivamente
     * @param name nome do Game a ser pesquisado
     * @param i NoInt em analise
     * @return true se achar nome ou false caso contrario
     */
    private boolean pesquisar(String name, NoInt i){
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
