package edu.uprm.cse.ds.sortedlist;

public class ListTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SortedList<String> theList = new SortedCircularDoublyLinkedList<String>();
		System.out.println("Empty List: " + theList.isEmpty());
		theList.add("Bob");
		System.out.println(theList.size());
		printList(theList);
		System.out.println("Empty List: " + theList.isEmpty());

		theList.add("Ron");
		System.out.println(theList.size());
		printList(theList);
		System.out.println(theList.size());
		printList(theList);
		theList.add("Jil");
		System.out.println(theList.size());
		printList(theList);
		System.out.println("Element 0 is Bob: " + theList.get(0).equals("Bob"));
		System.out.println("Element 1 is Jil: " + theList.get(1).equals("Jil"));
		System.out.println("List contains Jil: " + theList.contains("Jil"));
		System.out.println("Last index of Jil: " + theList.lastIndex("Jil"));
		System.out.println("Last index of Bob: " + theList.lastIndex("Bob"));
		System.out.println("First index of Bob: " + theList.lastIndex("Ron"));

		
		theList.remove("Jil");
		printList(theList);
		theList.add("Amy");
		System.out.println("First element is Amy: " + theList.first().equals("Amy"));
		theList.add("Ned");
		System.out.println("Last element is Ned: " + theList.last().equals("Ned"));  // must be false
		System.out.println("Last element is Ron: " + theList.last().equals("Ron"));
		theList.add("Cal");
		theList.add("Cal");
		printList(theList);
		theList.removeAll("Cal");
		System.out.println();
		printList(theList);

		printReverseList(theList);
		System.out.println("Remove element at position 1: " + theList.remove(1));
		printReverseList(theList);
		System.out.println("Remove last elements: " + theList.remove("Ron"));
		printList(theList);
		System.out.println();
		printReverseList(theList);
		

	}

	private static void printList(SortedList<String> theList) {
		for (String s: theList){
			System.out.println(s);
		}
	}

	private static void printReverseList(SortedList<String> theList) {
		for (ReverseIterator<String> iter = theList.reverseIterator(); iter.hasPrevious(); ){
			System.out.println(iter.previous());
		}
	}

}
