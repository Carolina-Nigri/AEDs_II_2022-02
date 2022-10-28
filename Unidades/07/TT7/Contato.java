/**
 * Classe Contato
 * @author Carolina Nigri
 * @version 27/10/22
 */
class Contato{
    // Atributos
    private String nome, telefone, email;
    private int cpf;

    // Construtores
    public Contato(){
        this("", "", "", -1);
    }
    public Contato(String nome, String telefone, String email, int cpf){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    // Getters e setters
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
    public int getCpf(){
        return cpf;
    }
    public void setCpf(int cpf){
        this.cpf = cpf;
    }
}
