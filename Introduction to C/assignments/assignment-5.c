#include <stdio.h>
#include <stdlib.h>

float addTwoNumbers(float first, float second){
    return first + second;
}

float subtractTwoNumbers(float first, float second){
    return first - second;
}

float multiplyTwoNumbers(float first, float second){
    return first * second;
}

float divideTwoNumbers(float first, float second){
    return first / second;
}

float convertGallonsToQuarts(float gallons){
    return gallons * 4;
}

float convertGallonsToPints(float gallons){
    return gallons * 8;
}

float convertGallonsToCups(float gallons){
    return gallons * 16;
}

float convertFahrenheitToCelsius(float degreesFahrenheit){
    return (5.0/9.0) * (degreesFahrenheit - 32);
}

float convertCelsiusToFahrenheit(float degreesCelsius){
    return degreesCelsius * (9.0/5.0) + 32;;
}



int main()
{
    float firstNumber, secondNumber, gallons, celsius, fahrenheit;
    printf("Starting Calculator...\n\n");

    printf("Please enter a number:");
    scanf("%f", &firstNumber);
    printf("Please enter a second number:");
    scanf("%f", &secondNumber);
    printf("The two numbers added together is %.02f.\n\n", addTwoNumbers(firstNumber, secondNumber));

    printf("Please enter a number:");
    scanf("%f", &firstNumber);
    printf("Please enter a second number:");
    scanf("%f", &secondNumber);
    printf("The two numbers subtracted is %.02f.\n\n", subtractTwoNumbers(firstNumber, secondNumber));

    printf("Please enter a number:");
    scanf("%f", &firstNumber);
    printf("Please enter a second number:");
    scanf("%f", &secondNumber);
    printf("The two numbers multiplied together is %.02f.\n\n", multiplyTwoNumbers(firstNumber, secondNumber));

    printf("Please enter a number:");
    scanf("%f", &firstNumber);
    printf("Please enter a second number:");
    scanf("%f", &secondNumber);
    printf("The two numbers divided together is %.02f.\n\n", divideTwoNumbers(firstNumber, secondNumber));

    printf("Starting conversion...\n\n");

    printf("Please enter an amount of gallons to convert:");
    scanf("%f", &gallons);
    printf("You have %.02f gallons, %.02f quarts, %.02f pints, and %.02f cups.\n\n", gallons, convertGallonsToQuarts(gallons), convertGallonsToPints(gallons), convertGallonsToCups(gallons));

    printf("Please enter degrees in Fahrenheit to convert to Celsius:");
    scanf("%f", &fahrenheit);
    printf("The degrees in Celsius is %.01f\xf8 C.\n\n", convertFahrenheitToCelsius(fahrenheit));

    printf("Please enter degrees in Celsius to convert to Fahrenheit:");
    scanf("%f", &celsius);
    printf("The degrees in Fahrenheit is %.01f\xf8 F.\n\n", convertCelsiusToFahrenheit(celsius));

    return 0;
}
