//import org.junit.Before;
//import org.junit.Test;
//
//import thirdparty.paymentgateway.TicketPaymentService;
//import thirdparty.seatbooking.SeatReservationService;
//import uk.gov.dwp.uc.pairtest.TicketService;
//import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
//import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
//import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;

// To-Do the test class for TicketServiceImpl 
public class TicketServiceImplTest {
//    private TicketPaymentService paymentService;
//    private SeatReservationService reservationService;
//    private TicketService ticketService;
//    private long event;
//
//    @Before
//    public void setUp() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//        paymentService = mock(TicketPaymentService.class);
//        reservationService = mock(SeatReservationService.class);
//        
//        Constructor<TicketServiceImpl> constructor = TicketServiceImpl.class.getDeclaredConstructor();
//
//        // Set the accessibility flag to true
//        constructor.setAccessible(true);
//
//        // Create an instance of the class
//        TicketServiceImpl ticketService = constructor.newInstance(paymentService, reservationService);
//        // ticketService = new TicketServiceImpl(paymentService, reservationService);
//    }
//
//    @Test
//    public void testPurchaseTickets_ValidTickets_Success() {
//        // Arrange
//        List<TicketTypeRequest> tickets = new ArrayList<>();
//        tickets.add(new TicketTypeRequest(Type.ADULT, 1));
//        tickets.add(new TicketTypeRequest(Type.CHILD,1));
//        when(paymentService.makePayment(event,15)).thenReturn(true);
//        when(reservationService.reserveSeat(event, 1)).thenReturn(true);
//
//        // Act
//        boolean purchaseResult = ticketService.purchaseTickets(event, tickets);
//
//        // Assert
//        assertTrue(purchaseResult);
//        verify(paymentService, times(1)).makePayment(15.0);
//        verify(reservationService, times(1)).reserveSeats(event, 1);
//    }
//
//    @Test
//    public void testPurchaseTickets_TooManyTickets_Failure() {
//        // Arrange
//        List<TicketTypeRequest> tickets = new ArrayList<>();
//        for (int i = 0; i < 21; i++) {
//            tickets.add(new TicketTypeRequest(Type.ADULT, 1));
//        }
//
//        // Act
//        boolean purchaseResult = ticketService.purchaseTickets(event, tickets);
//
//        // Assert
//        assertFalse(purchaseResult);
//        verify(paymentService, never()).makePayment(anyDouble());
//        verify(reservationService, never()).reserveSeats(any(Event.class), anyInt());
//    }
//
//    @Test
//    public void testPurchaseTickets_NoAdultTickets_Failure() {
//        // Arrange
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(new Ticket(TicketType.CHILD));
//        tickets.add(new Ticket(TicketType.INFANT));
//
//        // Act
//        boolean purchaseResult = ticketService.purchaseTickets(event, tickets);
//
//        // Assert
//        assertFalse(purchaseResult);
//        verify(paymentService, never()).makePayment(anyDouble());
//        verify(reservationService, never()).reserveSeats(any(Event.class), anyInt());
//    }
//
//    @Test
//    public void testPurchaseTickets_TooManyChildTickets_Failure() {
//        // Arrange
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(new Ticket(TicketType.ADULT));
//        tickets.add(new Ticket(TicketType.CHILD));
//        tickets.add(new Ticket(TicketType.CHILD));
//        tickets.add(new Ticket(TicketType.CHILD));
//
//        // Act
//        boolean purchaseResult = ticketService.purchaseTickets(event, tickets);
//
//        // Assert
//        assertFalse(purchaseResult);
//        verify(paymentService, never()).makePayment(anyDouble());
//        verify(reservationService, never()).reserveSeats(any(Event.class), anyInt());
//    }
//
//    @Test
//    public void testPurchaseTickets_TooManyInfantTickets_Failure() {
//        // Arrange
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(new Ticket(TicketType.ADULT));
//        tickets.add(new Ticket(TicketType.INFANT));
//        tickets.add(new Ticket(TicketType.INFANT));
//
//        // Act
//        boolean purchaseResult = ticketService.purchaseTickets(event, tickets);
//
//        // Assert
//        assertFalse(purchaseResult);
//        verify(paymentService, never()).makePayment(anyDouble());
//}
//    
//    @Test
//    void testPurchaseTicketsWithInvalidAge() {
//        List<TicketRequest> ticketRequests = new ArrayList<>();
//        ticketRequests.add(new TicketRequest(TicketType.ADULT, 1));
//        ticketRequests.add(new TicketRequest(TicketType.CHILD, 1, 2)); // Invalid age
//        ticketRequests.add(new TicketRequest(TicketType.INFANT, 1, 10)); // Invalid age
//        PurchaseResult result = ticketService.purchaseTickets(ticketRequests);
//        assertFalse(result.isSuccess());
//        assertEquals(2, result.getErrors().size());
//        assertTrue(result.getErrors().contains("Child ticket age must be between 2 and 18"));
//        assertTrue(result.getErrors().contains("Infant ticket age must be under 2"));
//    }
//
//    @Test
//    void testPurchaseTicketsWithInfantTicketOnly() {
//        List<TicketRequest> ticketRequests = new ArrayList<>();
//        ticketRequests.add(new TicketRequest(TicketType.INFANT, 1));
//        PurchaseResult result = ticketService.purchaseTickets(ticketRequests);
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getErrors().size());
//        assertTrue(result.getErrors().contains("Infant ticket must be purchased with an adult ticket"));
//    }
//
//    @Test
//    void testPurchaseTicketsWithChildTicketOnly() {
//        List<TicketRequest> ticketRequests = new ArrayList<>();
//        ticketRequests.add(new TicketRequest(TicketType.CHILD, 1, 5));
//        PurchaseResult result = ticketService.purchaseTickets(ticketRequests);
//        assertFalse(result.isSuccess());
//        assertEquals(1, result.getErrors().size());
//        assertTrue(result.getErrors().contains("Child ticket must be purchased with an adult ticket"));
//    }
//
//    @Test
//    void testPurchaseTicketsWithValidTickets() {
//        List<TicketRequest> ticketRequests = new ArrayList<>();
//        ticketRequests.add(new TicketRequest(TicketType.ADULT, 2));
//        ticketRequests.add(new TicketRequest(TicketType.CHILD, 1, 10));
//        PurchaseResult result = ticketService.purchaseTickets(ticketRequests);
//        assertTrue(result.isSuccess());
//        assertEquals(new BigDecimal("50.00"), result.getTotalPrice());
//        assertEquals(3, result.getSeatIds().size());
//        verify(paymentService).makePayment(any(BigDecimal.class)); // Verify payment service was called
//        verify(reservationService).reserveSeats(anyInt(), anyList()); // Verify reservation service was called
//    }
//
}
