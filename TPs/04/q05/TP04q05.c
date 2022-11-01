/**
 * @file TP02q05.c
 * @author Carolina Morais Nigri
 * @version 0.1
 * @date 2022-10-31
 * 
 * @copyright Copyright (c) 2022
 * 
 */
/**
 * @file Game.c
 * @author Pedro Costa
 * @version 0.1
 * @date 2022-09-25
 * 
 * @copyright Copyright (c) 2022
 */
// -------------------------------------------------------------------------------- //
// Includes
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
#include <err.h>
// -------------------------------------------------------------------------------- //
// Definitions
#define MAX_GAMES 2000
#define MAX_FIELD_SIZE 250
#define MAX_STRING_ARRAY_SIZE 100
// -------------------------------------------------------------------------------- //
typedef struct{
    int year,
        month;
} Date;
typedef struct{
    char name[MAX_FIELD_SIZE],
         owners[MAX_FIELD_SIZE],
         website[MAX_FIELD_SIZE],
         developers[MAX_FIELD_SIZE],
         languages[MAX_STRING_ARRAY_SIZE][30],
         genres[MAX_STRING_ARRAY_SIZE][30];
    Date release_date;
    int app_id, age, dlcs, avg_playtime, count_languages, count_genres;
    float price, upvotes;
    bool windows_os, mac_os, linux_os;
} Game;
// Celula
typedef struct Celula {
	Game elemento;        // Elemento inserido na celula
	struct Celula* prox; // Aponta a celula prox
} Celula;
// Pilha
Celula* primeiro;
Celula* ultimo;
// -------------------------------------------------------------------------------- //
// Prototipos
Celula* novaCelula(Game elemento);
void start();
void mostrar();
void inserir(Game game);
Game remover();
bool isFim(char *str);
void substring(char *string, char *string_start, int length);
char *getMonthName(int month);
int getMonthNumber(char *month);
void readFromFileID(char *csvFile, Game *game, int id);
void game_start(Game *game);
void game_print(Game *game);
Game game_clone(Game *game);
void game_read(Game *game, char *line);
int findAppID(char *line);
void createSubstring(char *line, int *index, int *atr_index, char *sub, char c_search);
void findString(char *line, int *index, int *atr_index, char *sub);
// -------------------------------------------------------------------------------- //
// Main
int main(){
    char csvFile[20] = "/tmp/games.csv"; // local do arquivo csv
    char gameID[20] = "";
    int id, i = 0;

    // inicializar Pilha
    start();

    scanf(" %s",gameID); 
    
    // le ids de games ate achar FIM e salva na Pilha
    while(!isFim(gameID)){        
        id = atoi(gameID);

        Game game;
        game_start(&game);

        readFromFileID(csvFile, &game, id);
        
        // salvar na Pilha
        inserir(game);

        scanf(" %s",gameID); 
    }

    // qtd de objetos a serem inseridos/removidos
    int qtd;
    scanf("%i",&qtd);

    char comando[40] = "";
    char sub[20];

    // le insercoes/remocoes e jogo mostra quando remover
    for(int i = 0; i < qtd; i++){
        Game game;
        game_start(&game);
        scanf(" %[^\n]",comando); 
        
        // interpreta e realiza comandos
        if(comando[0] ==  'I'){ // inserir
            substring(sub, &comando[2], (strlen(comando) - 3));
            id = atoi(sub);
            
            readFromFileID(csvFile, &game, id);
            inserir(game);
        } else{ // remover
            printf("(R) %s\n", remover().name);
        }
    }

    // mostra Pilha no final
    mostrar();

    return 0;
}
// -------------------------------------------------------------------------------- //
// Funcoes da Pilha
Celula* novaCelula(Game elemento){
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
  
   return nova;
}
void start(){
    Game empty;
    game_start(&empty);
    
    primeiro = novaCelula(empty);
    ultimo = primeiro;
}
void mostrar(){
    int j = 0;
    for(Celula* i = primeiro->prox; i != NULL; i = i->prox, j++){
        printf("[%i] ", j);
        game_print( &(i->elemento) );
    }
}
void inserir(Game x){
    ultimo->prox = novaCelula(x);
    ultimo = ultimo->prox;
}
Game remover(){
    if(primeiro == ultimo){
        errx(1, "Erro ao remover!");
    } 

    // Caminhar ate a penultima celula:
    Celula* i;
    for(i = primeiro; i->prox != ultimo; i = i->prox);

    Game removido = ultimo->elemento;
    ultimo = i;
    
    free(ultimo->prox);
    i = ultimo->prox = NULL;

    return removido;
}
// -------------------------------------------------------------------------------- //
// Functions
bool isFim(char *str){ 
    // verifica se string eh exatamente "FIM"
    return (strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M'); 
}
void substring(char *string, char *string_start, int length){
    strncpy(string, string_start, length);
    string[length] = '\0';
}
char *getMonthName(int month){
    // retorna nome do mes (3 letras) de acordo com numero
    switch(month){
        case 1:
            return "Jan";
            break;
        case 2:
            return "Feb";
            break;
        case 3:
            return "Mar";
            break;
        case 4:
            return "Apr";
            break;
        case 5:
            return "May";
            break;
        case 6:
            return "Jun";
            break;
        case 7:
            return "Jul";
            break;
        case 8:
            return "Aug";
            break;
        case 9:
            return "Sep";
            break;
        case 10:
            return "Oct";
            break;
        case 11:
            return "Nov";
            break;
        case 12:
            return "Dec";
            break;

        default:
            return "N/A";
            break;
    }
}
int getMonthNumber(char *month){
    int number;
    
    // retorna numero do mes de acordo com 3 letras do nome
    if(!strcmp(month, "Jan")) // strcmp() retorna 0 quando true
        number = 1;
    else if(!strcmp(month, "Feb"))
        number = 2;
    else if(!strcmp(month, "Mar"))
        number = 3;
    else if(!strcmp(month, "Apr"))
        number = 4;
    else if(!strcmp(month, "May"))
        number = 5;
    else if(!strcmp(month, "Jun"))
        number = 6;
    else if(!strcmp(month, "Jul"))
        number = 7;
    else if(!strcmp(month, "Aug"))
        number = 8;
    else if(!strcmp(month, "Sep"))
        number = 9;
    else if(!strcmp(month, "Oct"))
        number = 10;
    else if(!strcmp(month, "Nov"))
        number = 11;
    else if(!strcmp(month, "Dec"))
        number = 12;

    return number;
}
// -------------------------------------------------------------------------------- //
// Class game functions
void readFromFileID(char *csvFile, Game *game, int id){
    FILE *fp;
    char *line = NULL;
    size_t len = 0;
    ssize_t read;
    int arqID;
    bool achou = false;

    fp = fopen(csvFile, "r"); // abre arquivo csv p/leitura

    if(fp == NULL) exit(1); // abertura do arquivo falhou

    // le arquivo csv ate achar game com id procurado
    while( ((read = getline(&line, &len, fp)) != -1) && !achou ){
        arqID = findAppID(line);

        if(arqID == id){
            game_read(game, line);
            
            achou = true;
        }
    }

    fclose(fp); // fecha arquivo
}
void game_start(Game *game){
    // inicializa atributos String 
    strcpy(game->name, "");
    strcpy(game->owners, "");
    strcpy(game->website, "");
    strcpy(game->developers, "");

    // inicizaliza atributos Array de Strings
    for(int i = 0; i < MAX_STRING_ARRAY_SIZE; i++){
        strcpy(game->languages[i], "");
        strcpy(game->genres[i], "");
    }

    // inicializa atributos de data, inteiros, reais, booleanos e contadores
    game->release_date.month = -1;
    game->release_date.year = -1;
    game->app_id = -1;
    game->age = -1;
    game->dlcs = -1;
    game->avg_playtime = -1;
    game->price = -1;
    game->upvotes = -1;
    game->windows_os = false;
    game->mac_os = false;
    game->linux_os = false;
    game->count_languages = 0;
    game->count_genres = 0;
}
void game_print(Game *game){
    // calcula horas e minutos a partir do atributo avg_playtime
    int hours = game->avg_playtime / 60,
        minutes = game->avg_playtime % 60;

    printf("%i %s %s/%04i %s %i %.2f %i [", game->app_id, game->name, getMonthName(game->release_date.month), game->release_date.year, game->owners, game->age, game->price, game->dlcs);

    // imprime array de languages
    for(int i = 0; i < game->count_languages; i++){
        printf("%s%s", game->languages[i], (i < (game->count_languages - 1) ? ", " : ""));
        // coloca virgula ao fim se nao for ultimo elemento
    }

    printf("] %s %s %s %s ", game->website, (game->windows_os ? "true" : "false"), (game->mac_os ? "true" : "false"), (game->linux_os ? "true" : "false"));

    // imprime upvotes
    if(isnan(game->upvotes)) // verifica se nao eh um numero (NaN)
        printf("0.0%% ");
    else
        printf("%.0f%% ", game->upvotes);

    // imprime horas e minutos de avg_playtime
    if(hours > 0){
        printf("%ih ", hours);

        if(minutes > 0)
            printf("%im ", minutes);
    } else{
        if (minutes > 0)
            printf("%im ", minutes);
        else
            printf("null ");
    }

    printf("%s [", game->developers);

    // imprime array de genres
    for(int i = 0; i < game->count_genres; i++){
        printf("%s%s", game->genres[i], (i < (game->count_genres - 1) ? ", " : ""));
        // coloca virgula ao fim se nao for ultimo elemento
    }

    // nova linha
    printf("]\n");
}
Game game_clone(Game *game){ // copia tds atributos de game para o clone
    Game cloned;

    strcpy(cloned.name, game->name);
    strcpy(cloned.owners, game->owners);
    strcpy(cloned.website, game->website);
    strcpy(cloned.developers, game->developers);

    for(int i = 0; i < game->count_languages; i++){
        strcpy(cloned.languages[i], game->languages[i]);
    }
    for(int i = 0; i < game->count_genres; i++){
        strcpy(cloned.genres[i], game->genres[i]);
    }    

    cloned.release_date.month = game->release_date.month;
    cloned.release_date.year = game->release_date.year;
    cloned.app_id = game->app_id;
    cloned.age = game->age;
    cloned.dlcs = game->dlcs;
    cloned.avg_playtime = game->avg_playtime;
    cloned.price = game->price;
    cloned.upvotes = game->upvotes;
    cloned.windows_os = game->windows_os;
    cloned.mac_os = game->mac_os;
    cloned.linux_os = game->linux_os;
    
    return cloned;
}
void game_read(Game *game, char *line){
    char c_search, *sub = (char*) malloc(MAX_FIELD_SIZE);
    int index = 0, atr_index = 0;
    bool achou = false;

    // Find "AppID"
    createSubstring(line, &index, &atr_index, sub, ',');
    game->app_id = atoi(sub); // converte substring p/inteiro e salva atributo

    // Find "Name"
    findString(line, &index, &atr_index, sub);
    strcpy(game->name, sub);

    // Find release date
    if(line[atr_index] != ','){
        // define se vai procurar " ou ,
        if(line[atr_index] == '\"'){
            atr_index++; // atualiza index do atributo
            c_search = '\"';
        } else{
            c_search = ',';
        }

        achou = false;

        while(!achou){
            index++;

            // percorre string ate achar caractere procurado
            if(line[index] == c_search){
                substring(sub, &line[atr_index], (index - atr_index));

                char subDate[10];
                substring(subDate, &sub[0], 3); // substring com nome do mes
                // salva no atributo o numero do mes
                game->release_date.month = getMonthNumber(subDate); 

                if(c_search == ','){ // data tem mes e ano
                    // salva o ano
                    substring(subDate, &sub[4], 4);
                    game->release_date.year = atoi(subDate);
                    
                    index++;
                } else if(c_search == '\"'){ // data tem mes, dia e ano
                    int nmbSpace = 0, i = 0;
                    bool achouAno = false;

                    while(!achouAno){
                        if(sub[i] == ' '){ // conta espacos em brancos
                            nmbSpace++;
                        } 
                        if(nmbSpace == 2){ // salva o ano depois de achar 2 espacos
                            i++;
                            
                            substring(subDate, &sub[i], 4);
                            game->release_date.year = atoi(subDate);
                            
                            achouAno = true;
                        }

                        i++;
                    }
                    
                    index += 2; // pula " e , 
                }

                atr_index = index; // atualiza index p/proximo atributo
                achou = true;
            }
        }
    } else{ // atributo eh nulo
        game->release_date.month = 0;
        game->release_date.year = 0;

        index++; // pula a ,
        atr_index = index; // atualiza index p/proximo atributo
    }
    
    // Find "Owners"
    createSubstring(line, &index, &atr_index, sub, ',');
    strcpy(game->owners, sub);
    
    // Find "Age"
    createSubstring(line, &index, &atr_index, sub, ',');
    game->age = atoi(sub); // converte substring p/inteiro e salva atributo

    // Find "Price"
    createSubstring(line, &index, &atr_index, sub, ',');
    game->price = atof(sub); // converte substring p/real e salva atributo

    // Find "DLCs"
    createSubstring(line, &index, &atr_index, sub, ',');
    game->dlcs = atoi(sub); // converte substring p/inteiro e salva atributo

    // Find "Languages"
    achou = false;
    while(!achou){
        index++;

        // percorre string ate achar inicio de elemento (') ou fim do array (])
        if(line[index] == ']'){
            index++;
            
            // pula a , e/ou "
            if(line[index] == ',')
                index++;
            else if(line[index] == '\"')
                index += 2;
            
            atr_index = index; // atualiza index p/proximo atributo
            achou = true;
        } else if(line[index] == '\''){
            int wordStart = index + 1; // index de inicio de elemento do array
            bool achouElemento = false;

            while(!achouElemento){
                index++;

                // percorre string ate achar fim do elemento (')
                if(line[index] == '\''){
                    substring(sub, &line[wordStart], (index - wordStart));

                    // salva elemento no array e incrementa contador
                    strcpy(game->languages[game->count_languages], sub);
                    game->count_languages++;
                    
                    achouElemento = true;
                }
            }
        }
    }

    // Find "Website"
    findString(line, &index, &atr_index, sub);
    strcpy(game->website, sub);

    // Find "Windows"
    createSubstring(line, &index, &atr_index, sub, ',');
    if(!strcmp(sub, "True")) 
        game->windows_os = true;
   
    // Find "Mac"
    createSubstring(line, &index, &atr_index, sub, ',');
    if(!strcmp(sub, "True")) 
        game->mac_os = true;
  
    // Find "Linux"
    createSubstring(line, &index, &atr_index, sub, ',');
    if(!strcmp(sub, "True")) 
        game->linux_os = true;

    // Find "Upvotes"
    createSubstring(line, &index, &atr_index, sub, ',');
    int positives = atoi(sub); // converte substring p/inteiro e salva atributo
    
    createSubstring(line, &index, &atr_index, sub, ',');
    int negatives = atoi(sub); // converte substring p/inteiro e salva atributo 

    // calcula valor de upvotes
    game->upvotes = (float)(positives * 100) / (float)(positives + negatives);

    // Find "AVG Playtime"
    createSubstring(line, &index, &atr_index, sub, ',');
    game->avg_playtime = atoi(sub); // converte substring p/inteiro e salva atributo

    // Find "Developers"
    findString(line, &index, &atr_index, sub);
    strcpy(game->developers, sub);

    // Find "Genres"
    if(index < (strlen(line) - 1)){ // nao esta na ultima posicao da string
        if(line[index] == ','){ // atributo eh nulo
            index++; // pula a ,
            atr_index = index; // atualiza index p/proximo atributo
        }
        
        if(line[atr_index] == '\"'){ // array tem mais de um elemento
            atr_index++; // atualiza index do atributo
            bool achouFim = false;

            while(!achouFim){
                index++;

                if(line[index] == ','){ // acha o fim do elemento
                    substring(sub, &line[atr_index], (index - atr_index));
                    
                    // salva elemento no array e incrementa contador
                    strcpy(game->genres[game->count_genres], sub);
                    game->count_genres++;

                    index++; // pula a ,
                    atr_index = index; // atualiza index p/proximo elemento
                } else if(line[index] == '\"'){
                    substring(sub, &line[atr_index], (strlen(line) - 1 - atr_index));
                    
                    if(sub[strlen(sub) - 2] == '\"')
                        sub[strlen(sub) - 2] = '\0';
                    
                    // salva elemento no array e incrementa contador
                    strcpy(game->genres[game->count_genres], sub);
                    game->count_genres++;
                    
                    achouFim = true;
                }
            }
        } else{ // array tem so um elemento
            substring(sub, &line[atr_index], (strlen(line) - 1 - atr_index));
            sub[strlen(line) - 2 - atr_index] = '\0';

            // salva elemento no array e incrementa contador
            strcpy(game->genres[game->count_genres], sub);
            game->count_genres++;
        }
    }
}
int findAppID(char *line){
    bool achou = false;
    char sub[MAX_FIELD_SIZE];
    int id, index = 0;
    
    while(!achou){
        index++;

        // percorre string ate achar ','
        if(line[index] == ','){
            substring(sub, &line[0], index); 
            id = atoi(sub); // converte substring p/inteiro e salva id

            achou = true;
        }
    }

    return id;
}
void createSubstring(char *line, int *index, int *atr_index, char *sub, char c_search){
    bool achou = false;

    while(!achou){
        (*index)++;
        
        // percorre string ate achar caractere procurado
        if(line[(*index)] == c_search){
            substring(sub, &line[(*atr_index)], ((*index) - (*atr_index)));

            (*index)++; // pula a ,
            (*atr_index) = (*index); // atualiza index p/proximo atributo
            achou = true;
        }
    }
}
void findString(char *line, int *index, int *atr_index, char *sub){
    char c_search;
    bool achou = false;
    
    if(line[(*atr_index)] != ','){
        // define se vai procurar " ou ,
        if(line[(*atr_index)] == '\"'){
            (*atr_index)++; // atualiza index do atributo
            c_search = '\"';
        } else{
            c_search = ',';
        }

        createSubstring(line, index, atr_index, sub, c_search);
    } else{ // atributo eh nulo
        strcpy(sub, "null");
       
        (*index)++; // pula a ,
        (*atr_index) = (*index); // atualiza index p/proximo atributo
    }
}
