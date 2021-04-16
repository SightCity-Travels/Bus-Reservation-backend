package com.lti.resource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Admin;
import com.lti.entity.BookTicket;
import com.lti.entity.Bus;
import com.lti.entity.ChangePasswordDto;
import com.lti.entity.Passenger;
import com.lti.entity.Ticket;
import com.lti.entity.TicketDetailsDto;
import com.lti.entity.User;
import com.lti.entity.loginDto;
import com.lti.service.BusReservationService;

@RestController
@CrossOrigin
public class BusReservationController {

	@Autowired
	BusReservationService busService;
	
	//http://localhost:9090/registerorupdateuser
	@PostMapping(value="/registerorupdateuser")
	public User registerOrUpdateUser(@RequestBody User user) {
        //		User user= new User();
		/*
		 * DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 * user.setDateOfBirth(LocalDate.parse(userdto.getDateOfBirth() , formatter));
		 * user.setFirstName(userdto.getFirstName());
		 * user.setLastName(userdto.getLastName()); user.setEmail(userdto.getEmail());
		 * user.setGender(userdto.getGender()); user.setPassword(user.getPassword());
		 * user.setContactNo(userdto.getContactNo());
		 * user.setAddress(userdto.getAddress()); user.setWallet(userdto.getWallet());
		 */
		User userPersisted =  busService.registerOrUpdateUser(user);
		return userPersisted;
	}
	
	//http://localhost:9090/addorupdatebus
	@PostMapping(value="/addorupdatebus")
	public Bus  addOrUpdateBus(@RequestBody Bus bus) {
	
		Bus busPersisted= busService.addOrUpdateBus(bus);
		return busPersisted;
	}

	//http://localhost:9090/login
	@GetMapping(value="/login")
	public User login(@RequestBody loginDto dto) {
		
		User userPersisted = null;
		
		
		userPersisted= busService.loginUser(dto.getid(), dto.getPassword());
		
		return userPersisted;
	}
	
	//http://localhost:9090/loginadmin
	@GetMapping(value="/loginadmin")
	public Admin loginAdmin(@RequestBody loginDto dto) {
			
			Admin adminPersisted = null;
			
			adminPersisted= busService.loginAdmin(dto.getid(), dto.getPassword());
			
			return adminPersisted;
		}

	
	//http://localhost:9090/changepassword
	@PutMapping(value="/changepassword")
	public String changePassword(@RequestBody ChangePasswordDto dto) {
		
		String result = busService.changePassword(dto.getUserId(),dto.getPassword());
		 return result; 
		 
	}

	//http://localhost:9090/bookaticket
	@PostMapping(value="/bookaticket")
	public Ticket bookATicket(@RequestBody BookTicket bookTicket , @RequestParam("userId") int userId, @RequestParam("busId") int busId) {
		
		Ticket ticket = bookTicket.getTicket();
		 
	
		Bus bus = busService.chooseBus(busId);
		ticket.setBus(bus);
		
		User user = busService.findUser(userId);
		ticket.setUser(user);
		
		List<Passenger> passengers = bookTicket.getPassengers();
		

		ticket.setPassengers(passengers);
		
		for (int i = 0; i < passengers.size(); i++) {
			passengers.get(i).setTicket(ticket);
		}
		
		return busService.bookATicket(ticket);
	}

	
	//http://localhost:9090/searchbus
	@GetMapping(value="/searchbus")
	public List<Bus> searchBus(@RequestParam("source") String source,@RequestParam("destination") String destination) {
		// TODO Auto-generated method stub
		return busService.searchBus(source, destination);
	}

	
	/*
	 * public Bus chooseBus(int busId) { // TODO Auto-generated method stub return
	 * busService.chooseBus(busId); }
	 */

	
	//http://localhost:9090/fetchbookedseats
	@GetMapping(value="/fetchbookedseats")
	public List<Passenger> fetchBookedSeats(@RequestParam("travelDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate, @RequestParam("busId") 
	int busId) {
		// TODO Auto-generated method stub
		return busService.fetchBookedSeats(travelDate, busId);
	}

	//http://localhost:9090/frequentlytravelledroute
	@GetMapping(value="/frequentlytravelledroute")
	public List<Object[]> frequentlyTravelledRoute() {
		// TODO Auto-generated method stub
		return busService.frequentlyTravelledRoute();
	}

	//http://localhost:9090/viewallbuses
	@GetMapping(value="/viewallbuses")
	public List<Bus> viewAllBuses() {
		// TODO Auto-generated method stub
		return busService.viewAllBuses();
	}

	//http://localhost:9090/viewallregsiteredcustomers
	@GetMapping(value="/viewallregsiteredcustomers")
	public List<User> viewAllRegsiteredCustomers() {
		// TODO Auto-generated method stub
		return busService.viewAllRegsiteredCustomers();
	}

	//http://localhost:9090/viewcustomerwhoregisteredbutwithnobooking
	@GetMapping(value="/viewcustomerwhoregisteredbutwithnobooking")
	public List<User> viewCustomerWhoRegisteredButwithNoBooking() {
		// TODO Auto-generated method stub
		return busService.viewCustomerWhoRegisteredButwithNoBooking();
	}

	
	//http://localhost:9090/rechargeWallet
	@PutMapping(value="/rechargeWallet")
	public User rechargeWallet(@RequestParam("userId") int userId,@RequestParam("rechargeAmount") int rechargeAmount) {
		// TODO Auto-generated method stub
		return busService.rechargeWallet(userId, rechargeAmount);
	}

	
	//http://localhost:9090/ticketDetails
	@GetMapping(value="/ticketDetails")
	public List<Object[]> ticketDetails(@RequestParam("ticketId") int ticketId) {
		// TODO Auto-generated method stub
		return busService.ticketDetails(ticketId);
	}

	//http://localhost:9090/paythroughwallet
	@GetMapping(value="/paythroughwallet")
	public String payThroughWallet(@RequestParam("userId") int userId,@RequestParam("amount") double amount) {
		// TODO Auto-generated method stub
		return busService.payThroughWallet(userId, amount);
	}

	//http://localhost:9090/mostpreferredbus
	@GetMapping(value="/mostpreferredbus")
	public List<Integer> mostPreferredBus() {
		// TODO Auto-generated method stub
		return busService.mostPreferredBus();
	}

    //http://localhost:9090/cancelticket
	@DeleteMapping(value="/cancelticket")
	public String cancelTicket(@RequestParam("ticketId") int ticketId) {
		// TODO Auto-generated method stub
		return busService.cancelTicket(ticketId);
	}

    //http://localhost:9090/viewticketbookedbyuserid
	@GetMapping(value="/viewticketbookedbyuserid")
	public List<Ticket> viewTicketBookedByUserId(@RequestParam("userId") int userId) {
		// TODO Auto-generated method stub
		return busService.viewTicketBookedByUserId(userId);
	}

}
