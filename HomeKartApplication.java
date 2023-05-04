package sample;

import java.util.*;

public class HomeKartApplication {
    
    static List<User> users = new ArrayList<>();
    static List<Property> properties = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    
    static class User {
        String name;
        String contact;
        User(String name, String contact) {
            this.name = name;
            this.contact = contact;
        }
		@Override
		public String toString() {
			return "User [name=" + name + ", contact=" + contact + "]";
		}
        
    }
    
    static class Property {
        User user;
        String type;
        String address;
        String location;
        int rooms;
        double price;
        double securityDeposit;
        String dealType;
        Property(User user, String type, String address, String location, int rooms, double price, double securityDeposit, String dealType) {
            this.user = user;
            this.type = type;
            this.address = address;
            this.location = location;
            this.rooms = rooms;
            this.price = price;
            this.securityDeposit = securityDeposit;
            this.dealType = dealType;
        }
		@Override
		public String toString() {
			return "Property [user=" + user + ", type=" + type + ", address=" + address + ", location=" + location
					+ ", rooms=" + rooms + ", price=" + price + ", securityDeposit=" + securityDeposit + ", dealType="
					+ dealType + "]";
		}
        
    }
    
    static class Booking {
        Property property;
        User user;
		Booking(Property property, User user) {
            this.property = property;
            this.user = user;
        }
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		@Override
		public String toString() {
			return "Booking [property=" + property + ", user=" + user + "]";
		}
        
    }
    
    public static void main(String[] args) {
        // add some users
        User user1 = add_user("Gaurav", "1234567890");
        User user2 = add_user("Saar", "9876543210");
        User user3 = add_user("John", "567898765");
        User user4 = add_user("Amit", "9876543210");
        User user5 = add_user("Sam", "234567898");
        User user6 = add_user("Eric", "976541234");
        
        // add some properties
        Property prop1 = add_property(user2, "Flat", "123 Main St", "New York", 2, 1500.0, 1000.0, "Rent");
        Property prop2 = add_property(user2, "Independent House", "456 Elm St", "Los Angeles", 3, 2000.0, 1500.0, "Sale");
        Property prop3 = add_property(user2, "Flat", "123 Main St", "New York", 2, 1500.0, 1000.0, "Rent");
        Property prop4 = add_property(user4, "Independent House", "456 Elm St", "Los Angeles", 3, 2000.0, 1500.0, "Sale");
        Property prop5 = add_property(user1, "Flat", "123 Main St", "New York", 2, 1500.0, 1000.0, "Rent");
        Property prop6 = add_property(user1, "Independent House", "123 Main St", "New York", 2, 1500.0, 1000.0, "Rent");
        
        // search for properties
        List<Property> results = search_property(Arrays.asList("Rent", "New York"));
        for(Property prop : results) {
            System.out.println(prop);
        }
        
        // book a property
        Booking booking1 = book_property(prop1, user2);
        Booking booking2 = book_property(prop2, user2);
        Booking booking3 = book_property(prop3, user2);
        Booking booking4 = book_property(prop5, user4);
        Booking booking5 = book_property(prop4, user5);     
        
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking1);
        bookings.add(booking2);
        bookings.add(booking3);
        bookings.add(booking4);
        bookings.add(booking5);
        
        
        // show bookings for a user
        List<Booking> userBookings = show_bookings(user2);
        for(Booking books : userBookings){
        	System.out.println(books);
        }
        
        // cancel a booking
        boolean status = cancel_booking(user2);
        System.out.println(status ? "Booking deleted successfully" : "No bookings found");
        
        List<Booking> userBookings2 = show_bookings(user2);
        for(Booking books : userBookings2){
        	System.out.println(books);
        }
        
    }
    
    
    //Cancel bookings
    private static boolean cancel_booking(User user) {
    	for (Booking booking : bookings) {
            if (booking.getUser().equals(user)) {
                bookings.remove(booking);
                return true;
            }
        }
        return false;
	}

    
    //add new user
	public static User add_user(String name, String contact) {
        User user = new User(name, contact);
        users.add(user);
        return user;
    }
    
	
	//add new property
    public static Property add_property(User user, String type, String address, String location, int rooms, double price, double securityDeposit, String dealType) {
        Property property = new Property(user, type, address, location, rooms, price, securityDeposit, dealType);
        properties.add(property);
        return property;
    }
    
    
    //search for property
    public static List<Property> search_property(List<String> filters) {
        List<Property> results = new ArrayList<>();
        for (Property property : properties) {
            if (filters.contains(property.dealType) && filters.contains(property.location)) {
                results.add(property);
            }
        }
        return results;
    }
    
    
    // book property
    public static Booking book_property(Property property, User user) {
        Booking booking = new Booking(property, user);
        bookings.add(booking);
        return booking;
    }
    
    
    // show booking for a perticular user
    public static List<Booking> show_bookings(User user) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.user == user) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }
}
    
   
