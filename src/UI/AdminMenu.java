package UI;

import api.AdminResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.*;

public class AdminMenu {
    public static AdminResource adminResource = AdminResource.getInstance();
    boolean exit;
    public static void main(String[] args){
        AdminMenu menu =new  AdminMenu();
        menu.runadminMenu();
    }
    public static void adminMenu() {
        AdminMenu menu = new AdminMenu();
        menu.runadminMenu();
    }
    public void runadminMenu(){
        while (!exit){
            printAdminHead();
            printAdminMenu();

            int choise = getInput();
            action(choise);
        }
    }
    private void printAdminHead(){
        System.out.println("\nWelcome to the Hotel Admin menu:");
    }
    private void printAdminMenu() {
        System.out.println("\n1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
    }
    private int getInput(){
        Scanner key = new Scanner(System.in);
        int choice = -1;
        while (choice <0 || choice >5){
            try {
                System.out.println("\nEnter a number:");
                choice = Integer.parseInt(key.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("Invalid selection");
            }
        }
        return choice;
    }
    private void action(int choice){
        switch (choice){
            case 1:
                showAllCustomers();
                break;
            case 2:
                showAllRooms();
                break;
            case 3:
                adminResource.displayAllReservations();
                break;
            case 4:
                addRooms();
                break;
            case 5:
                exit = true;
                MainMenu.runMenu();
                break;
            default:
                break;
        }
    }
    private static void showAllCustomers(){
        HashSet<Customer> customerHashSet = (HashSet<Customer>) adminResource.getAllCustomers();
        for (Customer customer :customerHashSet){
            System.out.println(customer);
            System.out.println("\n");
        }
    }
    private static void showAllRooms(){
        Collection<IRoom> roomCollection = adminResource.getAllRooms();
        for (IRoom room : roomCollection){
            System.out.println(room);
            System.out.println("\n");
        }
    }

    private static void addRooms(){
        String moreRoom;
        int type;
        List<IRoom> rooms = new ArrayList<>();
        do {
            Scanner input = new Scanner(System.in);
            RoomType roomType = null;
            System.out.println("Enter room number:");
            String roomNumber = input.nextLine();
            System.out.println("Enter price per night:");
            Double roomPrice = input.nextDouble();
            do {
                System.out.println("Enter room type: 1 - Single bed, 2 - Double bed");
                type = input.nextInt();
                if (type == 1) {
                    roomType = RoomType.SINGLE;
                } else if (type == 2) {
                    roomType = RoomType.DOUBLE;
                } else {
                    System.out.println("Invalid input");
                }
            } while (type != 1 && type != 2);

            IRoom room = new Room(roomNumber,roomPrice,roomType);
            rooms.add(room);
            do {
                System.out.println("Would you like to add another room? y/n");
                moreRoom = input.next().toLowerCase().trim();
            } while (!moreRoom.equals("y")&&!moreRoom.equals("n"));
        } while (moreRoom.equals("y"));
        adminResource.addRoom(rooms);
    }

}
