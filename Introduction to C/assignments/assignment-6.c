#include <stdio.h>
#include <stdlib.h>
#define ARRAY_SIZE 10

int data[ARRAY_SIZE];

int main()
{
    int i, total;

    for (i = 0; i < ARRAY_SIZE; i++){
        printf("Enter number %d:", i + 1);
        scanf("%d", &data[i]);
        total += data[i];
    }

    for (i = 0; i < ARRAY_SIZE; i++){
        printf("Number %d is %d.\n", i + 1, data[i]);
    }

    printf("The average of all numbers is: %d", total / ARRAY_SIZE);

    return 0;
}
