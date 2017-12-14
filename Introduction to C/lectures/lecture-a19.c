#include <stdio.h>
#include <stdlib.h>
#include <malloc.h>
#include <string.h>
/**
More LinkedList notes
1) Assignment 14 is canceled and assignment 13 is extended to this thursday.
2) struct + next-> struct2 + next2-> struct3 + next-> keep adding until the end. The last next is NULL
3)
*/
//data structure for cars
struct cars{
    char make[50], model[50];
    char year[50], color[50];
    struct cars *next;
};

//global data struct variable car
struct cars car;

void enterCarInfo(struct cars *ci){
    printf("Enter the car make:");
    scanf("%s", ci->make);
    printf("Enter the car model:");
    scanf("%s", ci->model);
    printf("Enter the car year:");
    scanf("%s", ci->year);
    printf("Enter the car color:");
    scanf("%s", ci->color);
}

void addNewCar(){
    //Pointing to the global car data structure variable.
    struct cars *tmp = &car;

    //loop through the structures until we have reached the last. last in the list == NULL
    while (tmp->next != NULL){ //Loop until it is NULL
        //Points to the next structure in the list. "Move to the next successor" - rick 2017
        tmp = tmp->next;
    }

    //Calling calloc zeros it out. Prefered by the professor over the malloc function.
    tmp->next = calloc(sizeof(struct cars), 1);

    //Call the information function for the user to input details about the next car.
    enterCarInfo(tmp->next);
}

void displayCarInformation(){
    //Pointing to the global car data structure variable.
    struct cars *tmp = &car;

    //First in the linked-list is NULL so we skip to the next. Display the next piece of information
    tmp = tmp->next;

    //loop through the structures until we have reached the last. last in the list == NULL
    while (tmp != NULL){ //loop until tmp is NULL
        //Points to the next structure in the list. "Move to the next successor" - rick 2017
        printf("CAR INFORMATION:\n%s %s\n%s %s", tmp->make, tmp->model, tmp->year, tmp->color);
        tmp = tmp->next;
    }
}

void saveCarInformationToFile(){
    printf("\nSaving information.\n");
    //File pointer
    FILE *fp;
    //Pointing to the global car data structure variable.
    struct cars *tmp = &car;

    printf("Attempting to open file...\n");
    //opens the file or creates the file at that directory
    fp = fopen("C:\\Users\\Chris\\Desktop\\CarInformation.txt", "w");

    //check if the file exists or couldnt be created
    if (fp == NULL){
        printf("File not found.\n");
        return 0;
    }

    //skip the first in the list since it is NULL or BLANK. (first black header) - rick
    tmp = tmp->next;

    while (tmp != NULL){ //loop until tmp is NULL
        fprintf(fp, "%s", tmp->make); //writes the make to the file
        fprintf(fp, "%s", " "); //space
        fprintf(fp, "%s", tmp->model); //writes the model
        fprintf(fp, "%s", " "); //space
        fprintf(fp, "%s", tmp->year); //writes the year
        fprintf(fp, "%s", " "); //space
        fprintf(fp, "%s", tmp->color); //writes the color

        tmp = tmp->next; //next car
    }

    printf("Closing the file.");
    fclose(fp); //close the file.
}

void freeAllMemory(){
    //Pointing to the global car data structure variable.
    struct cars *tmp = &car, *veryTmp;

    //skip the first since we do not need to free it. First is NULL or empty
    tmp = tmp->next;

    while (tmp != NULL){ //loop until tmp is NULL
        //set a temp variable to equal the last tmp
        veryTmp = tmp;
        //set a new tmp
        tmp = tmp->next;
        //free memory from the last tmp
        free(veryTmp);
    }
}

int main()
{
    char answer[50];
    do {
        addNewCar();
        printf("\nIf you would like to stop inputing cars please type quit. to keep going press any key\n");
        scanf("%s", answer);
    } while (stricmp(answer, "quit") != 0);

    //Displays all the car information in our list
    displayCarInformation();

    //Create and save to file CarInformation.txt
    saveCarInformationToFile();

    //Free memory from the program
    freeAllMemory();
    return 0;
}
