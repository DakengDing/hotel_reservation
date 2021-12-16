package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource = null;
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();
    private AdminResource(){}
    public static AdminResource getInstance(){
        if (null == adminResource){
            adminResource = new AdminResource();

        }
        return adminResource;
    }
    public Customer getCustomer(String email){
        Collection<Customer> allCust = customerService.getAllCustomers();
        for (Customer fcust:allCust){
            if (fcust.getEmail().equals(email)){
                return fcust;

            }
        }
        return null;
    }
    public void addRoom(List<IRoom> rooms){
        for (IRoom room:rooms){
            reservationService.addRoom(room);
        }
    }
    public Collection<IRoom> getAllRooms(){

        return reservationService.rooms;

    }
    public Collection<Customer> getAllCustomers(){
        return customerService.customerSet;

    }
    public void displayAllReservations(){
        for(Reservation ress : reservationService.reservations){
            System.out.println(ress);
        }

    }
}
