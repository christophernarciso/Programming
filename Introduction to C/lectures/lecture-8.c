#include <stdio.h>
#include <stdlib.h>

/*Function syntax

1)Return type
int, double, float, and void

2)Identifier (parameters *if needed*)

*/

void theWeather(){
    //do stuff here
    printf("The weather is nice.\n");
    printf("Glad it stopped raining.\n");
}

void talkAboutUCF(){
    printf("UCF is heaven on earth.\n");
    printf("UCF has 66,000 students.\n");
}

int askForANumber(){
    int number;
    printf("Please enter a number:");
    scanf("%d", &number);
    return number;
}

int selectOperation(){
    int choice;
    do {
        printf("Select from the following operations:\n");
        printf("1.add\n2.subtract\n3.multiply\n4.divide\nYour choice?");
        scanf("%d", &choice);
    } while (choice < 1 || choice > 4);
    return choice;
}

int askForFirstNumber(){
    int num;
    printf("Please enter the first number:");
    scanf("%d", &num);
    return num;
}

int askForSecondNumber(){
    int num;
    printf("Please enter the second number:");
    scanf("%d", &num);
    return num;
}

int getTheAnswer(int operation, int first, int second){
    int result;
    switch(operation){
        case 1:
            result = first + second;
            break;
        case 2:
            result = first - second;
            break;
        case 3:
            result = first * second;
            break;
        case 4:
            if (first == 0 || second == 0){
                printf("Can not divide my zero. ERROR.");
                return 1;
            }
            result = first / second;
            break;
        default:
            break;
    }
    return result;
}

int main()
{
    int op, first, second, result, again;
    do {
        op = selectOperation();
        first = askForFirstNumber();
        second = askForSecondNumber();

        printf("Result is: %d\n", getTheAnswer(op, first, second));
        printf("Enter 1 to calculate again, otherwise hit another value to quit.\n");
        scanf("%d", &again);
    } while (again == 1);

    /*switch(op){
        case 1:
            result = first + second;
            break;
        case 2:
            result = first - second;
            break;
        case 3:
            result = first * second;
            break;
        case 4:
            result = first / second;
            break;
        default:
            break;
    }

    printf("Result is: %d", result);

    /*function call
    theWeather();
    printf("We are back from theWeather() function.\n");
    talkAboutUCF();
    printf("We are back from talkAboutUCF() function.\n");
    int num = askForANumber();
    printf("You entered: %d", num);
    */
    //selectOperation();

    return 0;
}
