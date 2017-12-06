#include <stdio.h>
#include <stdlib.h>

int main()
{
    int startingNumber, endingNumber, incrementValue, i = 0;

    printf("For loop\n");

    printf("What is your starting number: ");
    scanf("%d", &startingNumber);

    printf("What is your ending number: ");
    scanf("%d", &endingNumber);

    printf("What is your increment number: ");
    scanf("%d", &incrementValue);

    for (i = startingNumber; i <= endingNumber; i += incrementValue){
        printf("The counter value is %d\n", i);
    }

    printf("while loop\n");

    printf("What is your starting number: ");
    scanf("%d", &startingNumber);

    printf("What is your ending number: ");
    scanf("%d", &endingNumber);

    printf("What is your increment number: ");
    scanf("%d", &incrementValue);

    while (startingNumber <= endingNumber){
        printf("The counter value is %d\n", startingNumber);
        startingNumber += incrementValue;
    }

    printf("Do While loop\n");

    printf("What is your starting number: ");
    scanf("%d", &startingNumber);

    printf("What is your ending number: ");
    scanf("%d", &endingNumber);

    printf("What is your increment number: ");
    scanf("%d", &incrementValue);

    do {
        printf("The counter value is %d\n", startingNumber);
        startingNumber += incrementValue;
    } while (startingNumber <= endingNumber);


    return 0;
}
