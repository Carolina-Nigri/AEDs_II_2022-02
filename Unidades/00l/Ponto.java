public class Ponto {
    // atributos
    private double x;
    private double y;
    private int ID;
    // atributo estático (valor compartilhado p/tds objetos)
    private static int nextID = 0;

    // construtores
    public Ponto(){
        x = y = 0;
        ID = nextID;
        nextID++;
    }
    public Ponto(double x, double y){
        this.x = x;
        this.y = y;
        ID = nextID;
        nextID++;
    }
 
    // gets
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }  
    public int getID(){
        return ID;
    }
    public static int getNextID(){
        return nextID;
    }

    // sets
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    // métodos
    public double dist(Ponto p){
        // Ponto corrente
        double x1 = this.x;
        double y1 = this.y;
        // Ponto p
        double x2 = p.getX();
        double y2 = p.getY();
        
        // distancia entre dois Pontos 
        double d = Math.sqrt((Math.pow((x2-x1), 2)) + (Math.pow((y2-y1), 2)));
        
        return d;
    }
    public double dist(double x, double y){
        // Ponto corrente
        double x1 = this.x;
        double y1 = this.y;
        // Ponto (x,y)
        double x2 = x;
        double y2 = y;
        
        // distancia entre dois Pontos 
        double d = Math.sqrt((Math.pow((x2-x1), 2)) + (Math.pow((y2-y1), 2)));
        
        return d;
    }
    public static double dist(double x1, double y1, double x2, double y2){
        // distancia entre dois Pontos 
        double d = Math.sqrt((Math.pow((x2-x1), 2)) + (Math.pow((y2-y1), 2)));
        
        return d;
    }
    public static boolean isTriangulo(Ponto p1, Ponto p2, Ponto p3){
        boolean triang = false;

        // distancia entre pontos (lados do triangulo)
        double a = p1.dist(p2);
        double b = p1.dist(p3);
        double c = p2.dist(p3);

        // condição de existência de um triangulo
        if(Math.abs((b-c)) < a && a < (b+c)){ 
            // | b - c | < a < b + c
            if(Math.abs((a-c)) < b && b < (a+c)){ 
                // | a - c | < b < a + c
                if(Math.abs((a-b)) < c && c < (a+b)){ 
                    // | a - b | < c < a + b
                    triang = true;
                }
            }
        }

        return triang;
    }
}
