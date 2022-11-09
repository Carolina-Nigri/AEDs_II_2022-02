// bibliotecas
#include <stdio.h>
#include <stdlib.h>

// No da Arvore
typedef struct No{
    int elemento;
    struct No *esq, *dir;
} No;

// Arvore (No raiz)
No* raiz;

// prototipos
No* novoNo(int elemento);
void inserir(int x);
No* inserirRec(int x, No* i);
void caminharCentral();
No* caminharCentralRec(No* i);
void caminharPos();
No* caminharPosRec(No* i);
void caminharPre();
No* caminharPreRec(No* i);
int getAltura(No* i, int altura);

// principal
int main(){
    raiz = NULL; // start

    // insercoes
    inserir(3);
    inserir(5);
    inserir(1);
    inserir(2);
    inserir(8);
    inserir(11);
    inserir(11);

    // mostrar arvore de formas diferentes
    printf("Caminhamento central\n");
    caminharCentral();
    printf("Caminhamento pos-fixado\n");
    caminharPos();
    printf("Caminhamento pre-fixado\n");
    caminharPre();

    // descobre altura
    printf("Altura = %i\n", getAltura(raiz, 0));

    return 0;
}

// funcoes
No* novoNo(int elemento){
    No* novo = (No*) malloc(sizeof(No));
    novo->elemento = elemento;
    novo->esq = novo->dir = NULL;

    return novo;
}
void inserir(int x){
    raiz = inserirRec(x, raiz);
}
No* inserirRec(int x, No* i){
    if(i == NULL){
        i = novoNo(x);
    } else if(x > (i->elemento)){
        i->dir = inserirRec(x, i->dir);
    } else if(x < (i->elemento)){
        i->esq = inserirRec(x, i->esq);
    } else{
        printf("Elemento ja inserido\n");
    }

    return i;
} 
void caminharCentral(){
    caminharCentralRec(raiz);
}
No* caminharCentralRec(No* i){
    if(i != NULL){
        caminharCentralRec(i->esq);
        printf("%i\n", i->elemento);
        caminharCentralRec(i->dir);
    }
}
void caminharPos(){
    caminharPosRec(raiz);
}
No* caminharPosRec(No* i){
    if(i != NULL){
        caminharPosRec(i->esq);
        caminharPosRec(i->dir);
        printf("%i\n", i->elemento);
    }
}
void caminharPre(){
    caminharPreRec(raiz);    
}
No* caminharPreRec(No* i){
    if(i != NULL){
        printf("%i\n", i->elemento);
        caminharPreRec(i->esq);
        caminharPreRec(i->dir);
    }
}
int getAltura(No* i, int altura){
    if(i == NULL){
        altura--;
    } else{
        printf("altura = %i\n", altura);
        int alturaEsq = getAltura(i->esq, altura+1);
        printf("alturaEsq = %i\n", alturaEsq);
        int alturaDir = getAltura(i->dir, altura+1);
        printf("alturaDir = %i\n", alturaDir);

        if(alturaEsq > alturaDir)
            altura = alturaEsq; 
        else    
            altura = alturaDir;   
        
        printf("altura = %i\n", altura);
    }

    return altura;
}