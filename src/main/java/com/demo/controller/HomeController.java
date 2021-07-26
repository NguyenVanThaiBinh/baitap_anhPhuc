package com.demo.controller;

import com.demo.model.Customer;
import com.demo.model.Province;
import com.demo.repository.ICustomerRepository;
import com.demo.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    ICustomerRepository iCustomerRepository;

    @Autowired
    IProvinceRepository iProvinceRepository;

    @ModelAttribute("customerList")
    public List<Customer> getPerson(){
        return iCustomerRepository.findAll();
    }

    @GetMapping("/")
    public ModelAndView showIndex() {
        ModelAndView modelAndView = new ModelAndView("home");
        return modelAndView;
    }

    @GetMapping("/create_customer")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/create_form");
        List<Province> provinceList = iProvinceRepository.findAll();
        modelAndView.addObject("customer",new Customer());
        modelAndView.addObject("provinceList",provinceList);
        return  modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable String id){
        Optional<Customer> customer = iCustomerRepository.findById(Long.valueOf(id));
        Customer customer1 = customer.get();
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("customer",customer1);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createDone( @ModelAttribute("product") Customer customer,
                                    @RequestParam(name = "select_form", required = false) String province_id){

        ModelAndView modelAndView = new ModelAndView("home");
        Province province = iProvinceRepository.getById(Long.valueOf(province_id));
        customer.setProvince(province);
        iCustomerRepository.save(customer);
        modelAndView.addObject("customerList",iCustomerRepository.findAll());
        modelAndView.addObject("msgs","Create Done!");
        return  modelAndView;
    }



    @PostMapping("delete_done")
    public ModelAndView deleteDone(@ModelAttribute("product") Customer customer){
        ModelAndView modelAndView = new ModelAndView("home");
        System.out.println(customer);
        iCustomerRepository.deleteById(customer.getId());
        modelAndView.addObject("customerList",iCustomerRepository.findAll());
        modelAndView.addObject("msg_delete","Delete Done!");
        return  modelAndView;
    }

    @GetMapping("/edit{id}")
    public ModelAndView editForm(@PathVariable String id){
        Optional<Customer> customer = iCustomerRepository.findById(Long.valueOf(id));
        Customer customer1 = customer.get();
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("customer",customer1);
        return modelAndView;
    }

    @PostMapping("edit_done")
    public ModelAndView editDone(@ModelAttribute("customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("home");
        Optional<Customer> customer2 = iCustomerRepository.findById(customer.getId());
        modelAndView.addObject("msg_edit","Create Done!");
        Customer customer1 = customer2.get();



        customer1.setFullName(customer.getFullName());
        customer1.setId(customer.getId());
        customer1.setPhone(customer.getPhone());
        customer1.setEmail(customer.getEmail());
        customer1.getProvince().setName(customer.getProvince().getName());
        iCustomerRepository.save(customer1);
        modelAndView.addObject("customerList",iCustomerRepository.findAll());
        modelAndView.addObject("msg","Edit Done!");
        return  modelAndView;
    }
}