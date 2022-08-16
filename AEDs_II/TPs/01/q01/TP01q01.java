/* Carolina Morais Nigri
 * TP01q01 - Palindromo em Java
 */

class TP01q01{
    public static void main(String[] args){
        String frase;

        frase = MyIO.readLine(); // le frase

        while(!isFIM(frase)){ // sai quando frase = "FIM"
            // se eh palindromo, imprime "SIM", se nao, "NAO"
            if(isPalindromo(frase))
                MyIO.println("SIM");
            else 
                MyIO.println("NAO");
            
            frase = MyIO.readLine(); // le frase
        } 
    } // fim main()

    public static boolean isPalindromo(String frase){
        boolean palind = true;
        int tam = frase.length();
        int i = 0;

        // verifica se primeiros char da string sao iguais aos ultimos
        while(palind && (i < tam/2)){
            if(frase.charAt(i) != frase.charAt(tam-(i+1))) 
                palind = false;
            
            i++;
        }

        return palind;
    } // fim isPalindromo()

    public static boolean isFIM(String frase){
        boolean fim = false;

        // verifica se string eh igual a "FIM"
        if(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')
            fim = true;
        
        return fim;
    } // fim isFIM()
} // fim TP01q01