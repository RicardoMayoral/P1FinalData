package edu.uprm.cse.datastructures.cardealer;

import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Predicate;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import edu.uprm.cse.datastructures.cardealer.model.Car;
import edu.uprm.cse.datastructures.cardealer.model.CarList;
import edu.uprm.cse.datastructures.cardealer.util.CircularSortedDoublyLinkedList;
import javassist.NotFoundException; 

@Path("/cars")
public class CarManager {

	private final CircularSortedDoublyLinkedList<Car> cList = CarList.getInstance();   

	/**
	 * 
	 * method to get all cars from the list
	 */	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Car[] getAllCars() {
		Car[] result = new Car[cList.size()];
		for(int i=0; i<result.length; i++) {
			result[i] = cList.get(i);
		}
		return result;
	}

	/**
	 * 
	 * method to get car from the list based on id
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Car getCar(@PathParam("id") long id){
		for(Car c: cList) {
			if(c.getCarId() == id) {
				return c;
			}
		}
		return null;
	}     


	/**
	 * 
	 * method to add a car to the list
	 */
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCar(Car car){
		cList.add(car);
		return Response.status(201).build();
	}  


	/**
	 * 
	 * method to update the contents of a specific car based on id 
	 */
	@PUT
	@Path("{id}/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("id") long id, Car car){
		for(int i=0; i<cList.size(); i++) {
			if(cList.get(i).getCarId()==id) {
				long ctuid = cList.get(i).getCarId();
				Car newCar = new Car(ctuid, car.getCarBrand(), car.getCarModel(), car.getCarModelOption(), car.getCarPrice());
				cList.remove(i);
				cList.add(newCar);
				return Response.status(Response.Status.OK).build();
			}
		}

		return Response.status(Response.Status.NOT_FOUND).build();		

	}


	/**
	 * 
	 * method to delete a car from the list based on a car given as parameter
	 * @return 
	 */
	@DELETE
	@Path("/delete")
	public Response carDelete(Car c){
		for(int i=0; i<cList.size(); i++) {
			if(cList.get(i).getCarId()==c.getCarId()) {
				cList.remove(i);
				return Response.status(201).build();
			}
		}
		return Response.status(404).build();
	}
	
	/**
	 * 
	 * method to delete a car from the list based on id of car as parameter
	 * @return 
	 */
	@DELETE
	@Path("{id}/delete")
	public Response carDelete(@PathParam("id") long id){
		for(int i=0; i<cList.size(); i++) {
			if(cList.get(i).getCarId()==id) {
				cList.remove(i);
				return Response.status(201).build();
			}
		}
		return Response.status(404).build();
	}


}
