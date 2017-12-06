#include <stdio.h>
#include <stdlib.h>

int main()
{
    int personType, permitType;
    float total;
    char name[80];
    printf("What type of person are you:\n");

    printf("1.Student\n2.Staff\n3.Faculty\n");

    do {
        printf("Your choice:");
        scanf("%d", &personType);
    }while (personType < 1 || personType > 3);

    /*switch(personType){
        case 1:

            break;
        case 2:
            break;
        case 3:
            break;

    }testing*/



    printf("What type of permit would you like:\n");
    printf("1.Sticker\n2.Hangtag\n");

    do {
        printf("Your choice:");
        scanf("%d", &permitType);
    }while (permitType < 1 || permitType > 2);

    /*if (personType == 1 && permitType == 1){
        total = 100.0;
    } else if (personType == 1 && permitType == 2){
        total = 150.0;
    } else if (personType == 2  && permitType == 1){

    } */




    //convert from decimal to binary
    //first figure out a way to count 128, 64, 32 , etc.
    /*int i;
    int bitValue = 128;
    int decimalNumber;

    printf("What decimal number (from 0 - 255) would you like to convert:");
    scanf("%d", &decimalNumber);

    while (bitValue > 0){
        if (decimalNumber >= bitValue){
            printf("1 ");
            decimalNumber -= bitValue;
        } else {
            printf("0 ");
        }

        bitValue /= 2;

    }


    /*for (i = 1; i <= 8; i++){
        //printf("The bit value is %d\n", bitValue);

        if (decimalNumber >= bitValue){
            printf("1 ");
            decimalNumber -= bitValue;
        } else {
            printf("0 ");
        }

        bitValue /= 2;
    }


    */
    printf("\n");


    return 0;
}
