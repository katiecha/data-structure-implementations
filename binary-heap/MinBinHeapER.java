package binary_heap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MinBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;

    // Constructor that creates an empty heap of hospital. Prioritized objects.
    public MinBinHeapER() {
        _heap = new ArrayList<>();
    }

    // Constructor that builds a heap given an initial array of hospital. Prioritized objects.
    public MinBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>();
        for (int j = 0; j < initialEntries.length; j++) {
            _heap.add(initialEntries[j]);
        }
        for (int i = ((size()/2) - 1); i >= 0; i--) {
            bubbleDown(i);
        }
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient newPatient = new Patient<>(value, priority);
        _heap.add(newPatient);
        bubbleUp(size()-1);
    }

    // compareTo returns neg for less than, 0 for equal to, pos for greater than
    public int bubbleUp(int index) {
        if (index == 0){
            return(index);
        } else {
            Prioritized<V,P> child = _heap.get(index);
            Prioritized<V,P> parent = _heap.get(parentIndex(index));
            if (child.getPriority().compareTo(parent.getPriority()) <= 0){
                _heap.set(parentIndex(index), child);
                _heap.set(index, parent);
                return(bubbleUp(parentIndex(index)));
            } else {
                return(index);
            }
        }
    }

    static int parentIndex(int index) {
        return ((index-1)/2);
    }

    // enqueue
    public void enqueue(V value) {
        Patient newPatient = new Patient<>(value);
        _heap.add(newPatient);
        bubbleUp(size()-1);
    }

    // dequeue
    @Override
    public V dequeue() {
        if (size() == 0) { // empty
            return null;
        } else if (size() == 1) { // one element
            return(_heap.remove(0).getValue());
        } else {
            Prioritized<V,P> priorityPatient = _heap.get(0); // root
            _heap.set(0, _heap.remove(size() - 1)); // set last item in array list to root
            bubbleDown(0); // bubbling the new root down
            return(priorityPatient.getValue()); // return root
        }
    }

    public void bubbleDown(int index){
        Prioritized<V,P> parent = _heap.get(index); // set to parent or root
        if (!hasLeftChild(index) && !hasRightChild(index)) { // leaf -- no children
            return;
        } else if (!hasRightChild(index)) { // left child only
            Prioritized<V, P> leftChild = _heap.get(leftChildInd(index));
            if (((leftChild.getPriority()).compareTo(parent.getPriority())) <= 0) { // left child priority is less than parent
                _heap.set(index, leftChild); // swap parent and left child
                _heap.set(leftChildInd(index), parent);
                bubbleDown(leftChildInd(index)); // continue down
            } else {
                return;
            }
        } else { // 2 children
            Prioritized<V,P> leftChild = _heap.get(leftChildInd(index));
            Prioritized<V,P> rightChild = _heap.get(rightChildInd(index));
            if ((((leftChild.getPriority()).compareTo(parent.getPriority())) <= 0) || (((rightChild.getPriority()).compareTo(parent.getPriority())) <= 0)) { // either has higher priority
                if (((leftChild.getPriority()).compareTo(rightChild.getPriority())) <= 0) { // left child has higher priority
                    _heap.set(index, leftChild); // swap parent and left child
                    _heap.set(leftChildInd(index), parent);
                    bubbleDown(leftChildInd(index)); // continue down
                } else {// right child has higher priority
                    _heap.set(index, rightChild); // swap parent and right child
                    _heap.set(rightChildInd(index), parent);
                    bubbleDown(rightChildInd(index)); // continue down
                }
            } else {
                return;
            }
        }
    }

    boolean hasLeftChild(int index) {
        return(validIndex(leftChildInd(index)));
    }

    boolean hasRightChild(int index) {
        return(validIndex(rightChildInd(index)));
    }

    static int leftChildInd(int index) {
        return (2*index+1);
    }

    static int rightChildInd(int index) {
        return (2*index+2);
    }

    boolean validIndex(int index) {
        if ((index >=0) && (index <= size()-1)) {
            return true;
        } else {
            return false;
        }
    }

    // getMin
    @Override
    public V getMin() {
       if (size() == 0) {
           return null;
       } else {
           return(_heap.get(0).getValue());
       }
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }






}
