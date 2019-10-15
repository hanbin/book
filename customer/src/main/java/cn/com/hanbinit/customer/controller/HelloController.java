package cn.com.hanbinit.customer.controller;


import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    /**
     * GET 请求，通过hello?name=xxx的形式传入name=xxx, 返回hello xxx
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello0(@RequestParam String name) {
        return "hello " + name;
    }

    /**
     * GET请求，通过hello/xxx的形式传入name=xxx，返回hello xxx
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public String hello1(@PathVariable String name) {
        return "hello " + name;
    }

    /**
     *
     * @param customer
     * @return
     */
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