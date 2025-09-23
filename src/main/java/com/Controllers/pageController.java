package com.Controllers;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Entity.Role;
import com.Entity.User;
import com.Forms.UserForms;
import com.repositories.RoleRepository;
import com.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class pageController {
    // UserService autowired
    @Autowired
    private UserService userService;
    // RoleRepository autowired
    @Autowired
    private RoleRepository roleRepository;
    // Password encoder autowired
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Signup page
    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("UserForms", new UserForms());
        return "Signup";
    }

    // Signup page
    @PostMapping(value = "/do-signup")
    public String doSignup(@Valid @ModelAttribute UserForms userForms, BindingResult result,
            RedirectAttributes redirectAttributes) {
        // If there are errors, return to the Signup page
        if (result.hasErrors()) {
            return "Signup";
        }
        User user = new User();
        user.setUserName(userForms.getUserName());
        user.setEmail(userForms.getEmail());
        user.setPassword(passwordEncoder.encode(userForms.getPassword()));
        user.setEnabled(false);
        Set<Role> roles = new HashSet<>();
        for (String roleName : userForms.getRoles()) {
            Role role = roleRepository.findRoleByRoleName(roleName);
            // check if role is not null
            if (role == null) {
                // Role doesn"t exist, then create and save it
                role = new Role();
                // set role name
                role.setRoleName(roleName);
                // save role
                role = roleRepository.save(role);
            }
            roles.add(role);
        }
        // set the roles to the user
        user.setRoles(roles);
        // save the user by calling the userService
        userService.saveUser(user);
        // add flash attribute
        redirectAttributes.addFlashAttribute("message", "User registered successfully!.");
        // redirect to same signup page
        return "redirect:/Signup";
    }

}
