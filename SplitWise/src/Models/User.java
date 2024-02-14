package Models;

public class User {
    private final String id;
    private  final String firstName;
    private final String lastName;

    private final String bio;


    public  User(String id,String firstName,String lastName,String bio){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
    }
}
