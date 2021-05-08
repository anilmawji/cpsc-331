typedef struct Array {
    int* array;
    size_t length;
    size_t capacity;
} Array;

void arrayInit(Array*, size_t);

int arrayIsEmpty(Array*);

void arrayInsert(Array*, int);

int arrayRemoveAt(Array*, size_t);

int arrayRemove(Array*, int);

int arrayIndexOf(Array*, int);

int arrayContains(Array*, int);

void arrayClear(Array*);

char* arrayToString(Array*);

void arrayFree(Array*);
