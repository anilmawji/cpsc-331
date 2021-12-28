//Name: Anil MAwji
//UCID: 30099809

public class MinHeap implements PriorityQueue {

    private final int[] HEAP = new int[31];
    private int size = 0;

    public MinHeap() {}

    public boolean empty() {
        return size == 0;
    }
    
    public boolean full() {
        return size == HEAP.length;
    }

    /**
     * Adds a new key to the heap then increments the size of the heap by 1
     * 
     * @param key value to be added to the heap
     * @throws RuntimeException if the heap overflows
     */
    public void insert(int key) {
        if (full()) throw new RuntimeException("Failed to insert key: The heap is full!");
        
        increaseKey(size++, key);
    }

    /**
     * Gets and removes the key at the top of the heap
     * 
     * @throws RuntimeException if the heap underflows
     * @return smallest key in the heap
     */
    public int extractMin() {
        if (empty()) throw new RuntimeException("Failed to extract min key: The heap is empty!");

        int min = HEAP[0];
        HEAP[0] = HEAP[size-1];
        minHeapify(0);
        size--;
        return min;
    }

    /**
     * @return smallest key in the heap
     */
    public int min() {
        if (empty()) throw new RuntimeException("Failed to get min key: The heap is empty!");

        return HEAP[0];
    }

    /**
     * Changes the value of a key and reorders the heap
     * 
     * @param i index of the key to change
     * @param value new value of the key
     */
    public void increaseKey(int i, int value) {
        HEAP[i] = value;
        while (i > 0 && HEAP[i] < HEAP[parent(i)]) {
            swapKeys(i, parent(i));
            i = parent(i);
        }
    }

    /**
     * Reorders the heap starting at the given index so that elements are in proper order
     * The resulting heap will have keys that are smaller as you move up the tree
     * 
     * @param i index of the key at which the reordering should begin
     */
    public void minHeapify(int i) {
        //If the key has no children then it is already ordered
        if (isLeaf(i)) return;
        //Heapify the subheap, of which the left child is the root
        minHeapifyChild(i, left(i), right(i));
        //Heapify the subheap of which the right child is the root
        minHeapifyChild(i, right(i), left(i));
    }

    /**
     * Heapifies one of the subheaps of a given parent node
     * 
     * @param i index of the parent node
     * @param child_1 index of the child node to heapify
     * @param child_2 index of the sibling node
     */
    private void minHeapifyChild(int i, int child_1, int child_2) {
        //If the 1st child exists and is smaller than the parent
        if (child_1 < size && HEAP[child_1] < HEAP[i]) {
            //Get the smallest child: If the parent has a 2nd child then check if it is smaller than the 1st
            int smallest = child_2 < size ? (HEAP[child_2] < HEAP[child_1] ? child_2 : child_1) : child_1;
            //Swap the parent with its smaller child
            swapKeys(i, smallest);
            //Heapify the subheap starting at the child that was swapped with the parent
            minHeapify(smallest);
        }
    }

    /**
     * Swaps the values of two keys in the heap. Does NOT preserve proper heap ordering
     * 
     * @param i index of the 1st key
     * @param j index of the 2nd key
     */
    private void swapKeys(int i, int j) {
        int temp = HEAP[i];
        HEAP[i] = HEAP[j];
        HEAP[j] = temp;
    }

    public int parent(int i) {
        return (i-1)/2;
    }

    public int left(int i) {
        return 2*i + 1;
    }

    public int right(int i) {
        return 2*i + 2;
    }

    public boolean isLeaf(int i) {
        return !hasLeft(i) && !hasRight(i);
    }

    public boolean hasLeft(int i) {
        return left(i) < size;
    }

    public boolean hasRight(int i) {
        return left(i) < size;
    }

    /**
     * Creates a string ideal for displaying the heap in the console
     * First line is the size of the heap
     * 
     * @return string representing the heap
     */
    @Override
    public String toString() {
        if (empty()) return null;

        StringBuilder builder = new StringBuilder().append("size = ").append(Integer.toString(size));
        int lineSize = 1;
        
        for (int i = 0; i < size; i++) {
            //If next element should be on a new line
            if (i+1 == lineSize) {
                builder.append("\n");
                //Calculate number of keys in the new line
                lineSize *= 2;
            }
            //Add current key to the line
            builder.append(HEAP[i]).append(" ");
        }
        return builder.toString();
    }
}
