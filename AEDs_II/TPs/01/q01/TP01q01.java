/** 
 * TP01q01 - Palindromo em Java
 * Crie um metodo iterativo que recebe uma string como parametro e retorna true se 
 * essa eh um palindromo. Na saida padrao, para cada linha de entrada, escreva uma 
 * linha de saida com SIM/NAO indicando se a linha eh um palindromo. Destaca-se que 
 * uma linha de entrada pode ter caracteres nao letras. A entrada termina com a 
 * leitura de FIM.
 * 
 * @author Carolina Morais Nigri
 * @since 15/08/22
 * @version 18/08/22
 */

class TP01q01{
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
     * Metodo isPalindromo: verifica se frase eh palindromo, comparando os caracteres
     * iniciais com os finais
     * @param frase String
     * @return boolean palind
     */
    public static boolean isPalindromo(String frase){
        boolean palind = true;
        int tam = frase.length();
        int i = 0;

        while(palind && (i < tam/2)){
            if(frase.charAt(i) != frase.charAt(tam-(i+1))) 
                palind = false;
            
            i++;
        }

        return palind;
    } // fim isPalindromo()

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
} // fim TP01q01