package org.ldv.springbootaventure.controller.admin

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainControlleur {
    @GetMapping("/")
    fun home():String{
        return "visiteur/accueil"
    }

    @GetMapping("/inscription")
    fun inscription():String{
        return "visiteur/inscription"
    }
    @GetMapping("/login")
    fun login():String {
        return "visiteur/login"
    }
}
