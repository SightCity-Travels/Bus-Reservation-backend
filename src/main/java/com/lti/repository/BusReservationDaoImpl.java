package com.lti.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.Bus;
import com.lti.entity.Passenger;
import com.lti.entity.Status;
import com.lti.entity.Ticket;
import com.lti.entity.User;

@Repository
public class BusReservationDaoImpl implements BusReservationDao {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public User registerOrUpdateUser(User user) {
		User userPersisted = em.merge(user);
		return userPersisted;
	}

	@Transactional
	public Bus addOrUpdateBus(Bus bus) {
		Bus busPersisted = em.merge(bus);
		return busPersisted;
	}

	public boolean loginUser(int userId, String password) {
		String jpql = "select u from User u where u.userId=:id and u.password=:pass";

		TypedQuery<User> query = em.createQuery(jpql, User.class);

		query.setParameter("id", userId);
		query.setParameter("pass", password);
		User user = null;
		try {
			user = query.getSingleResult();

		} catch (Exception e) {

		}
		if (user == null) {
			return false;
		}
		return true;
	}

	/*
	 * public Passenger addOrUpdatePassenger(Passenger passenger) {
	 * 
	 * tx.begin(); Passenger passengerPersisted = em.merge(passenger); tx.commit();
	 * return passengerPersisted; }
	 */

	@Transactional
	public boolean changePassword(int userId, String password) {
		String jpql = "select u from User u where u.userId=:uId";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		query.setParameter("uId", userId);
		User user = null;
		try {
			user = query.getSingleResult();
			user.setPassword(password);

			em.merge(user);

		} catch (Exception e) {
		}
		if (user == null) {
			return false;
		}
		return true;
	}

	@Transactional
	public Ticket bookATicket(Ticket ticket) {

		Ticket persistedTicket = em.merge(ticket);
		/*
		 * int ticketId = persistedTicket.getTicketId(); String jpql =
		 * "update ticket t set t.user.userId=:uid where t.ticketId=:tid"; String jpql2
		 * = "update ticket t set t.bus.busId=:bid where t.ticketId=:tid";
		 * 
		 * Query query = em.createQuery(jpql); Query query2 = em.createQuery(jpql2);
		 * query.setParameter("uid", userId); query2.setParameter("bid", busId);
		 * query.setParameter("tid",ticketId); query2.setParameter("tid",ticketId);
		 * query.executeUpdate(); query2.executeUpdate();
		 */
		return persistedTicket;

	}

	public List<Bus> searchBus(String source, String destination) {
		String jpql = " select b from Bus b where b.source=:s and b.destination=:d";

		TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
		query.setParameter("s", source);
		query.setParameter("d", destination);
		List<Bus> bus = query.getResultList();
		return bus;
	}

	public Bus chooseBus(int busId) {
		String jpql = "select b from Bus b where b.busId=:bid";
		TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
		query.setParameter("bid", busId);
		Bus bus = query.getSingleResult();
		return bus;

	}

	public List<String> fetchBookedSeats(LocalDate travelDate, int busId) {

		String jpql = "select p.seatNo from Passenger p where p.ticket.travelDate=:tvlDate and p.ticket.bus.busId=:bId";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("tvlDate", travelDate);
		query.setParameter("bId", busId);
		List<String> seatNo = query.getResultList();
		return seatNo;

	}

	public List<Bus> viewAllBuses() {
		String jpql = "select b from Bus b";

		TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
		List<Bus> buses = query.getResultList();

		return buses;
	}

	public List<User> viewAllRegsiteredCustomers() {
		String jpql = "select u from User u";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> users = query.getResultList();

		return users;

	}
	

	public List<Object[]> frequentlyTravelledRoute() {

		String jpql = "select count(*),t.bus.source,t.bus.destination from Ticket t group by t.bus.source,t.bus.destination order by count(*) desc";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		List<Object[]> routes = query.getResultList();
		return routes;
	}
	

	public List<User> viewCustomerWhoRegisteredButwithNoBooking() {
		String jpql = "select u from User u where u.userId not in (select nvl2(t.user.userId,t.user.userId,0) from Ticket t)";
		TypedQuery<User> query = em.createQuery(jpql, User.class);
		List<User> user = query.getResultList();
		return user;
	}

	/*
	 * public Bus updateRoute(int busId, String source, String destination) { String
	 * jqpl = "select b from Bus b where b.busId=:bid";
	 * 
	 * TypedQuery<Bus> query = em.createQuery(jqpl, Bus.class);
	 * query.setParameter("bid", busId); Bus bus = null;
	 * 
	 * bus = query.getSingleResult(); bus.setSource(source);
	 * bus.setDestination(destination); tx.begin(); em.merge(bus); tx.commit();
	 * 
	 * return bus;
	 * 
	 * }
	 */

	@Transactional
	public User rechargeWallet(int userId, int rechargeAmount) {
		User user = em.find(User.class, userId);
		user.setWallet(user.getWallet() + rechargeAmount);

		User user1 = em.merge(user);

		return user1;

	}

	public Ticket ticketDetails(int ticketId) {
		//String jpql = "select t , p from Ticket t , Passenger p where t.ticketId=:tid and p.ticket.ticketId=:tid";
		String jpql="select t from Ticket t where t.ticketId=:tid";
		TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
		query.setParameter("tid", ticketId);
		Ticket ticketdetails = query.getSingleResult();

		return ticketdetails;
	}

	@Transactional
	public String payThroughWallet(int userId, double amount) {

		User user = em.find(User.class, userId);
		if (user.getWallet() < amount) {
			return "Insufficient amount in wallet";
		} else {
			user.setWallet(user.getWallet() - amount);

			em.merge(user);

			return "Payment successful";
		}

	}

	public List<Integer> mostPreferredBus() {
		String jpql = "select t.bus.busId from Ticket t group by t.bus.busId order by count(*) desc";

		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		List<Integer> tickets = query.getResultList();

		return tickets;
	}

	public List<Ticket> bookingsBasedOnPeriod(int choice, LocalDate travelDate, int month) {
		if (choice == 1) {
			String jpql = "select t from Ticket t where t.travelDate=:td";
		} else if (choice == 2) {
			String jpql = "select t from Ticket t where month(t.travelDate)";
		}

		return null;
	}

	@Transactional
	public String cancelTicket(int ticketId) {
		Ticket ticket = em.find(Ticket.class, ticketId);
		if (ticket.getStatus() == Status.CANCELLED) {
			return "You cannot cancel this ticket, it is already cancelled";
		} else {
			ticket.setStatus(Status.CANCELLED);
			double refund = ticket.getTotalAmount();
			try {
				ticket.getUser().setWallet(ticket.getUser().getWallet() + refund);
			} catch (Exception e) {
				return "You need to register to cancel your ticket";
			}

			em.merge(ticket);

			String jpql = "delete from Passenger p where p.ticket.ticketId=:tid";
			Query query = em.createQuery(jpql);
			query.setParameter("tid", ticketId);

			query.executeUpdate();

		}

		return "Ticket Cancelled Successfully";

	}

	public List<Ticket> viewTicketBookedByUserId(int userId) {
		String jpql = "select t from Ticket t where t.user.userId=:uid";
		TypedQuery<Ticket> query = em.createQuery(jpql, Ticket.class);
		query.setParameter("uid", userId);
		List<Ticket> tickets = query.getResultList();
		return tickets;
	}

	@Transactional
	public User findUser(int userId) {

		User user = null;
		user = em.find(User.class, userId);

		if (Objects.isNull(user)) {
			return user;
		}

		return user;
	}

	
	public Boolean loginAdmin(int adminId, String password) {
		String jpql1 = "select a from Admin a where a.adminId=:id and a.password=:pass";
		TypedQuery<Admin> query = em.createQuery(jpql1, Admin.class);
		query.setParameter("id", adminId);
		query.setParameter("pass", password);
		Admin admin = null;
		try {
			admin = query.getSingleResult();

		} catch (Exception e) {

		}
		if (admin == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isCustomerPresent(String email) {
		return (Long) em.createQuery("select count(u.userId) from User u where u.email = :em").setParameter("em", email)
				.getSingleResult() == 1 ? true : false;
	}

	@Override
	public List<Passenger> getPassenger(int ticketId) {
		String jpql = "select  p from Passenger p where p.ticket.ticketId=:tid";
		TypedQuery<Passenger> query = em.createQuery(jpql, Passenger.class);
		query.setParameter("tid", ticketId);
		List<Passenger> passsengerList = query.getResultList();

		return passsengerList;
	}

	@Override
	public Bus getBus(int ticketId) {
		String jpql="select b from Bus b where b.busId =(select t.bus.busId from Ticket t where t.ticketId=:tid)";
		TypedQuery<Bus> query = em.createQuery(jpql, Bus.class);
		query.setParameter("tid", ticketId);
		Bus getBus = query.getSingleResult();
		
		return getBus;
		
	}

}
