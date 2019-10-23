package edu.uprm.cse.datastructures.cardealer.model;

import java.util.Comparator;

public class CarComparator implements Comparator<Car>{

	//Implementation of the CarComparator. Compares all attributes of two chosen cars.
	@Override
	public int compare(Car c1, Car c2) {
		
		String currentCar = c1.getCarBrand() + c1.getCarModel() + c1.getCarModelOption();
		
		String secondCar = c2.getCarBrand() + c2.getCarModel() + c2.getCarModelOption();
		
		return currentCar.compareTo(secondCar);
		
	}
	
}