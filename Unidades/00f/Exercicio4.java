public class Exercicio4 {
    public static void main(String args[]){
        // le nomes dos arquivos
        MyIO.print("Arquivo 1: ");
        String nome1 = MyIO.readLine();
        MyIO.print("Arquivo 2: ");
        String nome2 = MyIO.readLine();
        
        // mostra conteudo do arquivo, convertendo p/maiusculas
        Arq.openRead(nome1);
        Arq.openWrite(nome2);
        
        do{
            MyIO.println(Arq.readLine());
        } while(Arq.hasNext());
        
        Arq.close();
    }
}
