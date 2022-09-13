/**
 * FilaCirc2: implementação de fila circular utilizando uma variável para apontar
 * para a primeira posição da fila e uma para a quantidade de elementos
 */
class FilaCirc2 { 
    private int[] array;
    private int primeiro; // aponta para primeira posição (lógica)
    private int n; // salva a quantidade atual de elementos
 
    // construtores
    public FilaCirc2() {
        this(6);
    }
    public FilaCirc2(int tamanho) {
        array = new int[tamanho]; 
        primeiro = n = 0; 
    }

    /**
     * isVazia(): verifica se a fila está vazia 
     * @return (true | false)
     */
    public boolean isVazia() {
        return (n == 0);
    }

    /**
     * isCheia(): verifica se a fila está cheia
     * @return (true | false)
     */
    public boolean isCheia() {
        return (n == array.length);
    }

    /**
     * inserir(): insere elemento na última posição se a fila não estiver cheia
     * e atualiza qtd de elementos (n)
     * @param x (inserido)
     * @throws Exception (fila cheia)
     */
    public void inserir(int x) throws Exception {
        // verifica se a fila está cheia 
        if(isCheia()) {
           throw new Exception("Erro ao inserir! Fila cheia");
        }
  
        array[(n + primeiro) % array.length] = x; // insere o valor enviado
        n++; // atualiza qtd de elementos
    }

    /**
     * remover(): remove elemento da primeira posição se a fila não estiver vazia 
     * e atualiza posição do primeiro e qtd de elementos (n)
     * @return resp (removido)
     * @throws Exception (fila vazia)
     */
    public int remover() throws Exception {
        // verifica se a fila está vazia
        if(isVazia()) {
           throw new Exception("Erro ao remover! Fila vazia");
        }
  
        int resp = array[primeiro]; // salva valor que será removido
        primeiro = (primeiro + 1) % array.length; // atualiza posição do primeiro 
        n--; // atualiza qtd de elementos

        return resp;
    }

    /**
     * mostrar(): imprime elementos da fila
     */
    public void mostrar() {
        System.out.print("[ ");
  
        for(int i = primeiro; i != (n + primeiro) % array.length; i = (i + 1) % array.length){
            System.out.print(array[i] + ",");
        }
  
        System.out.println("]");
    }

    /**
     * mostrarRec(): imprime elementos da fila recursivamente
     */
    public void mostrarRec() {
        System.out.print("[ ");
        mostrarRec(primeiro); // chama função recursiva
        System.out.println("]");
    }
    public void mostrarRec(int i) {
        if(i != ((n + primeiro) % array.length)){ 
            System.out.print(array[i] + ",");
            mostrarRec( (i + 1) % array.length ); // próxima posição
        }
    }

    /**
     * pesquisar(): pesquisa na fila o elemento (se ela não estiver vazia), 
     * retorna verdadeiro se achar ou falso se não
     * @param elemento (pesquisado)
     * @return (true | false) 
     * @throws Exception (fila vazia)
     */
    public boolean pesquisar(int elemento) throws Exception {
        // verifica se a fila está vazia
        if(isVazia()) {
            throw new Exception("Erro ao pesquisar! Fila vazia");
        }
        
        boolean achou = false;
        int i = primeiro;

        // percorre a fila e sai quando acha o elemento ou chega no último 
        while( !achou && (i != ((n + primeiro) % array.length)) ){
            if(array[i] == elemento)
                achou = true;

            i = (i+1) % array.length;
        }

        return achou;
    }

    /**
     * retornaElemento(): retorna elemento da fila na posição (lógica) enviada, 
     * se ela for válida e a fila não estiver vazia
     * @param posicao 
     * @return (elemento)
     * @throws Exception (fila vazia) | (posição inválida)
     */
    public int retornaElemento(int posicao) throws Exception {
        // verifica se a fila está vazia
        if(isVazia()) {
            throw new Exception("Erro ao retornar elemento! Fila vazia");
        }
        // verifica se a posição é válida
        if(posicao < 0 || posicao >= array.length){
            throw new Exception("Erro ao retornar elemento! Posicao invalida");
        }
        
        return array[(posicao + primeiro) % array.length];
    }
}