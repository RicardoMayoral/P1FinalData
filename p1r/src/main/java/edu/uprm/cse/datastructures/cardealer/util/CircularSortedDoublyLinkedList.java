package edu.uprm.cse.datastructures.cardealer.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class CircularSortedDoublyLinkedList<E>  implements  SortedList<E> {
	
	/*
	 * private class to manage the iterator method of the list
	 * 
	 */
	@SuppressWarnings("hiding")
	private class CircularSortedDoublyLinkedListIterator<E> implements Iterator<E>{
		private DNode<E> nextNode;
		
		
		@SuppressWarnings( "unchecked" )
		public CircularSortedDoublyLinkedListIterator() {
			this.nextNode = (DNode<E>) header.getNext();
		}
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				E result = this.nextNode.getElement();
				this.nextNode = this.nextNode.getNext();
				return result;
			}
			else {
				throw new NoSuchElementException();
			}
		}
		
	}
	
	/**
	 * 
	 * Internal private values of instance
	 * 
	 */	
	private DNode<E> header;
	private int size;
	private Comparator<E> c;
	private String lPM ="";
	
	public String getlPM() {
		return lPM;
	}

	public void setlPM(String lPM) {
		this.lPM = lPM;
	}

	/**
	 * CREATES a CircularSortedDoublyLinkedList instance of type <E> with its comparator for that type.
	 * 
	 * @param Comparator specific to <E> type
	 */
	public CircularSortedDoublyLinkedList(Comparator<E> comp) {
		super();
		this.header = new DNode<E>();
		this.c = comp;
		
		this.size = 0;
		header.setNext(header);
		header.setPrev(header);
	}
	
	/**
	 * @return Iterator<E> 
	 * 
	 * @param Comparator specific to <E> type
	 */	
	@Override
	public Iterator<E> iterator() {
		return new CircularSortedDoublyLinkedListIterator<E>();
	}

	/**
	 * @return boolean value true if it added the new Element, false if it was given a null Element as parameter 
	 * 
	 * @param Element of type <E>
	 */	
	@Override
	public boolean add(E obj) {
		if (obj == null) {
			return false;
		}
		DNode<E> nnode = new DNode<E>(obj);
		
		// List is empty
		if(size == 0) {
			nnode.setNext(header);
			nnode.setPrev(header);
			header.setNext(nnode);
			header.setPrev(nnode);
		}
		
		//List is not empty
		else {
			//first elements since list is not empty
			DNode<E> temp = this.header.getNext();
			
			int i=0;
			boolean sorted = false;
			E e1 = nnode.getElement();
			E e2 = temp.getElement();
			while( i<this.size() || sorted) {
				if(e1 == null) {
					System.out.println("first argument is equal to null");
				}
				if(e2 == null) {
					System.out.println("second argument is equal to null");
				}
				int comparedvalue = c.compare(e1, e2);
				System.out.println(comparedvalue);
				if(comparedvalue <= 0) {
					//System.out.println(comparedvalue);
					sorted = true;
					
				}
				if(sorted)
					break;
				temp = temp.getNext();
				i++;
			}
			
			
			nnode.setPrev(temp.getPrev());
			nnode.setNext(temp);
			temp.getPrev().setNext(nnode);
			temp.setPrev(nnode);
			
			
		}
		this.setlPM("add()");

		size++;
		return true;
	}

	/**
	 * @return size variable containing the size of the current instance.
	 *
	 */	
	@Override
	public int size() {
		return this.size;
	}

	
	/**
	 * @return boolean value if it found and removed the element at the @param
	 * 
	 * @param Element to be removed
	 */	
	@Override
	public boolean remove(E obj) {
		boolean result = false;
		DNode<E> curr = this.header.getNext();
		for(int i=0; i<this.size(); i++) {
			if(curr.getElement().equals(obj)) {
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
				result = true;
				size--;
			}
			curr=curr.getNext();
		}
		this.setlPM("remove(E obj)");

		return result;
	}

	
	/**
	 * @return boolean value if it found and removed the element with the given index at the @param
	 * 
	 * @param index for element to be removed
	 */	
	@Override
	public boolean remove(int index) {
		if(index>this.size()-1) {
			return false;
		}
		boolean result = false;
		DNode<E> curr = this.header.getNext();
		for(int i=0; i<this.size(); i++) {
			if(i==index) {
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
				result = true;
				size--;
			}
			curr=curr.getNext();
		}
		this.setlPM("remove(int index()");

		return result;
	}

	/**
	 * @return int value of the amount of found and removed elements equal to the @param
	 * 
	 * @param Element to be removed completely from the instance
	 */	
	@Override
	public int removeAll(E obj) {
		int result = 0;
		ArrayList<Integer> vals = new ArrayList<>();
		for(int i=0; i<this.size(); i++) {
			if(c.compare(this.get(i), obj) == 0) {
				vals.add(i);
			}
		}
		while(!vals.isEmpty()) {
			int last = vals.size()-1; //index of last elements in vals 
			this.remove(vals.get(last));
			vals.remove(last);
			result++;
		}
		
		this.setlPM("removeAll(E obj)");

		return result;
	}

	/**
	 * @return The first element of the instance
	 *
	 */	
	@Override
	public E first() {
		return this.header.getNext().getElement();
	}

	/**
	 * @return The last element of the instance
	 *
	 */	
	@Override
	public E last() {
		return this.header.getPrev().getElement();
	}

	/**
	 * @return element with index equal to @param
	 *
	 *@param index of the element to be returned
	 */	
	@Override
	public E get(int index) {
		//Returns null if not found
		DNode<E> curr = this.header.getNext();
		for(int i=0; i<this.size(); i++) {
			if(i==index) {
				return curr.getElement();
			}
			curr=curr.getNext();
		}
		return null;
	}

	/**
	 * Removes all the elements in the instance, finally having a size of 0
	 *
	 */	
	@Override
	public void clear() {
		while(this.size()!=0) {
			this.remove(0);
		}
		this.setlPM("clear()");

		
	}

	
	/**
	 * @return boolean value if the instance contains the element at the @param
	 *
	 *@param element to be decided if it is part of the instance
	 */	
	@Override
	public boolean contains(E e) {
		boolean result = false;

		DNode<E> curr = this.header;
		for(int i=0; i<this.size(); i++) {
			curr=curr.getNext();
			if(curr.getElement().equals(e))
				result = true;
		}
		
		return result;
	}

	/**
	 * @return boolean value true if the instance has size equal to 0, false if it is > 0
	 *
	 */	
	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	/**
	 * @return The first element equal to @param
	 *
	 * @param element to be found
	 */	
	@Override
	public int firstIndex(E e) {
		//return -1 if e is not found
		int result =-1;
		DNode<E> curr = this.header.getNext();
		for(int i=0; i<this.size(); i++) {
			if(curr.getElement().equals(e)) {
				result = i;
				return result;
			}
			curr=curr.getNext();
		}
		
		return result;
	}

	/**
	 * @return The last element equal to @param
	 *
	 * @param element to be found
	 */	
	@Override
	public int lastIndex(E e) {
		//return -1 if e is not found
		int result =-1;
		DNode<E> curr = this.header.getPrev();
		for(int i=this.size()-1; i>=0; i--) {
			if(curr.getElement().equals(e)) {
				result = i;
				return result;
			}
			curr=curr.getPrev();
		}
		
		return result;
	}
	
	//Private class of Nodes
	private static class DNode<E>{
		private DNode<E> prev, next;
		private E element;
		
		public DNode() {
			this(null);
		}
		@SuppressWarnings("unused")
		public DNode(DNode<E> prev, DNode<E> next, E element) {
			super();
			this.prev = prev;
			this.next = next;
			this.element = element;
		}
		public DNode(E e) {
			this(e, null, null);
		}
		public DNode(E e, DNode<E> p, DNode<E> n) {
			this.element = e;
			this.next = n;
			this.prev = p;
		}

		public E getElement() {
			return element;
		}

		@SuppressWarnings("unused")
		public void setElement(E element) {
			this.element = element;
		}

		public DNode<E> getPrev() {
			return prev;
		}

		public void setPrev(DNode<E> prev) {
			this.prev = prev;
		}

		public DNode<E> getNext() {
			return next;
		}

		public void setNext(DNode<E> next) {
			this.next = next;
		}
		 @SuppressWarnings("unused")
		public void clearNP(DNode<E> node) {
			 node.setNext(null);
			 node.setPrev(null);
		 }
	}

	
	
	

}
