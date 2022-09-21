import java.util.*;

class Excecao{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        try{
            int a = input.nextInt();
            int b = input.nextInt();

            int c = divisao(a,b);
            System.out.println(a+" / "+b+" = "+c);
        } catch (InputMismatchException IME){
            System.err.println("Tipo de dado incompatível");
            IME.printStackTrace();
        } catch (ArithmeticException AE){
            System.err.println("Divisão inválida");
            AE.printStackTrace();
        }

        input.close();
    }

    public static int divisao(int a, int b) throws ArithmeticException{
        return a/b;
    }
}