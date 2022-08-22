/** 
 * TP01q04 - Alteracao Aleatoria em Java
 * Crie um metodo iterativo que recebe uma string, sorteia duas letras minusculas 
 * aleatorias (codigo ASCII >= ’a’ e <= ’z’), substitui todas as ocorrencias da
 * primeira letra na string pela segunda e retorna a string com as alteracoes 
 * efetuadas. Na saida padrao, para cada linha de entrada, execute o metodo 
 * desenvolvido nesta questao e mostre a string retornada como uma linha de saida. 
 * 
 * @author Carolina Morais Nigri
 * @since 16/08/22
 * @version 18/08/22
 */

import java.util.Random;

class TP01q04{
    /** 
     * Metodo principal: le frases ate encontrar FIM (@see isFIM), a cada linha
     * sorteia aleatoriamente duas letras (@see letrasAleatorias) e altera a frase 
     * (@see fraseAlterada), mostrando resultados na tela
     * @param args String[]
     */
    public static void main(String[] args){
        String frase;
        char[] troca;

        Random gerador = new Random();
        gerador.setSeed(4);

        frase = MyIO.readLine();

        while(!isFIM(frase)){ 
            troca = letrasAleatorias(gerador);
            MyIO.println(fraseAlterada(frase, troca));
        
            frase = MyIO.readLine();
        }
    } // fim main()

    /**
     * Metodo letrasAleatorias: sorteia duas letras a partir do gerador Random e 
     * salva em um array
     * @param gerador Random
     * @return char[] letras
     */
    public static char[] letrasAleatorias(Random gerador){
        char[] letras = {' ',' '};
        
        letras[0] = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        letras[1] = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

        return letras;
    } // fim letrasAleatorias()

    /**
     * Metodo fraseAlterada: percorre a frase e compara seus caracteres com a primeira
     * letra de troca[]. Se for igual, soma a segunda letra de troca[] a string alter,
     * se nao, soma o caractere original
     * @param frase String
     * @param troca char[]
     * @return String alter
     */
    public static String fraseAlterada(String frase, char[] troca){
        String alter = "";

        for(int i=0; i < frase.length(); i++){
            if(frase.charAt(i) == troca[0]) 
                alter += troca[1];
            else
                alter += frase.charAt(i);
        }

        return alter;
    } // fim fraseAlterada()
    
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
} // fim TP01q04