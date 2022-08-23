/** 
 * TP01q13 - Ciframento de Cesar em Java RECURSIVO
 * 
 * @author Carolina Morais Nigri
 * @since 23/08/22
 * @version 23/08/22
 */

 class TP01q13 {
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
     * Metodo fraseCifrada(String frase): retorna funcao recursiva (@see fraseCifrada)
     * @param frase
     * @return
     */
    public static String fraseCifrada(String frase){
        return fraseCifrada(frase,0);
    } // fim fraseCifrada(String frase)

    /** 
     * Metodo fraseCifrada(String frase, int i): percorre a frase, somando aos caracteres 
     * atuais o valor da chave (3) recursivamente, formando a frase cifrada com os caracteres 
     * criptografados 
     * @param frase String
     * @return String cifra
     */
    public static String fraseCifrada(String frase, int i){
        String cifra = "";

        if(i < frase.length()){
            cifra = ((char)(frase.charAt(i) + 3)) + fraseCifrada(frase, i+1);
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
} // fim TP01q13
