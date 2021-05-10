#include <stdlib.h>

void insertionSort(int* arr, size_t length) {
    for (int j = 1; j < length; j++) {
        int key = arr[j];
        int i = j - 1;

        while (i >= 0 && arr[i] > key) {
            arr[i+1] = arr[i];
            i -= 1;
        }
        arr[i+1] = key;
    }
}

void selectionSort(int* arr) {

}

void mergeSort(int* arr) {

}

void swap(int* a, int* b) {
   int buf = *a;
   *a = *b;
   *b = buf;
}

void bubbleSort(int* arr, size_t length) {
    for (int i = 0; i < length-2; i++) {
        int swapped = 0;

        for (int j = length-1; j > i; j--) {
            if (arr[j] < arr[j-1]) {
                swap(&arr[j], &arr[j-1]);
                swapped = 1;
            }
        }
        if (!swapped) return;
    }
}
