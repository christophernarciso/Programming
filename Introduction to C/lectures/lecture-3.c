#include <stdio.h>
#include <stdlib.h>

int main()
{
    /* Bitwise operators */
    // Available Sunday 10th Review @6PM (2 Hours session)
    //Char values | unsigned has 8 bit values
    unsigned char first, second, third;

    /* Using the & operator 'AND'*/
    first = 1;
    second = 1;
    //the result will be 1
    third = first & second;

    first = 1;
    second = 3;
    //the result will be 1
    third = first & second;

    first = 3;
    second = 3;
    //the result will be 3
    third = first & second;

    first = 128;
    second = 128;
    //the result will be 128
    third = first & second;

    first = 3;
    second = 128;
    //the result will be 0
    third = first & second;


    /* Using the | operator 'OR'*/
    first = 1;
    second = 1;
    //the result will be 1
    third = first | second;

    first = 1;
    second = 2;
    //the result will be 3
    third = first | second;






    /* Operators Lesson */
    /* Can initialize the variable in the declaration
    int temp, val = 7, number = 8;

    //add
    temp = 6 + 7;
    temp = val + number;

    //sub
    temp = 6 - 7;
    temp = val - number;

    // Multi
    temp = 6 * 7;
    temp = val * number;

    //divide
    //Result will be 1 since int ignores the decimal.
    temp = 9 / 5;
    temp = val / number;

    //Modulus
    temp = 9 % 5;
    temp = val % number;

    //boolean operations > | < | >= | <= | == | !=
    temp = 6 > 7; // false
    temp = 7 > 6; // true
    temp = val > number;

    temp = 6 == 7; // false
    temp = 7 == 7; // true
    temp = val == number;

    temp = 6 != 7; // true
    temp = val != number;

    */
    return 0;
}
