/* Carolina Morais Nigri
 * TP01q11 - Palindromo em Java RECURSIVO
 */

class TP01q11{
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
        return isPalindromo(frase,0,(frase.length()-1));
    } // fim isPalindromo(String frase)

    public static boolean isPalindromo(String frase, int i, int j){
        boolean palind = true;
        
        // compara os primeiros char c/os ultimos, ate q o i encontre o j
        if(i < j){ 
            if(frase.charAt(i) == frase.charAt(j)){
                palind = isPalindromo(frase, i+1, j-1);
            } else{ 
                palind = false;
            }
        }

        return palind;
    } // fim isPalindromo(String frase, int i, int j)

    public static boolean isFIM(String frase){
        boolean fim = false;

        // verifica se string eh igual a "FIM"
        if(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')
            fim = true;
        
        return fim;
    } // fim isFIM()
} // fim TP01q11