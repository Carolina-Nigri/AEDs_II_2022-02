public class Lab01q01 {

    public static void main(String args[]){
        String str;
        int maiuscLin[] = new int[10];
        int cont = 0;

        // le as strings e conta maiusculas
        do{
            str = MyIO.readLine();

            // salva qtd de maiusculas por linha
            maiuscLin[cont] = contaMaiusculas(str); 
            cont++;
        }while(!isFIM(str)); // sai quando digita "FIM"

        // imprime qtd de maiusculas por linha
        for(int i = 0; i < cont - 1; i++){
            MyIO.println(maiuscLin[i]);
        }
    }

    public static boolean isFIM(String str){
        boolean FIM = false;
        
        if((str.length() == 3) && (str.compareTo("FIM") == 0)){
            FIM = true;
        }

        return FIM;
    }

    public static int contaMaiusculas(String str){
        int qtd = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                qtd++;
            }
        }
        
        return qtd;
    }
}