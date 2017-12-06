#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
* Lecture 17 Notes: Pointers 2
* 1) Review of Monday's lecture
* 2) Memory review on whiteboard. Ampersand '&' on a variable means "Show me the address of the variable of where it is stored"
* 3) CodeBlocks review
*/

void swap (int *first, int *second){
    int tmp;

    tmp = *first;
    *first = *second;
    *second = tmp;
}

void swap1 (double *first, double *second){
    double tmp;

    tmp = *first;
    *first = *second;
    *second = tmp;
}

void swap2 (char first[], char second[]){
    char tmp[50];

    strcpy(tmp, first);
    strcpy(first, second);
    strcpy(second, tmp);
}


int main()
{
    #if 0 //Pointer review
    int i; //First started off with int i = 5;
    int *pi;

    printf("i = %d\n", i);
    printf("%d\n", &i); // memory location: gives the memory address
    printf("%p\n", &i); // pointer location to memory address: Gives you a hexdecimal number

    //%d & %p: equivalent just different ways to view. One is decimal and the other Hexadecimal

    //Above is different when using global variables EX: a global int i;

    //Global variables always get initialized to zero ALWAYS. Local do not.

    pi = &i; //Pointing to i variable.
    printf("\nPointing pi to variable i (&i)\n", i);
    //No asterisks is asking for the memory location 'pi' | asterisk = contents of that pointer '*pi = 5';

    printf("\n|%d = in memory(demical)\n|%p = in memory(Hexadecimal)\n|%d = content value(What is was assigned)\n", pi, pi, *pi);
    //pi, pi represent the memory location | *pi represents the contents of the pointer
    // Examples Below
    *pi = 17;
    printf("\ni = %d *pi = %d", i, *pi);

    *pi = 99;
    printf("\ni = %d *pi = %d", i, *pi);

    *pi = 77;
    printf("\ni = %d *pi = %d", i, *pi);
    #endif // 0

    #if 1 //Swap review using the swap functions (Like the homework)
    int i = 77, j = 88;
    double k = 45.55, l = 55.45;
    char stringOne[] = "Rick", stringTwo[] = "Chris";

    printf("BEFORE SWAP: i = %d j = %d\n", i , j);
    swap(&i, &j);
    printf("AFTER SWAP: i = %d j = %d\n", i , j);

    printf("\nBEFORE SWAP: k = %.02lf l = %.02lf\n", k , l);
    swap1(&k, &l);
    printf("AFTER SWAP: k = %.02lf l = %.02lf\n", k , l);

    printf("\nBEFORE SWAP: stringOne = %s stringTwo = %s\n", stringOne, stringTwo);
    swap2(stringOne, stringTwo);
    printf("AFTER SWAP: stringOne = %s stringTwo = %s\n", stringOne, stringTwo);
    #endif // 0
    return 0;
}
