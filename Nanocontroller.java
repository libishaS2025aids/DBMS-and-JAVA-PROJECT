package com.libi.demo.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Nanocontroller {
    private final Map<String, String> records = new ConcurrentHashMap<>();

    @GetMapping("hello")
    public String get() {
        return "Hello World";
    }

    @PostMapping({"", "create"})
    public Map<String, Object> create(@RequestBody Map<String, Object> payload) {
        return Map.of(
                "message", "successfully created",
                "data", payload
        );
    }

    @PostMapping("create/{id}")
    public String create(@PathVariable String id, @RequestBody String payload) {
        records.put(id, payload);
        return payload + " successfully created";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable String id) {
        String deleted = records.remove(id);
        if (deleted == null) {
            return "No record found for id " + id;
        }
        return deleted + " successfully deleted";
    }
}
