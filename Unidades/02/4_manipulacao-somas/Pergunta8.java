public class Pergunta8 {
    public static void main(String[] args) {
        System.out.println(somatorioPA(1,3,5));
    }

    public static int somatorioPA(double a, double b, int n) {
        int soma = 0;
        
        for(int i=0; i<n; i++){
            soma += (int)(a + b*i);
        }
        
        return soma;
    }
}
