package javainuse.service;

import javainuse.step.DBReader;
import javainuse.step.UserList;

import java.util.ArrayList;

public abstract class Service {

    public static ArrayList searchUser(String firstname, String lastname) {
        ArrayList<UserList> userlist = new ArrayList<UserList>();

        try {
            DBReader r = new DBReader();
            userlist = r.searchUser(firstname, lastname);
        } catch (Exception e) {
            System.out.println("Error in service" + e);
        }
        return userlist;

    }

}
