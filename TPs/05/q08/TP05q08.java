import java.io.*;

// classe de execução
class TP05q08{
    public static void main(String[] args) throws Exception{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        String[] lin = input.readLine().split(" ");
        int cases = Integer.parseInt(lin[0]);
        
        for(int i = 1; i <= cases; i++){
            System.out.println("Case "+i+":");

            lin = input.readLine().split(" "); 
            int n = Integer.parseInt(lin[0]);

            lin = input.readLine().split(" ");
            ABP arvore = new ABP();
            
            for(int j = 0; j < n; j++){
                arvore.inserir( Integer.parseInt(lin[j]) );
            }

            System.out.print("Pre.: ");
            arvore.percursoPrefixo();

            System.out.print("In..: ");
            arvore.percursoInfixo();

            System.out.print("Post: ");
            arvore.percursoPosfixo();

            if(i != cases)
                System.out.println("");
        }
    }
}

// classe No(elemento = int)
class No{
    // atributos
    public int elemento;
    public No dir, esq;

    // construtores
    public No(int elemento){
        this(elemento, null, null);
    }
    public No(int elemento, No esq, No dir){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

// classe ABP(Arvore Binaria de Pesquisa)
class ABP{
    // atributos
    private No raiz;

    // construtor
    public ABP(){
		raiz = null;
	}

    // metodos
    /**
     * chama metodo recursivo para inserir elemento na arvore
     * @param x elemento a ser inserido
     * @throws Exception se elemento ja estiver na arvore
     */
    public void inserir(int x) throws Exception{
	    raiz = inserir(x, raiz);
	}
    /**
     * insere elemento na arvore, procurando posicao de insercao recursivamente
     * @param x elemento a ser inserido
     * @param i No em analise
     * @return i No em analise
     * @throws Exception se elemento ja estiver na arvore
     */
    private No inserir(int x, No i) throws Exception{
        if(i == null){ // pos de insercao
            i = new No(x);
        } else if(x < i.elemento){ // pos de insercao eh a esquerda
            i.esq = inserir(x, i.esq);
        } else if(x > i.elemento){ // pos de insercao eh a direita
            i.dir = inserir(x, i.dir);
        } else{
            throw new Exception("Erro ao inserir! Elemento ja inserido");
        }

        return i;
	}
    // chama metodo recursivo para fazer percurso infixo na arvore 
    public void percursoInfixo(){
		percursoInfixo(raiz);
        System.out.println("");
	}
    /**
     * faz percurso infixo na arvore recursivamente
     * @param i No atual
     */
    private void percursoInfixo(No i){
		if(i != null){
			percursoInfixo(i.esq); // percorre esquerda
			System.out.print(i.elemento + " "); // imprime no
			percursoInfixo(i.dir); // percorre direita
		}
	}
    // chama metodo recursivo para fazer percurso prefixo na arvore 
    public void percursoPrefixo(){
		percursoPrefixo(raiz);
        System.out.println("");
	}
    /**
     * faz percurso prefixo na arvore recursivamente
     * @param i No atual
     */
    private void percursoPrefixo(No i){
		if(i != null){
            System.out.print(i.elemento + " "); // imprime no
			percursoPrefixo(i.esq); // percorre esquerda
			percursoPrefixo(i.dir); // percorre direita
		}
	}
    // chama metodo recursivo para fazer percurso posfixo na arvore 
    public void percursoPosfixo(){
		percursoPosfixo(raiz);
        System.out.println("");
	}
    /**
     * faz percurso posfixo na arvore recursivamente
     * @param i No atual
     */
    private void percursoPosfixo(No i){
		if(i != null){
            percursoPosfixo(i.esq); // percorre esquerda
			percursoPosfixo(i.dir); // percorre direita
            System.out.print(i.elemento + " "); // imprime no
		}
	}
}