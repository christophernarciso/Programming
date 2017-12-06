#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
* Data Structures Lesson 2 WED LEC
* |Lesson Flow Chart|
* 1)Ask for Contact Info
* 2)Write to disk
* 3)Ask - quit
*/

struct contactInfo { //Contact Info Structure with its stored variables
    char name[50];
    char address[50];
    char city[50], state[50], zip[50];
    char phone[50];
};

struct contactInfo contactInfo; //Global structure variable

void enterContactInfo(){
    printf("What is your name:");
    gets(contactInfo.name);

    printf("What is your address:");
    gets(contactInfo.address);

    printf("What is your city:");
    gets(contactInfo.city);

    printf("What is your state:");
    gets(contactInfo.state);

    printf("What is your zipcode:");
    gets(contactInfo.zip);

    printf("What is your phone:");
    gets(contactInfo.phone);
}

void saveContactInfo(){
    FILE *fp;
    fp = fopen("C:\\Users\\Chris\\Desktop\\contactInfo.txt", "a"); //can replace to "contactInfo.txt" to use the current directory, "a" to append information

    if (fp == NULL){
        printf("File could not be opened");
        return;
    }

    fprintf(fp, "%s\n%s\n%s\n%s\n%s\n%s", contactInfo.name, contactInfo.address, contactInfo.city, contactInfo.state, contactInfo.zip, contactInfo.phone);

    fclose(fp);
}

void displayContactInfo(){
    FILE *fp;
    fp = fopen("C:\\Users\\Chris\\Desktop\\contactInfo.txt", "r"); //can replace to "contactInfo.txt" to use the current directory

    if (fp == NULL){
        printf("File could not be opened");
        return;
    }

    while (!feof(fp)){
        readNextRecord(fp);
        displayNextRecord(fp);
    }

    fclose(fp);
}

void readNextRecord(FILE *fp){
    fgets(contactInfo.name, sizeof(contactInfo.name), fp);
    fgets(contactInfo.address, sizeof(contactInfo.address), fp);
    fgets(contactInfo.city, sizeof(contactInfo.city), fp);
    fgets(contactInfo.state, sizeof(contactInfo.state), fp);
    fgets(contactInfo.zip, sizeof(contactInfo.zip), fp);
    fgets(contactInfo.phone, sizeof(contactInfo.phone), fp);
}

void displayNextRecord(FILE *fp){
    printf("Next record\n");
    printf("%s", contactInfo.name);
    printf("%s", contactInfo.address);
    printf("%s", contactInfo.city);
    printf("%s", contactInfo.state);
    printf("%s", contactInfo.zip);
    printf("%s", contactInfo.phone);
}

void doSearch(char searchCriteria[50]){
    FILE *fp;
    fp = fopen("C:\\Users\\Chris\\Desktop\\contactInfo.txt", "r"); //can replace to "contactInfo.txt" to use the current directory

    if (fp == NULL){
        printf("File could not be opened");
        return;
    }

    int length = strlen(searchCriteria); //grabs the length of the string

    while (!feof(fp)){
        readNextRecord(fp);
        if (strnicmp(searchCriteria, contactInfo.name, length) == 0){
            displayNextRecord(fp);
            break;
        }
    }

    fclose(fp);
}

int main()
{
    char answer[50];
    #if 0 //enters details, saves to a file, and displays the details
    do {
        enterContactInfo();
        printf("Would you like to add any more contacts? (type quit to QUIT inputing information or type any key to continue)\n");
        gets(answer);
    } while (stricmp(answer, "quit") != 0);

    saveContactInfo();
    displayContactInfo();
    #endif // 0
    #if 1 //searches the file for the name and displays contact information
    printf("Enter the name for which you want to search:");
    gets(answer); //similar to scanf
    doSearch(answer);
    #endif // 1
    return 0;
}
