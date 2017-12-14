#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define SIZE 10
/*
        Test 3 Review: Stuff to know

        #define SOMEVALUE 15
        #define SOMEFUNCTION x (x * x); //Probably not on the test
        #if 0 and other preprocessors
    */
//Declaring global variables
int someData[100]; //int array with a size of 100. Buffer overload: exceeding the maximum array index
int someIntArray[] = {24, 34, 44, 54, 64, 74, 84}; //Not on the test but this formatting is possible.

int main()
{

#if 0
    // ARRAYS
    int i, min = 2000, max = -2000, theirNumber;
    float average = 0, total = 0;

    /*Placing information into our int array
    someData[0] = 14; // Arrays start with the first index being zero; assigning 14 to index 0;
    someData[1] = 22; / and so on..to 100. */

    printf("Please enter 10 numbers\n");
    for (i = 0; i < 10; i++)
    {
        printf("Please enter a number:");
        scanf("%d", &theirNumber);
        //adding the number to our array
        someData[i] = theirNumber;
        //one step = scanf the array index &someData[i]
    }
    // if 1 : compiles  if 0: ignores
    for (i = 0; i < 10; i++)
    {
        printf("index %d has the value %d\n", i, someData[i]);
        if (someData[i] < min){
            min = someData[i];
        } else if (someData[i] > max){
            max = someData[i];
        }
        total += someData[i];
    }
    printf("\nThe min value is %d\nThe max value is %d\nThe average is %.02f\n", min, max, total / 10);
#endif // 0

#if 0    // FILE IO
    FILE *fp;
    int i, grade = 0, howMany = 0, index = 0;
    char name[20];

    fp = fopen("C:\\Users\\Chris\\Documents\\COP-Work\\somefile.txt", "r");

    if (fp == NULL){
        printf("File not found.\n");
        return 0;
    }

    printf("File found.\n");

    fscanf(fp, "%d", &howMany);
    printf("%d\n", howMany);

    for (i = 0; i < howMany; i++){
        fscanf(fp, "%s %d", name, &index);
        printf("Name: %s, HI: %d\n", name, index);
    }


    printf("File closed.\n");
    fclose(fp);
#endif // 0
#if 0
    char name[50];
    char copyOfName[50];
    int len;

    printf("What is your dogs name?");
    scanf("%s", name);

    strcpy(copyOfName, name); //string copy

    len = strlen(name); // string length
    printf("Your dogs name has %d characters\n", len);

    if (stricmp(name, "fido") == 0){ //string compare whole
        printf("Oh how original!!!\n");
    } else if (strncmp(name, "fido", 2) == 0){ //string compare 2 characters
        printf("Your dog's name might be fido\n");
    }

    strncpy(copyOfName, name, 2); //copy only two characters

#endif // 0

















    return 0;
}
