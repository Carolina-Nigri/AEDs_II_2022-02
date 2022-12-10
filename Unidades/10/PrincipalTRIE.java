/**
 * Classe PrincipalTRIE
 */
class PrincipalTRIE {
    /* Main */
    public static void main(String[] args) throws Exception {
        /** Teste ArvoreTRIEv1 **/
        ArvoreTRIEv1 trie1 =  new ArvoreTRIEv1();

        trie1.inserir("abacaxi");
        trie1.inserir("abaco");
        // trie1.inserir("aba"); // erro
        trie1.inserir("pastel");
        // trie1.inserir("pastelaria"); // erro
        trie1.inserir("zoologico");
        
        trie1.mostrar();

        System.out.println( trie1.pesquisar("abacaxi") );
        System.out.println( trie1.pesquisar("abacate") );
        System.out.println( trie1.pesquisar("pastelaria") );

        /** Teste ArvoreTRIEv2 **/
        ArvoreTRIEv2 trie2 =  new ArvoreTRIEv2();

        trie2.inserir("abacaxi");
        trie2.inserir("abaco");
        trie2.inserir("aba"); 
        trie2.inserir("pastel");
        trie2.inserir("pastelaria"); 
        trie2.inserir("zoologico");
        
        trie2.mostrar();

        System.out.println( trie2.pesquisar("abacaxi") );
        System.out.println( trie2.pesquisar("abacate") );
        System.out.println( trie2.pesquisar("pastelaria") );
    }
}
