/* Carolina Morais Nigri
 * TP01q03 - Ciframento de Cesar em Java
 */

class TP01q03{
    public static void main(String[] args){
        String frase;

        frase = MyIO.readLine(); // le frase

        while(!isFIM(frase)){ // sai quando frase = "FIM"
            // imprime a frase criptograda com cifra de Cesar
            MyIO.println(fraseCifrada(frase));
        
            frase = MyIO.readLine(); // le frase
        }
    } // fim main()

    public static String fraseCifrada(String frase){
        String cifra = "";
        char c;
        int chave = 3;

        for(int i=0; i < frase.length(); i++){
            // salva o char da frase somado da qtd que estiver na chave
            c = (char)(frase.charAt(i) + chave);
            // forma a cifra com os char criptografados
            cifra += c;
        }

        return cifra;
    } // fim fraseCifrada()

    public static boolean isFIM(String frase){
        boolean fim = false;

        // verifica se string eh igual a "FIM"
        if(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')
            fim = true;
        
        return fim;
    } // fim isFIM()
} // fim TP01q03