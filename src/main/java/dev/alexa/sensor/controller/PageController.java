package dev.alexa.sensor.controller;

import dev.alexa.sensor.domain.Type;
import dev.alexa.sensor.domain.Unit;
import dev.alexa.sensor.payload.SensorDto;
import dev.alexa.sensor.repository.TypeRepo;
import dev.alexa.sensor.repository.UnitRepo;
import dev.alexa.sensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;


@Controller
@ApiIgnore
public class PageController {
    @Autowired
    private TypeRepo typeRepo;
    @Autowired
    private UnitRepo unitRepo;
    @Autowired
    private SensorService service;

    @GetMapping("list")
    @PreAuthorize("hasRole('ADMIN') || hasRole('VIEWER')")
    public String getList(Model model, @RequestParam(required = false) String search)
    {
        List<SensorDto> list = new ArrayList<>();
        if (search != null)
        {
            list = service.getSensorsByText(search);
        }else
        {
            list = service.getAllSensors();
        }
        model.addAttribute("list", list);
        return "sensorList";
    }


    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getEditPage(Model model, @PathVariable(name = "id") Long id)
    {
        SensorDto sensorDto = service.getSensorById(id);
        List<Type> types = (List<Type>) typeRepo.findAll();
        List<Unit> units = (List<Unit>) unitRepo.findAll();
        model.addAttribute("sensor", sensorDto);
        model.addAttribute("types", types);
        model.addAttribute("units", units);
        return "edit";
    }



    @GetMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAddPage(Model model)
    {
        SensorDto sensorDto = new SensorDto();
        List<Type> types = (List<Type>) typeRepo.findAll();
        List<Unit> units = (List<Unit>) unitRepo.findAll();
        model.addAttribute("sensor", sensorDto);
        model.addAttribute("types", types);
        model.addAttribute("units", units);
        return "add";
    }


}
