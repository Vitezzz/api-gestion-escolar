package com.sige.api_gestion_escolar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    @GetMapping("/cuatrimestres")
    public String cuatrimestres() {
        return "cuatrimestres";
    }
    
    @GetMapping("/estudiantes")
    public String estudiantes() {
        return "estudiantes";
    }
    
    @GetMapping("/docentes")
    public String docentes() {
        return "docentes";
    }
    
    @GetMapping("/materias")
    public String materias() {
        return "materias";
    }
    
    @GetMapping("/programas")
    public String programas() {
        return "programas";
    }
    
    @GetMapping("/secciones")
    public String secciones() {
        return "secciones";
    }
}