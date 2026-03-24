package com.fziyo.sms.controller;

import com.fziyo.sms.mapper.EmpMapper;
import com.fziyo.sms.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    
    
}
