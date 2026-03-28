package ticket.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Ticket {
    private String ticketId;
    private String userId;
    private String source;
    private String destination;
    private Date date;
    private Train train;
}
