#include <stdio.h>
#include <stdlib.h>

void showSituation(){
    printf("You are walking down a dusty road\n");
    printf("You see a gemstone in the middle of the road\n");
    printf("What would you to do?\n");
}

int getChoice(){
    int i;
    while (i < 1 || i > 3){
        printf("1. Kick it off the road\n2. Pick it up\n3. Stomp on it\nEnter your choice:");
        scanf("%d", &i);
    }
    return i;
}

int getScore(int choice){
    int currentScore = 0;
    printf("Choice: %d\n", choice);
    switch (choice){
        case 1:
            printf("You kicked the hope diamond into the weeds. Boo-hoo\n");
            currentScore -= 10;
            break;
        case 2:
            printf("You pick it up. it starts glowing.\n");
            currentScore += 50;
            break;
        case 3:
            printf("You stomp on it. you hear a sudden roar.\n");
            currentScore += 150;
            break;
    }
    printf("Score: %d\n", currentScore);
    return currentScore;
}

int main()
{
    int choice, score = 0;

    showSituation();
    choice = getChoice();
    score = getScore(choice);





    /*
    -void is the returnType
    -myFunc is the the function name
    -in between the () are called parameters
    void myFunc(){
        code here
    }

    - int is the return type
    - everything but void must return the type presented in the return type.
    int myOther(){
        int i = 0;
        return i; //must return an integer value.
    )

    void myFunc2(int a){ // can be any other types , double a ,....
        printf("Val is %d", a);
    }



    adventure game below
    */





    return 0;
}
