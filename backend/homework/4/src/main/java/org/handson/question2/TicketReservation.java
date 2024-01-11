package org.handson.question2;
import java.util.*;
import java.util.stream.Colectors;

import org.handson.MyLogger;
public class TicketReservation {
 
    private static final int CONFIRMEDLIST_LIMIT = 10;
    private static final int WAITINGLIST_LIMIT = 3;
    
    private List<Passenger> confirmedList = new ArrayList<>();
    private Deque<Passenger> waitingList = new ArrayDeque<>();
 
    /**
     * Books a flight for a passenger.
     *
     * @param  firstName         the first name of the passenger
     * @param  lastName          the last name of the passenger
     * @param  age               the age of the passenger
     * @param  gender            the gender of the passenger
     * @param  travelClass       the travel class of the passenger
     * @param  confirmationNumber   the confirmation number of the flight
     * @return                   true if the flight is successfully booked, false otherwise
     */
    public boolean bookFlight(String firstName, String lastName, int age, String gender, String travelClass, String confirmationNumber) {
        if(confirmedList.size()>CONFIRMEDLIST_LIMIT)
        {
            if(waitingList.size()>WAITINGLIST_LIMIT)
            {
                return false;
            }
            else
            {
                waitingList.add(new Passenger(firstName, lastName, age, gender, travelClass, confirmationNumber));
            }
        }
        return true;
     }  
    
    /**
     * Cancels a reservation based on the provided confirmation number.
     *
     * @param  confirmationNumber  the confirmation number of the reservation to cancel
     * @return                     true if the reservation was successfully cancelled, false otherwise
     */
    public boolean cancel(String confirmationNumber) {
        List<String> confirmationNumberList = confirmedList.stream().map(n-> n.getConfirmationNumber()).toList();
                List<String> confirmationNumberWaitingList = confirmedList.stream().map(n-> n.getConfirmationNumber()).toList();

        if(confirmationNumberList.contains(confirmationNumber))
        {
          boolean isPassengerRemoved =  removePassenger(confirmedList.iterator(), confirmationNumber);
         if(isPassengerRemoved)
          {
                    Passenger headWaiter = waitingList.poll();
                    confirmedList.add(headWaiter);
                    MyLogger.customLogger("Head at waiting list updated: "+headWaiter.getFirstName()+" "+headWaiter.getLastName(),"DEBUG");
                    return true;
          }
          else
          {
                                MyLogger.customLogger("Error in cancelling - Passenger not found in confirmation list","ERROR");

            return false;
          }

        }
        else if(confirmationNumberWaitingList.contains(confirmationNumber))
        {
            boolean isPassengerRemoved =  removePassenger(waitingList.iterator(), confirmationNumber);
            if(isPassengerRemoved)
            {
                MyLogger.customLogger("Passenger removed from waiting list: Passenger not found in waiting list "+confirmationNumber,"DEBUG");
                return true;
            }
            else
            {
                MyLogger.customLogger("Error in cancelling: User not found in any list", "ERROR");
                return false;
            }
        
        }
        else
        {
                        

            return false;
        }
    }
 
    /**
     * Removes a passenger from an iterator based on their confirmation number.
     *
     * @param  iterator           the iterator containing the passengers
     * @param  confirmationNumber the confirmation number of the passenger to be removed
     * @return                    true if the passenger was successfully removed, false otherwise
     */
    public boolean removePassenger(Iterator<Passenger> iterator, String confirmationNumber) {
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            if (passenger.getConfirmationNumber().equals(confirmationNumber)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
