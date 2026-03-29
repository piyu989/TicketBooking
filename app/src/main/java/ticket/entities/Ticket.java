package ticket.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "ticketId")
public class Ticket {
    @JsonProperty("ticket_id")
    private String ticketId;
    @JsonProperty("user_id")
    private String userId;
    private String source;
    private String destination;
    @JsonProperty("date_of_travel")
    private Date date;
    private Train train;
    @JsonProperty("ticket_info")
    private String ticketInfo;
}
