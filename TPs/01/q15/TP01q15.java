/** 
 * TP01q15 - Is em Java RECURSIVO
 * 
 * @author Carolina Morais Nigri
 * @since 26/08/22
 * @version 26/08/22
 */

class TP01q15{
    /** 
     * Metodo principal: le frases ate encontrar FIM (@see isFIM) e verifica se
     * contem somente vogais (@see isSoVogais), contem somente consoantes (@see 
     * isSoConsoantes), eh inteiro (@see isInteiro) ou eh real (@see isReal), 
     * mostrando resultados na tela
     * @param args String[]
     */
    public static void main(String[] args){
        String frase;
        frase = MyIO.readLine(); 

        while(!isFIM(frase)){ 
            if(isSoVogais(frase)) MyIO.print("SIM ");
            else  MyIO.print("NAO ");
            
            if(isSoConsoantes(frase)) MyIO.print("SIM ");
            else  MyIO.print("NAO ");

            if(isInteiro(frase)) MyIO.print("SIM ");
            else  MyIO.print("NAO ");

            if(isReal(frase)) MyIO.print("SIM");
            else  MyIO.print("NAO");

            MyIO.println("");

            frase = MyIO.readLine();
        }
    } // fim main()

    /**
     * Metodo isVogal: verica se um caractere eh vogal (a,e,i,o,u,A,E,I,O,U)
     * @param c char
     * @return boolean vogal
     */
    public static boolean isVogal(char c){
        boolean vogal = false;

        if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U')
            vogal = true;

        return vogal;
    } // fim isVogal()

    /**
     * Metodo isSoVogais(String frase): retorna a funcao recursiva (@see isSoVogais)
     * @param frase String
     * @return boolean isSoVogais()
     */
    public static boolean isSoVogais(String frase){
        return isSoVogais(frase,0);
    } // fim isSoVogais(String frase)

    /**
     * Metodo isSoVogais(String frase, int i): percorre a frase e verfica se algum 
     * caractere nao eh vogal (@see isVogal) e retorna false se encontra. Senao, 
     * continua a percorrer a frase recursivamente
     * @param frase String
     * @param i int
     * @return boolean vogal
     */
    public static boolean isSoVogais(String frase, int i){
        boolean vogal = true;
        
        if(i < frase.length()){
            if(!isVogal(frase.charAt(i)))
                vogal = false;
            else 
                vogal = isSoVogais(frase,i+1);
        }
        
        return vogal;
    } // fim isSoVogais(String frase, int i)

    /**
     * Metodo isConsoante: verica se um caractere eh uma letra e se nao eh vogal
     * (@see isVogal), logo eh consoante
     * @param c char
     * @return boolean cons
     */
    public static boolean isConsoante(char c){
        boolean cons = false;

        if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
            if(!isVogal(c)){
                cons = true;
            }
        }             

        return cons;
    } // fim isConsoante()

    /**
     * Metodo isSoConsoantes(String frase): retorna a funcao recursiva 
     * (@see isSoConsoante)
     * @param frase String
     * @return boolean isSoConsoantes()
     */
    public static boolean isSoConsoantes(String frase){
        return isSoConsoantes(frase, 0);
    } // fim isSoConsoantes(String frase)

     /**
     * Metodo isSoConsoantes(String frase, int i): percorre a frase e verfica se algum 
     * caractere nao eh consoante (@see isConsoante) e retorna false se encontra. 
     * Senao, continua a percorrer a frase recursivamente
     * @param frase String
     * @param i int
     * @return boolean cons
     */
    public static boolean isSoConsoantes(String frase, int i){
        boolean cons = true;

        if(i < frase.length()){
            if(!isConsoante(frase.charAt(i)))
                cons = false;
            else
                cons = isSoConsoantes(frase, i+1);
        }

        return cons;
    } // fim isSoConsoantes(String frase, int i)

    /**
     * Metodo isNumero: verica se um caractere eh numero (0,1,2,3,4,5,6,7,8,9)
     * @param c char
     * @return boolean numero
     */
    public static boolean isNumero(char c){
        boolean numero = false;

        if(c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9')
            numero = true;

        return numero;
    } // fim isNumero()

    /**
     * Metodo isInteiro(String frase): retorna a funcao recursiva (@see isInteiro)
     * @param frase String
     * @return boolean isInteiro()
     */
    public static boolean isInteiro(String frase){
        return isInteiro(frase, 0);
    } // fim isInteiro(String frase)

    /**
     * Metodo isInteiro(String frase, int i): percorre a frase e verfica se algum 
     * caractere nao eh numero (@see isNumero) e retorna false se encontra. 
     * Senao, continua a percorrer a frase recursivamente
     * @param frase String
     * @param i int
     * @return boolean inteiro
     */
    public static boolean isInteiro(String frase, int i){
        boolean inteiro = true;

        if(i < frase.length()){
            if(!isNumero(frase.charAt(i)))
                inteiro = false;
            else   
                inteiro = isInteiro(frase, i+1);
        }

        return inteiro;
    } // fim isInteiro(String frase, int i)

    /**
     * Metodo isReal(String frase):  retorna a funcao recursiva (@see isReal)
     * @param frase String
     * @return boolean isReal()
     */
    public static boolean isReal(String frase){
        return isReal(frase,0,0);
    } // fim isReal(String frase)

    /**
     * Metodo isReal(String frase, int i): percorre a frase recursivamente,
     * verificando se o caractere eh um numero ou '.' ou ','. Se for '.' ou ',' soma
     * a variavel p, se ela for maior que 1 ou as condicoes acima nao forem 
     * verdadeiras, retorna false.
     * @param frase String
     * @param i int
     * @param p int
     * @return boolean real
     */
    public static boolean isReal(String frase, int i, int p){
        boolean real = true;
        
        if(i < frase.length()){
            if(isNumero(frase.charAt(i))){
                real = isReal(frase,i+1,p);
            } else if((frase.charAt(i) == '.' || frase.charAt(i) == ',') && (p < 1)){
                real = isReal(frase,i+1,p+1);
            } else{
                real = false;
            }
        }

        return real;
    } // fim isReal(String frase, int i)

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
} // fim TP01q15