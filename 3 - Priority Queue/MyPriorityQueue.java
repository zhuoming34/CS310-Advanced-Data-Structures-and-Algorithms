import java.util.Arrays;

public class MyPriorityQueue {
	Integer[] A;
	int n; //size of the priority queue

	public MyPriorityQueue(Integer[] A, int n){
		this.A = A;
		this.n = n;
	}

	// build a heap from array A
	public void build_heap() {
		for (int i = this.A.length/2-1; i >= 0; i--)
			heapify(i);
	}

	// apply heapify on element A[i]
	public void heapify(int i){
		int left = 2*i + 1;
		int right = 2*i + 2;
		int largest = i;
		if (left < this.n && this.A[left] > this.A[i]) largest = left;
		if (right < this.n && this.A[right] > this.A[largest]) largest = right;
		if (largest != i) {
			int temp = this.A[i];
			this.A[i] = this.A[largest];
			this.A[largest] = temp;
			heapify(largest);
		}
	}

	// insert x into pq
	public void insert(int x) {
		//System.out.println("n = " + this.n);
		if (this.n != this.A.length) {
			for (int i = 0; i < this.A.length; i++)
				if (this.A[i] == null) {
					this.A[i] = x;
					break;
				}
			this.n++;
			build_heap();
		} else {
			System.out.println("PQ is full, failed to insert " + x + ".");
		}
	}

	// return the element of pq with the largest value
	public int maximum(){
		if (this.A[0] == null) throw new NullPointerException("PQ is empty");
		int max = this.A[0];
		for (int i = 1; i < this.A.length && this.A[i] != null; i++)
			if (this.A[i] > max)
				max = this.A[i];
		return max;
	}

	// removes the element with the largest value from pq,
	// and return it
	public int extract_max() {
		int max = maximum();
		for(int i = 0; i < this.A.length && this.A[i] != null; i++) {
			//System.out.println("i = " + i);
			//System.out.println("A[i] = " + A[i]);
			if (this.A[i] == max) {
				int j = this.A.length - 1;
				while (this.A[j] == null) j--;
				int last = this.A[j];
				this.A[i] = last;
				this.A[j] = null;
				this.n--;
				build_heap();
				//break;
				//System.out.println("check");
			}
		}
		if (this.A[0] == max) extract_max();
		return max;
	}

	// increases the value of A[i] to the new value val
	public void increase_value(int i, int val) {
		this.A[i] = val;
		// build_heap();
		for (int j = i; j >= 0; j--)
			heapify(j);
	}

	// decreases the value of A[i] to the new value val
	public void decrease_value(int i, int val) {
		this.A[i] = val;
		// build_heap();
		for (int j = this.A.length/2-1; j >= i; j--)
			heapify(j);
	}

	public static void main(String[] args) {
		Integer[] A = {10,20,5,30,8,6,null,null,null,null};
		// Integer[] A = {null,null,null,null};
		int n = 0;
		for(Integer a: A){
			if(a == null) 
				break;
			n +=1;
		}

		MyPriorityQueue pq = new MyPriorityQueue(A, n);
		System.out.println("Creates a PQ: \n" + Arrays.toString(pq.A));
		//[10, 20, 5, 30, 8, 6, null, null, null, null]

		int index = 1;
		pq.heapify(index);
		System.out.println("Heapify at index " + index + ": \n" + Arrays.toString(pq.A));
		//[10, 30, 5, 20, 8, 6, null, null, null, null]

		pq.build_heap();
		System.out.println("Builds heap: \n" + Arrays.toString(pq.A));
		//[30, 20, 6, 10, 8, 5, null, null, null, null]

		System.out.println("Maximum: " + pq.maximum());

		int val = 19;
		pq.insert(val);
		System.out.println("Inserts value " + val + ": \n" + Arrays.toString(pq.A));
		//[30, 20, 19, 10, 8, 5, 6, null, null, null]

		int max = pq.extract_max();
		System.out.println("Extracts max=" + max + ": \n" + Arrays.toString(pq.A));
		//[20, 10, 19, 6, 8, 5, null, null, null, null]

		pq.increase_value(index=3, val=100);
		System.out.println("Increases value at index " + index + " to " + val + ": \n" + Arrays.toString(pq.A));
		//[100, 20, 19, 10, 8, 5, null, null, null, null]

		pq.decrease_value(index=2, val=1);
		System.out.println("Decreases value at index " + index + " to " + val + ": \n" + Arrays.toString(pq.A));
		//[100, 20, 5, 10, 8, 1, null, null, null, null]
        
		//You can give more tests:
		System.out.println("Maximum: " + pq.maximum());

		pq.decrease_value(index=0, val=20);
		System.out.println("Decreases value at index " + index + " to " + val + ": \n" + Arrays.toString(pq.A));

		val = 20;
		for (int i=0; i < 5; i++, val--) {
			System.out.println("Inserts value " + val + ": ");
			pq.insert(val);
			System.out.println(Arrays.toString(pq.A));
		}

		pq.increase_value(index=5, val=20);
		System.out.println("Increases value at index " + index + " to " + val + ": \n" + Arrays.toString(pq.A));

		max = pq.extract_max();
		System.out.println("Extracts max=" + max + ": \n" + Arrays.toString(pq.A));
	}
}
