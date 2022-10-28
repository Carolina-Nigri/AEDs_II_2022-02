/**
 * Classe No
 * @author Carolina Nigri
 * @version 27/10/22
 */
class No{
    // Atributos
    private char letra; // elemento do nó
    private No esq, dir; // ponteiros para nós da esquerda e da direita
    private Celula primeiro, ultimo; // ponteiros para primeira e última células
   
    // Construtores
    public No(){
        this(' ', null, null);
    }
    public No(char letra){
        this(letra, null, null);
    }
    public No(char letra, No esq, No dir){
        this.letra = letra;
        this.esq = esq;
        this.dir = dir;
        this.primeiro = new Celula( new Contato() ); // head (Contato vazio)
        this.ultimo = this.primeiro; // lista vazia -> primeiro=ultimo
    }

    // Getters e setters
    public char getLetra(){
        return letra;
    }
    public void setLetra(char letra){
        this.letra = letra;
    }
    public No getEsq(){
        return esq;
    }
    public void setEsq(No esq){
        this.esq = esq;
    }
    public No getDir(){
        return dir;
    }
    public void setDir(No dir){
        this.dir = dir;
    }
    public Celula getPrimeiro(){
        return primeiro;
    }
    public void setPrimeiro(Celula primeiro){
        this.primeiro = primeiro;
    }
    public Celula getUltimo(){
        return ultimo;
    }
    public void setUltimo(Celula ultimo){
        this.ultimo = ultimo;
    }
    
    // Métodos
    /**
	 * Calcula o tamanho, em número de elementos, da lista
	 * @return <code>int</code> tamanho da lista
	 */
    public int tamanho(){
        int tamanho = 0; 
        
        for(Celula i = primeiro; i != ultimo; i = i.getProx()){
            tamanho++;
        }
        
        return tamanho;
    }
    /**
     * Insere um contato no fim da lista do nó
     * @param contato <code>Contato</code> objeto a ser inserido
     * @return <code>true</code> se conseguir inserir ou 
     * <code>false</code> caso contrário
     */
    public boolean inserir(Contato contato){
        // insere no fim da lista encadeada
        ultimo.setProx( new Celula(contato) );
		ultimo = ultimo.getProx();

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
            for(int j = 0; j < pos; j++, i = i.getProx());
            
            // muda o prox de i p/célula na posição após a que se deseja remover 
            i.setProx( i.getProx().getProx() );
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
        primeiro = primeiro.getProx();
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
        for(i = primeiro; i.getProx() != ultimo; i = i.getProx());

        ultimo = i;
        ultimo.setProx(null);
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
        Celula i = primeiro.getProx();
        while( i != null && !achou ){
            pos++;
            if(nome.equals(i.getContato().getNome())){
                achou = true;
            }
            i = i.getProx();
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
        Celula i = primeiro.getProx();
        while( i != null && !achou ){
            if(nome.equals(i.getContato().getNome())){
                achou = true;
            }
            i = i.getProx();
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
        Celula i = primeiro.getProx();
        while( i != null && !achou ){
            if(cpf == i.getContato().getCpf()){
                achou = true;
            }
            i = i.getProx();
		}
		
        return achou;
	}  
}
