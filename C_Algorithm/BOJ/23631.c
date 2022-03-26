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
    double statement = ((double)(-1*k) + sqrt(pow((double)k,(double)2) + (double)(8 * k * (N-1)))) / (double)(2*k);
    int count = (int)floor(statement);
    int sum = (k*count*(count+1))/2;
    (*location) = k * ceil(((double)count / (double)2));
    if(count % 2 == 0){
        (*location) *= -1;
    }
    return sum;
}