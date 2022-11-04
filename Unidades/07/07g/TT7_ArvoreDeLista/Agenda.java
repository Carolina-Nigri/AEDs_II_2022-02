/**
 * Classe Agenda
 * @author Carolina Nigri
 * @version 28/10/22
 */
class Agenda{
    // Atributos
    private No raiz;

    // Construtor
    public Agenda(){
        this.raiz = null;
        // insere as 26 letras nos nós da árvore binária
        preencheArvore();
    }

    // Getters e setters
    public No getRaiz(){
        return raiz;
    }
    public void setRaiz(No raiz){
        this.raiz = raiz;
    } 

    // Métodos
    /**
     * Preenche a árvore binária da Agenda com 26 nós com as letras 
     * do alfabeto, em uma ordem determinada
     */
    public void preencheArvore(){
        char[] letras = {'M','F','T','C','I','P','W','A','B','D','E','G','H',
                         'J','K','L','N','O','Q','R','S','U','V','X','Y','Z'};

        for(int i = 0; i < letras.length; i++){
            this.inserirArvore(letras[i]);
        }
    }
    /**
     * Chama caminhamento da árvore, imprimindo todos os elementos da Agenda
     */
    public void print(){
        caminharPre(this.raiz);
    }
    /**
	 * Caminhamento recursivo na árvore, imprimindo elementos 
     * começando da raiz
	 * @param i No em análise
	 */
	private void caminharPre(No i){
		if(i != null){
			i.print(); // imprime conteúdo do nó
			caminharPre(i.esq); // percorre esquerda
			caminharPre(i.dir); // percorre direita
		}
	}
    /**
     * Insere uma letra no nó da árvore binária (método iterativo que
     * chama o recursivo que faz a inserção propriamente dita)
     * @param letra <code>char</code> a ser inserida
     */
    public void inserirArvore(char letra){
        this.raiz = inserirArvore(letra, this.raiz);
    }
    /**
	 * Insere uma letra no nó da árvore binária (método recursivo que
     * faz a inserção propriamente dita)
	 * @param letra <code>char</code> a ser inserida
	 * @param i <code>No</code> em análise
	 * @return <code>No</code> em análise, alterado ou não
	 */
	private No inserirArvore(char letra, No i){
        try {
            if(i == null){ // chegou à posição certa de inserção da letra na árvore
                i = new No(letra);
            } else if(letra < i.letra){ // letra tem que estar à esquerda (menor)
                // percorre esquerda da árvore recursivamente
                i.esq = inserirArvore(letra, i.esq); 
            } else if(letra > i.letra){ // letra tem que estar à direita (maior)
                // percorre direita da árvore recursivamente
                i.dir = inserirArvore(letra, i.dir);
            } else{ // letra é igual a alguma das já existentes na árvore 
                Exception equals = new Exception();
                throw equals;
            }
        } catch(Exception equals){
            System.err.println("Erro ao inserir! Letra ja existente na arvore");
        }

		return i;
	}
    /**
     * Insere um contato na agenda
     * @param contato <code>Contato</code> objeto a ser inserido
     * @return <code>true</code> se conseguir inserir ou 
     * <code>false</code> caso contrário
     */
    public boolean inserir(Contato contato){
        boolean sucesso = false;

        // pesquisar posição de inserção do contato pela primeira letra do nome
        No pos = pesquisar(contato.getNome().charAt(0), this.raiz);
        
        if(pos != null){
            // inserir na lista do nó
            sucesso = pos.inserir(contato);
        }
        
        return sucesso;
    }
    /**
     * Remove um Contato da agenda pelo nome
     * @param nome <code>String</code> nome do Contato a ser removido
     * @return <code>true</code> se conseguir remover ou 
     * <code>false</code> caso contrário
     */
    public boolean remover(String nome){
        boolean sucesso = false;

        // pesquisar posição de remoção do contato pela primeira letra do nome
        No pos = pesquisar(nome.charAt(0), this.raiz);

        if(pos != null){
            // inserir na lista do nó
            sucesso = pos.remover(nome);
        }

        return sucesso;
    }
    /**
     * Pesquisa um Contato da agenda pelo nome
     * @param nome <code>String</code> nome do Contato a ser pesquisado
     * @return <code>true</code> se achar ou <code>false</code> caso contrário
     */
    public boolean pesquisar(String nome){
        boolean achou = false;

        // pesquisa posição (nó da árvore) da primeira letra do nome do Contato
        No i = pesquisar(nome.charAt(0), this.raiz);
        
        if(i != null){
            // pesquisa na lista
            achou = i.pesquisar(nome);
        }

        return achou;
    }
    /**
     * Pesquisa uma letra a partir de um nó na árvore
     * @param letra <code>char</code> a ser pesquisada 
     * @param i <code>No</code> em análise 
     * @return <code>No</code> posição da letra encontrada 
     * <code>null</code> se não encontrar
     */
    private No pesquisar(char letra, No i){
        No pos = null;

        if(i != null){
            if(i.letra == letra){ // achou 
                pos = i;
            } else if(letra < i.letra){ // pesquisa à esquerda
                pos = pesquisar(letra, i.esq);
            } else if(letra > i.letra){ // pesquisa à direita
                pos = pesquisar(letra, i.dir);
            }
        }

        return pos;
    }
    /**
     * Pesquisa um Contato da agenda pelo cpf (método iterativo que
     * chama o recursivo que faz a pesquisa na árvore propriamente dita)
     * @param cpf <code>int</code> cpf do Contato a ser pesquisado
     * @return <code>true</code> se achar ou <code>false</code> caso contrário
     */
    public boolean pesquisar(int cpf){
        return pesquisarPre(cpf, this.raiz);
    }
    /**
     * Pesquisa um Contato da agenda pelo cpf (método recursivo que faz a pesquisa 
     * na árvore propriamente dita)
     * @param cpf <code>int</code> cpf do Contato a ser pesquisado
     * @return <code>true</code> se achar ou <code>false</code> caso contrário
     */
	private boolean pesquisarPre(int cpf, No i){
		boolean achou = false;

        if(i != null){
            achou = i.pesquisar(cpf); // pesquisa na lista do nó
			
            if(!achou) 
                achou = pesquisarPre(cpf, i.esq); // percorre a esquerda
            if(!achou) 
                achou = pesquisarPre(cpf, i.dir); // percorre a direita
		}

        return achou;
	}
}
