// ERRO no Verde

import java.io.*;

class Prova03q01{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n;

		do{
			n = Integer.parseInt(br.readLine());
			String[] l = new String[n];
			String[] nome = new String[n];
			int[] v = new int[n];

	//		System.out.println(n);
			for(int i = 0; i < n; i++){
				l[i] = br.readLine();
	//			System.out.println(l[i]);

				String[] s = l[i].split(" ");
				nome[i] = s[0];
	//			System.out.println(nome[i]);

				v[i] = Integer.parseInt(s[1]);
	//			System.out.println(v[i]);
			}
	
			int i=n-1;
			int q=n;
			while(q>1){
				int c=1;
			
				while(c != v[i]){
		//			System.out.println(c+" "+v[i]);
					c++;	
					
					if(i==(n-1)){
						i=0;
					} else if(v[i] == -1){
						c--;
						i++;
					} else{
						i++;
					}
				}

				if(c == v[i]){
					v[i] = -1; // jogador eliminado
					q--;
					
					if(i==(n-1)){
						i=0;
					} else{
						i++;
					}
				}
			}

			System.out.println("Vencedor (a): "+nome[i]);
		} while(n != 0);

		br.close();
	}
}
