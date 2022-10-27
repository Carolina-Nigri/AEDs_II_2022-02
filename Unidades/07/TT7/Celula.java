/**
 * Classe Celula
 */
class Celula{
    // atributos
    public Contato contato; // elemento da celula
    public Celula prox; // ponteiro para proxima celula
    
    // construtores
    public Celula(){
        this( new Contato() );
    }
    public Celula(Contato contato){
        this.contato = contato;
        this.prox = null;
    }
}
