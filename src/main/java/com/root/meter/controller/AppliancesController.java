package com.root.meter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Controller
public class AppliancesController {
    @GetMapping("/appliance")
    public String get(Model model) throws JsonProcessingException {
        WebClient webClient = WebClient.create("http://192.168.1.111:8181"); //10.42.0.2
        ResponseEntity<String> res = webClient.get().retrieve().toEntity(String.class).block();
        String appliance = res.getBody().toString().split(":")[1];
        appliance = appliance.substring(1,appliance.length()-2);
        System.out.println("appliance "+appliance);
        model.addAttribute("appliance",appliance);
        return "appliance";
    }
}
