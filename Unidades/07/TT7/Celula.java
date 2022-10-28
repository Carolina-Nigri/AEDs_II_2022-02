/**
 * Classe Celula
 * @author Carolina Nigri
 * @version 28/10/22
 */
class Celula{
    // Atributos
    public Contato contato; // elemento da célula
    public Celula prox; // ponteiro para próxima célula
    
    // Construtores
    public Celula(){
        this(null, null);
    }
    public Celula(Contato contato){
        this(contato, null);
    }
    public Celula(Contato contato, Celula prox){
        this.contato = contato;
        this.prox = prox;
    }
}
