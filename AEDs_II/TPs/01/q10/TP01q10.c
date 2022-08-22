/** 
 * TP01q10 - Arquivo em C
 * Faca um programa que leia um numero inteiro n indicando o numero de valores reais
 * que devem ser lidos e salvos sequencialmente em um arquivo texto. Apos a leitura
 * dos valores, devemos fechar o arquivo. Em seguida, reabri-lo e fazer a leitura de 
 * tras para frente usando os metodos getFilePointer e seek da classe RandomAccessFile
 * e mostre todos os valores lidos na tela. Nessa questao, voce nao pode usar, arrays,
 * strings ou qualquer estrutura de dados. A entrada padrao eh composta por um numero
 * inteiro n e mais n numeros reais. A saida padrao corresponde a n numeros reais 
 * mostrados um por linha de saida.
 * Refaca a questao anterior na linguagem C.
 * 
 * @author Carolina Morais Nigri
 * @since 18/08/22
 * @version 21/08/22
 */

// bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

/** 
 * Funcao principal: le a qtde de valores reais que serao lidos, abre um arquivo
 * para escrita e salva os n valores reais lidos, depois fecha o aqruivo. Entao, abre
 * arquivo para leitura e le os valores do arquivo de tras pra frente, mostrando eles
 * na tela
 * @return 0 int
 */
int main(){
    int n;
    scanf("%i",&n);
    FILE* arq = fopen("val.txt","wb");
    
    double val;

    for(int i=0; i<n; i++){
        scanf("%lf",&val);
        fwrite(&val,sizeof(double),1,arq);
    }

    fclose(arq);

    arq = fopen("val.txt","rb");

    fseek(arq,0,SEEK_SET);

    for(int i=n; i>0; i--){
        fseek(arq, (8 * (i-1)), SEEK_SET);
        fread(&val, sizeof(double), 1, arq);
        printf("%g\n",val);
    }

    fclose(arq);

    return 0;
} // fim main()
// fim TP01q10