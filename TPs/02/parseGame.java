import java.io.*;
import java.text.*;
import java.util.*;

class parseGame {
    public static void main(String[] args) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader("../teste.csv"));
        
        String game = input.readLine();
        
        parser(game);

        input.close();
    }

    public static void parser(String game) throws Exception {
        String[] atr = new String[17]; 
        int first;
        int last;

        for(int i=0; i<atr.length; i++){
            System.out.println("game: "+game);
            
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
            
            System.out.println(i+" "+atr[i]);
        }

        getAttributes(atr);
    }

    public static String cutString(String str, int first, int last){
        String res = "";

        for(int i=first; i<last; i++){
            res += str.charAt(i);
        }
        
        return res;
    }

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

    public static void getAttributes(String[] atr) throws Exception {
        // atributos
        int app_id, age, dlcs;
        float price;
        boolean windows, mac, linux;
        String name, owners, website, developers;
        Date release_date;
        
        float upvotes;
        String[] languages, genres;
        int avg_pt;
        
        // inteiros
        app_id = Integer.parseInt(atr[0]);
        age = Integer.parseInt(atr[4]);
        dlcs = Integer.parseInt(atr[6]);
        avg_pt = Integer.parseInt(atr[14]);

        // reais
        price = Float.parseFloat(atr[5]);
        upvotes = 100 * (float) ( 1.01 - (float) Integer.parseInt(atr[13]) / Integer.parseInt(atr[12]) );
        
        // boolean
        windows = Boolean.parseBoolean(atr[9]);
        mac = Boolean.parseBoolean(atr[10]);
        linux = Boolean.parseBoolean(atr[11]);

        // strings
        name = atr[1];
        owners = atr[3];
        website = atr[8];
        developers = atr[15];

        // arrays de strings
        languages = createArrayStrings(atr[7]);
        genres = createArrayStrings(atr[16]);

        // date
        SimpleDateFormat f;

        if(atr[2].charAt(6) == ',')
            f = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        else
            f =  new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);

        release_date = f.parse(atr[2]);
    
        // imprimir
        SimpleDateFormat formatter = new SimpleDateFormat("MMM/yyyy", Locale.ENGLISH);
        String date = formatter.format(release_date);

        System.out.println(app_id + " " + name + " " + date + " " + owners + " " +
        age + " " + price + " " + dlcs + " " + toStringArray(languages) + 
        " " + website + " " + windows + " " + mac + " " + linux  + " " + 
        (int)Math.round(upvotes) + "% " + (avg_pt / 60)+"h " + (avg_pt % 60) +"m " + developers  + " " + 
        toStringArray(genres));
    }

    public static String toStringArray(String[] array){
        String res = "[";
        int i = 0;
        
        while( array[i] != null ){
            res += array[i];

            if(array[i+1] != null)
                res += ", ";

            i++;
        }

        res += "]";

        return res;
    }

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
}
