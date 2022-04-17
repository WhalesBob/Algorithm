#define MALLOC(p,s){\
    if(!((p) = malloc(s))){\
        fprintf(stderr,"Insufficient Memory \n");\
        exit(EXIT_FAILURE);\
    }\
}\

#include <stdio.h>
#include <stdlib.h>

int* intArray; 

int* makeArray(int size);
int binomial(int n, int k, int MAX);
int minimum(int a, int b);

int main(void){
    int n,k, MAX;
    scanf("%d %d %d", &n,&k,&MAX);
    intArray = makeArray(n);
    k = ((k >= n-k) ? n-k : k);
    printf("%d", binomial(n,k,MAX));
    return 0;
}
int* makeArray(int size){
    int* p;
    MALLOC(p, (size+1) * sizeof(*p));
    
    return p;
}
int binomial(int n, int k, int MAX){
    int a,b;
    for(int i = 0; i <= n; i++){
        a = intArray[0];
        for(int j = 0; j <= min(i,k); j++){
            if(j == 0 || j == i){
                intArray[j] = 1;
            }else{
                b = intArray[j];
                intArray[j] = (a + b) % MAX;
                a = b;
            }
        }
    }
    return intArray[k];
}
int minimum(int a, int b){
    return ((a >= b) ? b : a); 
}