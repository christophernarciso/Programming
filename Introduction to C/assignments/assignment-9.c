#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void intSwap(int *one, int *two){
    printf("Swapping values....\n");
    int remember; //temp variable

    remember = *one; //remember = address one | temp variable
    *one = *two;// one is now assigned to address two
    *two = remember; //assign address two to temp variable (*one address)
}

void doubleSwap(double *one, double *two){
    printf("Swapping values....\n");
    double remember; //temp variable

    remember = *one; //remember = address one | temp variable
    *one = *two;// one is now assigned to address two
    *two = remember; //assign address two to temp variable (*one address)
}

void stringSwap(char *one, char *two){
    printf("Swapping values....\n");

    char remember[50];

    strcpy(remember, one);
    strcpy(one, two);
    strcpy(two, remember);
}

int main()
{
    int num1, num2;
    double num3, num4;
    char string1[50], string2[50];

    /* int swap */
    printf("Please enter two integer values(int1 SPACE int2): \n");
    scanf("%d %d", &num1, &num2);

    printf("Before we swap, the values you inputed are %d as value one and %d as value two.\n", num1, num2);
    intSwap(&num1, &num2);
    printf("After swapping the values, %d is value one and %d is value two.\n", num1, num2);

    /* double swap */
    printf("\nPlease enter two double values(dbl1 SPACE dbl2): \n");
    scanf("%lf %lf", &num3, &num4);

    printf("Before we swap, the values you inputed are %.02lf as value one and %.02lf as value two.\n", num3, num4);
    doubleSwap(&num3, &num4);
    printf("After swapping the values, %.02lf is value one and %.02lf is value two.\n", num3, num4);

    /* string swap */
    printf("\nPlease enter two strings(str1 SPACE str2): \n");
    scanf("%s %s", string1, string2);

    printf("Before we swap, the strings you inputed are %s as string one and %s as string two.\n", string1, string2);
    stringSwap(string1, string2);
    printf("After swapping the strings, %s is string one and %s is string two.\n", string1, string2);
    return 0;
}


