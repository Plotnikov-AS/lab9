package ru.pis.lab9.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pis.lab9.model.Automobile;
import ru.pis.lab9.model.Employee;
import ru.pis.lab9.service.MainService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService service;

    @GetMapping("/")
    public String roadList(Model model) {
        List<Employee> drivers = service.getAllDrivers();
        model.addAttribute("drivers", drivers);

        return "mainPage";
    }

    @PostMapping("/submit")
    public String submit(String driverId, String autoId, String length, String fuelUsage) {
        if (driverId.equals("0") || autoId.equals("0")) {
            return "redirect:/";
        }

        service.saveRoadList(driverId, autoId, length, fuelUsage);

        return "redirect:/";
    }

    @GetMapping("/showDriverStat")
    public String showDriverId(String driverId, Model model) {
        List<Employee> allDrivers = service.getAllDrivers();
        Integer middleLength = service.getMiddleLength();

        model.addAttribute("allDrivers", allDrivers);
        model.addAttribute("middleLength", middleLength);
        return "driverStat";
    }

    @GetMapping("/showAutoStat")
    public String showAutoStat(String autoId, Model model) {
        List<Automobile> allAutos = service.getAllAutos();
        Integer middleConsume = service.getMiddleConsume();

        model.addAttribute("allAutos", allAutos);
        model.addAttribute("middleConsume", middleConsume);
        return "autoStat";
    }
}
