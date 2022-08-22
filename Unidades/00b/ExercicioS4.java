class ExercicioS4 {
    public static void main(String args[]){
        System.out.println(doidao('e'));
    }

    public static boolean doidao(char c){
        boolean resp = false;
        int v = (int) c; // converte caractere para inteiro 

        // verifica pelos valores da tabela ASCII se é vogal (maiuscula ou minuscula)
        if(v == 65 || v == 69 || v == 73 || v == 79 || v == 85 || v == 97 || v == 101 || v == 105 || v == 111 || v == 117)
            resp = true;

        return resp;
    }
}
