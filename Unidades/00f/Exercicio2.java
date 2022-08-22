class Exercicio2 {
    public static void main(String args[]){
        // le nome do arquivo
        MyIO.print("Nome arquivo: ");
        String nome = MyIO.readLine();
        
        // mostra conteudo do arquivo
        Arq.openRead(nome);
        MyIO.println(Arq.readAll());
        
        Arq.close();
    }
}