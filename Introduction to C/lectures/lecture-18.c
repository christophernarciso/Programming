#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>

/**
Lesson 17 Notes:
1) Memory Allocation
2) LinkedLists
3) Structures
**/

struct contactInfo{
    char fn[50], ln[50];
    char address[50], city[50], state[50];
    char zip[50], phone[50];
    struct contactInfo *next_permutation;
};

void askForContactInfo(struct contactInfo *ci){
    printf("What is your first name?");
    scanf("%s", ci->fn);
    printf("What is your last name?");
    scanf("%s", ci->ln);
    printf("What is your address?");
    scanf("%s", ci->address);
    printf("What is your city?");
    scanf("%s", ci->city);
    printf("What is your state?");
    scanf("%s", ci->state);
    printf("What is your zipcode?");
    scanf("%s", ci->zip);
    printf("What is your phone number?");
    scanf("%s", ci->phone);
}

void addAnother(struct contactInfo *ci){
    ci->next_permutation = malloc(sizeof(struct contactInfo));
    ci->next_permutation->next_permutation = NULL;
    askForContactInfo(ci->next_permutation);
}


int main()
{
    #if 1

    #endif // 0

    #if 1

    struct contactInfo *ci;
    ci = malloc(sizeof(struct contactInfo));
    ci->next_permutation = NULL;
    askForContactInfo(ci);

    addAnother(ci);

    //ci->next_permutation = malloc(sizeof(struct contactInfo));
    //ci->next_permutation->next_permutation = NULL;
    //askForContactInfo(ci->next_permutation);

    #endif // 0

    #if 0
    //Replacing int data[10000] with an explicit method of a pointer instead of implicitly
    int *data; //pointer explicit
    //int data[100000] implicit

    //Dynamic memory allocation: Happens at runtime rather than at the compiler.
    //pointer = malloc(size of pointer); | Syntax of this function
    data = malloc(100000 * sizeof(int));

    /*Just like file pointers we have to error track these
    * Prevents the program from crashing. because of a null pointer
    */
    if (data == NULL){
        printf("Not enough memory.");
        return 0;
    }

    //Data is now a array
    //data[0] = 15;
    //data[1] = 999;

    *data = 15;//sets 5 to the next integer index
    data++;//points to the next integer index
    //Cleaner version of the above: *data++ = 15;
    *data = 999;

    /*
    When you allocate pointers they become like arrays.
    You should always free allocated memory when you get a chance.
    data[0] = 15; data++;
    *data = 15; (*data++ = 15;)
    The two are equivalent.
    */

    //After using our pointer we need to free up memory
    free(data);
    #endif // 0

    return 0;
}
