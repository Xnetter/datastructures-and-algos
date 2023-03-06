import com.datastructures.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedListTester = new LinkedList();
        linkedListTester.prependValue(1);

        System.out.println(linkedListTester);
        linkedListTester.partitionAroundValInPlace(10);
        System.out.println(linkedListTester);
    }
}