package ticket.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.entities.User;
import ticket.util.UserServiceUtil;

import java.io.File;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User loginUser;
    private List<User> users;
    private static final String USER_PATH = "../localDB/user.json";
    private static final ObjectMapper mapper=new ObjectMapper();

    public UserBookingService(User user){
        try {
            this.loginUser = user;
            File file = new File(USER_PATH);
            users = mapper.readValue(file, new TypeReference<List<User>>() {
            });
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean loginUser(){
        Optional<User> loginUsers = users.stream().filter(u ->{
            return u.getName().equals(this.loginUser.getName()) &&
                    UserServiceUtil.checkPassword(loginUser.getPassword(),u.getPassword());
        }).findFirst();
        return loginUsers.isPresent();
    }

    public boolean signUp(User user){
        try{
            users.add(user);
            saveUserToFile(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void saveUserToFile(User user) throws Exception {
        File file = new File(USER_PATH);
        mapper.writeValue(file,users);
    }

    public void fetchBooking(){
        loginUser.getTicketBooked();
    }

    public boolean cancelBooking(String ticketId){
        loginUser.getTicketBooked().removeIf(ticket ->
            ticket.getTicketId().equals(ticketId)
        );

        return false;
    }

}
