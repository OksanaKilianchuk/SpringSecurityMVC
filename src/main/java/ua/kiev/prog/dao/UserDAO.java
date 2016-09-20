package ua.kiev.prog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.kiev.prog.entity.Contact;
import ua.kiev.prog.entity.CustomUser;

public interface UserDAO {//extends JpaRepository<CustomUser, Long> {

    public CustomUser findByLogin(String login);
    public void add(CustomUser user);
}