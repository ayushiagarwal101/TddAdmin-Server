package javainuse.controller;

import javainuse.service.Service;
import javainuse.step.UserList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/searchUser")
    public ArrayList<UserList> searchUser(@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname) {
        return Service.searchUser(firstname, lastname);
    }
}