#include <stdio.h>
#include <stdlib.h>

unsigned long long multiply(int n, int m);

int main(void){
    int n,r,count;
    scanf("%d",&count);
    for(int i = 0; i < count; i++){
        scanf("%d %d",&r,&n);
        printf("%lld\n",lldiv(multiply(n-r+1,n),multiply(1,r)).quot);
    }
    return 0;
}
unsigned long long int multiply(int n, int m){
    long long int answer = 1;
    for(int i = n; i <= m; i++){
        answer *= i;
    }
    return answer;
}