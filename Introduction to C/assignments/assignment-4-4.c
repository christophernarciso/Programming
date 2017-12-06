#include <stdio.h>
#include <stdlib.h>
#include <windows.h>

int main()
{
    srand(GetTickCount()); // creates a unique pattern every time we play
    int theAnswer = rand() % 21; //0-20
    int theirGuess = -1;
    int numberOfGuesses = 0;

    do {
        printf("Please enter a number from 0 to 20: ");
        scanf("%d", &theirGuess);

        if (theirGuess > theAnswer){
            printf("That was too high.\n");
        } else if (theirGuess < theAnswer) {
            printf("That was too low.\n");
        } else {
            printf("You guessed correctly.\n");
        }

        numberOfGuesses++;
    } while (theAnswer != theirGuess);

    printf("You got it in %d guesses!", numberOfGuesses);

    return 0;
}
