package ticket.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@Data
@EqualsAndHashCode(of = "userId")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @JsonProperty("user_id")
    private String userId;
    private String name;
    @JsonProperty("tickets_booked")
    private List<Ticket> ticketBooked;
    private String password;
    @JsonProperty("hashed_password")
    private String hashedPassword;
}
