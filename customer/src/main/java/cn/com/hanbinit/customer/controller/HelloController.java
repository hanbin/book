package cn.com.hanbinit.customer.controller;


import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello0(@RequestParam String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello1(@PathVariable String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello2(@RequestBody Customer customer) {
        return "hello " + customer.getName();
    }
}

class Customer {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}