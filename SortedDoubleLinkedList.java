import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;


public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	Node<T> first;
	Node<T> last;
	int size;
	Comparator<T> listComparator;
	
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		super();
		listComparator = comparator;
	}
	
	public DoubleLinkedListIterator<T> iterator(){
		DoubleLinkedListIterator<T> it = new DoubleLinkedListIterator<T>(first, size);
		return it;
	}
	
	/*
	 * Adds a node to the list
	 */
	public void add(T data) {
		Node<T> x = new Node<T>(data);
		if(last == null) {
			first = last = x;
	}else{
			first.prev = x;
			x.next = first;
			first = x;
		}
		sort();
		size++;
	}
	
	/*
	 * Sorts List 
	 */
	public void sort() {
	    if (first == null){
	        return;
	    }
		
		int sortCount;
		Node<T> n;
	    do{
		    sortCount = 0;
		    n = first;
		    while(n.next != null) {
			    if (listComparator.compare(n.data, n.next.data) > 0){
			    	T t = n.data;
			    	n.data = n.next.data;
			    	n.next.data = t;
			    	sortCount++;
		        }
			    n = n.next;
		    }
		}while(sortCount != 0);
	}
	/*
	 * Obtains first element of the List
	 */
	public T retrieveFirstElement(){
		T x = first.data;
		if(first == last){
			first = null;
			last = null;
		}
			
		first = first.next;
		first.prev = null;
		return x;
	}
	
	/*
	 * Obtains Last Element of the List
	 */
	public T retrieveLastElement(){
		T x = last.data;
		if(first == last){
			first = null;
			last = null;
		}
		last = last.prev;
		last.next = null;	
		return x;
	}
	
	/*
	 * Returns First Element in the List
	 */
	public T getFirst() {
		return first.data;
	}
	
	
	/*
	 * Returns Last Element in the List
	 */
	public T getLast() {
		return last.data;
	}
	
	public Node<T> remove(T targetData, Comparator<T> comparator) {
		if (comparator.compare(targetData, first.data) == 0) {
			Node<T> x=first;
			if(first.next != null) {
				first.next.prev = null;
				first=first.next;
				}else{
				first = null;
				last = null;
			}
			size--;
			return x;
			}
		DoubleLinkedListIterator<T> it = iterator();
		while (it.hasNext() && comparator.compare(targetData, it.current.data) != 0) {
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
		sort();
		return null;
	}
	
	public int getSize() {
		return size;
	}
	
	public void addToEnd(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	public void addToFront(T data) throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
}