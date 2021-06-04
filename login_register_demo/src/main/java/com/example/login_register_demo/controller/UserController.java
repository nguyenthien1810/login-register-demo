package com.example.login_register_demo.controller;

import com.example.login_register_demo.model.UserEntity;
import com.example.login_register_demo.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserEntityService userEntityService;
    
    @GetMapping("")
	public String viewHomePage() {
		return "index";
	}

    @GetMapping("/login")
    public  String showFromLogin(){
        return "/loginPage";
    }
    
    @GetMapping("/home")
    public  ModelAndView test(){
        List<UserEntity> list = userEntityService.findAll();
        ModelAndView modelAndView = new ModelAndView("home","user",list);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam Optional<String> key_search){
        if(!key_search.isPresent()){
            return new ModelAndView("home", "user", userEntityService.findAll());
        }else {
            return new ModelAndView("home", "user", userEntityService.findByName(key_search.get()));
        }
    }

    @GetMapping("/register")
    public ModelAndView showFromRegister(){
        ModelAndView modelAndView = new ModelAndView("register","user",new UserEntity());
        return  modelAndView ;
    }

    @PostMapping("/register")
    public  ModelAndView Register(@ModelAttribute("user") UserEntity userEntity){
        userEntityService.save(userEntity);
        ModelAndView modelAndView = new ModelAndView("register","user",new UserEntity());
        return modelAndView;
    }

}
