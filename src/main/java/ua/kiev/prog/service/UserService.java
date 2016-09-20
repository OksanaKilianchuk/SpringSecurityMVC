package ua.kiev.prog.service;

import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Group;

import java.util.List;

public interface UserService {
    CustomUser getUserByLogin(String login);
    void addUser(CustomUser customUser);
    List<Group> getUserGroups(CustomUser user);

}
