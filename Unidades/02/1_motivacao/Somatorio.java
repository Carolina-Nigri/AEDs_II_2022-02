class Somatorio{
    public static void main(String args[]){
        int n = 5000;
        int soma = 0;

        for(int i = 1; i <= n; i++){
            soma += i;
        }

        System.out.println("Somatorio dos " + n + " primeiros numeros inteiros = " + soma);
    } 
}