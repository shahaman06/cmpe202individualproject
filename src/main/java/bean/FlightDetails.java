package bean;

import CardValidator.ChainValidator;

import java.util.*;

public class FlightDetails {

    private String flightNum;
    private String departureCity;
    private String arrivalCity;
    private int ecoSeats;
    private int busSeats;
    private int pecoSeats;
    private int ecoSeatsCost;
    private int busSeatsCost;
    private int pecoSeatsCost;

    public FlightDetails() {
    }

    public FlightDetails(String flightNum, String departureCity, String arrivalCity) {
        this.flightNum = flightNum;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public int getEcoSeats() {
        return ecoSeats;
    }

    public void setEcoSeats(int ecoSeats) {
        this.ecoSeats = ecoSeats;
    }

    public int getBusSeats() {
        return busSeats;
    }

    public void setBusSeats(int busSeats) {
        this.busSeats = busSeats;
    }

    public int getPecoSeats() {
        return pecoSeats;
    }

    public void setPecoSeats(int pecoSeats) {
        this.pecoSeats = pecoSeats;
    }

    public int getEcoSeatsCost() {
        return ecoSeatsCost;
    }

    public void setEcoSeatsCost(int ecoSeatsCost) {
        this.ecoSeatsCost = ecoSeatsCost;
    }

    public int getBusSeatsCost() {
        return busSeatsCost;
    }

    public void setBusSeatsCost(int busSeatsCost) {
        this.busSeatsCost = busSeatsCost;
    }

    public int getPecoSeatsCost() {
        return pecoSeatsCost;
    }

    public void setPecoSeatsCost(int pecoSeatsCost) {
        this.pecoSeatsCost = pecoSeatsCost;
    }


    public static class FlightDetailsHashMap {

        HashMap<String , HashMap<String, List<Integer>>> flights;
        HashMap<BookingRequest, String> invalidBookings = new HashMap<>();

        public void initializeFlights(List<FlightDetails> flightDetailsList){


            flights = new HashMap<>() ;
            for(FlightDetails f : flightDetailsList){

                        flights.put(f.getFlightNum(),
                                new HashMap<String,List<Integer>>()
                                {
                                    {
                                        put("Economy",
                                                new ArrayList<>(Arrays.asList(f.getEcoSeats(),f.getEcoSeatsCost())));
                                        put("Premium Economy",
                                                new ArrayList<>(Arrays.asList(f.getPecoSeats(),f.getPecoSeatsCost())));
                                        put("Business",
                                                new ArrayList<>(Arrays.asList(f.getBusSeats(),f.getBusSeatsCost())));

                                    }});

            }

            // code to iterate over the flightDetailsList obtained from the csvHandler class and create our hashmap of flights

        }

        // method to calculate the cost of bookings
        // Also edit the hashMap database when a certain booking is made, just get the seat type and reduce
        // the number of available seats by 1


        public List<BookingDetails> validateBooking(List<BookingRequest> bookingRequests){

            List<BookingDetails> bookingDetailsist = new ArrayList<>();
    //        boolean isBookingValid = true;

            if(!bookingRequests.isEmpty()){

                for(BookingRequest bookingRequest : bookingRequests){

                    BookingDetails bookingDetails = new BookingDetails();

                    //check if the flight is a valid flight
                    if(flights.containsKey(bookingRequest.getFlightNum())){

                        //check if the number of requested seats are valid (i.e less than or equal to available seats)
                        if(bookingRequest.getCategory().equals("Economy")){

                            bookingDetails = validateBooking(bookingRequest, "Economy");

                            if(bookingDetails != null){
                                bookingDetailsist.add(bookingDetails);
                            }

                           }
                        else if(bookingRequest.getCategory().equals("Premium Economy")){
                            bookingDetails = validateBooking(bookingRequest, "Premium Economy");

                            if(bookingDetails != null){
                                bookingDetailsist.add(bookingDetails);
                            }
                        }
                        else if(bookingRequest.getCategory().equals("Business")){
                            bookingDetails = validateBooking(bookingRequest, "Business");

                            if(bookingDetails != null){
                                bookingDetailsist.add(bookingDetails);
                            }
                        }else{
    //                        isBookingValid = false;
                            invalidBookings.put(bookingRequest,"invalid category");
                        }

                    }else
    //                    isBookingValid = false;
                        invalidBookings.put(bookingRequest,"invalid flight number");

                }

            }


            return bookingDetailsist;
        }

        public BookingDetails validateBooking(BookingRequest bookingRequest ,String category){
            BookingDetails bookingDetails = new BookingDetails();

            if(flights.get(bookingRequest.getFlightNum()).containsKey(category)) {
    //                                  int availableSeats = flights.get(bookingRequest.getFlightNum()).get("Economy").get(0);
                if(flights.get(bookingRequest.getFlightNum()).get(category).get(0) >= bookingRequest.getNumberOfSeats()){

                    // now that flight is valid and has enough seats we calculate the total cost
                    bookingDetails.setTotalPrice(flights.get(bookingRequest.getFlightNum()).get(category).get(1) * bookingRequest.getNumberOfSeats() );


                    //after calculating the cost we need to check if the card number is valid
                    // we will call the cardValidation methods from here
                    CardDetails card = new CardDetails();
                    card.setCardNumber(bookingRequest.getCardNumber());
    //                card.setExpirationDate(new Date(2020,04,26));
                    card.setNameOfCardholder("John Doe");
                    ChainValidator chain = new ChainValidator();
                    boolean isValid  = chain.handleChain(card);


                    if(isValid){
                        //After the card validation we will add the booking details in the booking details object
                        bookingDetails.setFlightNum(bookingRequest.getFlightNum());
                        bookingDetails.setCategory(bookingRequest.getCategory());
                        bookingDetails.setName(bookingRequest.getName());
                        bookingDetails.setNumberOfSeats(bookingRequest.getNumberOfSeats());


                        // after the booking is made we need to reduce the booked number of seats from the category for that flight
                        flights.get(bookingRequest.getFlightNum()).get(category).set(0,flights.get(bookingRequest.getFlightNum()).get(category).get(0) -bookingDetails.getNumberOfSeats() ) ;



                    }else{
                        invalidBookings.put(bookingRequest,"invalid card number");
                        return null;
                    }


                }else{
                    invalidBookings.put(bookingRequest,"seats not available");
                    return null;
                }

            }else{
                invalidBookings.put(bookingRequest,"invalid category");
            }

            return bookingDetails;
        }

        public List<String> getInvalidBookings(){
            List<String> invalidBookingList= new ArrayList<>();


            Iterator it = invalidBookings.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                BookingRequest b = (BookingRequest)pair.getKey();
                invalidBookingList.add("Please enter correct booking details for "+ b.getName() + " : " + pair.getValue());

            }

            return invalidBookingList;
        }



    }
}
