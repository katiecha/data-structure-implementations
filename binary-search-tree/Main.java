package binary_search_tree;

public class Main {
  public static void main(String[] args) {
    BST<Integer> bstTest = new NonEmptyBST<Integer>(78);
    bstTest = bstTest.insert(31);
    bstTest = bstTest.insert(84);
    bstTest = bstTest.insert(13);
    bstTest = bstTest.insert(38);
    bstTest = bstTest.insert(10);
    bstTest = bstTest.insert(40);
    bstTest = bstTest.insert(12);
    bstTest = bstTest.insert(39);
    bstTest = bstTest.insert(47);

    // tests for traversals
    bstTest.printPreOrderTraversal();
    System.out.println("");
    bstTest.printPostOrderTraversal();
    System.out.println("");
    bstTest.printBreadthFirstTraversal();
    System.out.println("");

    // test for removal
    bstTest = bstTest.remove(31);
    bstTest.printBreadthFirstTraversal();
  }
}
