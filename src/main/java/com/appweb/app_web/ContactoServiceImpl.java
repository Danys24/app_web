/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.appweb.app_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author ACER
 */
@Service
public class ContactoServiceImpl implements ContactoServiceApi{
    
    @Autowired
    private ContactoRepository contactoRepository;
    
    @Override
    public Page<Contacto> getAll(Pageable pageable) {
        return contactoRepository.findAll(pageable);
    }
    
}
