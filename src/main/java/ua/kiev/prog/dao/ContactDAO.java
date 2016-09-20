package ua.kiev.prog.dao;

import ua.kiev.prog.entity.Contact;
import ua.kiev.prog.entity.Group;

import java.util.List;

public interface ContactDAO {
    void add(Contact contact);
    void delete(Contact contact);
    void delete(long[] ids);
    List<Contact> list(Group group);
    List<Contact> list(String pattern);
}
