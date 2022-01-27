public class MyHashTable {
	int[] keys;
	int[] values;
	int m; // table size
	int n; // number of item
	
	public MyHashTable(int m){
		n = 0;
		this.m = m;
		keys = new int[m];
		values = new int[m];
		for(int i=0; i<m; i++){
			keys[i] = 0;
			values[i] = 0;
		}
	}
	
	int hash(int key){
		return key % this.m;
	}
	
	void printArray(){
		System.out.println("key array: ");
		for (int i = 0; i < this.m; i++)
			if(keys[i] != 0)
				System.out.print("(" + i + ": " + keys[i] + ") ");
		System.out.println("\nvalue array: ");
		for (int i = 0; i < this.m; i++)
			if(values[i] != 0)
				System.out.print("(" + i + ": " + values[i] + ") ");
		// System.out.println("\n");
	}


	//Implement other functions
	public void put(int key, int value){
		n++;
		if (n >= m/2) {
			rehashing();
		}
		int	b = hash(key);
		if (keys[b] == 0){
			keys[b] = key;
			values[b] = value;
		} else {
			for(int i=1; i <= m/2; i++){
				int idx = (b + i^2) % m;
				if (keys[idx] == 0){
					keys[idx] = key;
					values[idx] = value;
					break;
				}
			}
		}
	}


	public int get(int key){
		int value = 0;
		for(int i = 0; i<m; i++){
			if (keys[i] == key) {
				value = values[i];
			}
		}
		//if (value == 0) {
		//	System.out.println("Given key does not exist.");
		//}
		return value;
	}


	public boolean contains(int key) {
		// key and values are assumed to be greater than 0
		return get(key) != 0;
	}


	public void remove(int key){
		if (contains(key)) {
			for(int i = 0; i<m; i++){
				if (keys[i] == key) {
					keys[i] = 0;
					values[i] = 0;
				}
			}
		} else {
			System.out.println("Given key does not exist, nothing is removed.");
		}
	}

	public void rehashing(){
		int[] tmp_keys = new int[m];
		int[] tmp_values = new int[m];
		int old_size = m;
		for(int i=0; i < old_size; i++){
			tmp_keys[i] = keys[i];
			tmp_values[i] = values[i];
		}

		m = findPrime(old_size);

		keys = new int[m];
		values = new int[m];
		for(int i=0; i<m; i++){
			keys[i] = 0;
			values[i] = 0;
		}
		for(int i=0; i < old_size; i++) {
			keys[i] = tmp_keys[i];
			values[i] = tmp_values[i];
		}

	}


	public int findPrime(int x){
		x++;
		for(int i=2; i<x; i++){
			if (x%i == 0) {
				x++;
				i=2;
			} else {
				continue;
			}
		}
		return x;
	}


	public static void main(String[] args) {
		MyHashTable hashtable = new MyHashTable(11);
		System.out.println(hashtable.hash(5));
		for(int i = 1; i <= 4; i++) {
			System.out.println("Putting key-value pair: (" + 11 * i + ", " + 11 * i * 10 + ") into HashTable.");
			hashtable.put(11 * i, 11 * i * 10);
			System.out.println("Size of HashTable when it has "+ i + " item(s): " + hashtable.m);
		}
		hashtable.printArray();
		System.out.println("\n");
		for(int i = 1; i <= 4; i++) {
			System.out.println("Value of key: " + i + "is: " + hashtable.get(11 * i));
		}

		System.out.println("\n");
		int currNum = 8;
		System.out.println("Next prime bigger than " + currNum + " is: " + hashtable.findPrime(currNum));
		currNum = 22;
		System.out.println("Next prime bigger than " + currNum + " is: " + hashtable.findPrime(currNum));
		System.out.println("\n");
		//when i = 5, rehashing function should be called automatically from put function
		//because hashtable has 5 items, m/2 = 11/2 = 5
	    //the size of new hashtable should be 23
		for(int i = 5; i <= 10; i++) {
			System.out.println("Putting key-value pair: (" + 11 * i + ", " + 11 * i * 10 + ") into HashTable.");
			hashtable.put(11 * i, 11 * i * 10);
			System.out.println("Size of HashTable when it has "+ i + " item(s): " + hashtable.m);
		}
		hashtable.printArray();

		int remove_key = 44;
		System.out.println("\n\nRemoving key: " + remove_key + " from HashTable.");
		hashtable.remove(remove_key);

		hashtable.printArray();

		System.out.println("\n\nRemoving key: " + 220 + " from HashTable.");
		hashtable.remove(220);

		System.out.println("\nDoes the HashTable contains key: " + remove_key + "? : " + hashtable.contains(remove_key));
		System.out.println("\nDoes the HashTable contains key: " + 11 + "? : " + hashtable.contains(11));
		//put into the hashtable with more items
		//you can generate your own version of key-value pairs, do not have to (11*i, 11*i*10)
		//note that all keys and values must be nonzero, it is important. 
		//because in this homework, we are assuming 0 as the empty bucket.

		//test other functions: remove, contain
		
	}
}