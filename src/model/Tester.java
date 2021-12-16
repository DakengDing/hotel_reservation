package model;

import static model.RoomType.SINGLE;

public class Tester {
    public static void main(String[] args){
        //Customer customer = new Customer("first","second","j@domain.com");
        Customer customer = new Customer("first","second","email@email.com");
        System.out.println(customer);
        IRoom room =new Room("100",108.00,SINGLE);
        System.out.println(room);
    }
}
