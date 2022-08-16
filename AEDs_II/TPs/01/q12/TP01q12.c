/* Carolina Morais Nigri
 * TP01q12 - Palindromo em C RECURSIVO
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

int main(){
    char frase[500];

    scanf(" %500[^\n]", frase); // le frase
    
    while(!isFIM(frase)){ // sai quando frase = "FIM"
        // se eh palindromo, imprime "SIM", se nao, "NAO"
        if(isPalindromo(frase))
            puts("SIM");
        else 
            puts("NAO");
        
        scanf(" %500[^\n]", frase); // le frase
    }

    return 0;
} // fim main()

bool isPalindromo(char frase[]){
    return isPalindromoRec(frase,0,(strlen(frase)-1));
} // fim isPalindromo() 

bool isPalindromoRec(char frase[], int i, int j){
    bool palind = true;

    // compara os primeiros char c/os ultimos, ate q o i encontre o j
    if(i < j){ 
        if(frase[i] == frase[j]){
            palind = isPalindromoRec(frase, i+1, j-1);
        } else{ 
            palind = false;
        }
    }

    return palind;
} // fim isPalindromoRec()

bool isFIM(char frase[]){
    bool fim = false;

    // verifica se string eh igual a "FIM"
    if(strlen(frase) == 3 && frase[0] == 'F' && frase[1] == 'I' && frase[2] == 'M')
        fim = true;
    
    return fim;
} // fim isFIM()
// fim TP01q12