package binary_heap;


public interface BinaryHeap<V,P extends Comparable<P>> {
    // Returns number of elements in heap
    int size();

    // Create new hospital.Prioritized object and insert it into the heap
    void enqueue(V value, P priority);

    int bubbleUp(int index);

    // Remove the element with the smallest priority from the heap and return its value
    V dequeue();

    void bubbleDown(int index);

    // Return the smallest value in the heap without removing it
    V getMin();


    // Retrieves contents of heap as an array.
    Prioritized<V,P>[] getAsArray();
}
