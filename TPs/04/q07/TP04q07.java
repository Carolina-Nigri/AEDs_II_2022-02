/**
 * TP04Q07 - Matriz Dinamica em Java
 * @author Carolina Morais Nigri
 * @version 06/11/22
 */

import java.io.*;

// classe principal/de execucao
public class TP04q07{
    /**
     * Le numero de casos de teste. Para cada caso, cria duas matrizes a partir
     * de linhas e colunas lidas, mostra diagonais principal e secundaria da primeira
     * matriz e matrizes resultantes da soma e da multiplicacao das duas matrizes
     */
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // primeira linha = qtd de casos de teste
        int qtd = Integer.parseInt( input.readLine() );
        
        // repete para cada caso
        for(int k = 0; k < qtd; k++){
            /** Primeira Matriz **/
            // qtd de linhas e de colunas da matriz
            int l = Integer.parseInt( input.readLine() );
            int c = Integer.parseInt( input.readLine() ); 

            // instacia de matriz
            Matriz m1 = new Matriz(l, c);
            
            String linha = ""; 
            String[] colunas = new String[c];
            
            // le e insere elementos
            for(int i = 0; i < l; i++){
                linha = input.readLine();
                colunas = linha.split(" ");
                
                for(int j = 0; j < c; j++){
                    int e = Integer.parseInt(colunas[j]);
                    m1.inserir( e, i, j );
                }
            }

            l = c = 0;
            linha = "";
            colunas = null;

            /** Segunda Matriz **/
            // qtd de linhas e de colunas da matriz
            l = Integer.parseInt( input.readLine() );
            c = Integer.parseInt( input.readLine() ); 

            // instacia de matriz
            Matriz m2 = new Matriz(l, c);
            
            colunas = new String[c];
            
            // le e insere elementos
            for(int i = 0; i < l; i++){
                linha = input.readLine();
                colunas = linha.split(" ");
                
                for(int j = 0; j < c; j++){
                    int e = Integer.parseInt(colunas[j]);
                    m2.inserir( e, i, j );
                }
            }

            /** Operacoes sobre matrizes **/
            // imprime diagonais principal e secundaria da primeira matriz
            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria(); 

            // mostra a matriz resultante da soma de m1 e m2
            Matriz soma = m1.soma(m2);
            soma.mostrar();
            
            // mostra a matriz resultante da multiplicacao de m1 e m2
            Matriz mult = m1.multiplicacao(m2);
            mult.mostrar();
        
            m1 = m2 = null;
        }

        input.close();
    }
}

/** Classe Celula **/
class Celula{
    /** Atributos **/
    public int elemento; // elemento da celula
    public Celula inf, sup, esq, dir; // ponteiros para outras celulas da matriz

    /** Construtores **/
    public Celula(){
        this(0);
    }
    public Celula(int elemento){
        this(elemento, null, null, null, null);
    }
    public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
    }
}

/** Classe Matriz **/
class Matriz{
    /** Atributos **/
    private Celula inicio; // aponta para elemento(0,0) da matriz
    private int linha, coluna; // numero de linhas e colunas alocadas 

    /** Construtores **/
    public Matriz(){
        this(3, 3);
    }
    public Matriz(int linha, int coluna){
        this.linha = linha;
        this.coluna = coluna;
        this.inicio = null;

        // alocar a matriz de acordo com numeros de linhas e colunas definido
        alocaMatriz();
    }

    /** Metodos **/
    /**
     * Aloca espaco de memoria para matriz a partir dos numeros
     * de linhas e colunas definidos
     */
    private void alocaMatriz(){
        // aloca primeira celula e aponta inicio
        this.inicio = new Celula();

        Celula lin = inicio; // primeira linha = inicio
        for(int i = 1; i <= this.linha; i++, lin = lin.inf){ // percorre linhas
            Celula col = lin; // coluna inicializa na primeira celula da linha 
           
            for(int j = 1; j < this.coluna; j++, col = col.dir){ // percorre colunas
                // cria celula (coluna) e faz apontamentos para direita e esquerda
                Celula tmp = new Celula();
                col.dir = tmp;
                tmp.esq = col;

                if(i >= 2){ // a partir da segunda linha
                    // faz apontamentos para cima e baixo
                    tmp.sup = col.sup.dir;
                    col.sup.dir.inf = tmp;
                }

                tmp = null;
            }
            
            if(i != this.coluna){ // se nao for a ultima coluna
                // cria celula (linha) e faz  apontamentos para cima e baixo
                Celula tmp = new Celula();
                lin.inf = tmp;
                tmp.sup = lin;

                tmp = col = null;
            }
        }
        lin = null;
    }
    /**
     * Imprime elementos da matriz
     */
    public void mostrar(){
        Celula pont;
       
        for(int i = 0; i < this.linha; i++){
            pont = this.inicio; // volta ao inicio

            // caminha para baixo na matriz ate linha atual
            for(int k = 0; k < i; k++){
                pont = pont.inf;
            }
            
            for(int j = 0; j < this.coluna; j++, pont = pont.dir){
                System.out.print(pont.elemento+" ");
                
                if(j == (this.coluna - 1))
                    System.out.println(""); // nova linha
            }
        }
    }
    /**
     * Insere um elemento na matriz na posicao (lin, col)
     * @param elemento a ser inserido
     * @param lin linha de insercao
     * @param col coluna de insercao
     */
    public void inserir(int elemento, int lin, int col){
        Celula pont = this.inicio; // iniciliza ponteiro no inicio da matriz

        // caminha para baixo na matriz ate linha de insercao
        for(int i = 0; i < lin; pont = pont.inf, i++);

        // caminha para direita na matriz ate coluna de insercao 
        for(int j = 0; j < col; pont = pont.dir, j++);
        
        pont.elemento = elemento;
    }
    /**
     * Verifica se uma matriz tem o mesmo tamanho da outra
     * @param mat matriz com a qual se esta comparando 
     * @return true se forem de mesmo tamanho, false caso contrario 
     */
    public boolean temMesmoTam(Matriz mat){
        return (this.linha == mat.linha && this.coluna == mat.coluna);
    }
    /**
     * Soma elementos da matriz com os de outra (parametro), 
     * salvando resultados em uma nova matriz
     * @param mat Matriz com a qual se ira somar
     * @return matSoma Matriz com resultado da soma entre duas matrizes 
     */
    public Matriz soma(Matriz mat){
        Matriz matSoma = null;
        
        // matrizes precisam ser de msm tamanho p/somar
        if(this.temMesmoTam(mat)){
            matSoma = new Matriz(this.linha, this.coluna);
            
            Celula a, b, c;
            for(int i = 0; i < this.linha; i++){ // percorre linhas
                // retorna ponteiros das matrizes para inicio
                a = this.inicio;
                b = mat.inicio;
                c = matSoma.inicio;
                
                // caminha para baixo nas matrizes ate linha atual
                for(int k = 0; k < i; k++){
                    a = a.inf;
                    b = b.inf;
                    c = c.inf;
                }

                for(int j = 0; j < this.coluna; j++){ // percorre colunas
                    // soma elementos de a e b p/salvar em c
                    c.elemento = a.elemento + b.elemento;

                    // caminha para direita
                    a = a.dir;
                    b = b.dir;
                    c = c.dir;
                }
            }
            a = b = c = null;
        }
   
        return matSoma;
    }
    /**
     * Verifica se uma matriz tem o mesmo numero de colunas que o numero
     * de linhas da outra
     * @param mat matriz com a qual se esta comparando 
     * @return true ou false 
     */
    public boolean colIgualLin(Matriz mat){
        return (this.coluna == mat.linha);
    }
    /**
     * Multiplica elementos da matriz com os de outra (parametro), 
     * salvando resultados em uma nova matriz
     * @param mat Matriz com a qual se ira multiplicar
     * @return matMult Matriz com resultado da multiplicacao entre duas matrizes 
     */
    public Matriz multiplicacao(Matriz mat){
        Matriz matMult = null;
    
        // primeira matriz precisa ter msm numero de colunas q linhas da segunda
        if(this.colIgualLin(mat)){
            matMult = new Matriz(this.linha, mat.coluna);
            
            Celula a, b, c;
            for(int i = 0; i < this.linha; i++){ // percorre linhas de A
                for(int k = 0; k < mat.coluna; k++){ // percorre colunas de B
                    a = this.inicio;
                    b = mat.inicio;
                    c = matMult.inicio;

                    // move a e c, i vezes p/baixo (linha a ser multiplicada)
                    for(int lin = 0; lin < i; lin++){ 
                        a = a.inf;
                        c = c.inf;
                    }
                    
                    // move b e c, k vezes p/direita (coluna a ser multiplicada)
                    for(int col = 0; col < k; col++){
                        b = b.dir;
                        c = c.dir;
                    }
                    
                    for(int j = 0; j < this.coluna; j++){ // percorre colunas de A
                        c.elemento += a.elemento * b.elemento;

                        a = a.dir; // move p/prox elemento da linha de A
                        b = b.inf; // move p/prox elemento da coluna de B
                    }
                }
            }
            a = b = c = null;
        }
    
        return matMult;
    }
    /**
     * Verifica se a matriz eh quadrada (numero de linhas igual ao de colunas)
     * @return true se for quadrada, false caso contrario 
     */
    public boolean ehQuadrada(){
        return (this.linha == this.coluna);
    }
    /**
     * Imprime elementos na diagonal principal da matriz
     */
    public void mostrarDiagonalPrincipal(){
        if(ehQuadrada()){ // matriz precisa ser quadrada para ter diagonal
            Celula pont = inicio;

            // diagonal principal: i == j
            for(int i = 0; i < this.linha; i++){
                System.out.print(pont.elemento+" ");

                // caminha pra proxima celula se existir
                if(pont.inf != null)
                    pont = pont.inf.dir;
            }

            System.out.println("");
        }
    }
    /**
     * Imprime elementos na diagonal secundaria da matriz
     */
    public void mostrarDiagonalSecundaria(){
        if(ehQuadrada()){ // matriz precisa ser quadrada para ter diagonal
            Celula pont = inicio;

            // posiciona ponteiro na ultima celula da primeira linha
            for(int j = 1; j < this.coluna; j++){
                pont = pont.dir;
            }

            // diagonal secundaria: i (0..n-1) e j (n-1..0)
            for(int i = 0; i < this.linha; i++){
                System.out.print(pont.elemento+" ");

                // caminha pra proxima celula se existir
                if(pont.inf != null)
                    pont = pont.inf.esq;
            }

            System.out.println("");
        }
    }
}
