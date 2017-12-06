#include <stdio.h>
#include <stdlib.h>

/* Variable declaration */



int main()
{/* start of the program */
    /* integer -2.147billion - +2,147billion*/
    int heightInInches, distanceInMiles;
    int measureInLiquidOunces;
    /* unsigned int 0 - 4billion */
    unsigned int suzy;

    /* short 2 bytes -32767 - +32767*/
    short george;

    /* unsigned short 0 - 65535 */
    unsigned short sammy;

    /* long -2billion - 2billion */
    long timmy;

    /* unsigned long 0 - 4 billion */
    unsigned long jose;

    /* other types:
        flout
        double
        long double
    */

    float myBankAccount;

    /* char -127 - +127 */
    char a;

    /* unsigned char 0 to 235*/
    unsigned char b;

    /* string array */
    char myName[] = "Rick"; /* ,.... */

    /* assignment statements */
    suzy = 42;
    myBankAccount = 2.38;
    timmy = 1009;
    a = 65; /* or 'A' */

    /* print variables */

    /* placeholders %d || %f || %.02f 2 = number of zeros placement || %c*/
    printf("Now suzy is %d\n", suzy);

    /* limiting the zeros*/
    printf("Your bank account contains: $%.02f\n", myBankAccount);

    /* passing multiple types or values to print function */
    printf("Suzy's value is %d and timmy's value is %d\nand my bank account contains $%.02f\n", suzy, timmy, myBankAccount);

    /* print char aesky value*/
    printf("a is now %c\n", a);
    printf("a is now %d\n", a);

    /* print array */
    printf("My name is: %s", myName);



    /*
        Can not declare variables here. Must do it at the top of the program.
    */
    return 0;
}/* end of the program */
