
/**
 * CPSC 331 Assignment 3
 * 
 * Name: Anil Mawji
 * UCID: 30099809
 */

public class HashTable implements Dictionary {

    private static final int CAPACITY = 17;
    private static final String DELETED = "deleted";

    private String[] table;
    private int currentSize;

    public HashTable() {
        this.table = new String[CAPACITY];
    }

    public boolean empty() {
        return currentSize == 0;
    }

    public boolean full() {
        return currentSize == CAPACITY;
    }

    public void insert(String key) {
        if (full()) throw new RuntimeException("Failed to insert key: Hashtable overflow!");

        String insertedKey = "\"" + key + "\"";

        for (int i = 0; i < CAPACITY; i++) {
            int j = hash(key, i);
            if (table[j] == null || table[j].equals(DELETED)) {
                table[j] = insertedKey;
                currentSize++;
                return;
            }
        }
    }

    public void delete(String key) {
        if (empty()) throw new RuntimeException("Failed to delete key: Hashtable underflow!");

        int i = search(key);
        if (i != -1) {
            table[i] = DELETED;
            currentSize--;
        }
    }

    public boolean member(String key) {
        return search(key) != -1;
    }
    
    public int search(String key) {
        String insertedKey = '"' + key + '"';

        for (int i = 0; i < CAPACITY; i++) {
            int j = hash(key, i);
            if (table[j] != null && table[j].equals(insertedKey)) {
                return j;
            }
        }
        return -1;
    }

    //Hash function that uses linear probing
    private int hash(String key, int i) {
        return (hash(key) + i) % CAPACITY;
    }

    //Generate an index for a given key using the division method
    private int hash(String key) {
        return Math.abs(key.hashCode()) % CAPACITY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < CAPACITY; i++) {
            if (table[i] != null) {
                builder.append(i).append(":").append(table[i]).append("\n");
            }
        }
        return builder.toString();
    }
}
