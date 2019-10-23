//package edu.uprm.cse.datastructures.cardealer.model;
//
//import java.util.concurrent.CopyOnWriteArrayList;
//
//import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
//import edu.uprm.cse.datastructures.cardealer.util.SortedList;
//
//public class CarList {
//	private static CircularSortedDoublyLinkedList<Car> carList = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
//	
//	
//	public CarList() {
//		super();
//	}
//
//
//	public static void resetCars()
//	{ carList = new CircularSortedDoublyLinkedList<Car>(new CarComparator()); }
//
//	//TODO
//	public static CircularSortedDoublyLinkedList<Car> getInstance() {
//		return carList;
//	}
//
//}
package edu.uprm.cse.datastructures.cardealer.model;

import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;

public class CarList
{

	private static CircularSortedDoublyLinkedList<Car> carList = new CircularSortedDoublyLinkedList<Car>(new CarComparator());
	
	
	public static CircularSortedDoublyLinkedList<Car> getInstance(){
		return carList;
	}
	
	
	public static void resetCars() {
		carList.clear();
	}
	
}