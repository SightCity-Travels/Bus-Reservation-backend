package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Bus;
import com.lti.entity.Passenger;
import com.lti.entity.Ticket;
import com.lti.entity.User;

public interface BusReservationService {

	public User registerOrUpdateUser(User user); //1

//	public Bus  addOrUpdateBus(Bus bus); //2

	public boolean loginUser(int userId, String password); //3

//	public Passenger addOrUpdatePassenger(Passenger passenger);

	public String changePassword(int userId, String password); //4

	public Ticket bookATicket(Ticket ticket); //5

	public List<Bus> searchBus(String source, String destination); //6

	public Bus chooseBus(int busId); //7
	
	public List<String> fetchBookedSeats(LocalDate travelDate, int busId); //8
	
	public List<Object[]> frequentlyTravelledRoute(); //9
	
	public List<Bus> viewAllBuses(); //10
	
	public List<User> viewAllRegsiteredCustomers(); //11
	
	public List<User> viewCustomerWhoRegisteredButwithNoBooking(); //12
	
//  public Bus updateRoute(int busId, String source, String destination);
	
	public User rechargeWallet(int userId,int rechargeAmount); //13
	
	public Ticket ticketDetails(int ticketId); //14
	
	public String payThroughWallet(int userId, double amount); //15
	
	public List<Integer> mostPreferredBus(); //16
	
	// public List<Ticket> bookingsBasedOnPeriod(int choice, LocalDate travelDate, int month);
	
	public String cancelTicket(int ticketId); //17
	
	public List<Ticket> viewTicketBookedByUserId(int userId); //18
	
	public User findUser(int userId);
	
	public Boolean loginAdmin(int adminId, String password);
	
	public void sendEmail(User user);
	
	public List<Passenger> getPassenger(int ticketId);
	
	public Bus getBus(int ticketId);
	
	public Bus addBus(Bus bus); //2

	public Bus updateBus(int busId, String source, String destination, double fare);
}
