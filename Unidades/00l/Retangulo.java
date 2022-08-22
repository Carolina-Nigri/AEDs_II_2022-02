public class Retangulo{
    private int base;
    private int altura;
    public Retangulo(){
        base = altura = 0;
    } 
    public Retangulo(int base, int altura){
        this.base = base;
        this.altura = altura; 
    }
    public double getArea(){
        return (double) (base * altura);
    } 
    public double getPerimetro(){
        return (double) (2*base) + (2*altura);
    }
    public double getDiagonal(){
        return Math.sqrt((Math.pow(altura, 2)) + (Math.pow(base, 2)));
    }
}