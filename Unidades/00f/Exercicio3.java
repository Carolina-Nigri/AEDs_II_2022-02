public class Exercicio3 {
    public static void main(String args[]){
        // le nome do arquivo
        MyIO.print("Nome arquivo: ");
        String nome = MyIO.readLine();
        
        // mostra conteudo do arquivo, convertendo p/maiusculas
        Arq.openRead(nome);
        MyIO.println(Arq.readAll().toUpperCase());

        Arq.close();
    }
}
