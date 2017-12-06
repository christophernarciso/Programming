#include <stdio.h>
#include <stdlib.h>

int main()
{
    /* Variable declaration */
    int gallons;
    int quarts, pints, cups, ounces;

    printf("How many gallons do you have? ");
    scanf("%d", &gallons);

    /* Variable assignments */
    gallons = gallons * 4;
    pints = gallons * 8; /* or quarts * 2 */
    cups = pints * 2;
    ounces = cups * 8;

    /* Print all variables */
    printf("You have %d gallons, which is %d quarts, %d pints, %d cups, and %d ounces.", gallons, quarts, pints, cups, ounces);


    return 0;
}
