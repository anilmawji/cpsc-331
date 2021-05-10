#include <stdio.h>
#include <stdlib.h>
#include "dynamic_array.h"
#include "sorting.h"

int main() {
    Array a = arrayInit(&a, 3);
    arrayInsert(&a, 2);
    arrayInsert(&a, 6);
    arrayInsert(&a, 1);
    arrayInsert(&a, 4);
    arrayInsert(&a, 5);
    arrayInsert(&a, 3);
    arrayPrint(&a);

    insertionSort(a.array, a.length);
    arrayPrint(&a);

    return 0;
}
