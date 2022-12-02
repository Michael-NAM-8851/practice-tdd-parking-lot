package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StandardParkingBoyTest {

    @Test
    void should_return_ticket_when_park_given_a_parking_boy_and_parking_lot_and_a_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy parkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();

        //when
        Ticket ticket = parkingBoy.park(car);

        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_parked_car_when_fetch_car_given_parking_lot_with_parked_car_and_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Ticket ticket = standardParkingBoy.park(car);

        //when
        Car fetchedCar = standardParkingBoy.fetch(ticket);

        //then
        assertEquals(car, fetchedCar);
    }

    @Test
    void should_return_right_car_when_fetch_car_twice_given_parking_lot_with_two_parked_cars_and_two_ticket() {
        //given
        ParkingLot parkingLot =new ParkingLot();
        Car aliceCar = new Car();
        Car bobCar = new Car();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Ticket aliceTicket = standardParkingBoy.park(aliceCar);
        Ticket bobTicket = standardParkingBoy.park(bobCar);

        //when
        Car fetchedAliceCar = standardParkingBoy.fetch(aliceTicket);
        Car fetchedBobCar = standardParkingBoy.fetch(bobTicket);

        //then
        assertEquals(aliceCar, fetchedAliceCar);
        assertEquals(bobCar, fetchedBobCar);
    }

    @Test
    void should_return_exception_with_error_message_when_fetch_car_given_parking_lot_wrong_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Ticket ticket = new Ticket();
        //when
        Exception exception = assertThrows(UnrecognizedTicketException.class,
                () -> standardParkingBoy.fetch(ticket)
        );
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());

    }

    @Test
    void should_return_exception_with_error_message_when_fetch_car_given_parking_lot_and_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();
        Ticket ticket = standardParkingBoy.park(car);
        standardParkingBoy.fetch(ticket);

        //when
        Exception exception = assertThrows(UnrecognizedTicketException.class,
                () -> standardParkingBoy.fetch(ticket)
        );

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }
}
