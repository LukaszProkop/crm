package com.crm.hbdbweb2.controller;

import com.crm.hbdbweb2.dto.UsersDTO;
import com.crm.hbdbweb2.model.Customer;
import com.crm.hbdbweb2.model.Users;
import com.crm.hbdbweb2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/adminPanel")
    public String adminPanel(Model model) {
        List<Users> usersList = customerService.getUsersList();
        model.addAttribute("usersList", usersList);
        return "adminPanel";
    }

    @GetMapping("/addUser")
    public String addUser(Model model) {
        UsersDTO users = new UsersDTO();
        model.addAttribute("user", users);
        return "addUser";
    }

    @GetMapping("/userUpdate")
    public String userUpdate(@RequestParam("username") String username, Model model) {
        UsersDTO user = customerService.getUser(username);
        model.addAttribute("user", user);
        return "addUser";
    }

    @GetMapping("deleteUser")
    public String deleteUser(@RequestParam("username") String username) {
        customerService.deleteUser(username);
        return "redirect:/admin/adminPanel";
    }

    @PostMapping("saveUser")
    public String saveUser(@ModelAttribute("user") UsersDTO userDTO) {
        System.out.println(userDTO.getUsername());
        customerService.deleteUser(userDTO.getUsername());
        customerService.saveUser(userDTO);

        return "redirect:/customer/list";
    }
}
