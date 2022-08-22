class ExercicioS2_1 {
    public static void main(String args[]) {
        int x = 325; // numero de pesquisa
        int num[] = { 10, 2, 31, 324, 5, 36, 72, 81, 9, 20 }; // array de inteiros

        if (isInArray(x, num)) {
            System.out.println(x + " esta no array");
        } else {
            System.out.println(x + " nao esta no array");
        }
    }

    public static boolean isInArray(int x, int array[]) {
        boolean contain = false;
        int i = 0;

        while (i < array.length && !contain) {
            // percorre array e verifica se contém x
            if (array[i] == x) contain = true;
           
            i++;
        } // sai quando encontra ou quando chega ao último

        return contain;
    }
}