class NoExt{
    /* Atributos */
    public char letra; // elemento do nó
    public NoExt dir, esq; // ponteiros para nós da árvore externa
    public NoInt raizInt; // ponteiro para raiz da árvore interna

    /* Construtor */
    public NoExt(char letra){
        this.letra = letra;
        // inicializa ponteiros como NULL
        this.dir = this.esq = null;
        this.raizInt = null;
    }

    /* Métodos */
    
}
