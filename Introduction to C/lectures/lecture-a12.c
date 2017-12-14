#include <stdio.h>
#include <stdlib.h>
#include <string.h> // allows us to use the string comparison function

/*
    Strings lesson
    strings are character arrays
*/

char myName[] = "Chris"; // array total of 5
char yourName[50];
char name[50];
char input[50];

int main()
{
    /*ALL ON TEST. Monday is review day. ARRAYS | FILES | STRINGS
    int len;

    printf("What is your name?");
    scanf("%s", name);

    len = strlen(name); //string character length
    printf("Hello %s, your name has %d characters\n", name, len);


    /*
    //strncpy();
    printf("What is your name?");
    scanf("%s", name);

    //strcpy(input, name); //copy name array to input array using the string copy function.
    strncpy(input,name,2); // copy only the number of characters you input into the function.

    printf("You copied %s from name to input", input);



    /*
    char dir[50];
    printf("Lets play a game!!\nWhat is your name?");
    scanf("%s", yourName);

    do {
        printf("You can go North, South, East, West, or Quit\nWhat is your choice?");
        scanf("%s", dir);
        if (stricmp(dir, "north") == 0){
            printf("You are going to where is is cold\n");
            printf("Better bundle up\n");
        } else if (stricmp(dir, "south") == 0){
            printf("You are going to where is is warm\n");
            printf("Hope you dont run into the ocean\n");
        } else if (stricmp(dir, "east") == 0){
            printf("You are going to where you will get wet\n");
            printf("Take your bathing suit.\n");
        } else if (stricmp(dir, "west") == 0){
            printf("You are going a long way\n");
            printf("Stop in Lakeland\n");
        } else {
            printf("That was not a choice of direction. Please try again!");
        }
    } while(stricmp(dir, "Quit") != 0);


    /*
    yourName[0] = 'B'; // single quote means a single character
    yourName[1] = 'O'; // single quote means a single character
    yourName[2] = 'B'; // single quote means a single character
    yourName[3] = 'B'; // single quote means a single character
    yourName[4] = 'Y'; // single quote means a single character
    yourName[5] = 0; // NULL terminator

    printf("My name is %s\n", myName);
    printf("Your name is %s\n", yourName);

    printf("What is your dogs name:");
    scanf("%s", yourName); // for strings and arrays you do not need an ampersand or placeholder
    printf("You said your dogs name is %s\n", yourName);

    // strcmp just compares strings ignoring case sensitive
    // stricmp( compares the strings even if they are case sensitive. (insensitive compare)

    if (stricmp(yourName, "spot") == 0){ // string comparison function using insensitive compare
        printf("Your dog is Spot!\n");
    }

    strcat(yourName, " is your dog");
    printf("%s\n", yourName);
    */



    return 0;
}
