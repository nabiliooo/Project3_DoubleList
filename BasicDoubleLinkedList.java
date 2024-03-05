import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> {
	protected Node<T> first, last;
	protected int size;
	
	public BasicDoubleLinkedList(){
		first = null;
		last = null;
		size = 0;
	}
	
	/*
	 * Adds Element to the end of the List + Increase Size Value
	 */
	public void addToEnd(T data) {
		Node<T> x = new Node<T>(data);
		if(last == null){
			first = x;
			last = x;
		}else if(first == last) {
			last = x;
			last.prev = first;
			first.next = last;
		}else{
			last.next = x;
			x.prev = last;
			last = x;
		}
		size++;
	}
	
	/*
	 * Adds Element to the front of the List + Increase Size Value
	 */
	public void addToFront(T data) {
		Node<T> x  =  new Node<T>(data);
		if(first  ==  null){
			first  =  x;
			last  =  x;
		}else if(first  ==  last){
			first = x;
			first.next = last;
			last.prev = first;
		}else{
			first.prev = x;
			x.next = first;
			first = x;
		}
		size++;
	}
	
	/*
	 * First Element on the List
	 */
	public T getFirst() {
		return first.data;
	}
	
	
	/*
	 * Last Element on the List
	 */
	public T getLast() {
		return last.data;
	}
	
	
	/*
	 * Returns the Size on the List
	 */
	public int getSize() {
		return size;
	}
	

	public DoubleLinkedListIterator<T> iterator(){
		DoubleLinkedListIterator<T> it  =  new DoubleLinkedListIterator<T>(first, size);
		return it;
	}
	
	
	/*
	 * Remove instance of targetData
	 */
	public Node<T> remove(T targetData, Comparator<T> comparator){
		if (comparator.compare(targetData, first.data)  ==  0){
			Node<T> x = first;
			if(first.next !=  null){
				first.next.prev  =  null;
				first = first.next;
				}else{
					last = null;
					first = null;
					}
				size--;
				return x;
				}
		DoubleLinkedListIterator<T> it  =  iterator();
		while (it.hasNext() && comparator.compare(targetData, it.current.data) != 0){
			it.next();
		}
		if(comparator.compare(targetData, it.current.data) == 0){
			if (it.current == last){
				Node<T> x = last;
				last = last.prev;
				last.next = null;
				size--;
				return x;
			}
			it.current.prev.next = it.current.next;
			it.current.next.prev = it.current.prev;
			size--;
			return it.current;
		}
		return null;
	}
	
	/*
	 * Obtains the First Element on the List
	 */
	public T retrieveFirstElement() {
		T x = first.data;
		if(first == last)
			first = last = null;	
		
		
		first = first.next;
		first.prev = null;	
		return x;
	}
	
	/*
	 * Obtains the Last Element on the List
	 */
	public T retrieveLastElement() {
		T x = last.data;
		if (first == last) {
			first = last = null;	
		}
		last = last.prev;
		last.next = null;
		
		return x;
	}
	
	/*
	 * Returns ArrayList of all the items in DoubleLinkedList
	 */
	public ArrayList<T> toArrayList(){
		ArrayList<T> list  =  new ArrayList<T>();
		Node<T> x = first;
		while(x != null) {
			list.add(x.data);
			x = x.next;
		}
		return list;
	}
	protected class Node<T>{
		T data;
		Node<T> prev,next;
		public Node(){
			data = null;
			prev = next = null;
		}
		public Node(T d){
			data = d;
			prev = next = null;
			
		}
	}
	protected class DoubleLinkedListIterator<T> implements ListIterator<T>{
		int size, a;
		Node<T> current;
		Node<T> lastAccess;
		
		public DoubleLinkedListIterator(Node<T> c, int s){
			a = 0;
			current = c;
			size =  s;
		}
		
		@Override
		public boolean hasNext() {
			return a < size;
		}
		
		@Override
		public T next() throws NoSuchElementException{
			if(!hasNext())
				throw new NoSuchElementException();
			lastAccess = current;
			T x = lastAccess.data;
			if(current.next != null)
				current = current.next;
			a++;
			return x;
		}
		@Override
		public boolean hasPrevious(){
			return a > 0;
		}
		@Override
		public T previous() throws NoSuchElementException{
			if(!hasPrevious())
				throw new NoSuchElementException();
			lastAccess = current;
			T x = lastAccess.data;
			if(current.prev != null) {
				current = current.prev;
			}
			a--;
			return x;
		}

		@Override
		public int nextIndex()throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex()throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove()throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(Object e)throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(Object e) throws UnsupportedOperationException{
			throw new UnsupportedOperationException();
		}
		
	}
}