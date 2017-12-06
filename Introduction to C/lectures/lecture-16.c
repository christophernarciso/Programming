#include <stdio.h>
#include <stdlib.h>

/*
* Lecture 16 Notes:
* 1) Memory addresses
* 2) Pointers - Designated by an asterisk '*'
* 3) Pointer Examples: FILE *fp, int *pnt;
* 4) Assigning pointer variables
* 5) Example: pnt = &myVal; Where myVal is an integer. ("Show me the address of myVal")
*/



/* https://stackoverflow.com/questions/373419/whats-the-difference-between-passing-by-reference-vs-passing-by-value
* changeValue(int val){val *= 5;} passing by value
*
* changeValue(int *val){*val *= 5;} passing by reference
*/

void changeValue(int *val){
    *val *= 5;
}

/* swapping value references */
void swap(int *one, int *two){
    int remember; //temp variable

    remember = *one; //remember = address one | temp variable
    *one = *two;// one is now assigned to address two
    *two = remember; //assign address two to temp variable (*one address)
}

int main()
{
    int myVal = 5, yourVal = 12;
    int *pnt = &myVal;
    #if 0 //change 'changeValue(int *val) up top to pass by value for this portion *Check comment* and change &myVal to myVal.
    printf("The contents of myVal is %d and the address of myVal is %d\n", myVal, pnt);
    //Pointer has contents and address.
    printf("The value of the memory location is %d\n", *pnt);
    //pnt is the address location & *pnt is the contents value or 'what is in it'

    printf("myVal = %d\n", myVal);
    //Passed by value and not reference w/o '&' & the function params w/o pointer reference.
    changeValue(&myVal); // myVal : passing by value | &myVal: passing by address of myVal
    printf("myVal = %d\n", myVal); // myVal: 5 by value | myVal: 25 by address
    #endif // 0
    #if 0 //Swapping variable addresses
    printf("myVal=%d, yourVal=%d\n", myVal, yourVal);
    //Before: 5, 12 (myVal, yourVal)
    swap(&myVal, &yourVal);
    //After: 12, 5 (myVal, yourVal)
    printf("myVal=%d, yourVal=%d\n", myVal, yourVal);
    #endif // 0







    return 0;
}
