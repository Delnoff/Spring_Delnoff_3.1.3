package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AdminController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping()
    public String getAllUserInfo(Model model) {
        model.addAttribute("people", userService.read());
        return "admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", userService.showId(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("person", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        userValidator.validate(user,bindingResult);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("person", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "new";
        }

        userService.create(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("person",userService.showId(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid User user, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes, @PathVariable("id") long id) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("person",user);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "edit";
        }

        userService.update(id,user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
