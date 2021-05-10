#include <stdio.h>
#include <stdlib.h>
#include "dynamic_array.h"
#include "sorting.h"

int main() {
    Array a = arrayInit(3);
    arrayInsertAll(&a, 3, (int[]){2, 6, 1});

    arrayPrint(&a);
    insertionSort(a.array, a.length);
    arrayPrint(&a);

    return 0;
}
