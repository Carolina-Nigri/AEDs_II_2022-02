/**
 * Classe NoTRIE
 */
class NoTRIE {
    /* Atributos */
    public char letra; // elemento do nó
    public boolean fimS; // indica se nó é fim de uma cadeia S
    public NoTRIE[] filhos; // array de nós filhos
    public final int TAM = 255; // qtd max de filhos (valores ASCII)
    
    /* Construtores */
    public NoTRIE() {
        this((char)127); // valor ASCII de DEL (p/nao colocar espaco ao concatenar)
    }
    public NoTRIE(char letra) {
        this.letra = letra;
        this.fimS = false;

        // cria array de nós filhos e os inicializa 
        this.filhos = new NoTRIE[TAM];
        for(int i = 0; i < TAM; i++){
            this.filhos[i] = null;
        }
    } 
}
