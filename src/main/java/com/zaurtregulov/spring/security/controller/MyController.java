package com.zaurtregulov.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller// класс контроллер нужен, чтобы вписывать нужным нам юрл - и методы, которые здесь
// описанны откроют нужную нам веб страничку по данному прописанному нами юрл адресу
public class MyController {

    @GetMapping("/") //юрл по адресу "/" откроет нам страничку "view_for_all_users", которую
    //мы создадим в папке view
    public String getInfoForAllUsers() {
        return "view_for_all_users";
    }

    @GetMapping("/hr_info")
    public String getInfoOnlyForHR() {
        return "view_for_hr";
    }

    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers() {
        return "view_for_managers";
    }
}
