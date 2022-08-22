/** 
 * TP01q06 - Is em Java
 * Crie um metodo iterativo que recebe uma string e retorna true se a mesma eh
 * composta somente por vogais. Crie outro metodo iterativo que recebe uma string e 
 * retorna true se a mesma eh composta somente por consoantes. Crie um terceiro 
 * metodo iterativo que recebe uma string e retorna true se a mesma corresponde a um 
 * numero inteiro. Crie um quarto metodo iterativo que recebe uma string e retorna 
 * true se a mesma corresponde a um numero real. Na saida padrao, para cada linha de 
 * entrada, escreva outra de saida da seguinte forma X1 X2 X3 X4 onde cada Xi eh um 
 * booleano indicando se a e entrada eh: composta somente por vogais (X1); 
 * composta somente somente por consoantes (X2); um numero inteiro (X3); um numero 
 * real (X4). Se Xi for verdadeiro, seu valor sera SIM, caso contrario, NAO. 
 * 
 * @author Carolina Morais Nigri
 * @since 18/08/22
 * @version 18/08/22
 */

class TP01q06{
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

            if(isReal(frase)) MyIO.print("SIM ");
            else  MyIO.print("NAO ");

            MyIO.println(" ");

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
     * Metodo isSoVogais: percorre a frase e verfica se algum caractere nao eh 
     * vogal (@see isVogal) e sai se encontra
     * @param frase String
     * @return boolean vogal
     */
    public static boolean isSoVogais(String frase){
        boolean vogal = true;
        int i = 0;

        while(vogal && (i < frase.length())){
            if(!isVogal(frase.charAt(i)))
                vogal = false;

            i++;
        }
        
        return vogal;
    } // fim isSoVogais()

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
     * Metodo isSoConsoantes: percorre a frase e verfica se algum caractere nao eh 
     * consoante (@see isConsoante) e sai se encontra
     * @param frase String
     * @return boolean cons
     */
    public static boolean isSoConsoantes(String frase){
        boolean cons = true;
        int i = 0;

        while(cons && (i < frase.length())){
            if(!isConsoante(frase.charAt(i)))
                cons = false;

            i++;
        }

        return cons;
    } // fim isSoConsoantes()

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
     * Metodo isInteiro: percorre a frase e verfica se algum caractere nao eh 
     * numero (@see isNumero) e sai se encontra 
     * @param frase String
     * @return boolean inteiro
     */
    public static boolean isInteiro(String frase){
        boolean inteiro = true;
        int i = 0;

        while(inteiro && (i < frase.length())){
            if(!isNumero(frase.charAt(i)))
                inteiro = false;

            i++;
        }

        return inteiro;
    } // fim isInteiro()

    /**
     * Metodo isReal: percorre a frase e conta caracteres iguais a numero 
     * (@see isNumero) e conta '.' ou ','. Se a frase conter somente numeros (inteiro)
     * ou numeros e somente um '.' ou ',', eh real
     * @param frase String
     * @return boolean real
     */
    public static boolean isReal(String frase){
        boolean real = false;
        int tam = frase.length();
        int num = 0;
        int pontoVirg = 0;
        
        for(int i=0; i < tam; i++){
            if(isNumero(frase.charAt(i))){
                num++;
            } else if((frase.charAt(i) == '.') || (frase.charAt(i) == ',')){
                pontoVirg++;
            }
        }

        if((num == tam) || (num == (tam - 1) && pontoVirg == 1)) 
            real = true;

        return real;
    } // fim isReal()

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
} // fim TP01q06