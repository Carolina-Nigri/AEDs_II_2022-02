/* Carolina Morais Nigri
 * TP01q02 - Palindromo em C
 */

// bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// prototipos
bool isPalindromo(char frase[]);
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
    bool palind = true;
    int tam = strlen(frase);
    int i = 0;

    // verifica se primeiros char da string sao iguais aos ultimos
    while(palind && (i < tam/2)){
        if(frase[i] != frase[tam-(i+1)]) 
            palind = false;
        
        i++;
    }

    return palind;
} // fim isPalindromo()

bool isFIM(char frase[]){
    bool fim = false;

    // verifica se string eh igual a "FIM"
    if(strlen(frase) == 3 && frase[0] == 'F' && frase[1] == 'I' && frase[2] == 'M')
        fim = true;
    
    return fim;
} // fim isFIM()
// fim TP01q02