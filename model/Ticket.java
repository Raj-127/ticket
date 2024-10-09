package com.example.ticketbooking.model;

import jakarta.persistence.*;
@Entity
@Table(name = "ticketinfo") // Ensure this matches your table name
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_seq")
    @SequenceGenerator(name = "ticket_seq", sequenceName = "ticket_seq", allocationSize = 1)
    private Long id;

    @Column(name = "USER_NAME") // Ensure this matches the column name
    private String userName;

    @Column(name = "EVENT")
    private String event;

    @Column(name = "SEAT_NUMBER")
    private String seatNumber;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}
