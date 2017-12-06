#include <stdio.h>
#include <stdlib.h>

int main()
{
    float firstNumber, secondNumber, calculation, gallons, quarts, pints, cups, ounces, miles, yards, feet, inches;

    printf("Enter a number:");
    scanf("%f", &firstNumber);

    printf("Enter another number:");
    scanf("%f", &secondNumber);

    calculation = firstNumber + secondNumber;
    printf("Those two added together is %.02f\n", calculation);

    printf("\nEnter a number:");
    scanf("%f", &firstNumber);

    printf("Enter another number:");
    scanf("%f", &secondNumber);

    calculation = firstNumber - secondNumber;
    printf("The second number subtracted from the first number is %.02f\n", calculation);

    printf("\nEnter a number:");
    scanf("%f", &firstNumber);

    printf("Enter another number:");
    scanf("%f", &secondNumber);

    calculation = firstNumber * secondNumber;
    printf("The first number times the second number is %.02f\n", calculation);

    printf("\nEnter a number:");
    scanf("%f", &firstNumber);

    printf("Enter another number:");
    scanf("%f", &secondNumber);

    calculation = firstNumber / secondNumber;
    printf("The first number divided by the second number is %.02f\n", calculation);

    printf("\nEnter a number of gallons:");
    scanf("%f", &gallons);

    quarts = gallons * 4;
    pints = gallons * 8;
    cups = gallons * 16;
    ounces = gallons * 128;

    printf("You have %.02f gallons, %.02f quarts, %.02f pints, %.02f cups, and %.02f ounces.\n", gallons, quarts, pints, cups, ounces);

    printf("\nEnter a number of miles:");
    scanf("%f", &miles);

    yards = miles * 1760;
    feet = miles * 5280;
    inches = miles * 63360;

    printf("You have %.02f miles, %.02f yards, %.02f feet, and %.02f inches.\n", miles, yards, feet, inches);





    return 0;
}
