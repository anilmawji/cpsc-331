#include <stdio.h>
#include <stdlib.h>
#include "dynamic_array.h"

int main() {
    Array a = arrayCreate(3);
    arrayInsertAll(&a, 6, (int[]){2, 6, 1, 4, 5, 3});
    arrayPrint(&a);

    return 0;
}
