/** 
 * TP01q02 - Palindromo em C
 * Crie um metodo iterativo que recebe uma string como parametro e retorna true se 
 * essa eh um palindromo. Na saida padrao, para cada linha de entrada, escreva uma 
 * linha de saida com SIM/NAO indicando se a linha eh um palindromo. Destaca-se que 
 * uma linha de entrada pode ter caracteres nao letras. A entrada termina com a 
 * leitura de FIM.
 * Refaca em C a questao anterior.
 * 
 * @author Carolina Morais Nigri
 * @since 15/08/22
 * @version 18/08/22
 */

// bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// prototipos
bool isPalindromo(char frase[]);
bool isFIM(char frase[]);

/** 
 * Funcao principal: le frases ate encontrar FIM (@see isFIM) e verifica se 
 * eh palindromo (@see isPalindromo), mostrando resultados na tela
 * @return 0 int
 */
int main(){
    char frase[500];
    scanf(" %500[^\n]", frase);
    
    while(!isFIM(frase)){ 
        if(isPalindromo(frase))
            puts("SIM");
        else 
            puts("NAO");
        
        scanf(" %500[^\n]", frase);
    }

    return 0;
} // fim main()

/** 
 * Funcao isPalindromo: verifica se frase eh palindromo, comparando os caracteres
 * iniciais com os finais
 * @param frase char[]
 * @return bool palind
 */
bool isPalindromo(char frase[]){
    bool palind = true;
    int tam = strlen(frase);
    int i = 0;

    while(palind && (i < tam/2)){
        if(frase[i] != frase[tam-(i+1)]) 
            palind = false;
        
        i++;
    }

    return palind;
} // fim isPalindromo()

/** 
 * Funcao isFIM: verifica se frase eh "FIM", comparando tamanho (3) e 
 * caractere por caractere ('F','I','M')
 * @param frase char[]
 * @return bool fim
 */
bool isFIM(char frase[]){
    bool fim = false;

    // verifica se string eh igual a "FIM"
    if(strlen(frase) == 3 && frase[0] == 'F' && frase[1] == 'I' && frase[2] == 'M')
        fim = true;
    
    return fim;
} // fim isFIM()
// fim TP01q02