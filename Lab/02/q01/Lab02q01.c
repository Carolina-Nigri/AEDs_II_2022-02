#include <stdio.h>
#include <stdlib.h>
#include <string.h> 

int main(){
    char prim[60] = "";
    char seg[60] = "";

    while(scanf("%s %s",prim, seg) != EOF){ // le duas strings ate fim do arquivo
        char res[120] = "";

        for(int i = 0, j = 0; i < strlen(prim); i++, j++){ // percorre a maior string
            for(i; i < strlen(seg); i++, j++){ // percorre a menor string
                // res recebe letras de prim e de prim
                res[j] = prim[i];
                j++;
                res[j] = seg[i];
            }
            
            // res recebe letras que restaram da maior string
            res[j] = prim[i];
        }
        printf("%s\n",res);
    }
    
    return 0;
}