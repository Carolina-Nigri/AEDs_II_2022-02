import java.util.*;

// classe de execução
class TP05q09{
    public static void main(String[] args) throws Exception{
        Scanner input = new Scanner(System.in);
		String comando;     

        ABP arvore = new ABP();

        while(input.hasNext()){
            comando = input.nextLine();

            if(comando.contains("I ")){ 
                arvore.inserir( comando.charAt(2) );
            } else if(comando.contains("IN")){
                arvore.percursoInfixo();
            } else if(comando.contains("PR")){
                arvore.percursoPrefixo();
            } else if(comando.contains("PO")){
                arvore.percursoPosfixo();
            } else if(comando.contains("P ")){
                if(arvore.pesquisar( comando.charAt(2) ))
                    System.out.println(comando.charAt(2)+" existe");   
                else
                    System.out.println("nao existe");
            }
        }

        input.close();
    }
}

// classe No(elemento = char)
class No{
    // atributos
    public char elemento;
    public No dir, esq;

    // construtores
    public No(char elemento){
        this(elemento, null, null);
    }
    public No(char elemento, No esq, No dir){
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
    public void inserir(char x) throws Exception{
	    raiz = inserir(x, raiz);
	}
    /**
     * insere elemento na arvore, procurando posicao de insercao recursivamente
     * @param x elemento a ser inserido
     * @param i No em analise
     * @return i No em analise
     * @throws Exception se elemento ja estiver na arvore
     */
    private No inserir(char x, No i) throws Exception{
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
    /**
     * chama metodo recursivo para pesquisar elemento na arvore 
     * @param x elemento a ser pesquisado
     * @return true se achar, false se nao
     */
    public boolean pesquisar(char x){
		return pesquisar(x, raiz);
	}
	/**
     * pesquisa elemento na arvore recursivamente
     * @param x elemento a ser pesquisado
     * @param i No em analise
     * @return true se achar, false se nao
     */
	private boolean pesquisar(int x, No i){
        boolean achou;
        
        if(i == null){ // chegou ao ultimo no possivel e nao encontrou nada
            achou = false;
        } else if(x == i.elemento){ // achou elemento
            achou = true;
        } else if(x < i.elemento){ // pesquisa a esquerda
            achou = pesquisar(x, i.esq);
        } else{ // pesquisa a direita
            achou = pesquisar(x, i.dir);
        }

        return achou;
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