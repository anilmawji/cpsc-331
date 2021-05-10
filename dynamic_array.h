typedef struct Array {
    int* array;
    size_t length;
    size_t capacity;
} Array;

Array arrayInit(Array*, size_t);

int arrayIsEmpty(Array*);

void arrayInsert(Array*, int);

void arrayInsertAll(Array*, int, int*);

int arrayRemoveAt(Array*, size_t);

int arrayRemove(Array*, int);

int arrayIndexOf(Array*, int);

int arrayContains(Array*, int);

void arrayClear(Array*);

char* arrayToString(Array*);

void arrayPrint(Array*);

void arrayFree(Array*);
