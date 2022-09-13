// Carolina Morais Nigri - 13/09/22
// Prova pratica 01 - q01: Busca na internet

import java.util.*;

class ProvaQ01{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t; // qtde de pessoas q clicaram no 3o link
		int p; // qtde de pessoas q clicaram no 1o link

		// le inteiros (t) enquanto nao chega ao fim do arquivo 
		while(sc.hasNext()){
			do{
				t = sc.nextInt();
			} while(t<1 || t > 1000); // consiste valor de t e repete leitura

			// calcula vezes q 1o link foi clicado a partir de t
			p = 2*(2*t);	
			
			// imprime p
			System.out.println(p);			
		}		
	}
}
