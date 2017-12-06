#include <stdio.h>
#include <stdlib.h>

int main()
{

    int miles, yards, feet, inches;

    printf("How many miles did you trudge up the snow-covered hill: ");
    scanf("%d", &miles);

    yards = miles * 1760;
    feet = yards * 3;
    inches = feet * 12;

    printf("You made the long journey of %d miles.\nThat is %d yards, %d feet, and %d inches.",
           miles, yards, feet, inches);



    return 0;
}
