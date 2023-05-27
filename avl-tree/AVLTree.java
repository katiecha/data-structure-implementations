package avl_tree;

import binary_search_tree.BST;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateLeft() {
         AVLTree<T> root = _right; // right child becomes "root"
         AVLTree<T> center = _right._left; // right child's left child becomes "center"
         root._left = this; // right child's left child becomes this
         this._right = center; // this's right child becomes "center"
         fixHeight(this); // fixing height
         fixHeight(root);
         root._size = _size; // fixing size
         _size = _left.size() + _right.size() + 1;
         return root;
     }
    
    /**
     *
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
         // same logic as above but reversed
         AVLTree<T> root = _left;
         AVLTree<T> center = _left._right;
         root._right = this;
         this._left = center;
         fixHeight(this);
         fixHeight(root);
         root._size = _size;
         _size = _left.size() + _right.size() + 1;
         return root;
     }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    // helper method: updates the height of a tree
    public void fixHeight(AVLTree<T> tree){
         int newHeight = Math.max(tree.getLeft().height(), tree.getRight().height()); // returns the max value between left child and right child's height
         tree._height = newHeight + 1;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
        if (_value == null){ // empty AVL tree
            AVLTree<T> newTree = new AVLTree<>(); // creates new tree
            newTree._value = element; // sets value to element
            newTree._right = new AVLTree<T>(); // sets right child to empty tree
            newTree._left = new AVLTree<T>(); // sets left child to empty tree
            newTree._size = 1; // sets size to 1
            newTree._height = 0; // sets height to 0
            return newTree;
        } else if (element.compareTo(this.getValue()) < 0) { // element is less than value of root
            _left = (AVLTree<T>) _left.insert(element); // tries to insert element on the left
            _size++; // increases size
        } else if (element.compareTo(this.getValue()) > 0) { // element is greater than value of root
            _right = (AVLTree<T>) _right.insert(element); // tries to insert element on the right
            _size++; // increases size
        } else { // element is already in tree
            return this;
        }
        return balance(); // returns the tree but balanced
    }

    public AVLTree<T> balance() {
         fixHeight(this);
         if (_left.height() - _right.height() > 1) { // L imbalance
             if (_left._right.height() > _left._left.height()) { // LR imbalance
                 this._left = this._left.rotateLeft(); // rotates left
             }
             return this.rotateRight(); // rotates right
         } else if (_left.height() - _right.height() < -1) { // R imbalance
             if (_right._right.height() < _right._left.height()) { // RL imbalance
                 this._right = this._right.rotateRight(); // rotates right
             }
             return this.rotateLeft(); // rotates left
         } else {
             return this; // returns tree
         }
    }

    /**
     * Removes element from tree and returns resulting
     * tree after removal. Depending on implementation,
     * this may or may not be the same object that you
     * started with. If element is not in the tree, the
     * tree should remain unchanged and return itself.
     **/

    @Override
    public SelfBalancingBST<T> remove(T element) {
        if (_value == null) { // empty AVL tree
            return this; // returns tree
        }
        if (!contains(element)) {
            return this;
        } else if (element.compareTo(this.getValue()) < 0) { // element is less than value of root
            _left = (AVLTree<T>) _left.remove(element); // tries to remove element on left
            _size--; // decreases size
            return balance();
        } else if (element.compareTo(this.getValue()) > 0) { // element is greater than value of root
            _right = (AVLTree<T>) _right.remove(element); // tries to remove element on right
            _size--; // decreases size
            return balance(); // balances tree
        } else { // found element in tree
            if (_left.getValue() == null) { // right child present
                _size--; // decreases size
                return _right; // returns right child as new tree after removal
            } else if (_right.getValue() == null) { // left child present
                _size--; // decreases size
                return _left; // returns left child as new tree after removal
            } else { // both children
                _size--; // decreases size
                _value = _right.findMin(); // min value on right (aka smallest value on right)
                _right = (AVLTree<T>) _right.remove(_value); // tries to remove min value on right and set as new right child
                return balance(); // balances tree
            }
        }
    }

    @Override
    public T findMin() {
        AVLTree<T> current = this;
        while (!current.getLeft().isEmpty()) {
            current = (AVLTree<T>) current.getLeft(); // moves left until cannot go further
        } return current.getValue();
    }

    @Override
    public T findMax() {
        AVLTree<T> current = this;
        while (!current.getRight().isEmpty()) {
            current = (AVLTree<T>) current.getRight(); // moves right until cannot go further
        } return current.getValue();
    }

    @Override
    public boolean contains(T element) {
        AVLTree<T> current = this; // root node
        while (!current.isEmpty()) { // for entire tree
            if (element.compareTo(current.getValue()) < 0) { // element is less than current
                current = (AVLTree<T>) current.getLeft(); // keep going left
                return current.contains(element);
            } else if (element.compareTo(current.getValue()) > 0) { // element is greater than current
                current = (AVLTree<T>) current.getRight(); // keep going right
                return current.contains(element);
            } else if (element.compareTo(current.getValue()) == 0) { // element is current
                return true; // found
            }
        } return false; // nothing in tree so false
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }

         return _right;
    }

}