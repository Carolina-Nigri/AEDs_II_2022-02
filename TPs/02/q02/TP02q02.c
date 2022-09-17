/**
 * @author Carolina Morais Nigri
 * @brief TP02 - Q02: Registro Game em C
 */

/** bibliotecas **/
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
#include <time.h>
#include <locale.h>

/** definições **/
#define MAX_STR 100

/** structs (registros) **/
typedef struct tm Date;
typedef struct{
    int app_id, age, dlcs, avg_pt;
    char *name, *owners, *website, *developers;
    Date release_date;
    float price, upvotes;
    char **languages, **genres;
    bool windows, mac, game_linux;
} Game;

/** protótipos **/
// funções gerais
bool isFIM(char*);
int calcRound(float);
int lengthArray(char** array);
void toStringArray(char**);
bool strToBool(char*);
char** createArrayStrings(char*);
int contaVirg(char* str);
int findNextSep(char*);
char* cutString(char*, int, int);
// funções do struct Date
Date parseData(char*);
int mesNum(char*);
// funções do struct Game
Game* clone(Game*);
void print(Game*);
void readFromFilePrintID(Game*, char*, int);
int saveID(char*);
void parseGame(Game*, char*);
void getAttributes(Game*, char**);
// funções de alocação
char* newString();
char* newLongString();
char** newArrayString(int);
Game* newGame();
Game* newGameAtr(int, char*, Date, char*, int, float, int, char**, char*, bool, bool,
                 bool, float, int, char*, char**);

/** função principal **/
// lê ids dos games da entrada padrão até achar FIM, procura id no csv e imprime
int main(){
    char* gameID = newString();
    
    scanf(" %[^\n]",gameID); 
    
    while(!isFIM(gameID)){
        int id = atoi(gameID);
        
        Game* game = newGame();
        readFromFilePrintID(game, "/tmp/games.csv", id);

        scanf(" %[^\n]",gameID); 
    }
    
    return 0;
}

/** funções gerais **/
// verifica se string é igual a "FIM"
bool isFIM(char* str){ 
    bool fim = false;

    if(strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
        fim = true;
    
    return fim;
} 
// arredonda um número real para inteiro mais próximo
int calcRound(float x){ 
    int a = (int) x;

    if((x - a) >= 0.5)
        a += 1;

    return a;
}
// calcula o tamanho de um array de strings
int lengthArray(char** array){
    int length = 0;

    for(int i=0; array[i] != NULL; i++){
        length++;
    }

    return length;
}
// transforma array de strings em string
void toStringArray(char** array){ 
    int length = lengthArray(array);

    printf("[");
    
    if(length == 1){
        printf("%s", array[0]);
    } else{
        for(int j=0; j<length; j++){
            for(int i=0; i<strlen(array[j]); i++){
                printf("%c", *( array[j] + i));
            }
            
            if(j+1 != length)
                printf(", ");
        }
    }
    
    printf("]");
}
// converte string para booleano
bool strToBool(char* str){
    bool boolean;

    if(strcmp(str, "True") == 0) boolean = true;
    else if(strcmp(str, "False") == 0) boolean = false;

    return boolean;
}
// transforma string em array de strings
char** createArrayStrings(char* str) {
    char** array = newArrayString(50);
    int i = 0, j = 0, x = 0;
    int length = strlen(str);

    array[0] = newString();

    if(length > 0){
        if(str[i] == '['){
            i++;
            
            while( (i<length) && (str[i] != ']') ){
                if(str[i] == (char) 39){ // char 39 = '
                    i++;
                    
                    if(str[i] == ','){
                        j++;
                        i+=2;
                        
                        array[j] = newString();
                        x = 0;
                    }
                } else{ 
                    // add uma string do array em array[j] de char em char
                    *( array[j] + x) = str[i];

                    x++;
                    i++;
                }
            }
        } 
        else{
            if(contaVirg(str) > 0){
                while(i < length){
                    // add uma string do array em array[j] de char em char
                    *( array[j] + x) = str[i];

                    x++;
                    i++;
                    if( (i != length) && (str[i] == ',') ){
                        j++;
                        i++;

                        array[j] = newString();
                        x = 0;
                    }
                }
            } else if(contaVirg(str)==0 && j==0){
                str = cutString(str, 0, strlen(str)-1);
                strcpy(array[j], str);
            }
        }
        
        array[j+1] = NULL;
    }

    return array;
}
// conta virgulas numa string
int contaVirg(char* str){
    int virg = 0;

    for(int i=0; i<strlen(str); i++){
        if(str[i] == ',')
            virg++;
    }

    return virg;
}
// acha separador de atributos da string do .csv
int findNextSep(char* strCsv){
    int index = -1;
    bool sep = false;
    int length = strlen(strCsv);

    if(strCsv[0] == '"'){
        index += 2;

        while(strCsv[index] != '"'){
            index++;
        }
        
        index++;
        
        if((index != length) && (strCsv[index] == ','))
                sep = true;

    } else{
        while((index < length) && !sep){
            index++;
            if(strCsv[index] == ',') 
                sep = true;
        }
    }
        
    return index;
}
// cria uma substring
char* cutString(char* str, int first, int last){
    char* res = newLongString();
    int j = 0;

    for(int i=first; i<last; i++){
        res[j] = str[i];

        j++;
    }

    return res;
}

/** funções do struct Date **/
// faz o parse da string data
Date parseData(char* strData){
    Date data;

    data.tm_mon = ( mesNum( cutString(strData, 0, 3) ) - 1 );

    if(strData[6] == ','){
        // "MMM dd, yyyy"
        data.tm_mday = atoi(cutString(strData, 4, 6));
        data.tm_year = atoi(cutString(strData, 8, strlen(strData))) - 1900;
    } else if(strData[5] == ','){
        // "MMM d, yyyy"
        data.tm_mday = atoi(cutString(strData, 4, 5));
        data.tm_year = atoi(cutString(strData, 7, strlen(strData))) - 1900;
    } else{ 
        // "MMM yyyy"
        data.tm_mday = 0;
        data.tm_year = atoi(cutString(strData, 4, strlen(strData))) - 1900;
    }

    return data;
}
// transforma mês por extenso e número
int mesNum(char* mesExt){
    int mes = 0;

    if(strcmp(mesExt, "Jan") == 0) mes = 1;
    else if(strcmp(mesExt, "Feb") == 0) mes = 2;
    else if(strcmp(mesExt, "Mar") == 0) mes = 3;
    else if(strcmp(mesExt, "Apr") == 0) mes = 4;
    else if(strcmp(mesExt, "May") == 0) mes = 5;
    else if(strcmp(mesExt, "Jun") == 0) mes = 6;
    else if(strcmp(mesExt, "Jul") == 0) mes = 7;
    else if(strcmp(mesExt, "Aug") == 0) mes = 8;
    else if(strcmp(mesExt, "Sep") == 0) mes = 9;
    else if(strcmp(mesExt, "Oct") == 0) mes = 10;
    else if(strcmp(mesExt, "Nov") == 0) mes = 11;
    else if(strcmp(mesExt, "Dec") == 0) mes = 12;

    return mes;
}

/** funções do struct Game **/
// clona o struct Game
Game* clone(Game* atual){
    Game* novo = newGameAtr(atual->app_id, atual->name, atual->release_date, 
                            atual->owners, atual->age, atual->price, atual->dlcs, 
                            atual->languages, atual->website, atual->windows, 
                            atual->mac, atual->game_linux, atual->upvotes, 
                            atual->avg_pt, atual->developers, atual->genres);

    return novo;
}
// imprime o struct Game
void print(Game* game){ 
    // formata a data
    char* date = newString();
    strftime(date, (sizeof(char)*MAX_STR), "%b/%Y", &game->release_date);

    // imprime atributos app_id, name, date, owners, age, price, dlcs
    printf("%i %s %s %s %i %.2f %i ", game->app_id, game->name, date, game->owners,
                                      game->age, game->price, game->dlcs);

    // imprime atributo languages[]
    toStringArray(game->languages);
    printf(" ");

    // imprime atributo website, se for vazio imprime "null"
    if(strlen(game->website) == 0) printf("null ");
    else printf("%s ", game->website);
    
    // imprime atributos windows, mac, game_linux como "true" ou "false"
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

    // imprime atributo developers, se for vazio imprime "null"
    if(strlen(game->developers) == 0) printf("null ");
    else printf("%s ", game->developers);
    
    // imprime atributo genres[]
    toStringArray(game->genres);
    
    // nova linha
    puts("");
}
// ler do arquivo e imprimir jogo de ID específico 
void readFromFilePrintID(Game* game, char* file, int id){
    FILE* arq = fopen(file, "r");
    char* strGame = newLongString();
    int arqID;
    bool achou = false;

    while( !feof(arq) && !achou ) {
        fgets(strGame, (sizeof(char) * MAX_STR * 10), arq);

        arqID = saveID(strGame);

        if(id == arqID){
            parseGame(game, strGame);
            print(game);
            
            achou = true;
        }
    }
        
    fclose(arq);
}
// faz o parse somente do ID da string game
int saveID(char* strGame){
    char* gameID = newString();
    int id;
    int i = 0;

    while( strGame[i] != ',' ){
        gameID[i] = strGame[i];
        
        i++;
    }

    id = atoi(gameID);

    return id;
}
// faz o parse da string, separando atributos lidos
void parseGame(Game* game, char* strGame){
    int arrLen = 17;
    char** atr = newArrayString(arrLen); 
    int first;
    int last;
    int gameLen = strlen(strGame);

    // cria array separando atributos 
    for(int i=0; i<arrLen; i++){
        first = 0;
        gameLen = strlen(strGame);
            
        if(gameLen == 0){ 
            atr[i] = strGame;
        } else {
            if(i == 16 && strGame[first] != '"'){
                last = gameLen;
            } else{
                last = findNextSep(strGame);
            }
        
            if(strGame[first] == '"'){
                atr[i] = cutString(strGame, ++first, --last);
                
                first = last + 2;
            } else{
                atr[i] = cutString(strGame, first, last);

                first = last + 1;
            }
          
            if(first < gameLen){
                strGame = cutString(strGame, first, gameLen);
            }
          
        }
    }

    // salva atributos
    getAttributes(game, atr);
}
// salva atributos no objeto
void getAttributes(Game* game, char** atr){
    // inteiros
    game->app_id = atoi(atr[0]);
    game->age = atoi(atr[4]);
    game->dlcs = atoi(atr[6]);
    game->avg_pt = atoi(atr[14]);

    // reais
    game->price = atof(atr[5]);
    game->upvotes = 100 * ( (float) atoi(atr[12]) / ( atoi(atr[12]) + atoi(atr[13]) ) );
    
    // booleanos
    game->windows = strToBool(atr[9]);
    game->mac = strToBool(atr[10]);
    game->game_linux = strToBool(atr[11]);
    
    // strings
    game->name = atr[1];
    game->owners = atr[3];
    game->website = atr[8];
    game->developers = atr[15];

    // arrays de strings
    game->languages = createArrayStrings(atr[7]);
    game->genres = createArrayStrings(atr[16]);
    
    // date
    game->release_date = parseData(atr[2]);
}

/** funções de alocação **/
char* newString(){
	return (char*) malloc(sizeof(char) * MAX_STR);
}
char* newLongString(){
	return (char*) malloc(sizeof(char) * MAX_STR * 100);
}
char** newArrayString(int size){
    return (char**) malloc(size * sizeof (char*));
}
Game* newGame(){
	return (Game*) malloc(sizeof(Game));
}
Game* newGameAtr(int app_id, char* name, Date release_date, char* owners,
                 int age, float price, int dlcs, char** languages,  
                 char* website, bool windows, bool mac, bool game_linux, 
                 float upvotes, int avg_pt, char* developers, char** genres){

    Game* game = newGame();

    game->app_id = app_id;
    game->name = name;
    game->release_date = release_date;
    game->owners = owners;
    game->age = age;
    game->price = price;
    game->dlcs = dlcs;
    game->languages = languages;
    game->website = website;
    game->windows = windows;
    game->mac = mac;
    game->game_linux = game_linux;
    game->upvotes = upvotes;
    game->avg_pt = avg_pt;
    game->developers = developers;
    game->genres = genres;

    return game;
}
