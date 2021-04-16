package com.lti.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Admin;
import com.lti.entity.Bus;
import com.lti.entity.Passenger;
import com.lti.entity.Ticket;
import com.lti.entity.User;
import com.lti.repository.BusReservationDao;
import com.lti.repository.BusReservationDaoImpl;

@Service
public class BusReservationServiceImpl implements BusReservationService {

	@Autowired
	BusReservationDao busDao;
	
	public User registerOrUpdateUser(User user) {
		// TODO Auto-generated method stub
		return busDao.registerOrUpdateUser(user);
	}

	public Bus  addOrUpdateBus(Bus bus) {
		// TODO Auto-generated method stub
		return busDao. addOrUpdateBus(bus);
	}

	public User loginUser(int userId, String password) {
		// TODO Auto-generated method stub
		return busDao.loginUser(userId, password);
	}

	public String changePassword(int userId, String password) {
		// TODO Auto-generated method stub
		return busDao.changePassword(userId, password);
	}

	public Ticket bookATicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return busDao.bookATicket(ticket);
	}

	public List<Bus> searchBus(String source, String destination) {
		// TODO Auto-generated method stub
		return busDao.searchBus(source, destination);
	}

	public Bus chooseBus(int busId) {
		// TODO Auto-generated method stub
		return busDao.chooseBus(busId);
	}

	public List<Passenger> fetchBookedSeats(LocalDate travelDate, int busId) {
		// TODO Auto-generated method stub
		return busDao.fetchBookedSeats(travelDate, busId);
	}

	public List<Object[]> frequentlyTravelledRoute() {
		// TODO Auto-generated method stub
		return busDao.frequentlyTravelledRoute();
	}

	public List<Bus> viewAllBuses() {
		// TODO Auto-generated method stub
		return busDao.viewAllBuses();
	}

	public List<User> viewAllRegsiteredCustomers() {
		// TODO Auto-generated method stub
		return busDao.viewAllRegsiteredCustomers();
	}

	public List<User> viewCustomerWhoRegisteredButwithNoBooking() {
		// TODO Auto-generated method stub
		return busDao.viewCustomerWhoRegisteredButwithNoBooking();
	}

	public User rechargeWallet(int userId, int rechargeAmount) {
		// TODO Auto-generated method stub
		return busDao.rechargeWallet(userId, rechargeAmount);
	}

	public List<Object[]> ticketDetails(int ticketId) {
		// TODO Auto-generated method stub
		return busDao.ticketDetails(ticketId);
	}

	public String payThroughWallet(int userId, double amount) {
		// TODO Auto-generated method stub
		return busDao.payThroughWallet(userId, amount);
	}

	public List<Integer> mostPreferredBus() {
		// TODO Auto-generated method stub
		return busDao.mostPreferredBus();
	}

	public String cancelTicket(int ticketId) {
		// TODO Auto-generated method stub
		return busDao.cancelTicket(ticketId);
	}

	public List<Ticket> viewTicketBookedByUserId(int userId) {
		// TODO Auto-generated method stub
		return busDao.viewTicketBookedByUserId(userId);
	}

	@Override
	public User findUser(int userId) {
		
		return busDao.findUser(userId);
	}

	@Override
	public Admin loginAdmin(int adminId, String password) {
		// TODO Auto-generated method stub
		return busDao.loginAdmin(adminId, password);
	}

}
