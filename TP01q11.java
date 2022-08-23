/** 
 * TP01q11 - Palindromo em Java RECURSIVO
 * 
 * @author Carolina Morais Nigri
 * @since 16/08/22
 * @version 23/08/22
 */

class TP01q11{
    /** 
     * Metodo principal: le frases ate encontrar FIM (@see isFIM) e verifica se 
     * eh palindromo (@see isPalindromo), mostrando resultados na tela
     * @param args String[]
     */
    public static void main(String[] args){
        String frase;
        frase = MyIO.readLine();

        while(!isFIM(frase)){ 
            if(isPalindromo(frase))
                MyIO.println("SIM");
            else 
                MyIO.println("NAO");
            
            frase = MyIO.readLine();
        } 
    } // fim main()

    /** 
     * Metodo isPalindromo(String frase): retorna funcao recursiva (@see isPalindromo)
     * @param frase String
     * @return boolean isPalindromo()
     */
    public static boolean isPalindromo(String frase){
        return isPalindromo(frase,0,(frase.length()-1));
    } // fim isPalindromo(String frase)

    /** 
     * Metodo isPalindromo(String frase, int i, int j): verifica se frase eh palindromo, 
     * comparando os caracteres iniciais com os finais recursivamente
     * @param frase String
     * @param i int
     * @param j int
     * @return boolean palind
     */
    public static boolean isPalindromo(String frase, int i, int j){
        boolean palind = true;
        
        if(i < j){ 
            if(frase.charAt(i) == frase.charAt(j)){
                palind = isPalindromo(frase, i+1, j-1);
            } else{ 
                palind = false;
            }
        }

        return palind;
    } // fim isPalindromo(String frase, int i, int j)

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
} // fim TP01q11