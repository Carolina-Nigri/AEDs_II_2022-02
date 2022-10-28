/**
 * Classe Celula
 * @author Carolina Nigri
 * @version 27/10/22
 */
class Celula{
    // Atributos
    private Contato contato; // elemento da célula
    private Celula prox; // ponteiro para próxima célula
    
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

    // Getters e setters
    public Contato getContato(){
        return contato;
    }
    public void setContato(Contato contato){
        this.contato = contato;
    }
    public Celula getProx(){
        return prox;
    }
    public void setProx(Celula prox){
        this.prox = prox;
    }
}
