// bibliotecas
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

// protótipos
bool isFIM(char str[]);

// structs (registros)
typedef struct{
    int dia, mes, ano;
} Date;

typedef struct {
    int app_id, age, dlcs, avg_pt;
    // String name, owners, website, developers;
    Date release_date;
    float price, upvotes;
    // String[] languages, genres;
    bool windows, mac, Glinux;
} Game;

// função principal
    // lê ids dos games da entrada padrão até achar FIM, procura id no csv e imprime
int main(){
    char gameID[20]; 
    scanf(" %20[^\n]", gameID);

    while(!isFIM(gameID)){
        int id = atoi(gameID);
        
        Game game;
    //     game.readFromFilePrintID("/tmp/games.csv",id); 

        scanf(" %20[^\n]", gameID);
    }

    return 0;
}

// verifica se string é igual a "FIM"
bool isFIM(char str[]){
    bool fim = false;

    if(strlen(str) == 3 && str[0] == 'F' && str[1] == 'I' && str[2] == 'M')
        fim = true;
    
    return fim;
} 

// funções do registro Game:

/*
// clone (sobreescreve função da superclasse Object)
@Override
public Game clone() {
    Game novo = new Game(app_id, name, release_date, owners, age, price, dlcs, 
                    languages, website, windows, mac, linux, upvotes, avg_pt,
                    developers, genres);

    return novo;
}

// imprimir
public void print(){
    // formata a data
    SimpleDateFormat formatter = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);
    String date = formatter.format(release_date);

    // imprime atributos app_id, name, date, owners, age
    System.out.print(app_id + " " + name + " " + date + " " + owners + " " + 
                        age + " ");
    
    // imprime atributo price com duas casas decimais                          
    System.out.printf(Locale.ENGLISH, "%.2f", price);
    
    // imprime atributos dlcs, languages[] 
    System.out.print(" " + dlcs + " " + toStringArray(languages) + " ");
    
    // imprime atributo website, se for vazio imprime "null"
    if(website == "") System.out.print("null");
    else System.out.print(website);
    
    // imprime atributos windows, mac, linux, upvotes arredondado
    System.out.print(" " + windows + " " + mac + " " + linux  + " " + 
                        ((int)Math.round(upvotes)) + "% "); 
    
    // imprime atributo avg_pt em h e min, se for 0 imprime "null"
    if(avg_pt == 0) System.out.print("null ");
    else System.out.print((avg_pt / 60)+"h " + (avg_pt % 60) +"m ");
    
    // imprime atributo website, se for vazio imprime "null"
    if(developers == "") System.out.print("null");
    else System.out.print(developers);

    // imprime atributo genres[]
    System.out.print(" " + toStringArray(genres));
    
    // nova linha
    System.out.println("");
}

// tranforma array de strings em string
public String toStringArray(String[] array){
    String str = "[";
    int i = 0;
    
    while( array[i] != null ){
        str += array[i];

        if(array[i+1] != null)
            str += ", ";

        i++;
    }

    str += "]";

    return str;
}

// ler do arquivo e imprimir jogo de ID específico 
public void readFromFilePrintID(String file, int id) throws Exception{
    BufferedReader input = new BufferedReader(new FileReader(file));
    String game = input.readLine();
    int arqID;
    boolean achou = false;
    
    do{
        arqID = saveID(game);

        if(id == arqID){
            parseGame(game);
            print();
            achou = true;
        }
    } while( ((game = input.readLine()) != null) && (!achou) );
        
    input.close();
}

// faz o parse somente do ID da string game
public int saveID(String game){
    String gameID = "";
    int id;
    int i = 0;

    while( game.charAt(i) != ',' ){
        gameID += game.charAt(i);

        i++;
    }

    id = Integer.parseInt(gameID);

    return id;
}

// faz o parse da string, separando atributos lidos
public void parseGame(String game) throws Exception{
    String[] atr = new String[17]; 
    int first;
    int last;

    // cria array separando atributos 
    for(int i=0; i<atr.length; i++){
        first = 0;
        
        if(game.length() == 0){ 
            atr[i] = game;
        } else{
            if(i == 16 && game.charAt(first) != '"'){
                last = game.length();
            } else{
                last = findNextSep(game);
            }
        
            if(game.charAt(first) == '"'){
                atr[i] = cutString(game, ++first, --last);
                
                first = last + 2;
            } else{
                atr[i] = cutString(game, first, last);

                first = last + 1;
            }
            
            game = cutString(game, first, game.length());
        }
    }

    // salva atributos
    getAttributes(atr);
}

// salva atributos no objeto
public void getAttributes(String[] atr) throws Exception {
    // inteiros
    this.app_id = Integer.parseInt(atr[0]);
    this.age = Integer.parseInt(atr[4]);
    this.dlcs = Integer.parseInt(atr[6]);
    this.avg_pt = Integer.parseInt(atr[14]);
    
    // reais
    this.price = Float.parseFloat(atr[5]);
    this.upvotes = 100 * (float) ( 1.00 - (float) Integer.parseInt(atr[13]) / Integer.parseInt(atr[12]) );
    
    // booleanos
    this.windows = Boolean.parseBoolean(atr[9]);
    this.mac = Boolean.parseBoolean(atr[10]);
    this.linux = Boolean.parseBoolean(atr[11]);
    
    // strings
    this.name = atr[1];
    this.owners = atr[3];
    this.website = atr[8];
    this.developers = atr[15];
    
    // arrays de strings
    this.languages = createArrayStrings(atr[7]);
    this.genres = createArrayStrings(atr[16]);
    
    // date
    SimpleDateFormat formatter;
    if(atr[2].charAt(6) == ',')
        formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
    else
        formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);

    this.release_date = formatter.parse(atr[2]);
}

// transforma string em array de strings
public static String[] createArrayStrings(String str) {
    String[] array = new String[50];
    int i = 0, j = 0;
    array[0] = "";

    if(str.length() > 0){
        if(str.charAt(i) == '['){
            i++;

            while( (i<str.length()) && (str.charAt(i) != ']') ){
                
                if(str.charAt(i) == 39){ // char 39 = '
                    i++;
                    
                    if(str.charAt(i) == ','){
                        j++;
                        i+=2;
                        
                        array[j] = "";
                    }
                } else{ 
                    array[j] += str.charAt(i);

                    i++;
                }

            }
        } 
        else{
            while(i < str.length()){
                array[j] += str.charAt(i);

                i++;

                if( (i != str.length()) && (str.charAt(i) == ',') ){
                    j++;
                    i++;

                    array[j] = "";
                }
            }
        }
    }

    return array;
}

// acha separador de atributos da string do .csv
public static int findNextSep(String strCsv){
    int index = -1;
    boolean sep = false;

    if(strCsv.charAt(0) == '"'){
        index += 2;

        while(strCsv.charAt(index) != '"'){
            index++;
        }
        
        index++;
        
        if((index != strCsv.length()) && (strCsv.charAt(index) == ','))
                sep = true;

    } else{
        while(index < strCsv.length() && !sep){
            index++;

            if(strCsv.charAt(index) == ',') 
                sep = true;
        }
    }
        
    return index;
}

// cria uma substring
public static String cutString(String str, int first, int last){
    String res = "";

    for(int i=first; i<last; i++){
        res += str.charAt(i);
    }
    
    return res;
}
*/