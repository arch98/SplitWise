package Models;

import java.util.List;

public class Group {
    private final String name;
    private final String description;
    private final List<String> userList;

    public  Group(String name,String description,List<String>userList){
        this.name = name;
        this.description = description;
        this.userList = userList;
    }

    public List<String> getUsers(){
        return userList;
    }
}
