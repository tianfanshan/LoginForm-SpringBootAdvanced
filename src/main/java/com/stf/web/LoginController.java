package com.stf.web;

import com.stf.domain.User;
import com.stf.form.UserForm;
import com.stf.service.UserServiceImp;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImp userService;

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String userName,
                            @RequestParam String password,
                            HttpSession session){
        User user = userService.login(userName,password);
        if (user != null){
            session.setAttribute("user",user);
            return "index";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid UserForm userForm, BindingResult result) {
        if (!userForm.confirm()) {
            result.rejectValue("confirmPassword", "confirmError", "两次密码不一致");
        }
        if (result.hasErrors()) {
            return "register";
        }
        User user = userForm.convertToUser();
        userService.save(user);
        return "redirect:/login";
    }

    /**
     * error500
     * @return
     */
    @GetMapping("/exception")
    public String testException() {
        throw new RuntimeException("测试异常处理");
    }

}
