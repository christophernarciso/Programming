/* Defines for functions */
#include <stdio.h>
#include <stdlib.h>

int main()
{

    /* Use camel casing when declaring variable names: firstName, lastName, ... */
    /* Integer number */
    int age, months, days;
    /* array of characters | Buffer overload error: when it passes the 50 indexs or space*/
    char firstName[50];
    char lastName[50];
    /* Can also change to use floats. Just change the declarations and placeholders */

    /* Ask the user for age input */
    printf("Please enter your age: \n");
    /* removing the '\n' will put it on one line. */

    /* Looks for user input in console */
    /* & points to the variable type | 'put it' into the variable */
    scanf("%d", &age);
    /* Can also add multiple variables to the scanf function ex. int weight = 32;
        scanf("%d %d", &age, &weight);
    */

    /* initialize variable months */
    months = age * 12;
    days = age * 365;
    /* Print age to console */
    printf("Oh, you said that you are %d years old.\n", age);
    printf("You are %d months old. \nYou are %d days old. \n", months, days);

    /* Ask for name input from the console */
    printf("Please enter your first and last name: \n");
    /* if only needing one string do %s. For multiple add more placeholder '%s' */
    /* char arrays do not need the '&' in front of the variable for the scanf function*/
    scanf("%s %s", firstName, lastName);

    /* Print the char to console */
    printf("You said your name is: %s %s\n", firstName, lastName);
    return 0;
}
