class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) { val = x; }
}

public class LinkedListOperations {
	
	public int getSize(ListNode head){
		int size = 0;
		while(head!=null){
			size += 1;
			head = head.next;
		}
		return size;
	}

	public boolean isNull (ListNode head){
		if (getSize(head) == 0) return true;
		return false;
	}

	public void display (ListNode head){
		String str = "";
		if (head == null) str = "Empty LinkedList, nothing to display.";
		while (!isNull(head)){
			str += head.val;
			head = head.next;
			if (head != null) str += ", ";
		}
		System.out.println(str);
	}

	public void insert (ListNode head, int n, int val){
		ListNode newNode = new ListNode(val);
		ListNode curr = head;
		if (n > getSize(head)-1) {
			System.out.println("Exceeding index of LinkedList, inserted to the end.");
			while (curr.next != null){
				curr = curr.next;
			}
			curr.next = newNode;
		} else {
			for (int i=0; i < n-1; i++){
				curr = curr.next;
			}
			if (n==0) {
				newNode.next = curr;
				head = newNode;
			} else {
				newNode.next = curr.next;
				curr.next = newNode;
			}
		}
	}

	public void remove (ListNode head, int n){
		ListNode curr = head;
		if (n > getSize(head)-1) {
			System.out.println("Index does not exist.");
			return;
		} else {
			for (int i=0; i < n-1; i++){
				curr = curr.next;
			}
			if (n==0) {
				head = curr.next;
			} else {
				curr.next = curr.next.next;
			}
		}
	}

	public void removeVal (ListNode head, int val){
		ListNode curr = head;
		if (head == null) {
			System.out.println("Error, leading null.");
		} else if (isNull(head)) {
			System.out.println("Null list! Nothing to remove.");
		} else {
			for (int i=0; i<getSize(head);i++){
				int match = -1;
				for(int j=0; j<getSize(head); j++){
					if (curr.val == val) {
						match = j;
						break;
					}
					curr = curr.next;
				}
				if (match == -1) return;
				remove(head, match);
				curr = head;
			}
		}
	}

	public void reverse (ListNode head){
		ListNode prev = null;
		ListNode curr = head;
		ListNode next = null;
		while (curr != null) {
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev;
	}
	//Please implement other functions
	
	public static void main(String[] args){
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(2);
		ListNode l5 = new ListNode(5);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		LinkedListOperations l = new LinkedListOperations();

		System.out.println("Value(s) in LinkedList l1:");
		l.display(l1);
		System.out.println("The size of LinkedList l1 is: " + l.getSize(l1));
		System.out.println("\nValue(s) in LinkedList l5:");
		l.display(l5);
		System.out.println("The size of LinkedList l5 is: " + l.getSize(l5));

		System.out.println("\nLet indices start from 0...");
		int ind = 4, inval = 4;
		System.out.println("Insert a new node with value " + inval + " to LinkedList l1 at index " + ind + ":");
		l.insert(l1, ind, inval);
		l.display(l1);
		System.out.println("After insert(), value(s) in LinkedList l5:");
		l.display(l5);

		int rmind = 3;
		System.out.println("\nRemove index " + rmind + " from LinkedList l1:");
		l.remove (l1, rmind);
		l.display(l1);
		System.out.println("After insert(), value(s) in LinkedList l5:");
		l.display(l5);

		int rmval = 2;
		System.out.println("\nRemove all nodes with value " + rmval + " from LinkedList l1:");
		l.removeVal(l1,rmval);
		l.display(l1);
		System.out.println("After remove(), value(s) in LinkedList l5:");
		l.display(l5);

		System.out.println("\nReverse LinkedList l1:");
		l.reverse(l1);
		l.display(l1);
		System.out.println("The size of LinkedList l1 is: " + l.getSize(l1));
		System.out.println("After reverse(), value(s) in LinkedList l5:");
		l.display(l5);
		System.out.println("The size of LinkedList l5 is: " + l.getSize(l5));
		System.out.println("");
		l.display(null);
		System.out.println("The size of empty LinkedList is: " + l.getSize(null));
	}
}
