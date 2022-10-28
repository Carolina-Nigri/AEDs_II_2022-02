/**
 * Classe Principal: teste de execução
 * @author Carolina Nigri
 * @version 28/10/22
 */
public class Principal{
    public static void main(String[] args){
        Agenda agenda = new Agenda();
        System.out.println("****Agenda criada****");
        agenda.print();

        String[] nomes = {"Alex","Amanda","Maria", "Mario","Fernanda","Carol", 
                          "Bernardo", "Ana Luiza", "Ana Clara", "Dani", "Dario",
                          "Otavio", "Olivia", "Walter", "Vitoria", "Vitor", 
                          "Melissa", "Leandro"};

        // insere contatos
        Contato[] contatos = new Contato[nomes.length];
        for(int i = 0; i < contatos.length; i++){
            contatos[i] = new Contato(nomes[i], "(31) 90000-0000",
                            "fulano@email.com", 1001001001);
            agenda.inserir(contatos[i]);
        }
        Contato c = new Contato("Ze", "ze@email.com", 
                        "(31) 91234-567", 1234567891); 
        agenda.inserir(c);
        
        System.out.println("\n****Agenda pos-insercoes****");
        agenda.print();

        // remove contatos
        agenda.remover("Leandro");
        agenda.remover("Maria");
        agenda.remover("Ana Luiza");

        System.out.println("\n****Agenda pos-remocoes****");
        agenda.print();

        System.out.println("\n****Pesquisas****");
        if(agenda.pesquisar("Maria"))
            System.out.println("Achou");
        else
            System.out.println("Nao achou");
        if(agenda.pesquisar("Carol"))
            System.out.println("Achou");
        else
            System.out.println("Nao achou");
        if(agenda.pesquisar(1234567891))
            System.out.println("Achou");
        else
            System.out.println("Nao achou");
    }
}
