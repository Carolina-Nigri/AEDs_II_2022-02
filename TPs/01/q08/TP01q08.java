/** 
 * TP01q08 - Leitura de Pagina HTML em Java
 * 
 * @author Carolina Morais Nigri
 * @since 20/08/22
 * @version 24/08/22
 */

import java.io.*;
import java.net.*;

class TP01q08{
    /** 
     * Metodo principal: le nomes e enderecos de sites ate encontrar FIM (@see
     * isFIM), pega o conteudo html da pagina (@see getHTML) e conta: vogais com e 
     * sem acento (@see imprimeQtdVogais), consoantes (@see imprimeQtdConsoantes) e 
     * tags "<br>" e "<table>" (@see imprimeTags), mostrando resultados e o nome da 
     * pagina na tela
     * @param args String[]
     */
    public static void main(String[] args){
        MyIO.setCharset("UTF-8");
        
        String nomePag;
        String endereco;
        String html;
        
        nomePag = MyIO.readLine();
        
        while(!isFIM(nomePag)){
            endereco = MyIO.readLine();
            html = getHTML(endereco);

            imprimeQtdVogais(html);
            imprimeQtdConsoantes(html);
            imprimeTags(html);
            MyIO.print(nomePag);
            MyIO.println("");
             
            nomePag = MyIO.readLine();
        }
    } // fim main()

    /**
     * Metodo getHTML: salva o conteudo do html da URL (endereco) passada por 
     * parametro
     * @param endereco String
     * @return String html
     * @throws IOException
     * @throws MalformedURLException
     */
    public static String getHTML(String endereco){
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;
  
        try {
           url = new URL(endereco);
           is = url.openStream();  // throws an IOException
           br = new BufferedReader(new InputStreamReader(is));
  
           while ((line = br.readLine()) != null) {
              resp += line + "\n";
           }
        } catch (MalformedURLException mue) {
           mue.printStackTrace();
        } catch (IOException ioe) {
           ioe.printStackTrace();
        } 
  
        try {
           is.close();
        } catch (IOException ioe) {
  
        }
  
        return resp;
    } // fim getHTML()

    /**
     * Metodo imprimeQtdVogais: conta qtd de vogais com e sem acento da string e
     * imprime
     * @param html String
     */
    public static void imprimeQtdVogais(String html){
        int[] vogais = {97,101,105,111,117,
                        225,233,237,243,250,
                        224,232,236,242,249,
                        227,245,
                        226,234,238,244,251};
        int qtd;
        char v;

        for(int i=0; i<vogais.length; i++){
            qtd = 0;
            v = (char) vogais[i];

            for(int j=0; j<html.length(); j++){
                if(html.charAt(j) == v)
                    qtd++;
            }
            
            if(v == 'a') 
                qtd -= qtdTags(html, "table>");
            else if(v == 'e')    
                qtd -= qtdTags(html, "table>");
            
            MyIO.print(v+"("+qtd+") ");
        }
    } // fim imprimeQtdVogais()

    /**
     * Metodo imprimeQtdConsoantes: conta qtd de consoantes na string html e imprime
     * @param html String 
     */
    public static void imprimeQtdConsoantes(String html){
        String cons = "qwrtypsdfghjklzxcvbnm";
        int qtd = 0;

        for(int j=0; j<html.length(); j++){
            for(int i=0; i<cons.length(); i++){
                if(html.charAt(j) == cons.charAt(i))
                    qtd++;
            }
        }

        int table = qtdTags(html, "table>");
        int br = qtdTags(html,"br>");

        if(table > 0) qtd -= 3;
        if(br > 0) qtd -= 2;

        MyIO.print("consoante("+qtd+") ");
    } // fim qtdConsoantes()

    /**
     * Metodo imprimeTags: imprime qtd de tags (@see qtdTags) "br" e "table" na 
     * string html
     * @param html String
     */
    public static void imprimeTags(String html){
        String br = "br>";
        String table = "table>";

        MyIO.print("<br>("+qtdTags(html,br)+") ");
        MyIO.print("<table>("+qtdTags(html,table)+") ");
    } // fim imprimeTags()

    /**
     * Metodo qtdTags: percorre a string html e quando encontra um '<', verifica se
     * os caracteres seguintes correspondem a string tag enviada
     * @param html String
     * @param tag String
     * @return int qtd 
     */
    public static int qtdTags(String html, String tag){
        int qtd = 0;
        int tam = 0;

        for(int i=0; i<html.length(); i++){
            if(html.charAt(i) == '<'){
                tam = 0;

                for(int j=0; j<tag.length(); j++){
                    i++;
                    
                    if(html.charAt(i) == tag.charAt(j))
                        tam++;
                }

                if(tam == tag.length()) 
                    qtd++;
            }
        }

        return qtd;
    } // fim qtdTags()

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
} // fim TP01q08