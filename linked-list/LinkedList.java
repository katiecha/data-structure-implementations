package linked_list;

import java.util.ArrayList;

public class LinkedList<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    // Return true if this linked list is equal to the list argument, false otherwise.
    // Two lists are equal if they have the same size, and the same elements in the same order.
    public boolean isEqual(LinkedList list2) {
        boolean result = false;
        if (size() == list2.size()){
            if (size() == 0) {
                return true;
            } for(int i = 0; i < size(); i++) {
                if(get(i) == list2.get(i)) {
                        result = true;
                } else {
                    return false;
                }
            }
        }
        return result;
    }

    // Return true if the list is symmetrical, false otherwise
    public boolean isSymmetrical() {
        boolean result = false;
        if (size() == 0) {
            return true;
        }
        for (int i = 1; i <= (size() / 2); i++) {
            if (get(i - 1) == get(size - i)){
                result = true;
            }
            else {
                return false;
            }
        }
        return result;
    }

    // Stretch the list so that each element in the list is represented factor times
    // If the factor is 0 the list should be cleared (have 0 nodes)
    public void multiply(int factor) {
        if (factor == 0){
            clear();
        }
        else if (factor > 1) {
            int origSize = size();
            for (int i = origSize - 1; i >= 0; i--){
                for (int j = 0; j < factor - 1; j++){
                    add(i, get(i));
                }
            }
        }
    }

    // Return true if the list contains a cycle, false otherwise
    public boolean containsCycle() {
        ArrayList<Node<T>> recorder = new ArrayList<>();
        Node<T> current = getHead();
        while (current != null) {
            if (recorder.contains(current)) {
                return true;
            }
            else {
                recorder.add(current);
            }
            current = current.getNext();
        }
        return false;
    }

    // Merge the given linked list into the current list. The 2 lists will always be either the same size, or the current list will be longer than list2.
    public void merge(LinkedList list2) {
        int origSize = list2.size();
        for (int i = origSize - 1; i >= 0; i--){
            add(i + 1, list2.get(i));
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(Object element) {
        Node<T> current = head;
        while(current != null) {
            if(current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public T[] toArray() {
        T[] arr =  (T[]) new Object[size()];
        Node<T> current = head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(Object element) {
        Node<T> newNode = new NodeImpl<T>((T) element, null);
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    public boolean remove(Object element) {
        Node<T> current = head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            head = head.getNext();
            size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            tail = current;
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    public T get(int index) {
        validIndex(index);
        Node<T> current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public T set(int index, Object element) {
        validIndex(index);
        Node<T> current = head;
        T prevValue = null;
        int i = 0;
        if(index == 0) {
            prevValue = head.getValue();
            head.setValue((T) element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue((T) element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, Object element) {
        if(index > size) {
            validIndex(index);
        }
        Node<T> current = head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node<T> newNode = new NodeImpl<T>((T) element, head);
                head = newNode;
                size++;
                return;
            }

        }  else if(index == size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node<T> temp = current.getNext();
                Node<T> newNode = new NodeImpl<T>((T) element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(Object element) {
        Node<T> current = head;
        int index = 0;
        while(current != null) {
            if(current.getValue().equals((T) element)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(Object element) {
        Node<T> current = head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue().equals ((T) element)) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        String list = "";
        Node<T> current = head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }
}