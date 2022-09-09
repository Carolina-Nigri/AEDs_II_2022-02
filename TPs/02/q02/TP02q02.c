// bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>

// definições
#define MAX_LIN 100 
#define MAX_COL 50

// structs (registros)
typedef struct String{
    char chars[MAX_LIN];
} String;

typedef struct LongString{
    char chars[MAX_LIN * 10];
} LongString;

/*
 * !!TODO : achar forma mais fácil de fazer data em C
 */
typedef struct Date{ 
    int dia, mes, ano;
} Date;

/*
 * !!TODO : fazer uma array de strings, alocando dinamicamente, com tamanhos variados,
 * de forma q consiga ler até o fim do array (languages, genres)
 */
typedef struct Game{
    int app_id, age, dlcs, avg_pt;
    String *name, *owners, *website, *developers;
    Date* release_date;
    float price, upvotes;
    // String* languages[MAX_COL], genres[MAX_COL];
    bool windows, mac, game_linux;
} Game;

// protótipos
bool isFIM(String* str);
int calcRound(float x);
Game* clone(Game* atual);
void print(Game* game);
String* newString();
LongString* newLongString();
Date* newDate();
Game* newGame();
Game* newGameAtr(int app_id, String* name, Date* release_date,  String* owners,
                 int age, float price, int dlcs, /*String[] languages,*/  
                 String* website, bool windows, bool mac, bool game_linux, 
                 float upvotes, int avg_pt, String* developers/*, String*[] genres*/);

// função principal
int main(){
    Date* d = newDate();
    d->dia = 9;
    d->mes = 9;
    d->ano = 2022;

    Game* game = newGameAtr(1,"CS",d,"abc",10,2.50,2,"https://www.google.com/",true,false, false, 88.97564,150,"a,b,c");

    print(game);

    return 0;
}

// funções de uso geral
bool isFIM(String* str){ // verifica se string é igual a "FIM"
    bool fim = false;

    if(strlen(str->chars) == 3 && str->chars[0] == 'F' && str->chars[1] == 'I' && str->chars[2] == 'M')
        fim = true;
    
    return fim;
} 
int calcRound(float x){ // arredonda um número real para inteiro mais próximo
    int a = (int) x;

    if((x - a) >= 0.5)
        a += 1;

    return a;
}

// funções do struct Game
/**
 * !!TODO : incluir atributos String[] languages e genres 
 * em todas as funções do struct Game
 */
Game* clone(Game* atual){ // clona o struct Game
    Game* novo = newGameAtr(atual->app_id, atual->name, atual->release_date, 
                            atual->owners, atual->age, atual->price, atual->dlcs, 
                            /*atual->languages,*/ atual->website, atual->windows, 
                            atual->mac, atual->game_linux, atual->upvotes, 
                            atual->avg_pt, atual->developers/*, atual->genres*/);

    return novo;
}
/**
 * !!TODO: formatar data p/imprimir
 */
void print(Game* game){ // imprime o struct Game
    // formata a data
    // SimpleDateFormat formatter = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);
    // String date = formatter.format(release_date);

    String* date = "MMM/yyyy";

    // imprime atributos app_id, name, date, owners, age, price e s
    printf("%i %s %s %s %i %.2f %i ", game->app_id, game->name, date, game->owners,
                                         game->age, game->price, game->dlcs);

    // imprime atributo languages[]
    // printf("%s ", toStringArray(game->languages));

    // imprime atributo website, se for vazio imprime "null"
    if(game->website == "") printf("null ");
    else printf("%s ", game->website);
    
    // imprime atributos windows, mac, linux como "true" ou "false"
    if(game->windows) printf("true ");
    else printf("false ");
    if(game->mac) printf("true ");
    else printf("false ");
    if(game->game_linux) printf("true ");
    else printf("false ");

    // imprime upvotes arredondado
    printf("%i%% ", calcRound(game->upvotes)); 
    
    // imprime atributo avg_pt em h e min, se for 0 imprime "null"
    if(game->avg_pt == 0){
        printf("null ");
    } else {
        if((game->avg_pt / 60) != 0) printf("%ih ", (game->avg_pt / 60));
        if((game->avg_pt % 60) != 0) printf("%im ", (game->avg_pt % 60));
    } 

    // imprime atributo website, se for vazio imprime "null"
    if(game->developers == "") printf("null ");
    else printf("%s ", game->developers);
    
    // imprime atributo genres[]
    // printf(toStringArray(game->genres));
    
    // nova linha
    puts("");
}

// funções de alocação
String* newString(){
	return (String*) malloc(sizeof(String));
}
LongString* newLongString(){
	return (LongString*) malloc(sizeof(LongString));
}
Date* newDate(){
	return (Date*) malloc(sizeof(Date));
}
Game* newGame(){
	return (Game*) malloc(sizeof(Game));
}
/**
 * !!TODO : incluir atributos String[] languages e genres
 */
Game* newGameAtr(int app_id, String* name, Date* release_date, String* owners,
                 int age, float price, int dlcs, /*String[] languages,*/  
                 String* website, bool windows, bool mac, bool game_linux, 
                 float upvotes, int avg_pt, String* developers/*, String*[] genres*/){

    Game* game = newGame();

    game -> app_id = app_id;
    game -> name = name;
    game -> release_date = release_date;
    game -> owners = owners;
    game -> age = age;
    game -> price = price;
    game -> dlcs = dlcs;
    // game -> languages = languages;
    game -> website = website;
    game -> windows = windows;
    game -> mac = mac;
    game -> game_linux = game_linux;
    game -> upvotes = upvotes;
    game -> avg_pt = avg_pt;
    game -> developers = developers;
    // game -> genres = genres;

    return game;
}
