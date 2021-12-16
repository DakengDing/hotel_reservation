package model;

import java.util.Date;
import java.util.Objects;

public class Reservation {
    public Customer customer;
    public IRoom room;
    public Date checkInDate;
    public Date checkOutDate;

    public Reservation(Customer customer,IRoom room,Date checkInDate,Date checkOutDate){
        this.room = room;
        this.customer = customer;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }



    @Override
    public String toString(){
        return "Reservation: " +
                "Customer " + customer.getFirstName() + " " + customer.getLastName() + '\''+
                " " + room +'\'' +
                " Checkin: " + checkInDate + '\''+
                " Checkout: " + checkOutDate + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(customer, that.customer) && Objects.equals(room, that.room) && Objects.equals(checkInDate, that.checkInDate) && Objects.equals(checkOutDate, that.checkOutDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, room, checkInDate, checkOutDate);
    }
}
