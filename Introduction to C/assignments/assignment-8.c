#include <stdio.h>
#include <stdlib.h>

struct contactInfo {
    char firstName[50];
    char lastName[50];
    char city[50];
    char state[50];
    char phone[50];
};

struct contactInfo ci;

void getContactInformation(){
    printf("First Name:");
    scanf("%s", ci.firstName);

    printf("Last Name:");
    scanf("%s", ci.lastName);

    printf("City:");
    scanf("%s", ci.city);

    printf("State:");
    scanf("%s", ci.state);

    printf("Phone:");
    scanf("%s", ci.phone);
}

void displayContactInformation(){
    printf("Hello, %s %s.\n", ci.firstName, ci.lastName);
    printf("You live in %s, %s.\n", ci.city, ci.state);
    printf("Your phone number is %s.\n", ci.phone);
}

int main()
{
    getContactInformation();
    displayContactInformation();
    return 0;
}
