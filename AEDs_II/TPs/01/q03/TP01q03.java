 /** 
 * TP01q03 - Ciframento de Cesar em Java
 * (...)A ideia basica eh um simples deslocamento de caracteres. Assim, por exemplo, 
 * se a chave utilizada para criptografar as mensagens for 3, todas as ocorrencias do
 * caractere ’a’ sao substituidas pelo caractere ’d’, as do ’b’ por ’e’, e assim 
 * sucessivamente. Crie um metodo iterativo que recebe uma string como parametro e
 * retorna outra contendo a entrada de forma cifrada. Neste exercicio, suponha a chave
 * de ciframento tres. Na saida padrao, para cada linha de entrada, escreva uma linha
 * com a mensagem criptografada.
 * 
 * @author Carolina Morais Nigri
 * @since 16/08/22
 * @version 18/08/22
 */

class TP01q03{
    /** 
     * Metodo principal: le frases ate encontrar FIM (@see isFIM) e criptografa
     * a frase com cifra de Cesar (@see fraseCifrada), mostrando resultados na tela
     * @param args String[]
     */
    public static void main(String[] args){
        String frase;
        frase = MyIO.readLine();

        while(!isFIM(frase)){
            MyIO.println(fraseCifrada(frase));
        
            frase = MyIO.readLine();
        }
    } // fim main()
    
    /** 
     * Metodo fraseCifrada: percorre a frase, somando aos caracteres atuais o valor 
     * da chave, formando a frase cifrada com os caracteres criptografados 
     * @param frase String
     * @return String cifra
     */
    public static String fraseCifrada(String frase){
        String cifra = "";
        char c;
        int chave = 3;

        for(int i=0; i < frase.length(); i++){
            c = (char)(frase.charAt(i) + chave);
            cifra += c;
        }

        return cifra;
    } // fim fraseCifrada()

    /** 
     * Metodo isFIM: verifica se frase eh "FIM", comparando tamanho (3) e 
     * caractere por caractere ('F','I','M')
     * @param frase String
     * @return boolean fim
     */
    public static boolean isFIM(String frase){
        boolean fim = false;

        if(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')
            fim = true;
        
        return fim;
    } // fim isFIM()
} // fim TP01q03