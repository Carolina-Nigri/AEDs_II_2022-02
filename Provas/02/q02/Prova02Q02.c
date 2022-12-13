// 100% no Verde
/**
 * Prova02Q02 - So crescendo
 * @author Carolina Morais Nigri
 * @version 08/11/22 
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// troca dois elementos de posicao no array
void swap(int a[], int i, int j){
	int tmp = a[i];
	a[i] = a[j];
	a[j] = tmp;
}
// ordena o array (algoritmo: selection sort)
void sort(int a[], int n){
	for(int i = 0; i < (n - 1); i++){
		int m = i;

		// acha menor a partir da pos i
		for(int j = (i + 1); j < n; j++){
			if(a[j] < a[m])
				m = j;	
		}

		// troca menor com pos i
		swap(a, i, m);
	}	
}
// verifica se elementos do array estao sempre crescendo 
bool ehCresc(int a[], int n){
	bool cresc = true;
	int i = 0;
	
	// percorre array ate fim ou ate achar elemento nao crescente
	while(i < (n - 1) && cresc){
		// verifica se elemento i eh maior ou igual ao prox
		if(a[i] >= a[i+1])
			cresc = false;
	
		i++;
	}

	return cresc;
}
// funcao principal
int main(){
	// numero de testes 
	int t;
	scanf("%i", &t);

	for(int k = 0; k < t; k++){		
		int n; // tamanho do array
		scanf("%i", &n);
	
		// le array
		int a[n];
		for(int i = 0; i < n; i++){
			scanf("%i", &(a[i]));		
		}
		
		// ordena o array
		sort(a, n);

		// verifica se eh sempre crescente (a1 < a2 < ... < an)
		if(ehCresc(a, n))
			printf("SIM\n");
		else
			printf("NAO\n");
	}

	return 0;
}
