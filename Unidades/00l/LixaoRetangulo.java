class LixaoRetangulo{
   public static void main(String[] args){
      Retangulo r1 = new Retangulo(); // objeto vazio
      Retangulo r2 = new Retangulo(4,3); // objeto com valores
     
      // testa métodos em r2
      System.out.println("Area r2 = " + r2.getArea());
      System.out.println("Perimetro r2 = " + r2.getPerimetro());
      System.out.println("Diagonal r2 = " + r2.getDiagonal());
   }    
}