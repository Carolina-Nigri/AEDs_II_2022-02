import java.util.Scanner;

class Lab02q02{
    public static void main(String[] args) {
        int limInf, limSup;
        String sequencia;
        Scanner input = new Scanner(System.in);
        
        while(input.hasNext()){ // le enquanto encontra uma entrada
            sequencia = ""; 
            limInf = input.nextInt();
            limSup = input.nextInt();

            for(int i = limInf; i <= limSup; i++){ 
                // imprime a sequencia na ordem natural
                System.out.print(i);
                // salva sequencia em uma string
                sequencia += i;
            }

            for(int i = sequencia.length(); i > 0 ; i--){
                // imprime a sequencia de forma espelhada
                System.out.print(sequencia.charAt(i-1));
            }

            System.out.println();
        }

        input.close();
    }
}