#include <stdio.h>
#include <stdlib.h>
#define ASIZE 10

/*
    Arrays lesson two


int array[ASIZE];
int result[ASIZE];
int combine[ASIZE];

float grade[100];
*/


int main(){
    /* FILE POINTER EXAMPLES

    FILE *fp;
    int howManyGrades, i;
    float thisGrade, min = 1000, max = -1000, avg, total;

    fp = fopen("C:\\cop3223\\grades.txt", "r"); //(PATH TO FILE, "r" for READING THE FILE)
    if (fp == NULL){ // IF FILE IS MISSING STOP THE PROGRAM
        printf("Could not open file.\n");
        return 0;
    }
    printf("File was opened.\n");
    fscanf(fp, "%d", &howManyGrades); //READ FROM THE FILE
    printf("There are %d grades in grades.txt\n", howManyGrades); //PRINT GRADES

    for (i = 0; i < howManyGrades; i++){
        fscanf(fp, "%f", &thisGrade);
        printf("This grade is %.02f\n", thisGrade);

        total += thisGrade;

        if (thisGrade < min)
            min = thisGrade;
        if (thisGrade > max)
            max = thisGrade;
    }

    avg = total / howManyGrades;
    printf("AVG: %.02f MIN: %.02f MAX: %.02f", avg, min, max);

    fclose(fp); //CLOSE THE FILE.

    */

    /* ARRAY EXAMPLES
    int i;

    for (i = 0; i < ASIZE; i++){
        array[i] = i * 2 + 1;
        //array[i] = (i + 1) * 5;
        result[i] = array[i] * 2;
        combine[i] = array[i] + result[i];
        printf("Array i: %d\n\nResult i %d\n\nCombine i %d\n\n", array[i], result[i], combine[i]);
    }


    int i, howManyGrades;
    float thisGrade, min = 1000, max = -1000, avg, total;


    do {
        printf("How many grades:");
        scanf("%d", &howManyGrades);
    } while (howManyGrades < 1 || howManyGrades > 100);

    for (i = 0; i < howManyGrades; i++){
        printf("Enter the next grade:");
        scanf("%f", &thisGrade);
        grade[i] = thisGrade;
        total += grade[i];

        if (thisGrade < min){
            min = thisGrade;
        }
        if (thisGrade > max){
            max = thisGrade;
        }
    }

    avg = total / howManyGrades;

    printf("Grade avg: %.02f  Min grade %.02f:  Max grade: %.02f", avg, min, max);
    */

    return 0;
}
