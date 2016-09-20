package ua.kiev.prog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.kiev.prog.entity.Contact;
import ua.kiev.prog.entity.CustomUser;
import ua.kiev.prog.entity.Group;
import ua.kiev.prog.service.ContactService;
import ua.kiev.prog.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class MyController {
    static final int DEFAULT_GROUP_ID = -1;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        CustomUser dbUser = userService.getUserByLogin(login);
        List<Group> listGroups = userService.getUserGroups(dbUser);
        model.addAttribute("groups", listGroups);
        if (listGroups.size() > 0) {
            model.addAttribute("contacts", contactService.listContacts(listGroups.get(0)));//все контакты
        }
        return "index";
    }

    @RequestMapping("/registration")
    public String userAddPage(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registration/addUser", method = RequestMethod.POST)
    public String addUser(@RequestParam String login,
                          @RequestParam String password,
                          @RequestParam String phone,
                          @RequestParam String email,
                          Model model) {
        ShaPasswordEncoder spe = new ShaPasswordEncoder();
        String codePassword = spe.encodePassword(password, null);
        CustomUser user = new CustomUser(login, codePassword, email, phone);
        userService.addUser(user);
        model.addAttribute("success", "Registration successful!");
        return "login";
    }

    @RequestMapping("/unauthorize")
    public String unauthorized(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorize";
    }


    @RequestMapping("/contact_add_page")
    public String contactAddPage(Model model) {
        model.addAttribute("groups", contactService.listGroups());
        return "contact_add_page";
    }

    @RequestMapping("/group_add_page")
    public String groupAddPage() {
        return "group_add_page";
    }

    @RequestMapping("/group/{id}")
    public String listGroup(@PathVariable(value = "id") long groupId, Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("currentGroup", group);
        model.addAttribute("contacts", contactService.listContacts(group));
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String pattern, Model model) {
        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.searchContacts(pattern));
        return "index";
    }

    @RequestMapping(value = "/contact/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> delete(@RequestParam(value = "toDelete[]", required = false) long[] toDelete, Model model) {
        if (toDelete != null)
            contactService.deleteContact(toDelete);

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(null));

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/add", method = RequestMethod.POST)
    public String contactAdd(@RequestParam(value = "group") long groupId,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String phone,
                             @RequestParam String email,
                             Model model) {
        Group group = (groupId != DEFAULT_GROUP_ID) ? contactService.findGroup(groupId) : null;

        Contact contact = new Contact(group, name, surname, phone, email); //параметры из пост запроса
        contactService.addContact(contact);

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(null));
        return "redirect:/";
    }

    @RequestMapping(value = "/group/add", method = RequestMethod.POST)
    public String groupAdd(Model model, @RequestParam String name) {
        contactService.addGroup(new Group(name));

        model.addAttribute("groups", contactService.listGroups());
        model.addAttribute("contacts", contactService.listContacts(null));
        return "index";
    }
}
