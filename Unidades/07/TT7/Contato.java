/**
 * Classe Contato
 */
class Contato{
    // atributos
    private String nome, telefone, email;
    private int CPF;

    // construtores
    public Contato(){ }
    public Contato(String nome, String telefone, String email, int CPF){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.CPF = CPF;
    }

    // getters e setters
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public int getCPF(){
        return CPF;
    }
    public void setCPF(int CPF){
        this.CPF = CPF;
    }
}
