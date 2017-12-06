#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
int main()
{
    //loops
    //int i = 0;
    //int start, end, increment;

    /*starting point;condition;increment
    for (i; i <= 10; i++){
        //loops through 10 or anything you set
        printf("Counter: %d\n", i);
    }*/

    /*printf("Starting number: ");
    scanf("%d", &start);

    printf("Ending number: ");
    scanf("%d", &end);

    printf("Incrementing number: ");
    scanf("%d", &increment);*/

    /*for(i = start; i <= end; i+=increment){
        printf("The value of i is %d\n", i);
    }*/ //for loop

    //Two keywords for loops: continue; & break; if i == 5 continue; or if i == 5 break;

    /*while loop below

    while (start <= end){
        printf("The value of i is %d\n", start);
        start+=increment;
    }*/


    //guessing game 1
    /*srand(GetTickCount()); // creates a unique pattern every time we play
    int theAnswer = rand() % 20;
    int theirGuess = -1;
    int numberOfGuesses = 0;

    while (theAnswer != theirGuess){
        printf("Please enter a number from 0 - 19: ");
        scanf("%d", &theirGuess);

        if (theirGuess > theAnswer)
            printf("That was too high.\n");
        else if (theirGuess < theAnswer)
            printf("That was too low.\n");
        else
            printf("You guessed correctly.\n");

        numberOfGuesses++;
    }

    printf("It took you %d guesses to get the correct answer.", numberOfGuesses);
    */

    /*int choice;

    printf("You are walking down a dusty road\n");
    printf("You see a gemstone in the middle of the road\n");
    printf("What would you like to do?\n");

    //Show choices

    printf("1. Kick it off the road\n");
    printf("2. Pick it up\n");
    printf("3. Stomp on it\n");

    while (choice < 1 || choice > 3){
        printf("Your choice:");
        scanf("%d", &choice);
    }*/


    /*int i;

    for(i=1; i <= 10; i++){
        printf("The value of i is now %d\n", i);
    }*/


    //do while loop

    int i = 0;
\
    do {
        printf("The value of i is %d\n", i);
        i++;
    }while(i <= 10);
















    return 0;
}
