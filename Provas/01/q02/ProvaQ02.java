// Carolina Morais Nigri - 13/09/22
// Prova pratica 01 - q02: Telefone

import java.util.*;

class ProvaQ02{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String telefone = "";

		// le telefones enquanto nao eh "FIM"
		while(!isFIM(telefone)){
			telefone = sc.nextLine();
			
			// imprime telefone convertido (letras -> numeros)
			if(!isFIM(telefone)) 
				System.out.println(converte(telefone));
		}
	}

	// converte letras para numeros e mantem numeros e hifens
	public static String converte(String telefone){
		String convertido = "";

		for(int i=0; i<telefone.length(); i++){
			if(!isNum(telefone.charAt(i)) && telefone.charAt(i) != '-'){
				// converte letra para numero
				if(telefone.charAt(i)=='A' || telefone.charAt(i)=='B' || telefone.charAt(i)=='C'){			
					convertido+='2';
				} else if(telefone.charAt(i)=='D' || telefone.charAt(i)=='E' || telefone.charAt(i)=='F'){
					convertido+='3';
				} else if(telefone.charAt(i)=='G' || telefone.charAt(i)=='H' || telefone.charAt(i)=='I'){
					convertido+='4';
				} else if(telefone.charAt(i)=='J' || telefone.charAt(i)=='K' || telefone.charAt(i)=='L'){
					convertido+='5';
				} else if(telefone.charAt(i)=='M' || telefone.charAt(i)=='N' || telefone.charAt(i)=='O'){
					convertido+='6';
				} else if(telefone.charAt(i)=='P' || telefone.charAt(i)=='Q' || telefone.charAt(i)=='R' || telefone.charAt(i)== 'S'){
					convertido+='7';
				} else if(telefone.charAt(i)=='T' || telefone.charAt(i)=='U' || telefone.charAt(i)=='V'){
					convertido+='8';
				} else if(telefone.charAt(i)=='W' || telefone.charAt(i)=='X' || telefone.charAt(i)=='Y' || telefone.charAt(i)=='Z'){
					convertido+='9';
				}
			} else{
				// salva numero ou hifen na string
				convertido += telefone.charAt(i);
			}	
		}

		return convertido;
	}

	// verifica se caractere eh numero
	public static boolean isNum(char c){
		boolean  num = false;

		if(c=='0' || c=='1' || c=='2' || c=='3' || c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9')
			num = true;

		return num;
	}

	// verifica se string eh "FIM"
	public static boolean isFIM(String str){
		boolean fim = false;

		if(str.length()==3 && str.charAt(0)=='F' && str.charAt(1)=='I' && str.charAt(2)=='M')
			fim = true;

		return fim;
	}
}
