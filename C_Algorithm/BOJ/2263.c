#define MALLOC(p,s){\
    if(!((p) = malloc(s))){\
        fprintf(stderr, "Insufficient Memory \n");\
        exit(EXIT_FAILURE);\
    }\
}\

#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode* TreePointer;

typedef struct TreeNode{
    int data;
    TreePointer leftChild;
    TreePointer rightChild;
}TreeNode;

int* makeArray(int size);
TreePointer makeTree(int* inOrder, int* postOrder, int start, int end);
int findIndex(int* array, int value, int start, int end);
TreePointer makeTreeNode(int n);
void printPreOrder(TreePointer node);

int main(void){

    int n;

    scanf("%d",&n);
    int* inOrder = makeArray(n);
    int* postOrder = makeArray(n);
    
    TreePointer head = makeTree(inOrder,postOrder,0,n);
    printPreOrder(head);
    
    return 0;
}
int* makeArray(int size){
    int* p;
    MALLOC(p,size * sizeof(*p));

    for(int i = 0; i < size; i++){
        scanf("%d", &p[i]);
    }
    return p;
}
TreePointer makeTree(int* inOrder, int* postOrder, int start, int end){
    if(start >= end){
        return makeTreeNode(inOrder[start]);
    }else{
        int headNum = postOrder[--end];
        TreePointer node = makeTreeNode(headNum);
        int divideIndex = findIndex(inOrder, headNum, start, end);
        node->leftChild = makeTree(inOrder, postOrder, start, divideIndex-1);
        node->rightChild = makeTree(inOrder, postOrder, divideIndex+1, end);
    
        return node;
    }
}
int findIndex(int* array, int value, int start, int end){
    for(int i = start; i <= end; i++){
        if(array[i] == value){
            return i;
        }
    }
    return -1;
}
TreePointer makeTreeNode(int n){
    TreePointer p;
    MALLOC(p, sizeof(*p));
    p->data = n;
    p->leftChild = NULL;
    p->rightChild = NULL;

    return p; 
}
void printPreOrder(TreePointer node){
    printf("%d ", node->data);
    if(node->leftChild != NULL){
        printPreOrder(node->leftChild);
    }
    if(node->rightChild != NULL){
        printPreOrder(node->rightChild);
    }
}
