/** 
 * TP01q09 - Arquivo em Java
 * Faca um programa que leia um numero inteiro n indicando o numero de valores reais
 * que devem ser lidos e salvos sequencialmente em um arquivo texto. Apos a leitura
 * dos valores, devemos fechar o arquivo. Em seguida, reabri-lo e fazer a leitura de 
 * tras para frente usando os metodos getFilePointer e seek da classe RandomAccessFile
 * e mostre todos os valores lidos na tela. Nessa questao, voce nao pode usar, arrays,
 * strings ou qualquer estrutura de dados. A entrada padrao eh composta por um numero
 * inteiro n e mais n numeros reais. A saida padrao corresponde a n numeros reais 
 * mostrados um por linha de saida.
 * 
 * @author Carolina Morais Nigri
 * @since 18/08/22
 * @version 21/08/22
 */

import java.io.RandomAccessFile;

class TP01q09{
    /** 
     * Metodo principal: le a qtde de valores reais que serao lidos, abre um arquivo
     * para escrita e salva os n valores reais lidos, depois fecha o aqruivo. Entao, abre
     * arquivo para leitura e le os valores do arquivo de tras pra frente, mostrando eles
     * na tela
     * @param args String[]
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        int n = MyIO.readInt();
        double val;
        
        RandomAccessFile RAF = new RandomAccessFile("val.txt", "rw");
        
        for(int i=0; i<n; i++){
            val = MyIO.readDouble();
            RAF.writeDouble(val);
        }

        RAF.close();
        
        RAF = new RandomAccessFile("val.txt", "r");

        RAF.seek(RAF.length());

        for(int i=0; i<n; i++){
            RAF.seek(8 * (n - i -1));
            val = RAF.readDouble();

            if((val % 1) == 0)
                MyIO.println((int)val);
            else
                MyIO.println(val);
        }

        RAF.close();
    } // fim main()
} // fim TP01q09