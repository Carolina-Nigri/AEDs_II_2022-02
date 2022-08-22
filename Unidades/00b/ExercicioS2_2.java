class ExercicioS2_2 {
    public static void main(String args[]){
        int x = 1; // numero de pesquisa
        int num[] = {1,2,3,4,5,6,7,8,9}; // array de inteiros
        
        if(isInArray(x,num)){
            System.out.println(x + " esta no array");
        } else{
            System.out.println(x + " nao esta no array");
        }
    }
    
    public static boolean isInArray(int x, int array[]){ // assume que esta ordenado
        boolean contain = false;
        int meio = (array.length) / 2; 
        
        // verifica x com meio do array
        if(x == array[meio]){ // igual
            contain = true;
        } else if(x > array[meio]){ // maior
            int i = meio + 1;
            
            // percorre array p/direita e verifica se contem x
            while(i < array.length && !contain){
                if(array[i] == x) contain = true;
                
                i++;
            }
        } else{ // menor
            int i = meio - 1;
            
            // percorre array p/esquerda e verifica se contem x
            while(i >= 0 && !contain){
                if(array[i] == x) contain = true;
                
                i--;
            }
        }

        return contain;
    }
}
