package ticket.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.entities.Train;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class TrainService {
    private List<Train> trains;
    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String TRAIN_PATH = "app/src/main/java/ticket/localDB/trains.json";
    public TrainService() {
        try{
            File file = new File(TRAIN_PATH);
            trains = objectMapper.readValue(file, new TypeReference<List<Train>>() {});
            System.out.println("trains loaded successfully "+trains);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void searchTrain(String source,String destination){
        List<Train> searchTrains = trains.stream().filter(t -> t.getStation().contains(source) &&
                t.getStation().contains(destination)).collect(Collectors.toList());
        if (!searchTrains.isEmpty()){
//            AtomicBoolean flag = new AtomicBoolean(true);
            searchTrains.forEach(train -> {
                int sourceIdx = train.getStation().indexOf(source);
                int destinationIdx = train.getStation().indexOf(destination);
                if (sourceIdx != -1 && destinationIdx != -1 && sourceIdx < destinationIdx) {
//                    flag.set(false);
                    System.out.println("Trains found for the given source and destination:");
                    searchTrains.forEach(t -> System.out.println(t.getTrainInfo()));
                    System.out.println(train.getStation());
                }
            });


        }else {
            System.out.println("No train found for the given source and destination");
        }
    }

    public void bookSeat(int noOfSeats,int trainNo){
        try {
            Train train = trains.stream().filter(t -> t.getTrainNo().equals(String.valueOf(trainNo))).findFirst().orElse(null);
            List<List<Integer>> seats = train.getSeats();
            int totalSeats = seats.size() * seats.get(0).size();
            int availableSeats = (int) seats.stream().flatMap(List::stream).filter(s -> s == 0).count();
            if (noOfSeats > availableSeats) {
                System.out.println("Not enough seats available");
                return;
            } else {
                int bookedSeats = 0;
                for (List<Integer> row : seats) {
                    for (int i = 0; i < row.size(); i++) {
                        if (row.get(i) == 0) {
                            row.set(i, 2); // Mark seat as booked
                            bookedSeats++;
                            if (bookedSeats == noOfSeats) {
                                break;
                            }
                        }
                    }
                }
                train.setSeats(seats);
                trains.add(train);
                objectMapper.writeValue(new File(TRAIN_PATH), trains);
                System.out.println("Successfully booked " + noOfSeats + " seats on train " + trainNo);
                for (List<Integer> row : seats) {
                    for (int i = 0; i < row.size(); i++) {
                        System.out.print(row.get(i)+" ");
                    }
                    System.out.println();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelBooking(int noOfSeats,int trainNo){
        try {
            Train train = trains.stream().filter(t -> t.getTrainNo().equals(String.valueOf(trainNo))).findFirst().orElse(null);
            List<List<Integer>> seats = train.getSeats();
            int totalSeats = seats.size() * seats.get(0).size();
            int availableSeats = (int) seats.stream().flatMap(List::stream).filter(s -> s == 0).count();
            if (noOfSeats > availableSeats) {
                System.out.println("Not enough seats available");
                return;
            } else {
                int bookedSeats = 0;
                for (List<Integer> row : seats) {
                    for (int i = 0; i < row.size(); i++) {
                        if (row.get(i) == 2) {
                            row.set(i, 0); // Mark seat as booked
                            bookedSeats++;
                            if (bookedSeats == noOfSeats) {
                                break;
                            }
                        }
                    }
                }
                train.setSeats(seats);
                trains.add(train);
                objectMapper.writeValue(new File(TRAIN_PATH), trains);
                System.out.println("Successfully booked " + noOfSeats + " seats on train " + trainNo);
                for (List<Integer> row : seats) {
                    for (int i = 0; i < row.size(); i++) {
                        System.out.print(row.get(i)+" ");
                    }
                    System.out.println();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
