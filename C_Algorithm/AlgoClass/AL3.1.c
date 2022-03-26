#include <stdio.h>

int main(void){
    int n,max;
    scanf("%d",&n);
    scanf("%d",&max);
    if(n == 0){
        printf("0");
        return 0;
    }
    int before = 0, after = 1, temp;
    for(int i = 2; i <= n; i++){
        temp = after;
        after = (after + before) % max;
        before = temp;
    }
    printf("%d",after);
    return 0;
}