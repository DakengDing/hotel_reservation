package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {
    public Set<IRoom> rooms = new HashSet<IRoom>();
    public Set<Reservation> reservations = new HashSet<Reservation>();
    //public Collection<IRoom> availableRooms = new LinkedHashSet<>();

    private static ReservationService reservationService = null;
    private ReservationService(){}
    public static ReservationService getInstance(){
        if (null == reservationService){
            reservationService = new ReservationService();
        }
        return reservationService;
    }


    public void addRoom(IRoom room){
        if (rooms.add(room)){
            System.out.println("Room is added.");
        }
        else {
            System.out.println("Room is already exists.");
        }

    }

    public IRoom getARoom(String roomId){
        for (IRoom target : rooms){
            if (roomId.equals(target.getRoomNumber())){
                //System.out.println(target);
                return target;
            }
        }
        System.out.println("No room found!");
        return null;



    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation newReservation = new Reservation(customer,room,checkInDate,checkOutDate);

        System.out.println("Your reservation has been booked");

        reservations.add(newReservation);
        return newReservation;



    }
    /*public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> availableRooms = new HashSet<>();
        //System.out.println(availableRooms);
        if (reservations.size()==0){
            availableRooms = rooms;
            System.out.println(availableRooms);
            return availableRooms;
        }
        else{
            for (Reservation res: reservations){
                for (IRoom findings : rooms){
                    if (res.room.getRoomNumber().equals(findings.getRoomNumber())
                            &&(checkInDate.before(res.getCheckInDate()))
                            &&(checkOutDate.before(res.getCheckInDate()))
                            ||(checkOutDate.after(res.getCheckOutDate()))&&(checkInDate.after(res.getCheckOutDate()))
                            ||(!res.room.getRoomNumber().contains(findings.getRoomNumber()))){
                        availableRooms.add(findings);
                    }
                    else if (res.room.getRoomNumber().equals(findings.getRoomNumber())){
                        availableRooms.remove(findings);
                    }
                }

            }
        }
        System.out.println(availableRooms);
        return availableRooms;
    }*/

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : rooms){
            if (!isRoomReserved(room,checkInDate,checkOutDate)){
                availableRooms.add(room);

            }
        }

        //System.out.println(availableRooms);
        return availableRooms;
    }
    public boolean isRoomReserved (IRoom room,Date checkInDate, Date checkOutDate){
        if (reservations.isEmpty())return false;
        for (Reservation reservations : reservations){
            IRoom  resRoom = reservations.getRoom();
            if (resRoom.getRoomNumber().equals(room.getRoomNumber())){
                if (isDateWithinRande(checkInDate,checkOutDate,reservations)){
                    return true;
                }
            }
        }
        return false;
    }
    boolean isDateWithinRande(Date checkIndate, Date checkOurData, Reservation reservation){
        return !(checkOurData.before(reservation.getCheckInDate())|| checkIndate.after(reservation.getCheckOutDate()));
    }

    public Collection<Reservation> getCustomerReservation(Customer customer){
        Collection<Reservation> customerRes = new ArrayList<>();
        for (Reservation customerResvs:reservations){
            if (customerResvs.getCustomer().equals(customer)){
                customerRes.add(customerResvs);

            }

        }
        return customerRes;

    }

    public void printAllReservation(){
        for (Reservation allRes:reservations){
            System.out.println(allRes);
        }

    }

}
