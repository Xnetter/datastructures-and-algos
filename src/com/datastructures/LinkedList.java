package com.datastructures;

public class LinkedList {

    private Node head;

    public LinkedList(){
        head = null;
    }

    /**
     * Adds an integer value to the end of the list.
     * @param value The value to be added to the end of
     *              the linked list.
     */
    public void appendValue(int value){
        if(head == null){
            head = new Node(value);
            return;
        }

        Node runner = head;
        while(runner.next != null){
            runner = runner.next;
        }
        runner.next = new Node(value);
    }

    /**
     * A public method to remove duplicates.
     * No hash-map -- O(1) space, O(n^2) time.
     */
    public void removeDuplicates(){
        Node anchor = head;

        while(anchor != null && anchor.next != null) {
            Node runner = anchor;

            while (runner.next != null) {
                // Remove next from list if it is a duplicate of the current anchor
                if (runner.next.data == anchor.data)
                    runner.next = runner.next.next;
                // Otherwise, continue searching
                else
                    runner = runner.next;
            }
            anchor = anchor.next;
        }
    }

    /**
     * Place a value at the front of the linked list.
     *
     * @param value - The value to prepend to the linked list.
     */
    public void prependValue(int value){
        Node newHead = new Node(value);
        newHead.next = head;
        head = newHead;
    }

    /**
     * Partition linked list around a particular value.
     * All values less than the partition will appear before,
     * or to the left of, all values greater than the partition.
     *
     * Runs in O(n^2) time, where n is the size of the list.
     *
     * @param partitionVal - The value to partition the list around.
     */
    public void partitionAroundVal(int partitionVal){
        LinkedList partitionedList = new LinkedList();
        Node runner = head;
        while(runner != null){
            int currentVal = runner.data;
            if(currentVal < partitionVal)
                partitionedList.prependValue(currentVal);
            else
                partitionedList.appendValue(currentVal);
            runner = runner.next;
        }

        this.head = partitionedList.head;
    }

    /**
     * Partition linked list around a particular value, in place.
     *
     * All values less than the partition will appear before,
     * or to the left of, all values greater than the partition.
     * Runs in O(n) time.
     *
     * @param partitionVal - The value to partition the list around.
     */
    public void partitionAroundValInPlace(int partitionVal){
        if(this.isEmpty())
            return;

        Node runner = head;
        while(runner.next != null) {
            int nextVal = runner.next.data;
            if (nextVal < partitionVal) {
                Node nodeToPrepend = runner.next;
                runner.next = runner.next.next;
                nodeToPrepend.next = head;
                head = nodeToPrepend;
            } else {
                runner = runner.next;
            }
        }
    }

    /**
     * Returns whether the list is empty.
     */
    public boolean isEmpty(){
        return head == null;
    }

    /***
     * Takes two linked lists representing numbers, and
     * returns the summation.
     *
     * @param num1 The first number being added.
     * @param num2 The second number being added.
     */
    public static LinkedList sumLinkedListNums(LinkedList num1, LinkedList num2){
        if(num1.isEmpty())
            return num2;
        if(num2.isEmpty())
            return num1;

        /**
         * Create the summation linked list.
         * Create nodes to track the current digit for each number.
         */
        LinkedList summation = new LinkedList();
        Node currDigit1 = num1.head;
        Node currDigit2 = num2.head;

        // Loop over each digit for each number, and add them.
        int carry = 0;
        while(currDigit1 != null || currDigit2 != null){

            // Null values are leading zeroes -- treat them as such.
            int data1 = currDigit1 == null ? 0 : currDigit1.data;
            int data2 = currDigit2 == null ? 0 : currDigit2.data;

            // Find the current digit, and append it to the list.
            int sum = data1 + data2 + carry;
            int newDigit = sum % 10;
            summation.appendValue(newDigit);

            // Calculate the value that should be carried over.
            carry = sum / 10;

            // Advance to next digits to add.
            if(currDigit1 != null)
                currDigit1 = currDigit1.next;
            if(currDigit2 != null)
                currDigit2 = currDigit2.next;
        }

        // We may have a final carry to add after looping over all digits.
        if(carry != 0)
            summation.appendValue(carry);

        return summation;
    }

    /**
     *
     * @return String representation of the linked list.
     */
    @Override
    public String toString(){
        if(head == null)
            return "[]";
        Node runner = head;
        StringBuilder output = new StringBuilder();

        output.append("[");
        while(runner.next != null){
            output.append(runner.data + "->");
            runner = runner.next;
        }
        output.append(runner.data + "]");

        return output.toString();
    }

    private class Node {
        public int data;
        public Node next = null;

        public Node(int value){
            this.data = value;
        }
    }

}
