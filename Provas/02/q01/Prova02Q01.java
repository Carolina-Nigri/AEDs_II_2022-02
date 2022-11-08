import java.io.*;

/**
 * Prova02Q01 - Sanduicheiche
 * @author Carolina Morais Nigri
 * @version 08/11/22 
 */
public class Prova02Q01{
	public static void main(String[] args) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String str = "", resp = "";

		// le strings da entrada padrao ate achar "FIM"
		str = in.readLine();
		while(!isFIM(str)){
			resp = "";
			
			int n = str.length();
			boolean achou = false;
			int i = (n - 2);

			// percorre string procurando fim repetido
			while(i >= 0 && !achou){
				// verifica se ultimo char da str eh igual ao atual (i)
				if(str.charAt(n-1) == str.charAt(i)){
					// faz substring do inicio ate o fim da str encontrado (i)
					resp = str.substring(0, i+1); 
					achou = true; // sai do laco
				}	
				i--;
			}
			
			// str nao tem fim repetido
			if(!achou)
				resp = str;

			System.out.println(resp);
			str = in.readLine();
		}
	}
	public static boolean isFIM(String str){
		return (str.equals("FIM")); // verifica se str eh exatamente = "FIM"
	}
}
