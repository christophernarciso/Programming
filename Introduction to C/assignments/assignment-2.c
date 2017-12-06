#include <stdio.h>
#include <stdlib.h>

int main()
{
    /*Variable declaration*/
    int intVal;
    float floatVal;
    double doubleVal;
    short shortVal;

    /* Ask user for input values and assign them*/
    printf("Please enter an integer value: ");
    scanf("%d", &intVal);

    printf("Please enter a float value: ");
    scanf("%f", &floatVal);

    printf("Please enter a double value: ");
    scanf("%lf", &doubleVal);

    printf("Please enter a short value: ");
    scanf("%hd", &shortVal);

    /*Print the assigned values*/
    printf("\nThe integer value is %d\n", intVal);
    printf("The float value is %f\n", floatVal);
    printf("The double value is %lf\n", doubleVal);
    printf("The short value is %hd\n", shortVal);




    return 0;
}
