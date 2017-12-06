#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void askForName(char preface[], char result[]){
    printf("%s", preface);
    scanf("%s", result);
}

int main()
{
    //Define our variables
    char resFirst[100], resLast[100], nameFull[100];

    //ask for the first/last name.
    askForName("Enter your first name:", resFirst);
    askForName("Enter your last name:", resLast);

    /*copy the arrays to name
    char *strcpy(char *dest, const char *src)
    Copies the string pointed to, by src to dest.
    */
    strcpy(nameFull, resFirst);

    /*
    char *strcat(char *dest, const char *src)
    Appends the string pointed to, by src to the end of
    the string pointed to by dest.
    */
    strcat(nameFull, " ");
    strcat(nameFull, resLast);

    //output the FIRST, LAST, FULL NAME, LENGTH1, LENGTH2, LENGTH3
    printf("\nFirst name: %s  Last name: %s  Full name: %s\n", resFirst, resLast, nameFull);
    printf("Length of first name: %d  Length of last name: %d  Length of full name: %d\n\n", strlen(resFirst), strlen(resLast), strlen(nameFull));

    //Reusing declared variable resFirst for state portion
    askForName("Enter a state:", resFirst);
    if (stricmp(resFirst, "Alaska") == 0){
        printf("%s is cold.\n", resFirst);
    } else if (stricmp(resFirst, "Florida") == 0){
        printf("%s is hot.\n", resFirst);
    } else {
        printf("%s is a nice state.\n", resFirst);
    }
    return 0;
}
