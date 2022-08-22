public class ExercicioS7 {
    public static void main(String args[]){
        String str = "sdagr";
        
        if(isConsoante(str,0)){
            System.out.println("É consoante");
        } else{
            System.out.println("Não é consoante");
        }
    }

    public static boolean isConsoante(String s, int n){
        boolean resp = true;

        if(n != s.length()){
            if(s.charAt(n) < '0' || s.charAt(n) > '9'){
                if(isVogal(s.charAt(n))){
                    resp = false;
                } else{
                    resp = isConsoante(s, n + 1);
                }
            } else{
                resp = false;
            }
        }

        return resp;
    }

    public static boolean isVogal(char c){
        boolean vogal = c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
      
        return vogal;
    }
}
