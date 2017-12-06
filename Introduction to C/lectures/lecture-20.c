#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <malloc.h>
#define NUM_DATA 10
#define NUM_MEMORY_DATA 1000000

/**
* Exam Review Notes
* Topics on the exam:
* 1) Variables: Holds values or data.
* 2) Arrays: int data[]; or set a size int myData[500];
* 3) Pass by Value & Pass by Reference | scanf("%d", &data); || scanf("%d", data);
* 4) Functions | Type, Parameters, return types | void does not return any type. | Functions with pointers
* 5) Loops | for, while, do while
* 6) Pointers: Review LinkedList notes(check webcourses) + assignment
* 7) Struct
* 8) Strings
* 9) Modulus
* 10) Conditionals
* 11) Operators
**/


/*Function Examples*/
void someFunction(){
    //No return type
}

int myIntFunction(int someVal){
    //must return int
    return someVal * 99;
}

double myDoubleFunction(){
    return 302.323;
}

float myFloatFunction(){
    return 232.3222323;
}

/*Linked Lists example*/
struct info {
    char fn[50], ln[50];
    struct info *next; //next points to a successor
};

struct info i;

void getInformation(struct info *i){
    printf("Enter your first name: ");
    gets(i->fn);

    printf("Enter your last name: ");
    gets(i->ln);
}

void addInformationToList(){
    //temporary variable
    struct info *tmp = &i;

    //While there is a successor.
    while (tmp->next != NULL){
        //move to the next successor
        tmp = tmp->next;
    }

    //calloc(what is being allocated, size of allocation)
    tmp->next = calloc(sizeof(struct info), 1);
}

void displayInformation(){
    //temporary variable
    struct info *tmp = &i;

    //skip the first in the list since its NULL or blank.
    tmp = tmp->next

    //while pointer location is not NULL loop and display information
    while (tmp != NULL){
        printf("%s %s", i->fn, i->ln);
        tmp = tmp->next;
    }
}

void saveInformationToDisk(){
    FILE *fp;
    struct info
}

void freeAllMemory(){
    //This will be extra credit on the exam. More for using recursion

    //Normal way shown in lecture
    struct info *tmp1 = &i, *tmp2;

    while (tmp1 != NULL){
        //set tmp2 to hold tmp1 information
        tmp2 = tmp1;
        tmp1 = tmp1->next;
        free(tmp2);
    }

    //search freeing recursively
}





int main()
{
    #if 0
    //Arrays
    int array[NUM_DATA]; //anything declared locally is allocated off the stack.
    int *array2; //pointer
    int *array3;
    int i;

    array2 = array; //point to array[10];
    array3 = malloc(sizeof(int) * NUM_MEMORY_DATA); // can replace 'array[i]' below with 'array3[i]'
    //Counting from 0-9 since arrays start at 0 index
    for (i = 0; i < NUM_DATA; i++){
        array[i] = 2 * i;

    }

    for (i = 0; i < NUM_DATA; i++){
        printf("array[%d] = %d\n", i, array[i]);
    }
    #endif // end of arrays

    #if 1
    //Loops
    int i;
    for (i = 0; i < 10; i++){// incrememnt 'i++' can be changed to increment by any value | i+= 20
        //loops 9 times
    }

    while (i < 100){ //loops until the (condition) is false
        //loops 99 times
        i++; //increment i by 1 each loop
    }

    do {
        //Goes through at least once
        //loop 49 times
        i++; //increment i by 1 each loop
    } while (i < 50); //loops until the (condition) is false
    #endif // end of loops

    #if 0
    //

    #endif // end of

    return 0;
}
