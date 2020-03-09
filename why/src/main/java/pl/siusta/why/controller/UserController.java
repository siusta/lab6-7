package pl.siusta.why.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.siusta.why.model.User;
import pl.siusta.why.repo.UserRepo;


@Controller
public class UserController {
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @RequestMapping("/signup")
    public ModelAndView signUp(User user) {
        return new ModelAndView("register", "user", new User());
    }

    @RequestMapping("/register")
    public ModelAndView register(User user){
        System.out.println("test1");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        System.out.println("test2");
    return new ModelAndView("redirect:/login");
}



}
