package binary_search_tree;

import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element) {
		// compareTo returns neg for less than, 0 for equal to, pos for greater than
		if (_element == null){ // case 0: empty tree
			_element = element; // insert element
		} else if (element.compareTo(_element) < 0) { // element less than root node --> insert on left
			if (getLeft().isEmpty()) { // no child to left
				_left = new NonEmptyBST<T>(element); // insert element as left child
			} else { // child already to left; use recursion to call insert
				getLeft().insert(element); // finding node with no leftmost child
			}
		} else if (element.compareTo(_element) > 0) { // element greater than root node --> insert on right
			if (getRight().isEmpty()) { // no child on the right
				_right = new NonEmptyBST<T>(element); // insert element as right child
			} else{ // child already to right; use recursion to call insert
				getRight().insert(element); // finding node with no rightmost child
			}
		} return this; // returns new tree with insert
	}

	// helper method: getSmallest
	public BST<T> getSmallest(BST<T> node) {
		BST<T> small = node; // sets root node as small
		while (!small.getLeft().isEmpty()){ // iterates through tree until no more left children
			small = small.getLeft(); // sets small to new leftmost (smallest) child
		}
		return small; // returns smallest node
	}

	// helper method: getLargest
	public BST<T> getLargest(BST<T> node) {
		BST<T> large = node; // sets root node as large
		while (!large.getRight().isEmpty()){ // iterates through tree until no more right children
			large = large.getRight(); // sets large to rightmost (largest) child
		}
		return large; // returns largest node
	}


	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		// compareTo returns neg for less than, 0 for equal to, pos for greater than
		if (_element == null){ // case 0: empty tree
			return this; // return empty tree
		}
		else if (element.compareTo(_element) < 0) { // element less than root node --> search left side
			_left = getLeft().remove(element); // call remove on left side
		}
		else if (element.compareTo(_element) > 0) { // element greater than root node --> search right side
			_right = getRight().remove(element); // call remove on right side
		}
		else if (element.compareTo(_element) == 0) { // found element!
			if (getLeft().isEmpty() && getRight().isEmpty()) { // no children
				return new EmptyBST<T>(); // return empty BST for new child
			} else if (getRight().isEmpty()) { // left child exists
				BST<T> largeLeft = getLargest(getLeft()); // find the largest child on left
				_element = largeLeft.getElement(); // set element of current node to largest value on left
				_left = getLeft().remove(largeLeft.getElement()); // remove node with the largest value on left
			} else { // right child exists
				BST<T> smallRight = getSmallest(getRight()); // find the smallest child on right
				_element = smallRight.getElement(); // set element of current node to smallest value on right
				_right = getRight().remove(smallRight.getElement()); // remove node with the largest value on right
			}
		} return this; // return new tree with remove
	}

	// TODO: printPreOrderTraversal
	@Override
	// hint: stack
	public void printPreOrderTraversal() {
		// pre order traversal: root --> left --> right
		if (isEmpty()){ // case 0: empty tree
			System.out.println("");
		} else {
			System.out.print(getElement() + " "); // print root
			_left.printPreOrderTraversal(); // print left with recursion
			_right.printPreOrderTraversal(); // go back to print right with recursion
		}
	}

	// TODO: printPostOrderTraversal
	@Override
	// hint: stack
	public void printPostOrderTraversal() {
		// post order traversal: left --> right --> root
		if (isEmpty()){ // case 0: empty tree
			System.out.println("");
		} else {
			_left.printPostOrderTraversal(); // print left with recursion
			_right.printPostOrderTraversal(); // print right with recursion
			System.out.print(getElement() + " "); // print node
		}
	}

	// TODO: printBreadthFirstTraversal
	@Override
	// hint: queue
	public void printBreadthFirstTraversal() {
		// breadth first traversal: root --> left child --> right child --> left child's left child --> left child's right child --> ...
		Queue<BST<T>> queue = new LinkedList<>(); // makes queue
		queue.add(this); // adds root to queue

		while(queue.size() > 0){ // iterates through queue
			BST<T> node = queue.poll(); // pops off head of queue
			System.out.print(node.getElement() + " "); // prints head of queue

			if (!node.getLeft().isEmpty()) { // if left child exists, add left child of node to queue
				queue.add(node.getLeft());
			}
			if (!node.getRight().isEmpty()){ // if right child exists, add right child of node to queue
				queue.add(node.getRight());
			} // continues to run until nothing left in queue
		}
	}

	// provided methods
	@Override
	public int getHeight() {
		return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
