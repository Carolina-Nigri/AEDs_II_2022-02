// 100% no Verde

import java.io.*;

class Prova03q02{
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());

		while(t > 0){
			String l = br.readLine();
			String[] s = l.split(" ");
			int m = Integer.parseInt(s[0]), n = Integer.parseInt(s[1]);

			String[] j = new String[4];
			String[] p = new String[4];

			for(int i=0; i<m; i++){
				j[i] = br.readLine();
				p[i] = br.readLine();
			}	

			l = "";
			while(n>0){
				l = br.readLine();
				s = l.split(" ");	
			
				for(int i=0; i<s.length; i++){
					boolean a = false;
					for(int k=0; k<j.length && !a; k++){
						if(s[i].equals(j[k])){
							System.out.print(p[k]);
							a = true;
						}
					}
					if(!a)
						System.out.print(s[i]);

					if(i!=s.length-1)
						System.out.print(" ");
				}
				System.out.println("");
				n--;
			}
			if(t!=1) System.out.println("");

			t--;
		}

		br.close();
	}
}
