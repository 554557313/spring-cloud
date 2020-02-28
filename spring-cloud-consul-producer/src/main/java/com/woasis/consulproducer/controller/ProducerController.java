package com.woasis.consulproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.woasis.consulproducer.model.UserDto;
import com.woasis.consulproducer.mq.MQSender;


@Controller
//@RefreshScope
@RequestMapping("/producer")
public class ProducerController {

	//@Value("${config}")
	//private String devConfig;
	
	@Autowired
	private MQSender mqsender;
	
    @GetMapping("/get")
    @ResponseBody
    public String producer(){
        System.out.println("I'm producer");
        return "Hello, I'm producer,";
    }
    
    @GetMapping("/mqsender")
    @ResponseBody
    public String mqsender(){
        System.out.println("mqsender");
        try {
			mqsender.send("sender", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "mq sender success!";
    }
}
