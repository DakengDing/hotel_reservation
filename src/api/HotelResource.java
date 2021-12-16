package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    private static HotelResource hotelResource = null;
    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();
    private HotelResource(){}
    public static HotelResource getInstance(){
        if (null == hotelResource){
            hotelResource = new HotelResource();
        }
        return hotelResource;
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

    public void createACustmor(String email,String firstName,String lastName){
        customerService.addCustomer(email,firstName,lastName);

    }
    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);


    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate,Date checkOutDate){
        Customer fcus = getCustomer(customerEmail);
        return reservationService.reserveARoom(fcus,room,checkInDate,checkOutDate);

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        Customer fcus = getCustomer(customerEmail);
        return reservationService.getCustomerReservation(fcus);

    }
    public Collection<IRoom> findARoom(Date checkIn,Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);

    }
}
