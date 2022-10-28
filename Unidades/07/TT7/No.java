/**
 * Classe No
 * @author Carolina Nigri
 * @version 28/10/22
 */
class No{
    // Atributos
    public char letra; // elemento do nó
    public No esq, dir; // ponteiros para nós da esquerda e da direita
    public Celula primeiro, ultimo; // ponteiros para primeira e última células
   
    // Construtores
    public No(){
        this(' ');
    }
    public No(char letra){
        this.letra = letra;
        this.esq = this.dir = null;
        Contato head = new Contato(); // head (Contato vazio)
        this.ultimo = this.primeiro = new Celula(head); // lista vazia
    }
    // Métodos
    /**
	 * Calcula o tamanho, em número de elementos, da lista
	 * @return <code>int</code> tamanho da lista
	 */
    public int tamanho(){
        int tamanho = 0; 
        
        for(Celula i = primeiro; i != ultimo; i = i.prox){
            tamanho++;
        }
        
        return tamanho;
    }
    /**
     * Imprime o No
     */
    public void print(){
        System.out.println("No ("+letra+"):");
        System.out.print("Lista: ");

        if(primeiro == ultimo){
            System.out.println("Vazia");
        } else{
            System.out.println("");
            int j = 0;
            for(Celula i = primeiro.prox; i != null ; i = i.prox, j++){
                System.out.print("["+j+"] ");
                i.contato.print(); // imprime dados do contato
            }
        }
    }
    /**
     * Insere um contato no fim da lista do nó
     * @param contato <code>Contato</code> objeto a ser inserido
     * @return <code>true</code> se conseguir inserir ou 
     * <code>false</code> caso contrário
     */
    public boolean inserir(Contato contato){
        // insere no fim da lista encadeada
        ultimo.prox = new Celula(contato);
		ultimo = ultimo.prox;

        return true;
    }
    /**
     * Remove um Contato da lista do nó pelo nome
     * @param nome <code>String</code> nome do Contato a ser removido
     * @return <code>true</code> se conseguir remover ou 
     * <code>false</code> caso contrário
     */
    public boolean remover(String nome){
        boolean sucesso = false;
        int pos = pesquisarPos(nome);
        
        if(pos != -1)
            sucesso = remover(pos);

        return sucesso;
    }
    /**
     * Remove um Contato da lista do nó em determinada posição
     * @param pos <code>int</code> posição do Contato a ser removido
     * @return <code>true</code> se conseguir remover ou 
     * <code>false</code> caso contrário
     */
    public boolean remover(int pos){
        boolean sucesso = false;
        int tamanho = tamanho();

        if(primeiro == ultimo){
            System.err.println("Erro ao remover! Lista vazia");
            sucesso = false;
        } else if(pos < 0 || pos >= tamanho){
            System.err.println("Erro ao remover! Posicao invalida");
            sucesso = false;
        } else if(pos == 0){
            sucesso = removerInicio();
        } else if(pos == (tamanho - 1)){
            sucesso = removerFim();
        } else{
            // Caminhar até a posição anterior à remoção
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
            
            // muda o prox de i p/célula na posição após a que se deseja remover 
            i.prox = i.prox.prox;
            sucesso = true;
        }

        return sucesso;
    }
    /**
     * Remove um Contato do início da lista do nó
     * @return <code>true</code> se conseguir remover ou 
     * <code>false</code> caso contrário
     */
	public boolean removerInicio(){
		boolean sucesso = false;

        if(primeiro == ultimo){
			System.err.println("Erro ao remover! Lista vazia");
            sucesso = false;
		}

        // muda o head para a célula removida (remoção lógica)
        primeiro = primeiro.prox;
        sucesso = true;
        
		return sucesso;
	}
    /**
     * Remove um Contato do fim da lista do nó
     * @return <code>true</code> se conseguir remover ou 
     * <code>false</code> caso contrário
     */
	public boolean removerFim(){
		boolean sucesso = false;

        if(primeiro == ultimo){
			System.err.println("Erro ao remover! Lista vazia");
            sucesso = false;
		}

        // Caminhar ate a penúltima célula
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        ultimo = i;
        ultimo.prox = null;
        i = null;

        sucesso = true;

		return sucesso;
	}
    /**
	 * Pesquisa um nome na lista de Contato
	 * @param nome <code>String</code> nome do Contato a ser pesquisado
	 * @return <code>int</code> posição da Celula em que está o Contato,
     * <code>-1</code> se não encontrar
	 */
	public int pesquisarPos(String nome){
		int pos = -1;
        boolean achou = false;
		
        // percorre lista encadeada
        Celula i = primeiro.prox;
        while( i != null && !achou ){
            pos++;
            if(nome.equals(i.contato.getNome())){
                achou = true;
            }
            i = i.prox;
		}
		
        return pos;
	}
    /**
	 * Pesquisa um nome na lista de Contato
	 * @param nome <code>String</code> nome do Contato a ser pesquisado
	 * @return <code>true</code> se achar ou <code>false</code> caso contrário
	 */
	public boolean pesquisar(String nome){
		boolean achou = false;
		
        // percorre lista encadeada
        Celula i = primeiro.prox;
        while( i != null && !achou ){
            if(nome.equals(i.contato.getNome())){
                achou = true;
            }
            i = i.prox;
		}
		
        return achou;
	}
    /**
	 * Pesquisa um cpf na lista de Contato
	 * @param cpf <code>int</code> cpf do Contato a ser pesquisado
	 * @return <code>true</code> se achar ou <code>false</code> caso contrário
	 */
	public boolean pesquisar(int cpf){
		boolean achou = false;
		
        // percorre lista encadeada
        Celula i = primeiro.prox;
        while( i != null && !achou ){
            if(cpf == i.contato.getCpf()){
                achou = true;
            }
            i = i.prox;
		}
		
        return achou;
	}  
}
