/**
 * @file TP04q06.c
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
#include <time.h>
// -------------------------------------------------------------------------------- //
// Definitions
#define MAX_GAMES 2000
#define MAX_FIELD_SIZE 250
#define MAX_STRING_ARRAY_SIZE 100
// -------------------------------------------------------------------------------- //
typedef struct{
    int year,
        month,
        day;
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
// CelulaDupla
typedef struct CelulaDupla{
	Game elemento;        // Elemento inserido na celula
	struct CelulaDupla* prox; // Aponta a celula prox
    struct CelulaDupla* ant;  // Aponta a celula anterior
} CelulaDupla;
// ListaDupla
CelulaDupla* primeiro;
CelulaDupla* ultimo;
int comp, mov;
// -------------------------------------------------------------------------------- //
// Prototipos
void ordena();
void quicksort(int esq, int dir);
bool equals(Date a, Date b);
bool before(Date a, Date b);
bool after(Date a, Date b);
bool dataENomeAntes(Game i, Game pivo);
bool dataENomeDepois(Game j, Game pivo);
CelulaDupla* celulaPos(int pos);
void swap(int i, int j);
bool vemAntes(char *str1, char *str2);
CelulaDupla* novaCelulaDupla(Game elemento);
void start();
int tamanho();
void mostrar();
void inserirInicio(Game x);
void inserirFim(Game x);
void inserir(Game x, int pos);
Game removerInicio();
Game removerFim();
Game remover(int pos);
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
    double time = 0.0;
    clock_t begin = clock(); // tempo de inicio
    FILE* logFile = fopen("matricula_quicksort2.txt","w");
    char csvFile[20] = "/tmp/games.csv"; // local do arquivo csv
    char gameID[20] = "";
    int id, i = 0;

    // inicializar ListaDupla
    start();

    scanf(" %s",gameID); 
    
    // le ids de games ate achar FIM e salva na ListaDupla
    while(!isFim(gameID)){        
        id = atoi(gameID);

        Game game;
        game_start(&game);

        readFromFileID(csvFile, &game, id);

        // salvar na ListaDupla
        inserirFim(game);

        scanf(" %s",gameID); 
    }

    // ordena a ListaDupla 
    ordena();

    // mostra ListaDupla no final
    mostrar();

    clock_t end = clock(); // tempo de fim
    time += (double)(end - begin) / CLOCKS_PER_SEC; // calcula tempo de execucao
    fprintf(logFile, "761400\t%i\t%i\t%lf", comp, mov, time);
    fclose(logFile);

    return 0;
}
// -------------------------------------------------------------------------------- //
// Ordenação
void ordena(){
    comp = mov = 0;
    quicksort(0, (tamanho() - 1));
}
void quicksort(int esq, int dir){
    int i = esq, j = dir;
    Game pivo = celulaPos((dir + esq) / 2)->elemento;
    
    while(i <= j){
        // data e nome vem antes da do pivo
        while(dataENomeAntes(celulaPos(i)->elemento, pivo)){
            comp++;
            i++;
        }
        
        // data e nome vem depois da do pivo
        while(dataENomeDepois(celulaPos(j)->elemento, pivo)){
            comp++;
            j--;
        }
        
        if(i <= j){
            swap(i, j);
            mov+=3;
            
            i++;
            j--;
        }
    }

    if(esq < j)  
        quicksort(esq, j);
    if(i < dir)  
        quicksort(i, dir);
}
bool equals(Date a, Date b){
    return ( (a.month == b.month) && (a.year == b.year) && (a.day == b.day) );
}
bool before(Date a, Date b){
    bool bef = false;

    if(a.year < b.year){
        bef = true;
    } else if(a.year == b.year){
        if(a.month < b.month){
            bef = true;
        } else if(a.month == b.month){
            if(a.day < b.day)
                bef = true;
        }
    }

    return bef;
}
bool after(Date a, Date b){
    bool aft = false;

    if(a.year > b.year){
        aft = true;
    } else if(a.year == b.year){
        if(a.month > b.month){
            aft = true;
        } else if(a.month == b.month){
            if(a.day > b.day)
                aft = true;
        }
    }
    
    return aft;
}
bool dataENomeAntes(Game i, Game pivo){
    bool antes = false;

    if( before(i.release_date, pivo.release_date) ){
        antes = true;
    } else if( equals(i.release_date, pivo.release_date) 
               && vemAntes(i.name, pivo.name) ){
        antes = true;
    }

    return antes;
}
bool dataENomeDepois(Game j, Game pivo){
    bool depois = false;
    
    if( after(j.release_date, pivo.release_date) ){
        depois = true;
    } else if( equals(j.release_date, pivo.release_date) 
               && vemAntes(pivo.name, j.name) ){
        depois = true;
    }

    return depois;
}
CelulaDupla* celulaPos(int pos){
    if(primeiro == ultimo){
        errx(1, "Lista vazia");
    } 
    if(pos < 0 || pos > (tamanho()-1)){
        errx(1, "Posicao invalida!");
    }
    
    CelulaDupla* i;
    
    if(pos == 0){
        i = primeiro->prox;
    } else if(pos == (tamanho() - 1)){
        i = ultimo;
    } else{
        i = primeiro->prox;
        for(int j = 0; j < pos; j++, i = i->prox);
    }

    return i;
}
void swap(int i, int j){
    CelulaDupla* cel_i = celulaPos(i);
    CelulaDupla* cel_j = celulaPos(j);

    Game tmp = cel_i->elemento;
    cel_i->elemento = cel_j->elemento;
    cel_j->elemento = tmp;
}
bool vemAntes(char *str1, char *str2){
    // verifica se a primeira string vem antes da segunda (ordem alfabetica)
    bool primeira = false, saiu = false;
    int maior = strlen(str1), menor = strlen(str2);
    
    // salva tamanhos das strings
    if(strlen(str1) < strlen(str2)){
        maior = strlen(str2);
        menor = strlen(str1); 
        primeira = true;
    } 
    
    int i = 0;
    
    while( i < maior && !saiu ){
        if(i < menor){
            if(str1[i] != str2[i]){
                if(str1[i] >= 0 && str2[i] >= 0 || str1[i] < 0 && str2[i] < 0){
                    if(str1[i] < str2[i])
                        primeira = true;
                    else
                        primeira = false;

                    saiu = true;
                } else{
                    if(str1[i] > str2[i])
                        primeira = true;
                    else
                        primeira = false;
                    
                    saiu = true;
                }
            }

            i++;
        } else{
            saiu = true;
        }
    }

    return primeira;
}
// -------------------------------------------------------------------------------- //
// Funcoes da ListaDupla
CelulaDupla* novaCelulaDupla(Game elemento){
   CelulaDupla* nova = (CelulaDupla*) malloc(sizeof(CelulaDupla));
   nova->elemento = elemento;
   nova->ant = nova->prox = NULL;
  
   return nova;
}
void start(){
    Game empty;
    game_start(&empty);
    
    primeiro = novaCelulaDupla(empty);
    ultimo = primeiro;
}
int tamanho(){
    int tamanho = 0; 
    for(CelulaDupla* i = primeiro; i != ultimo; i = i->prox){
        tamanho++;
    }
   
    return tamanho;
}
void mostrar(){
    int j = 0;
    for(CelulaDupla* i = primeiro->prox; i != NULL; i = i->prox, j++){
        game_print( &(i->elemento) );
    }
}
void inserirInicio(Game x){
   CelulaDupla* tmp = novaCelulaDupla(x);

    tmp->ant = primeiro;
    tmp->prox = primeiro->prox;
    primeiro->prox = tmp;
   
    if(primeiro == ultimo){                    
        ultimo = tmp;
    } else{
        tmp->prox->ant = tmp;
    }
    
    tmp = NULL;
}
void inserirFim(Game x){
    ultimo->prox = novaCelulaDupla(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}
void inserir(Game x, int pos){
    int tam = tamanho();

    if(pos < 0 || pos > tam){
        errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
    } else if (pos == 0){
        inserirInicio(x);
    } else if (pos == tam){
        inserirFim(x);
    } else{
        // Caminhar ate a posicao anterior a insercao
        CelulaDupla* i = primeiro;
        for(int j = 0; j < pos; j++, i = i->prox);

        CelulaDupla* tmp = novaCelulaDupla(x);
        tmp->ant = i;
        tmp->prox = i->prox;
        tmp->ant->prox = tmp->prox->ant = tmp;
        tmp = i = NULL;
    }
}
Game removerInicio(){
    if (primeiro == ultimo){
        errx(1, "Erro ao remover (vazia)!");
    }

    CelulaDupla* tmp = primeiro;
    primeiro = primeiro->prox;
    Game removido = primeiro->elemento;
    
    tmp->prox = primeiro->ant = NULL;
    free(tmp);
    tmp = NULL;

    return removido;
}
Game removerFim(){
    if (primeiro == ultimo){
        errx(1, "Erro ao remover (vazia)!");
    } 

    Game removido = ultimo->elemento;
    ultimo = ultimo->ant;
    
    ultimo->prox->ant = NULL;
    free(ultimo->prox);
    ultimo->prox = NULL;
    
    return removido;
}
Game remover(int pos){
    Game removido;
    int tam = tamanho();

    if(primeiro == ultimo){
        errx(1, "Erro ao remover (vazia)!");
    } else if(pos < 0 || pos >= tam){
        errx(1, "Erro ao remover (posicao %d/%d invalida!", pos, tam);
    } else if (pos == 0){
        removido = removerInicio();
    } else if (pos == tam - 1){
        removido = removerFim();
    } else{
        // Caminhar ate a posicao anterior a insercao
        CelulaDupla* i = primeiro->prox;
        for(int j = 0; j < pos; j++, i = i->prox);

        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        removido = i->elemento;
        
        i->prox = i->ant = NULL;
        free(i);
        i = NULL;
    }

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

    // inicializa atributos de data, inteiros, reais, boolos e contadores
    game->release_date.month = -1;
    game->release_date.year = -1;
    game->release_date.day = -1;
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
    cloned.release_date.day = game->release_date.day;
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
                    game->release_date.day = 0; // dia vazio
                    
                    index++;
                } else if(c_search == '\"'){ // data tem mes, dia e ano
                    int nmbSpace = 0, i = 0;
                    bool achouAno = false;

                    while(!achouAno){
                        if(sub[i] == ' '){ // conta espacos em brancos
                            nmbSpace++;
                        }
                        if(nmbSpace == 1){ // salva dia
                            i++;
                            
                            if(sub[i+1] == ','){ // dia com 1 dígito
                                substring(subDate, &sub[i], 1);
                                i++;
                            } else{ // dia com 2 dígitos
                                substring(subDate, &sub[i], 2);
                                i+=2;
                            }

                            game->release_date.day = atoi(subDate);
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
        game->release_date.day = 0;

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
