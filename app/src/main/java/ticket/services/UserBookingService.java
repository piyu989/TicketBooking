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
    private static final String USER_PATH = "app/src/main/java/ticket/localDB/user.json";
    private static final ObjectMapper mapper=new ObjectMapper();

    private TrainService trainService = new TrainService();

    public UserBookingService(User user){
        try {
            System.out.println("login user before "+loginUser);
            this.loginUser = user;
            System.out.println("login user after "+loginUser);

            File file = new File(USER_PATH);
            loadUser();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public UserBookingService(){
        System.out.println("loading user");
        loadUser();
    }

    public void loadUser(){
        try {
            File file = new File(USER_PATH);
            users = mapper.readValue(file, new TypeReference<List<User>>() {
            });
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean loginUser(){
        Optional<User> loginUsers = users.stream().filter(u -> u.getName().equals(this.loginUser.getName()) &&
                UserServiceUtil.checkPassword(loginUser.getPassword(),u.getHashedPassword())).findFirst();
        return loginUsers.isPresent();
    }

    public boolean signUp(User user){
        try{
            saveUserToFile(user);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void saveUserToFile(User user) throws Exception {
        System.out.println("previous users is "+users);
        users.add(user);
        System.out.println("new users is "+users);
        File file = new File(USER_PATH);
        mapper.writeValue(file,users);
    }

    public void fetchBooking(){
        loginUser.getTicketBooked();
    }

    public boolean cancelBooking(String ticketId) throws Exception {
        loginUser.getTicketBooked().removeIf(ticket ->
            ticket.getTicketId().equals(ticketId)
        );
//        users.remove( loginUser);
//        users.add(loginUser);

        saveUserToFile(loginUser);

        return false;
    }

}
