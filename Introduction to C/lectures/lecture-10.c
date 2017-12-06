#include <stdio.h>
#include <stdlib.h>
#define MAX_INDEX 10

void calcNumbers(int numbers[], int howMany){
    int min = 2000000000, max = -2000000000, total = 0, avg, i;
    for (i = 0; i < howMany; i++){
        if (numbers[i] < min){
            min = numbers[i];
        } else if (numbers[i] > max){
            max = numbers[i];
        }
        total += numbers[i];
    }

    avg = total / howMany;

    printf("min = %d and max = %d and avg = %d", min, max, avg);
}

int main(){
2    /*
    int i;
    int userInput;
    int myList[MAX_INDEX]; //index max is 10
    /*set the list by list index
   myList[0] = 47;
   //index1
   myList[1] = 17;
   //and so on.. till the max index declared: 10


   for(i = 0; i <= MAX_INDEX; i++){
        printf("For index %d enter a number:", i);
        scanf("%d", &userInput);
        //assign inputed number to the list index.
        myList[i] = userInput;
   }

   printf("\nList all values for myList\n\n");

    //loop through the list index and print the values
   for (i = 0; i <= MAX_INDEX; i++){
        printf("The value at index %d is %d.\n", i , myList[i]);
   }*/

   char name[MAX_INDEX];
   name[0] = 'R';
   name[1] = 'i';
   name[2] = 'c';
   name[3] = 'k';
   name[4] = 0;
   printf("%s", name);

   //new
   char *names[] = {
        "Chris", "Bob", "Rick"
   };

    return 0;
}
