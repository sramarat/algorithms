package misc;

public class MyHashMap<K, V> {

	private int capacity;
	private MyEntry<K, V>[] entries;

	public MyHashMap(int capacity) {
		this.capacity = capacity + 1;
		entries = new MyEntry[this.capacity];
	}

	public MyHashMap() {
		this(100);
	}

	public void put(K key, V value) {

		int index;
		int hashCode = -1;

		if (key == null)
			index = capacity - 1;
		else {
			hashCode = key.hashCode();
			index = computeIndexForHash(hashCode);
		}

		MyEntry<K, V> entry = entries[index];
                if(entry == null)
                {
                        entries[index] = new MyEntry<K, V>(key, value, hashCode, entry);
                        return;
                }

                MyEntry<K,V> prev = null;
                for (; entry != null; entry = entry.getNext()) {

                        K curKey = entry.getKey();

                        if (curKey == key || curKey.equals(key)) {
                                entry.setValue(value);
                                return;
                        }

                        prev = entry;
                }

                prev.next = new MyEntry<K, V>(key, value, hashCode, entry);
	}

	public V getValue(K key) {
		
		int hashCode = key.hashCode();
		int index = computeIndexForHash(hashCode);
		MyEntry<K, V> entry = entries[index];

		for (; entry != null; entry = entry.getNext()) {
			K curKey = entry.getKey();

			if (curKey == key || curKey.equals(key)) {
				return entry.getValue();
			}
		}

		return null;
	}

	private int computeIndexForHash(int hashcode) {
		return hashcode % (capacity - 2);
	}

	public static class MyEntry<K, V> {
		private K key;
		private V value;
		private int hash;
		private MyEntry<K, V> next;

		public MyEntry(K key, V value, int hash, MyEntry<K, V> next) {
			this.key = key;
			this.value = value;
			this.hash = hash;
			this.next = next;
		}

		public MyEntry<K, V> getNext() {
			return next;
		}

		public int getHash() {
			return hash;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}
	}
}
