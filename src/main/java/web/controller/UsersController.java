package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {
    
    final UserService userService;
    
    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(value = "/")
    public String listUsers(ModelMap model) {
        model.addAttribute("usersList", userService.listUsers());
        return "index";
    }
    
    @GetMapping(value = "/addUser")
    public String showAddUserForm(@ModelAttribute("addUser") User addUser) {
        return "addUser";
    }
    
    @PostMapping("/")
    public String addUser(@ModelAttribute("addUser") User addUser) {
        userService.addUser(addUser);
        return "redirect:/";
    }
    
    
    @GetMapping(value = "/", params = "id")
    public String showEditUserForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("editUser", userService.getUser(id));
        return "editUser";
    }
    
    @PatchMapping(value = "/", params = "id")
    public String editUser (@ModelAttribute("editUser") User editUser,
                          @RequestParam("id") Long id) {
        userService.updateUser(editUser, id);
        return "redirect:/";
    }
    
    @DeleteMapping(value = "/", params = "id")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}