#include <stdio.h>
#include <stdlib.h>
#include "dynamic_array.h"
#include "sorting_algs.h"

int main() {
    Array a = arrayInit(3);
    arrayInsertAll(&a, 6, (int[]){2, 6, 1, 4, 5, 3});

    arrayPrint(&a);
    insertionSort(a.array, a.length);
    arrayPrint(&a);

    return 0;
}
