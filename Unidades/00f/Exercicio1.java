class Exercicio1 {
    public static void main(String args[]){
        // le nome do arquivo
        MyIO.print("Nome arquivo: ");
        String nome = MyIO.readLine();
        // le frase
        MyIO.print("Frase: ");
        String frase = MyIO.readLine();

        // salva frase no arquivo
        Arq.openWrite(nome);
        Arq.println(frase);
        
        Arq.close();
    }
}