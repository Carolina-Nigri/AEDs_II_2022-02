/** 
 * TP01q07 - Is em C
 * Crie um metodo iterativo que recebe uma string e retorna true se a mesma eh
 * composta somente por vogais. Crie outro metodo iterativo que recebe uma string e 
 * retorna true se a mesma eh composta somente por consoantes. Crie um terceiro 
 * metodo iterativo que recebe uma string e retorna true se a mesma corresponde a um 
 * numero inteiro. Crie um quarto metodo iterativo que recebe uma string e retorna 
 * true se a mesma corresponde a um numero real. Na saida padrao, para cada linha de 
 * entrada, escreva outra de saida da seguinte forma X1 X2 X3 X4 onde cada Xi eh um 
 * booleno indicando se a e entrada eh: composta somente por vogais (X1); 
 * composta somente somente por consoantes (X2); um numero inteiro (X3); um numero 
 * real (X4). Se Xi for verdadeiro, seu valor sera SIM, caso contrario, NAO. 
 * Refaca em C a questao anterior.
 * 
 * @author Carolina Morais Nigri
 * @since 18/08/22
 * @version 18/08/22
 */

// bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// prototipos
bool isVogal(char c);
bool isSoVogais(char frase[]);
bool isConsoante(char c);
bool isSoConsoantes(char frase[]);
bool isNumero(char c);
bool isInteiro(char frase[]);
bool isReal(char frase[]);
bool isFIM(char frase[]);

/** 
 * Funcao principal: le frases ate encontrar FIM (@see isFIM) e verifica se
 * contem somente vogais (@see isSoVogais), contem somente consoantes (@see 
 * isSoConsoantes), eh inteiro (@see isInteiro) ou eh real (@see isReal), 
 * mostrando resultados na tela
 * @return 0 int
 */
int main(){
    char frase[500];
    scanf(" %500[^\n]", frase);

    while(!isFIM(frase)){ 
        if(isSoVogais(frase)) printf("SIM ");
        else  printf("NAO ");
        
        if(isSoConsoantes(frase)) printf("SIM ");
        else  printf("NAO ");

        if(isInteiro(frase)) printf("SIM ");
        else  printf("NAO ");

        if(isReal(frase)) printf("SIM ");
        else  printf("NAO ");

        puts(" ");

        scanf(" %500[^\n]", frase);
    }

    return 0;
} // fim main()

/**
 * Funcao isVogal: verica se um caractere eh vogal (a,e,i,o,u,A,E,I,O,U)
 * @param c char
 * @return bool vogal
 */
bool isVogal(char c){
    bool vogal = false;

    if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U')
        vogal = true;

    return vogal;
} // fim isVogal()

/**
 * Funcao isSoVogais: percorre a frase e verfica se algum caractere nao eh 
 * vogal (@see isVogal) e sai se encontra
 * @param frase char[]
 * @return bool vogal
 */
bool isSoVogais(char frase[]){
    bool vogal = true;
    int i = 0;

    while(vogal && (i < strlen(frase))){
        if(!isVogal(frase[i]))
            vogal = false;

        i++;
    }
    
    return vogal;
} // fim isSoVogais()

/**
 * Funcao isConsoante: verica se um caractere eh uma letra e se nao eh vogal
 * (@see isVogal), logo eh consoante
 * @param c char
 * @return bool cons
 */
bool isConsoante(char c){
    bool cons = false;

    if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')){
        if(!isVogal(c)){
            cons = true;
        }
    }             

    return cons;
} // fim isConsoante()

/**
 * Funcao isSoConsoantes: percorre a frase e verfica se algum caractere nao eh 
 * consoante (@see isConsoante) e sai se encontra
 * @param frase char[]
 * @return bool cons
 */
bool isSoConsoantes(char frase[]){
    bool cons = true;
    int i = 0;

    while(cons && (i < strlen(frase))){
        if(!isConsoante(frase[i]))
            cons = false;

        i++;
    }

    return cons;
} // fim isSoConsoantes()

/**
 * Funcao isNumero: verica se um caractere eh numero (0,1,2,3,4,5,6,7,8,9)
 * @param c char
 * @return bool numero
 */
bool isNumero(char c){
    bool numero = false;

    if(c=='0'||c=='1'||c=='2'||c=='3'||c=='4'||c=='5'||c=='6'||c=='7'||c=='8'||c=='9')
        numero = true;

    return numero;
} // fim isNumero()

/**
 * Funcao isInteiro: percorre a frase e verfica se algum caractere nao eh 
 * numero (@see isNumero) e sai se encontra 
 * @param frase char[]
 * @return bool inteiro
 */
bool isInteiro(char frase[]){
    bool inteiro = true;
    int i = 0;

    while(inteiro && (i < strlen(frase))){
        if(!isNumero(frase[i]))
            inteiro = false;

        i++;
    }

    return inteiro;
} // fim isInteiro()

/**
 * Funcao isReal: percorre a frase e conta caracteres iguais a numero 
 * (@see isNumero) e conta '.' ou ','. Se a frase conter somente numeros (inteiro)
 * ou numeros e somente um '.' ou ',', eh real
 * @param frase char[]
 * @return bool real
 */
bool isReal(char frase[]){
    bool real = false;
    int tam = strlen(frase);
    int num = 0;
    int pontoVirg = 0;
    
    for(int i=0; i < tam; i++){
        if(isNumero(frase[i])){
            num++;
        } else if((frase[i] == '.') || (frase[i] == ',')){
            pontoVirg++;
        }
    }

    if((num == tam) || (num == (tam - 1) && pontoVirg == 1)) 
        real = true;

    return real;
} // fim isReal()

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
// fim TP01q07