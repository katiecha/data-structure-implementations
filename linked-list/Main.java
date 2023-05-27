package linked_list;

public class Main {

    public static void main(String[] args) {
        // Test  for isEqual()
        LinkedList list1 = new LinkedList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        LinkedList list2 = new LinkedList();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        LinkedList list3 = new LinkedList<Integer>();
        list3.add(1);
        list3.add(5);
        LinkedList list4 = new LinkedList();
        list4.add(2);
        list4.add(5);
        System.out.println(list1.isEqual(list2));
        System.out.println(list3.isEqual(list4));

        // Test for isSymmetrical()
        LinkedList list5 = new LinkedList<Integer>();
        list5.add(1);
        list5.add(2);
        list5.add(3);
        list5.add(2);
        list5.add(1);
        LinkedList list6 = new LinkedList<String>();
        list6.add("a");
        list6.add("b");
        list6.add("c");
        list6.add("b");
        list6.add("f");
        System.out.println(list5.isSymmetrical());
        System.out.println(list6.isSymmetrical());

        // Test for multiply()
        LinkedList list7 = new LinkedList<Integer>();
        list7.add(1);
        list7.add(2);
        list7.add(3);
        list7.multiply(3);
        System.out.println(list7.toString());

        // Test for merge()
        LinkedList list8 = new LinkedList<Integer>();
        list8.add(1);
        list8.add(2);
        list8.add(3);
        list8.add(4);
        LinkedList list9 = new LinkedList<Integer>();
        list9.add(5);
        list9.add(6);
        list8.merge(list9);
        System.out.println(list8.toString());
    }
}
