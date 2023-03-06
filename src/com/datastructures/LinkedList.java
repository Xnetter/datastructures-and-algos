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