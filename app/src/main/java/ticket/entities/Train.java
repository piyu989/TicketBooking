package ticket.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

@Data
public class Train {
    @JsonProperty("train_id")
    private String trainId;
    @JsonProperty("train_no")
    private String trainNo;
    @JsonProperty("seats")
    private List<List<Integer>> seats;
    @JsonProperty("station_times")
    private Map<String, String> stationTimes;
    @JsonProperty("stations")
    private List<String> station;
    @JsonProperty("train_info")
    private String trainInfo;
}
