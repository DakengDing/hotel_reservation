package UI;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/*
Attributions:
https://knowledge.udacity.com/questions/574406
https://knowledge.udacity.com/questions/555990
 */

public class MainMenu {
    public static HotelResource hotelResource = HotelResource.getInstance();
    private static final String format = "MM/dd/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(format);



    public static void main(String[] args){
        MainMenu mainMenu = new MainMenu();
        mainMenu.runMenu();

    }
    public static void runMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the Hotel Reservation Application:");
            printMenu();
            Scanner scanner = new Scanner(System.in);
            int userInput = -1;
            while (userInput <0|| userInput>5){
                try {
                    System.out.println("\nEnter a number:");
                    userInput = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e){
                    System.out.println("Invalid selection");
                }
            }

            switch (userInput) {
                case 1:
                    findARoom();

                    break;
                case 2:
                    seeMyReservations();

                    break;
                case 3:
                    addNewCustomer();


                    break;
                case 4:
                    exit = true;
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.adminMenu();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Thank you for your using our application.");

            }
        }
    }


    public static void printMenu(){
        System.out.println("\n1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
    }
    // some method used in the findARoom()
    public static boolean checkFormat(String date){
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            System.out.println("date format is good.");
            return true;
        }
        catch (ParseException e){
        return false;
        }
    }
    public static Date getDate(String date, SimpleDateFormat dateFormat){
        try {
            return dateFormat.parse(date);
        }
        catch (ParseException e){
            System.out.println("Sorry cannot get date.");
        }
        return null;
    }
    public static Date getCheckIndate(Scanner scanner){
        boolean check = false;
        while (!check){
            try{
                System.out.println("check in date:");
                System.out.println("Please entry the date (Format: MM/DD/YYYY):");
                String userInput = scanner.nextLine();
                check = checkFormat(userInput);
                if (check){
                    return getDate(userInput, dateFormat);
                }
            }catch (IllegalArgumentException e){
                System.out.println("Invalid date format.");
            }
        }
        return null;
    }
    public static Date getCheckOutdate(Scanner scanner, Date checkInDate){
        boolean check =false;
        while (!check){
            try {
                System.out.println("check out date:");
                System.out.println("Please entry the date (Format: MM/DD/YYYY):");
                String userInput = scanner.nextLine();
                check = checkFormat(userInput);
                if (check){
                    Date checkOutDate = getDate(userInput,dateFormat);
                    if (checkOutDate.after(checkInDate)){
                        check = true;
                        //System.out.println(checkOutDate);
                        return checkOutDate;
                    }
                }
            }catch (IllegalArgumentException e){
                System.out.println("Invalid date format.");
            }
        }
        return null;
    }
    public static Customer checkCustomer(Scanner userInput) {
        boolean keep2 = false;
        boolean keep = false;
        do {
            keep = false;
            try {
                System.out.println("Do you have an account associated with our hotel? (y/n)");
                String accinput = userInput.nextLine();
                if (accinput.equals("y") || accinput.equals("n")) {
                    if (accinput.equalsIgnoreCase("y")) {
                        System.out.println("Please enter your email address:");
                        String useremail = userInput.nextLine();
                        if (hotelResource.getCustomer(useremail) == null) {
                            System.out.println("Sorry cannot find the account.");
                            return addNewCustomer();
                        } else {
                            return hotelResource.getCustomer(useremail);
                        }
                    } else if (accinput.equalsIgnoreCase("n")) {

                        do {
                            keep2 =false;
                            try {
                                System.out.println("Would you like to set up an account with us?(y/n)");
                                String accSetInput = userInput.nextLine();
                                if (accSetInput.equals("y") || accSetInput.equals("n")) {
                                    if (accSetInput.equalsIgnoreCase("y")) {
                                        return addNewCustomer();
                                    }
                                    if (accSetInput.equalsIgnoreCase("n")) {
                                        return null;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Please enter 'y' or 'n'!");
                                keep2 = true;
                            }
                        }while (keep2);
                    }
                }
            }
            catch(Exception e){
                System.out.println("Please enter 'y' or 'n'!");
                keep = true;
            }
        } while (keep);

        return null;
    }



/*        if (!accinput.equals("y")&& !accinput.equals("n")){
        System.out.println("Please enter 'y' or 'n'!");}

        if (accinput.equalsIgnoreCase("y")){
            System.out.println("Please enter your email address:");
            String useremail = userInput.nextLine();
            if (hotelResource.getCustomer(useremail)==null) {
                System.out.println("Sorry cannot find the account.");
                return addNewCustomer();
            }
            else {
                return hotelResource.getCustomer(useremail);
            }
        }
        else if (accinput.equalsIgnoreCase("n")){
                System.out.println("Would you like to set up an account with us?(y/n)");
                String accSetInput = userInput.nextLine();
            if (!accSetInput.equals("y")&& !accSetInput.equals("n")){
                throw new IllegalArgumentException("Please enter 'y' or 'n'!");}
            if (accSetInput.equalsIgnoreCase("y")){
                    return addNewCustomer();
                }
            }
        return null;
    }*/




    // Find a room and reserve it
    public static void findARoom(){
        boolean keep = false;
        Scanner userInput = new Scanner(System.in);
        Date checkInDate = getCheckIndate(userInput);
        //check data
        System.out.println(checkInDate);
        Date checkOutDate = getCheckOutdate(userInput,checkInDate);
        System.out.println(checkOutDate);
        Customer customer = checkCustomer(userInput);
        Collection<IRoom> availableRoom = new ArrayList<>(hotelResource.findARoom(checkInDate,checkOutDate));
        for (IRoom aRoom : availableRoom){
            System.out.println(aRoom);
        }
        if (availableRoom.isEmpty()){
            System.out.println("There is no available rooms,please use alternative dates.");
            Calendar newCheckInDate = new GregorianCalendar();
            Calendar newCheckOutDate = new GregorianCalendar();
            newCheckInDate.setTime(checkInDate);
            newCheckOutDate.setTime(checkOutDate);
            newCheckInDate.add(newCheckInDate.DATE,7);
            newCheckOutDate.add(newCheckOutDate.DATE,7);
            checkInDate = newCheckInDate.getTime();
            checkOutDate = newCheckOutDate.getTime();
            Collection<IRoom> newRooms = new ArrayList<>(hotelResource.findARoom(checkInDate,checkOutDate));
            if (newRooms.isEmpty()){
                System.out.println("Sorry, no rooms are found, please entry another date.");

            }
            else {
                System.out.println("Here are recommend rooms with 7-days delay");
                System.out.println("Dates: " + checkInDate + "-" + checkOutDate + "\n");
                for(IRoom room :newRooms) {
                    System.out.println(room);
                }
                do {
                    keep = false;
                    System.out.println("Please select one room:");
                    String userSelection = userInput.nextLine();
                    if (newRooms.contains(hotelResource.getRoom(userSelection))){
                        IRoom userRoom = hotelResource.getRoom(userSelection);
                        hotelResource.bookARoom(customer.getEmail(),userRoom,checkInDate,checkOutDate);
                        System.out.println("Room " + userRoom +" reserve success!");

                    }
                    else {
                        System.out.println("Sorry, please select one room from above.");
                        keep = true;
                    }

                } while (keep);
            }
        }
        else {
            do {
                keep = false;
                System.out.println("Please select one room:");
                String userSelection = userInput.nextLine();
                if (availableRoom.contains(hotelResource.getRoom(userSelection))){
                    IRoom userRoom = hotelResource.getRoom(userSelection);
                    hotelResource.bookARoom(customer.getEmail(),userRoom,checkInDate,checkOutDate);
                    System.out.println("Room " + userRoom +" reserve success!");

                }
                else {
                    System.out.println("Sorry, please select one room from above.");
                    keep = true;
                }

            } while (keep);
            }
        }




    // see my reservations
    public static Collection<Reservation> seeMyReservations(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter your email address:");
        String userEmail = userInput.nextLine();
        Customer customer = hotelResource.getCustomer(userEmail);
        //System.out.println(customer);
        if (customer == null){
            System.out.println("Sorry cannot find the account.");
        }
        else {
            Collection<Reservation> customerReservation = hotelResource.getCustomersReservations(customer.getEmail());
            if (customerReservation.isEmpty()){
                System.out.println("Sorry You do not have reservations.");
            }
            else {
                for (Reservation cusRes:customerReservation){
                    System.out.println(cusRes);
                }


                return customerReservation;
            }
        }
        return null;
    }
    // add new customer
    public static Customer addNewCustomer(){
        boolean keep = false;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your firstname:");
        String userFirstname = userInput.nextLine();
        System.out.println("Enter your Last");
        String userLastname = userInput.nextLine();
        do {
            keep = false;
            System.out.println("Enter your email: (format:name@domain.com)");
            String userEmail = userInput.nextLine();
            try {
                hotelResource.createACustmor(userEmail,userFirstname,userLastname);
                return hotelResource.getCustomer(userEmail);
            }catch (IllegalArgumentException ex){
                System.out.println(ex.getLocalizedMessage());
                keep = true;
            }

        }while (keep);
        return null;



    }
}
