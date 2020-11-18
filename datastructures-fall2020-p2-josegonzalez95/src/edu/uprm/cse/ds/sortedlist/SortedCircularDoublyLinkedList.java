package edu.uprm.cse.ds.sortedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedCircularDoublyLinkedList<E extends Comparable<E>> implements SortedList<E> {

	private static class Node<E> {
		public Node() {
			this.element = null;
			this.next = null;
			this.prev = null;
			// TODO Auto-generated constructor stub
		}

		public Node(E e, Node<E> next, Node<E> previous) {
			this.element = e;
			this.next = next;
			this.prev = previous;
		}

		private E element;
		private Node<E> next;
		private Node<E> prev;

		public E getElement() {
			return element;
		}
		public void setElement(E element) {
			this.element = element;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public Node<E> getPrev() {
			return prev;
		}
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}	
	}

	private Node<E> header;
	private int size;

	public SortedCircularDoublyLinkedList() {
		this.header = new Node<E>();
		this.size = 0;
	}
	@SuppressWarnings({ "unchecked", "hiding" })
	private class ListIterator<E> implements Iterator<E> {

		private Node<E> currentNode;
		private int count;

				public ListIterator() {
			this.currentNode = (Node<E>) header;
			this.count = 0;
		}

		public ListIterator(int index) {
			int i = 0;
			this.currentNode = (Node<E>) header;
			while (i < index) {
				this.currentNode = this.currentNode.getNext();
				i++;
			}
			this.count = 0;
		}


		@Override
		public boolean hasNext() {
	
			return count < size();
		}

		
		@Override
		public E next() {
			if (this.hasNext()) {
				E result = this.currentNode.getNext().getElement();
				this.currentNode = this.currentNode.getNext();
				this.count ++;
				return result;
			}else {
				throw new NoSuchElementException();
			}
		}

	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ListIterator<E>();	
	}

	@Override
	public boolean add(E obj) {
		// TODO Auto-generated method stub
		if(obj.equals(null)) {
			return false;
		}

		Node<E> newNode = new Node<E>(obj, null, null);

		if(this.isEmpty()) {
			this.header.setNext(newNode);
			this.header.setPrev(newNode);
			newNode.setNext(this.header);
			newNode.setPrev(this.header);
			this.size++;
			return true;
		}
		Node<E> temp = this.header.getNext();
		while(temp != this.header) {
			if(temp.getElement().compareTo(obj) > 0) {
				newNode.setNext(temp);
				newNode.setPrev(temp.getPrev());
				temp.setPrev(newNode);
				newNode.getPrev().setNext(newNode);
				this.size++;
				return true;
			}
			temp = temp.getNext();
		}
		newNode.setNext(this.header);
		newNode.setPrev(this.header.getPrev());
		this.header.setPrev(newNode);
		newNode.getPrev().setNext(newNode);
		this.size++;
		return true;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public boolean remove(E obj) {
		// TODO Auto-generated method stub

		if (isEmpty()) {
			return  false;

		}

		remove(firstIndex(obj));
		return true;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			return false;
		}
		this.checkIndex(index);
		if(index==0) {
			Node<E> temp = this.header.getNext();
			this.header.setNext(temp.getNext());
			temp.setElement(null);
			temp.setNext(null);
			temp.setPrev(null);
			this.size--;
			return true;
		}
		else {
			Node<E> temp1 = this.findNode(index - 1);
			Node<E> temp2 = temp1.getNext();
			temp1.setNext(temp2.getNext());
			temp1.getNext().setPrev(temp1);
			temp2.setNext(null);
			temp2.setPrev(null);
			temp2.setElement(null);
			this.size--;
			return true;
		}
	}

	@Override
	public int removeAll(E obj) {
	
		// TODO Auto-generated method stub
		int count = 0;
		Node<E> temp = this.header;
		while (temp.getNext() != this.header) {
			if(temp.getNext().getElement().equals(obj)){
				this.remove(this.firstIndex(obj));
				count++;
			}else {
				temp = temp.getNext();
			}
			
		}
		return count;
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub
		return this.header.getNext().getElement();
	}

	@Override
	public E last() {
		// TODO Auto-generated method stub
		return this.header.getPrev().getElement();
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		this.checkIndex(index);
		Node<E> target = this.findNode(index);
		return target.getElement();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		while(!this.isEmpty()) {
			this.remove(0);
		}
	}

	@Override
	public boolean contains(E e) {

		// TODO Auto-generated method stub
		Node<E> temp = this.header.getNext();
		

		while (temp != this.header) {
			if (temp.getElement().equals(e)) {
				return true;
			}
			temp = temp.getNext();
		}
		
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.size == 0;
	}


	
	@Override
	public Iterator<E> iterator(int index) {
		// TODO Auto-generated method stub
		return new ListIterator<E>(index);
	}

	@Override
	public int firstIndex(E e) {
		// TODO Auto-generated method stub
		int index = 0;
		Node<E> temp = this.header;
	
		while ((index < this.size()) && !temp.getNext().getElement().equals(e) ) {
			temp = temp.getNext();
			index ++;
		}
		if ((index < this.size()) && temp.getNext().getElement().equals(e))
			return index;
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		// TODO Auto-generated method stub
		int index = 0;
		int lastIndexPos = -1;
		Node<E> temp = this.header;

		while (index < this.size()) {
			if (temp.getNext().getElement().equals(e)) {
				lastIndexPos = index;
			}
				
			temp = temp.getNext();
			index ++;
		}
		return lastIndexPos;
	}
	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	private class ReverseListIterator<E> implements ReverseIterator<E> {

		private Node<E> currentNode;
		private int count;

	
		public ReverseListIterator() {
			this.currentNode = (Node<E>) header;
			this.count = 0;
		}
		
		public ReverseListIterator(int index) {
			int i = 0;
			this.currentNode = (Node<E>) header;
			while (i < index) {
				this.currentNode = this.currentNode.getPrev();
				i++;
			}
			this.count = 0;
		}
		
		@Override
		public boolean hasPrevious() {
			return this.count < size();
		}


		@Override
		public E previous() {
			if (this.hasPrevious()) {
				E result = this.currentNode.getPrev().getElement();
				this.currentNode = this.currentNode.getPrev();
				this.count ++;
				return result;
			}else {
				throw new NoSuchElementException();
			}
		}
		
	}

	@Override
	public ReverseIterator<E> reverseIterator() {
		// TODO Auto-generated method stub
		return new ReverseListIterator<E>();
	}

	@Override
	public ReverseIterator<E> reverseIterator(int index) {
		// TODO Auto-generated method stub
		return new ReverseListIterator<E>(index);
	}
	
	private void checkIndex(int index) {
		if ((index < 0) || (index >= this.size())){
			throw new IndexOutOfBoundsException();
		}
	}
	private Node<E> findNode(int index) {
		Node<E> temp = this.header.getNext();
		int i = 0;

		while (i < index) {
			temp = temp.getNext();
			i++;
		}
		return temp;

	}
}
