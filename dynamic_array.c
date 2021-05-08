#include <stdio.h>
#include <stdlib.h>
#include "dynamic_array.h"

void arrayInit(Array* a, size_t capacity) {
    if (capacity < 0) printf("Error: Illegal array size: %d", capacity);

    a->array = malloc(capacity * sizeof(int));
    a->length = 0;
    a->capacity = capacity;
}

int arrayIsEmpty(Array* a) {
    return a->length == 0;
}

void arrayInsert(Array* a, int value) {
    if (a->length == a->capacity) {
        a->capacity *= 2;
        a->array = realloc(a->array, a->capacity * sizeof(int));
    }
    a->array[a->length++] = value;
}

int arrayRemoveAt(Array* a, size_t rm_index) {
    if (rm_index >= a->length || rm_index < 0) {
        printf("Error: Illegal array index: %d", rm_index);
    }

    int rm_value = a->array[rm_index];
    int* newArray = malloc((a->length - 1) * sizeof(int));

    for (int i = 0, j = 0; i < a->length; i++, j++) {
        if (i == rm_index) j--;
        else newArray[j] = a->array[i];
    }
    free(a->array);
    a->array = newArray;
    a->capacity = --a->length;

    return rm_value;
}

int arrayRemove(Array* a, int value) {
    for (int i = 0; i < a->length; i++) {
        if (a->array[i] == value) {
            arrayRemoveAt(a, i);
            return 0;
        }
    }
    return 1;
}

int arrayIndexOf(Array* a, int value) {
    for (int i = 0; i < a->length; i++) {
        if (a->array[i] == value) {
            return i;
        }
    }
    return -1;
}

int arrayContains(Array* a, int value) {
    return arrayIndexOf(a, value) != -1;
}

void arrayClear(Array* a) {
    for (int i = 0; i < a->length; i++) {
        a->array[i] = 0;
    }
}

char* arrayToString(Array* a) {
    if (a->length == 0) return "[]";
  
    char* str = (char*) malloc(128);
    char* ptr = str+1;  

    *str = '[';
    for (int i = 0; i < a->length - 1; i++) {
       ptr += sprintf(ptr, "%d, ", a->array[i]);
    }
    ptr += sprintf(ptr, "%d]", a->array[a->length - 1]);

    return str;
}

void arrayFree(Array* a) {
    free(a->array);
    a->array = NULL;
    a->length = a->capacity = 0;
}
