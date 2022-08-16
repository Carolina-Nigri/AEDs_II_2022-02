import java.util.Random;

/* Carolina Morais Nigri
 * TP01q04 - Alteracao Aleatoria em Java
 */

class TP01q04{
    public static void main(String[] args){
        Random gerador = new Random();
        gerador.setSeed(4);
        int a = (Math.abs(gerador.nextInt()) % 26);
        int b = (Math.abs(gerador.nextInt()) % 26);
        MyIO.println("a = " + a);
        MyIO.println("b = " + b);
        
/*
        String frase;
        
        frase = MyIO.readLine(); // le frase
        
        while(!isFIM(frase)){ // sai quando frase = "FIM"
            // imprime a frase alterada aleatoriamente
            MyIO.println(fraseAlterada(frase));
        
            frase = MyIO.readLine(); // le frase
        }
 */
    } // fim main()

    public static String fraseAlterada(String frase){
        String alter = "";
        Random gerador = new Random();
        gerador.setSeed(4);
        
        // sorteia 2 letras minusculas aleatorias

        // substitui ocorrencias da 1a letra na frase pela 2a


        return alter;
    } // fim fraseAlterada()

    public static boolean isFIM(String frase){
        boolean fim = false;

        // verifica se string eh igual a "FIM"
        if(frase.length() == 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M')
            fim = true;
        
        return fim;
    } // fim isFIM()
} // fim TP01q04