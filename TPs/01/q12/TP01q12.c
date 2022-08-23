/** 
 * TP01q12 - Palindromo em C RECURSIVO
 * 
 * @author Carolina Morais Nigri
 * @since 16/08/22
 * @version 23/08/22
 */

// bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// prototipos
bool isPalindromo(char frase[]);
bool isPalindromoRec(char frase[], int i, int j);
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
 * Metodo isPalindromo: retorna funcao recursiva (@see isPalindromoRec)
 * @param frase String
 * @return boolean isPalindromo()
 */
bool isPalindromo(char frase[]){
    return isPalindromoRec(frase,0,(strlen(frase)-1));
} // fim isPalindromo() 

/** 
 * Metodo isPalindromoRec: verifica se frase eh palindromo, 
 * comparando os caracteres iniciais com os finais recursivamente
 * @param frase String
 * @param i int
 * @param j int
 * @return boolean palind
 */
bool isPalindromoRec(char frase[], int i, int j){
    bool palind = true;

    if(i < j){ 
        if(frase[i] == frase[j]){
            palind = isPalindromoRec(frase, i+1, j-1);
        } else{ 
            palind = false;
        }
    }

    return palind;
} // fim isPalindromoRec()

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
// fim TP01q12
