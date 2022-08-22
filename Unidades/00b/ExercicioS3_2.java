// TO DO: fazer menos comparações entre os elementos do array

class ExercicioS3_2 {
    public static void main(String args[]){
        int num[] = {10,22,34,40,15,6,17,8,9}; // array de inteiros

        System.out.println(biggerArray(num) + " é o maior");
        System.out.println(smallerArray(num) + " é o menor");
    }
 

    public static int biggerArray(int array[]){ 
        int bigger = array[0];

        for(int i=1; i < array.length; i++){ // compara com array até encontrar maior
            if(array[i] > bigger) bigger = array[i];
        }

        return bigger;
    }

    public static int smallerArray(int array[]){
        int smaller = array[0];

        for(int i=1; i < array.length; i++){ // compara com array até encontrar menor
            if(array[i] < smaller) smaller = array[i];
        }

        return smaller;
    }
}
