package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

	private final TicketPaymentService paymentService;
    private final SeatReservationService reservationService;
    private static final int MAX_TICKETS_PER_PURCHASE = 20;
    private final int ADULT_TICKET_PRICE = 20;
    private final int CHILD_TICKET_PRICE = 10;
    
    private TicketServiceImpl(TicketPaymentService paymentService, SeatReservationService reservationService) {
        this.paymentService = paymentService;
        this.reservationService = reservationService;
    }
    
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        // Ensure an Adult ticket is being purchased for each Child or Infant ticket
    	int numAdultTickets = 0;
        int numChildTickets = 0;
        int numInfantTickets = 0;
        for (TicketTypeRequest ticket : ticketTypeRequests) {
            if (ticket.getTicketType() == TicketTypeRequest.Type.ADULT) {
            	numAdultTickets +=ticket.getNoOfTickets();
            } else if (ticket.getTicketType() == TicketTypeRequest.Type.CHILD) {
                numChildTickets +=ticket.getNoOfTickets();
            } else if (ticket.getTicketType() == TicketTypeRequest.Type.INFANT) {
                numInfantTickets +=ticket.getNoOfTickets();
            }
        }
        // Ensure no more than MAX_TICKETS_PER_PURCHASE tickets are being purchased
        if (numAdultTickets + numChildTickets > MAX_TICKETS_PER_PURCHASE) {
            throw new InvalidPurchaseException("No more than " + MAX_TICKETS_PER_PURCHASE + " tickets can be purchased at once.");
        }
        if (numChildTickets > 0 && numAdultTickets == 0) {
            throw new InvalidPurchaseException("An Adult ticket must be purchased for each Child ticket.");
        }
        if (numInfantTickets > numAdultTickets) {
            throw new InvalidPurchaseException("No more than one Infant can be allocated per Adult.");
        }
        int totalPrice = 0;
        int numSeatsToReserve = numAdultTickets + numChildTickets;
        totalPrice = calculateTicketPrice(TicketTypeRequest.Type.ADULT, numAdultTickets)
                + calculateTicketPrice(TicketTypeRequest.Type.CHILD, numChildTickets);
        try {
			// Make the payment request to the TicketPaymentService
			paymentService.makePayment(accountId, totalPrice);
			
			// Make the seat reservation request to the SeatReservationService
			reservationService.reserveSeat(accountId, numSeatsToReserve);
		} catch (Exception ex) {
			// To-Do
			// log the exception ex for debug purpose then throw meaningful error to user.
			throw new InvalidPurchaseException("Seats could not be booked due to Technical Fault, any deduction in payment will be credited back shortly");
		}
    
    }

	private int calculateTicketPrice(Type type, int quantity) {
		int ticketPrice;
        switch (type) {
            case ADULT:
                ticketPrice = ADULT_TICKET_PRICE;
                break;
            case CHILD:
                ticketPrice = CHILD_TICKET_PRICE;
                break;
            case INFANT:
                ticketPrice = 0; // future we can change due to inflation
                break;
            default:
                throw new IllegalArgumentException("Invalid ticket type");
        }

        return ticketPrice * quantity;
	}

}
