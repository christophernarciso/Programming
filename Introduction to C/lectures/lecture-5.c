#include <stdio.h>
#include <stdlib.h>

//global variables are always set to zero
int thisIsAGlobalVariable;

int main()
{

    /*int age;

    printf("What is your age?");

    scanf("%d", &age);

    /*
    * if condition one
      else if condition two
      else ....
    *
    */
    /*if (age == 21)
        printf("you are LEGAL\n");
    else if (age <= 5)
        printf("you are a toddler\n");
    else printf("That is a nice age.\n");

    */
    //local variables must be set to their respective number. in this case zero
    int choice, score = 0;


    /*
        Programming is full of patterns. the following code will follow a situation pattern.
    */
    printf("You are walking down a dusty road\n");
    printf("You see a gemstone in the middle of the road\n");
    printf("What would you like to do?\n");

    //Show choices

    printf("1. Kick it off the road\n");
    printf("2. Pick it up\n");
    printf("3. Stomp on it\n");

    scanf("%d", &choice);

    //use conditional statements

    if (choice == 1){
        printf("You just kicked the hope diamond into the weeds.");
        score -= 100;
    } else if (choice == 2) {
        printf("Good job, you are now rich\n");
        score += 250;
    } else if (choice == 3) {
        printf("The gemstone explodes and you are burnt.");
        score -= 150;
    } else {
        printf("That was not a choice. Please try again.");
        score -= 1000;
    }

    //show the score
    printf("Your score is %d\n", score);

    //show situation 2.
    printf("You see a cave on the side of the road.\n");
    printf("It looks foreboding\n");
    printf("What do you want to do?\n");

    //Display choices
    printf("1.Enter the cave\n");
    printf("2.Run home to mommy\n");
    printf("3.Throw the gemstone in the cave\n");

    //grab input
    scanf("%d", &choice);

    /*Respond to choice using conditionals */
    if (choice == 1){
        printf("Ah, an adventurer you enter\n");
        score += 50;
    } else if (choice == 2){
        printf("You are a whiner");
        score -= 17;
    } else if (choice == 3){
        printf("You just lost the diamond. Now you have to go get it.");
        score -= 10;
    } else {
        printf("Incorrect input. Please try again.");
        score -= 1000;
    }

    //display situation 3
    printf("The cave is dark, and you are apprehensive.\n");
    printf("You see some movement in the back of the cave\n");
    printf("You have several choices\n");

    //display choices
    printf("1.Light a bonfire in middle of the room\n");
    printf("2.Throw the gem at the movement\n");
    printf("3.Run headlong into the movement\n");

    scanf("%d", &choice);

    //respond to choice using conditional statements
    if (choice == 1){
        printf("A cuddly koala bear sits down beside you\n");
        score += 100;
    } else if (choice == 2){
        printf("A huge monster just swallowed your diamond\n");
        score -= 16;
    } else if (choice == 3){
        printf("Your head wracks you pain as your skull cracks\n");
        score -= 176;
    } else {
        printf("That was not a choice. try again.");
        score -= 1000;
    }


    return 0;
}

