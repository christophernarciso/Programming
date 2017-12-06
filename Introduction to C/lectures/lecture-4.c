#include <stdio.h>
#include <stdlib.h>

int main()
{
    //Binary 128 64 32 16 8 4 2 1

    //11000011 = 128+64+2+1=
    //10100101 = 128+32+4+1=165


    //Conditionals
    /*
        if it rains I will use my umbrella
            *'if' being the keyword for the conditional test
            *if the conditional test is true 'rains' then the result will be used 'I will use my umbrella
        *if (test) {        or        if(test)
            result                    {
         }                            }
    */

    /*
    int rain = 0;

    if (rain == 10) { // if 0 equals 10 is our test FALSE
        printf("It is raining.\n");
        printf("I need an umbrella.\n");
    }

    if (rain == 0) { // if 0 equals 0 is our test TRUE
        printf("It is not raining.\n");
        printf("I need sunglasses.\n");
    }
    */
    /*
    int age;
    printf("What is your age?");
    scanf("%d", &age);

    if (age == 21){
        printf("You are now completely legal\n");
    } else if (age <= 5){
        printf("You are very young\n");
    } else if (age == 16){
        printf("Driver's license year\n");
    } else {
        printf("That is a good age\n");
    } */

    /*
    int height;
    printf("What is your height (in inches):");
    scanf("%d", &height);

    if (height >= 87){
        printf("You are very tall\n");
    } else if (height >= 72){
        //nested if statement
        if (height == 74){
            printf("That is my favorite height\n");
        } else printf("That is a good height\n");
    } else if (height >= 60){
        printf("You are in the average range\n");
    } else {
        printf("Consider the circus\n");
    } */

    int rain;
    int close;

    printf("Enter 0 if it is not raining and 10 if it is:");
    scanf("%d", &rain);
    if (rain != 0 && rain != 10){
        printf("That is wrong, values of 0 or 10\n");
        return 0; //ends the program
    }
    printf("Enter 0 if you are close to your car and 10 if it is far:");
    scanf("%d", &close);
    if (close != 0 && close != 10){
        printf("That is wrong, values of 0 or 10");
        exit(0); //another way to end the program
    }

    if (rain == 10 && close == 0){ //if rain equals 10 AND close equals 0 | && = AND operator
        printf("It is raining and I am going to run to the car\n");
    } else if (rain == 10 && close == 10){
        printf("It is raining and I am NOT going to run to the car\n");
    } else if (rain == 0 && close == 10){
        printf("...");
    } else if (rain == 0 && close == 0){
        printf("...");
    }









    return 0;
}
