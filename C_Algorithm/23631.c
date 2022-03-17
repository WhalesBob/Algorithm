#include <stdio.h>
#include <math.h>

int addAll(int k, int N,int* location);

int main(void){

    int repeat, n,k;
    scanf("%d",&repeat);
    for(int i = 0; i < repeat; i++){
        scanf("%d %d",&n,&k);
        int location = 0;
        int leftMeter = (n-1) - addAll(k,n,&location);
        
        if(location > 0){
            printf("%d L\n",location - leftMeter);
        }else{
            printf("%d R\n",location + leftMeter);
        }
    }

    return 0;
}
int addAll(int k, int N,int* location){
    int sum = 0, next = k, count = 1;
    while(sum + next<= N-1){
        sum += next;
        (*location) = k * ceil(((double)count / (double)2));
        if(count % 2 == 0){
            (*location) *= -1;
        }
        next = k*(++count);
    }
    return sum;
}