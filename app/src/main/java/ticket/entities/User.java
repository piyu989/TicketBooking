package ticket.entities;

import lombok.Data;

import java.util.List;
@Data
public class User {
    private String userId;
    private String name;
    private List<Ticket> ticketBooked;
    private String password;
    private String hashpassword;
}
