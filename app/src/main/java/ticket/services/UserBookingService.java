package ticket.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.entities.User;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

public class UserBookingService {
    private User user;
    private List<User> users;
    private static final String USER_PATH = "../localDB/user.json";
    private static final ObjectMapper mapper=new ObjectMapper();

    public UserBookingService(User user){
        try {
            this.user = user;
            File file = new File(USER_PATH);
            users = mapper.readValue(file, new TypeReference<List<User>>() {
            });
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean loginUser(){
        
    }

}
