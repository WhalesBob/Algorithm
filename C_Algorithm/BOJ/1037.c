#define MALLOC(p,s){\
    if(!((p) = malloc(s))){\
        fprintf(stderr, "Insufficient Memory \n");\
        exit(EXIT_FAILURE);\
    }\
}\

#define MAX_STRING_SIZE 10000

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int* make1DArray(int n);

int main(void) {

    int count, * numbers, max = -1, min = 1000001;
    scanf("%d", &count);

    numbers = make1DArray(count);
    for (int i = 0; i < count; i++) {
        if(numbers[i] < min) {
            min = numbers[i];
        }
        if (numbers[i] > max) {
            max = numbers[i];
        }
    }

    printf("%d", max * min);

    return 0;
}
int* make1DArray(int n) {
    int* p, i = 0;
    MALLOC(p, n * sizeof(p));

    char str[MAX_STRING_SIZE];
    getchar();
    gets(str);
    char* temp = strtok(str, " ");
    while (temp != NULL) {
        p[i++] = atoi(temp);
        temp = strtok(NULL, " ");
    }

    return p;
}