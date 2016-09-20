package ua.kiev.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.kiev.prog.dao.UserDAO;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Group;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public CustomUser getUserByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    @Transactional
    public void addUser(CustomUser customUser) {
        userDAO.add(customUser);
    }

    @Override
    public List<Group> getUserGroups(CustomUser user){
        return user.getGroups();
    }

}
