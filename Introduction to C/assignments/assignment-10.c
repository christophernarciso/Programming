#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#define MAX_LOOP 10

struct contactInfo{
    char fn[50], ln[50];
    char address[50], city[50], state[50];
    char zip[50], phone[50];
    struct contactInfo *next_permutation;
};

struct contactInfo ci;

void getContactInfo(struct contactInfo *ci){
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

void addContactInfoToList(){
    struct contactInfo *tmp = &ci;

    while (tmp->next_permutation != NULL){ //If tmp->next_permutation is not null, then there is a successor.
        tmp = tmp->next_permutation;
    }

    tmp->next_permutation = calloc(sizeof(struct contactInfo), 1); //mallac allocates memory, calloc allocates and zeros it out
    getContactInfo(tmp->next_permutation);

}

void displayContactInfo(){
    printf("\nDisplaying all contact info.\n");
    struct contactInfo *tmp = &ci;
    int i = 0;

    tmp = tmp->next_permutation;

    while (tmp != NULL){ //If tmp->next_permutation is not null, then there is a successor.
        printf("\nContact Info [%d].\nFirst:%s Last:%s\nAddress:%s, City:%s, State:%s Zipcode:%s\nPhone Number:%s\n",
                i + 1, tmp->fn, tmp->ln, tmp->address, tmp->city, tmp->state, tmp->zip, tmp->phone);
        tmp = tmp->next_permutation;
        i++;
    }
}

int main()
{
    int i;

    for (i = 0; i < MAX_LOOP; i++){
        printf("CONTACT [%d]\n", i + 1);
        addContactInfoToList();
    }

    displayContactInfo();

    return 0;
}
