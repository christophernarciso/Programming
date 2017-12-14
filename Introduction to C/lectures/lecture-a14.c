#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define NUMSTATES 50
/*
    Lesson: Structures
*/

struct state{
    char name[50];
    char capital[50];
    int population;
    float lat, logi;
};

struct state st[NUMSTATES];

void loadStates(){
    FILE *file;
    int i;

    file = fopen("PATH_TO_STATES.TXT_FILE", "r"); //attempt to open file

    if (file == NULL){ //check if it exists
        printf("File not found.\n");
        return; //leave the function
    }

    for (i = 0; i < NUMSTATES; i++){
        fscanf(file, "%s %s %f %f %d", st[i].name, st[i].capital, &st[i].lat, &st[i].logi, &st[i].population);
    }

    fclose(file); //close file
}

void displayStates(){
    int i;
    for (i = 0; i < NUMSTATES; i++){
        printf("%s %s %d\n", st[i].name, st[i].capital, st[i].population);
    }
}

void findState(char fs[]){
    int i;
    for (i = 0; i < NUMSTATES; i++){
        if (stricmp(fs, st[i].name) == 0){
            printf("Match found.\n");
            break;
        }
    }
}

int main()
{
    //struct state st;
#if 0 //set to 1 to show code
    strcpy(st.name,"Florida");
    strcpy(st.capital,"Tallahassee");
    st.population = 1000000;
    st.lat = 4.4;
    st.logi = -81;
#endif // 0
#if 0 //set to 1 to show code
    printf("Enter information about a state.\n");
    printf("What is the name of the state?");
    scanf("%s", st.name);

    printf("What is the capital?");
    scanf("%s", st.capital);

    printf("What is the current population?");
    scanf("%d", &st.population);

    printf("What is the latitude?");
    scanf("%f", &st.lat);

    printf("What is the longitude?");
    scanf("%f", &st.logi);

    printf("Name:%s  Capital:%s  Population:%d\nLatitude:%.02f  Longitude:%.02f",
            st.name, st.capital, st.population, st.lat, st.logi);
#endif // 0

#if 0
    char searchState[50];
    loadStates();
    displayStates();

    printf("What state would you like to search for?");
    scanf("%s", searchState);
    findState(searchState);
#endif // 0
    return 0;
}
