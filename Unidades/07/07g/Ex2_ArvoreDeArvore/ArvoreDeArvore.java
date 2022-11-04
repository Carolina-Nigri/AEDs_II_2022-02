class ArvoreDeArvore{
    /* Atributos */
    public NoExt raizExt; // raiz da árvore externa

    /* Construtor */
    public ArvoreDeArvore(){
        this.raizExt = null;
        // insere as 26 letras nos nós da árvore binária
        preencheArvoreExt();
    }

    /* Métodos */
    public void preencheArvoreExt(){
        char[] letras = {'M','F','T','C','I','P','W','A','B','D','E','G','H',
                         'J','K','L','N','O','Q','R','S','U','V','X','Y','Z'};

        for(int i = 0; i < letras.length; i++){
            this.inserirLetra(letras[i]);
        }
    }
    public void inserirLetra(char letra){
        this.raizExt = inserirLetra(letra, this.raizExt);
    }
    private NoExt inserirLetra(char letra, NoExt i){
        if(i == null){ // chegou à posição certa de inserção da letra na árvore
            i = new NoExt(letra);
        } else if(letra < i.letra){ // letra tem que estar à esquerda (menor)
            // percorre esquerda da árvore recursivamente
            i.esq = inserirLetra(letra, i.esq); 
        } else if(letra > i.letra){ // letra tem que estar à direita (maior)
            // percorre direita da árvore recursivamente
            i.dir = inserirLetra(letra, i.dir);
        }

		return i;
	}
    public void inserirPalavra(String palavra){
        // pesquisa nó externo que contém primeira letra da palavra 
        NoExt pos = pesquisarLetra(palavra.charAt(0), this.raizExt);
        
        // chama função do nó interno que insere palavra na árvore interna
        if(pos != null){
            pos.raizInt.inserir(palavra);
        }
    }
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

}
