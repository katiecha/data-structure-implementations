package binary_search_tree;

import java.util.Queue;

public interface BST<T extends Comparable<T>> {
 

  // Inserts element into the tree in the appropriate position.
  // Either returns the mutated tree after insertion or a new tree with the inserted element if necessary.
  BST<T> insert(T element);

  // Removes the element from the tree if it is present.
  // Either returns the possibly mutated tree after removal or an empty tree.
  BST<T> remove(T element);

  // Prints the tree in depth first pre order traversal.
  // Print the elements all in one line with a space after each element.
  void printPreOrderTraversal();

  // Prints the tree in depth first post order traversal.
  // Print the elements all in one line with a space after each element.

  void printPostOrderTraversal();

  // Prints the breadth first traversal of the tree.
  // Print the elements all in one line with a space after each element.
  void printBreadthFirstTraversal();

  // Returns the longest path from the root value to a leaf in the tree
  int getHeight();

  BST<T> getLeft();

  BST<T> getRight();

  T getElement();
  
  boolean isEmpty();
}
