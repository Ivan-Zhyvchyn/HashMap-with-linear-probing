package com.softindex.test;

public class MyHashMap {
    private final static int SIZE = 128;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int count = 0;
    HashEntry[] hashtable;

    public MyHashMap() {
        hashtable = new HashEntry[SIZE];
    }

    MyHashMap(int size) {
        hashtable = new HashEntry[size];
    }

    class HashEntry {
        private int key;
        private long value;

        HashEntry(int key, long value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return (key + ": " + value);
        }

    }

    public void put(int key, long value) {
        int probe = 0;
        int hash = 0;
        if (getLoadFactor()) {
            do {
                hash = (Math.abs(Long.hashCode(key)) + probe) % SIZE;

                if (hashtable[hash] == null) {
                    hashtable[hash] = new HashEntry(key, value);
                    count++;
                    return;
                } else if (hashtable[hash].getKey() == key) {
                    hashtable[hash].setValue(value);
                    count++;
                    return;
                }
                probe++;
            } while (probe < SIZE);
        } else {
            doubleSize();
        }
    }

    public long get(int key) {
        int probe = 0;


        do {
            int hash = (Math.abs(Long.hashCode(key)) + probe) % SIZE;

            if (hashtable[hash] == null) {
                // hastable doesn't have such key
                throw new IndexOutOfBoundsException("There is no such entry");
            } else if (hashtable[hash].getKey() == key) {
                return hashtable[hash].getValue();
            }
            probe++;
        } while (probe < SIZE);
        // hashtable doesn't have such key
        throw new IndexOutOfBoundsException("There is no such entry");
    }

    public int size() {

        return count;
    }

    private boolean getLoadFactor() {
        return (double) count / (double) SIZE < DEFAULT_LOAD_FACTOR;
    }

    public void doubleSize() {
        HashEntry[] oldTable = hashtable;
        oldTable = new HashEntry[hashtable.length];
        count = 0;
        for (int i = 0; i < oldTable.length; ++i) {
            if (oldTable[i] != null) {
                put(oldTable[i].getKey(), oldTable[i].getValue());
            }
        }
    }

    @Override
    public String toString() {
        String res = "";

        for (int i = 0; i < SIZE; i++) {
            if (hashtable[i] != null) {
                res += hashtable[i].toString() + "\n";
            }
        }
        return res;
    }
}