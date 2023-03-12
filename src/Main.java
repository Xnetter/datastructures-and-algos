import com.datastructures.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList num1 = new LinkedList();
        num1.appendValue(9);
        num1.appendValue(9);
        num1.appendValue(9);

        LinkedList num2 = new LinkedList();
        num2.appendValue(9);
        num2.appendValue(9);
        num2.appendValue(9);


        System.out.println(LinkedList.sumLinkedListNums(num1, num2));
    }
}