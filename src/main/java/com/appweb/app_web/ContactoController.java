/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.appweb.app_web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ACER
 */
@Controller
public class ContactoController {

    @Autowired
    ContactoRepository contactoRepository;
    
    @Autowired
    private ContactoServiceApi contactoServiceApi;
    
    @GetMapping("/")
    public String index(@RequestParam Map<String, Object> params, Model model) {
        int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 3);
        Page<Contacto> contactos = contactoServiceApi.getAll(pageRequest);
        int totalPage = contactos.getTotalPages();
        if(totalPage > 0){
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        
        model.addAttribute("contactos", contactos.getContent());
        
        return "index";
    }
    
    /*
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size=10) Pageable pageable) {
        Page<Contacto> contactos = contactoRepository.findAll(pageable);
        model.addAttribute("contactos", contactos);
        return "index";
    }
    */

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("contacto", new Contacto());
        return "form";
    }

    @PostMapping("/nuevo")
    public String crear(Contacto contacto, RedirectAttributes ra) {
        contactoRepository.save(contacto);
        ra.addFlashAttribute("mExito", "El contacto ha sido creado con exito");
        return "redirect:/";
    }

    @GetMapping("/hola")
    public String hola(Model model) {
        model.addAttribute("mensaje", "hola desde thymeleaf yupe jpa");
        return "hola";
    }
}
