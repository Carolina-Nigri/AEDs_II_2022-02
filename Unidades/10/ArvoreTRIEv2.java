/**
 * Classe ArvoreTRIEv2
 * => Implementacao de inserir permite insercao de palavras que sejam prefixo
 * de outra ja inserida
 */
class ArvoreTRIEv2 {
    /* Atributos */
    public NoTRIE raiz;

    /* Construtores */
    public ArvoreTRIEv2() {
        this.raiz = new NoTRIE();
    }

    /* Metodos */
    
    /** Pesquisa **/
    /**
     * Retorna chamada do metodo privado recursivo que efetivamente realiza pesquisa
     * @param str String a ser pesquisada
     * @return true se encontrar string, false se nao encontrar
     * @throws Exception caso pesquisa dê erro 
     */
    public boolean pesquisar(String str) throws Exception {
        return pesquisar(str, this.raiz, 0);
    }
    /**
     * Realiza pesquisa na TRIE a partir da raiz, procurando caractere i da string no 
     * nó filho do no atual (char estara no nó da posicao equivalente ao seu valor 
     * ASCII). Quando chega ao ultimo char, verifica se o nó filho é fim da cadeia
     * @param str String a ser pesquisada
     * @param no NoTRIE atual (pai do nó que pode conter char pesquisado)
     * @param i int Posicao da string (char pesquisado no momento)
     * @return true se encontrar string, false se nao encontrar
     * @throws Exception caso pesquisa dê erro 
     */
    private boolean pesquisar(String str, NoTRIE no, int i) throws Exception {
        boolean achou = false;

        // nao achou letra i da string no nó filho onde estaria (ele é nulo)
        if( no.filhos[str.charAt(i)] == null ){
            achou = false;
        } else if( i < (str.length() - 1) ){ // nao chegou ao ultimo char da string
            // pesquisa filhos char da string (incrementa i e vai pro nó filho)
            achou = pesquisar(str, no.filhos[str.charAt(i)], i + 1);
        } else if( i == (str.length() - 1) ){ // ultimo char da string
            // acha string se nó filho com ultimo char for fim de uma cadeia S 
            achou = ( no.filhos[str.charAt(i)].fimS );
        } else { // erro
            throw new Exception("Erro ao pesquisar!");
        }

        return achou;
    }
    
    /** Mostrar **/
    /**
     * Chama metodo privado recursivo que imprime todas as strings presentes na TRIE
     */
    public void mostrar() {
        mostrar("", this.raiz);
    }
    /**
     * Mostra todas as strings presentes na arvore TRIE, concatenando char a char 
     * de cada nó até fimS, recursivamente
     * @param str String a ser mostrada
     * @param no No atual
     */
    private void mostrar(String str, NoTRIE no) {
        if(no.fimS){ // chegou ao ultimo char da string 
            System.out.println("Palavra: " + (str + no.letra));
        }
        
        // percorre todos os filhos do nó 
        for(int i = 0; i < no.filhos.length; i++){
            if(no.filhos[i] != null){ // nó filho tem letra
                // concatena letra do nó atual com string e vai pro nó filho
                mostrar( (str + no.letra), no.filhos[i] );
            }
        }
    }
   
    /** Inserir **/
    /**
     * Chama metodo privado recursivo que efetivamente insere string na TRIE
     * @param str String a ser inserida
     */
    public void inserir(String str) {
        inserir(str, this.raiz, 0);
    }
    /**
     * Insere string na TRIE, colocando letras da string no nó filho quando ainda nao 
     * possuir e setando fim quando terminar de inserir chars. Se string ja é prefixo
     * de outra, apenas seta fim 
     * @param str String a ser inserida
     * @param no NoTRIE atual 
     * @param i int Posicao da string
     */
    private void inserir(String str, NoTRIE no, int i) {
        // nó filho nao possui letra
        if( no.filhos[str.charAt(i)] == null ){ 
            // cria nó filho com char i da string 
            no.filhos[str.charAt(i)] = new NoTRIE(str.charAt(i));
        }

        if( i == (str.length() - 1) ){ // char i é ultimo char da string
            no.filhos[str.charAt(i)].fimS = true;
        } else{ // char i nao é ultimo char da string: continua insercao
            inserir(str, no.filhos[str.charAt(i)], i + 1);
        }
    }   
}
