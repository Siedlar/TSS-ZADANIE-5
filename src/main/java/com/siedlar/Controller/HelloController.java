package com.siedlar.Controller;

import com.siedlar.entity.Car;
import com.siedlar.service.CarService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
class HelloController {
    @RequestMapping("/")
    public String callpage(Model themodel) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        LocalDate localDate=LocalDate.now();
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mvc-demo-servlet.xml");
      CarService carService=ctx.getBean("serwis", CarService.class);
        System.out.println(   carService.getCar(2));
        themodel.addAttribute("lista",carService.pobierzListe());
        return "hello";
    }

    @RequestMapping("/dodajAuto")
    public String dodajAuto(Model themodel){
       Car car=new Car();
       themodel.addAttribute("auto",car);
        return "dodaj-auto";
    }
    @RequestMapping("/usunAuto")
    public String UsunAuto(Model themodel){
        Car car=new Car();
        themodel.addAttribute("auto",car);
        return "usun-auto";
    }
    @RequestMapping("/wyswietlAuta")
    public String dodajUzytkowika(Model themodel) throws SQLException {
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mvc-demo-servlet.xml");
        CarService carService=ctx.getBean("serwis", CarService.class);
        List<Car> listaAut=carService.pobierzListe();
        themodel.addAttribute("lista",listaAut);
        return "wyswietl-auta";
    }

    @RequestMapping("/dodano")
    public String dodano(@Valid @ModelAttribute("auto") Car car,BindingResult bindingResult, Model model ) throws SQLException {
    if(bindingResult.hasErrors()==true){
        return "dodaj-auto";}else {
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mvc-demo-servlet.xml");
        CarService carService=ctx.getBean("serwis", CarService.class);
        carService.dodaj(car);
        model.addAttribute("auto",car);
        return "dodano";
    }
    }

    @RequestMapping("/usunieto")
    public String usunieto(@ModelAttribute("auto") Car car,Model model) throws SQLException {
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mvc-demo-servlet.xml");
        CarService carService=ctx.getBean("serwis", CarService.class);
        carService.usun(car.getIdCars());
        model.addAttribute("value",car.getIdCars());

        return "usunieto";
    }
    @RequestMapping("/usunWszystko")
    public String usunWszystko(){
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mvc-demo-servlet.xml");
        CarService carService=ctx.getBean("serwis", CarService.class);
        carService.usunWszystko();
        return "usunietoWszystko";
    }
    @RequestMapping("/aktualizujForm")
    public String aktualizujForm(Model model){
        Car car=new Car();
        model.addAttribute("auto",car);
        return "aktualizujForm";
    }
    @RequestMapping("/aktualizuj")
    public String aktualizuj(@ModelAttribute("auto") Car car,Model model){
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:spring-mvc-demo-servlet.xml");
        CarService carService=ctx.getBean("serwis", CarService.class);
        int a=car.getIdCars();
        car=carService.getCar(a);
        model.addAttribute("auto",car);
        return "dodaj-auto";
    }
}
