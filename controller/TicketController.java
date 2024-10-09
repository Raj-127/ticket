package com.example.ticketbooking.controller;

import com.example.ticketbooking.model.Ticket;
import com.example.ticketbooking.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket) {
        Ticket savedTicket = ticketService.bookTicket(ticket);
        return ResponseEntity.ok(savedTicket);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadTicket(@PathVariable Long id) throws IOException {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }

        // Generate a simple PDF
        byte[] pdfBytes = generateTicketPdf(ticket);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "ticket_" + id + ".pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    private byte[] generateTicketPdf(Ticket ticket) throws IOException {
        // Simple PDF generation logic
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(("Ticket ID: " + ticket.getId() + "\n").getBytes());
        baos.write(("Name: " + ticket.getUserName() + "\n").getBytes());
        baos.write(("Event: " + ticket.getEvent() + "\n").getBytes());
        baos.write(("Seat Number: " + ticket.getSeatNumber() + "\n").getBytes());
        return baos.toByteArray();
    }
}
