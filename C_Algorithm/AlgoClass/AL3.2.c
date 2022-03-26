#define MALLOC(p,s){\
    if(!((p) = malloc(s))){\
        fprintf(stderr,"Insufficient Memory \n");\
        exit(EXIT_FAILURE);\
    }\
}\

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_STRING_SIZE 1000

int count, *intArray;

int* getStatement(int size);
int* make1DArray(int n);
void mergeSort(int low, int high);
void merge2(int low, int mid, int high);

int main(void){
    int n;
    scanf("%d",&n);
    intArray = getStatement(n);
    mergeSort(0,n-1);

    printf("%d\n",count);
    for(int i = 0; i < n; i++){
        printf("%d ",intArray[i]);
    }
    return 0;
}
int* getStatement(int size){
    int* p, i = 0;
    MALLOC(p, size * sizeof(*p));
    char str[MAX_STRING_SIZE];
    __fpurge(stdin);
    gets(str);
    char* temp = strtok(str, " ");
    while(temp != NULL){
        p[i++] = atoi(temp);
        temp = strtok(NULL," ");
    }

    return p;
}
void merge2(int low, int mid, int high){

    count++;
    
    int* tempArray = make1DArray(high-low+1);
    int front = low,back = mid+1, index = 0;
    while(front <= mid && back <= high){
        if(intArray[front] < intArray[back]){
            tempArray[index++] = intArray[front++];
        }else{
            tempArray[index++] = intArray[back++];
        }
    }
    while(front <= mid){
        tempArray[index++] = intArray[front++];
    }
    while(back <= high){
        tempArray[index++] = intArray[back++];
    }
    
    index = 0;
    int copy = low;
    for(int i = 0; i < high-low+1; i++){
        intArray[copy++] = tempArray[index++];
    }
    free(tempArray);
}
void mergeSort(int low, int high){
    if(low < high){
        int mid = (low + high) / 2;
        mergeSort(low,mid);
        mergeSort(mid+1,high);
        merge2(low,mid,high);
    }

}
int* make1DArray(int n){
    int* p;
    MALLOC(p,n*sizeof(*p));
    for(int i = 0; i < n; i++){
        p[i] = 0;
    }

    return p;
}