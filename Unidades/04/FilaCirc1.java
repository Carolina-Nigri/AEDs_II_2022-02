/**
 * FilaCirc1: implementação de fila circular utilizando variáveis para apontar
 * para a primeira e última posição da fila, gastando um espaço a mais do que o 
 * tamanho da fila real
 */
class FilaCirc1 { 
    private int[] array;
    private int primeiro; // aponta para primeira posição (lógica)
    private int ultimo; // aponta para última posição (lógica)
 
    // construtores
    public FilaCirc1() {
        this(6);
    }
    public FilaCirc1(int tamanho) {
        // tamanho físico > lógico:
        // última posição é apenas para apontar para o último
        array = new int[tamanho+1]; 
        primeiro = ultimo = 0; 
    }

    /**
     * isVazia(): verifica se a fila está vazia 
     * @return (true | false)
     */
    public boolean isVazia() {
        return (primeiro == ultimo);
    }

    /**
     * isCheia(): verifica se a fila está cheia
     * @return (true | false)
     */
    public boolean isCheia() {
        return ( ((ultimo + 1) % array.length) == primeiro );
    }

    /**
     * inserir(): insere elemento na última posição se a fila não estiver cheia
     * e atualiza a posição do último
     * @param x (inserido)
     * @throws Exception (fila cheia)
     */
    public void inserir(int x) throws Exception {
        // verifica se a fila está cheia 
        if(isCheia()) {
           throw new Exception("Erro ao inserir! Fila cheia");
        }
  
        array[ultimo] = x; // insere o valor enviado
        ultimo = (ultimo + 1) % array.length; // atualiza posição do último
    }

    /**
     * remover(): remove elemento da primeira posição se a fila não estiver vazia 
     * e atualiza a posição do primeiro
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
        
        return resp;
    }

    /**
     * mostrar(): imprime elementos da fila (primeiro-ultimo)
     */
    public void mostrar() {
        System.out.print("[ ");
  
        for(int i = primeiro; i != ultimo; i = ((i + 1) % array.length)) {
           System.out.print(array[i] + ",");
        }
  
        System.out.println("]");
    }

    /**
     * mostrarRec(): imprime elementos da fila (primeiro-ultimo) recursivamente
     */
    public void mostrarRec() {
        System.out.print("[ ");
        mostrarRec(primeiro); // chama função recursiva
        System.out.println("]");
    }
    public void mostrarRec(int i) {
        if(i != ultimo){ 
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
        while( !achou && (i != ultimo) ){
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
        if(posicao < 0 || posicao >= (array.length - 1)){
            throw new Exception("Erro ao retornar elemento! Posicao invalida");
        }
        
        return array[(posicao + primeiro) % array.length];
    }
}