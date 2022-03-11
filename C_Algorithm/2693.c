#define MALLOC(p,s){\
    if(!((p) = malloc(s))){\
        fprintf(stderr, "Insufficient Memory \n");\
        exit(EXIT_FAILURE);\
    }\
}\

#define MAX_STRING_SIZE 10000
#define SWAP(x,y,t) ((t) = (x), (x) = (y), (y) = (t))

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int* make1DArray(void);

int main(void){

    int count;
    scanf("%d",&count);
    for(int i = 0; i < count; i++){
        int* numbers = make1DArray();
        printf("%d\n",numbers[7]);
        free(numbers);
    }

    return 0;
}
int* make1DArray(void) {
    int* p, i = 0;
    MALLOC(p, 10 * sizeof(*p));

    char str[MAX_STRING_SIZE];
    fflush(stdin);
    gets(str);
    char* temp = strtok(str, " ");
    while (temp != NULL) {
        p[i++] = atoi(temp);
        temp = strtok(NULL, " ");
    }

    for(int i = 0; i < 10; i++){
        int min = 1001, index = 11, temp = 0;
        for(int j = i; j < 10; j++){
            if(p[j] < min){
                min = p[j];
                index = j;
            }
        }
        SWAP(p[i], p[index], temp);
    }

    return p;
}