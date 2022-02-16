package com.sonle.testmodule4.controller;

import com.sonle.testmodule4.model.City;
import com.sonle.testmodule4.model.Country;
import com.sonle.testmodule4.service.ICityService;
import com.sonle.testmodule4.service.ICountryService;
import com.sonle.testmodule4.validate.ValidateName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    ICityService cityService;
    @Autowired
    ICountryService countryService;
    @Autowired
    ValidateName validateName;

    @ModelAttribute("Country")
    public List<Country> showList(){
        return countryService.findAll();
    }
    @ModelAttribute("city")
    public City getUsers(){
        return  new City();
    }

    @GetMapping("/citys")
    public ModelAndView findAll(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("citys", cityService.findAll(PageRequest.of(page, 3)));
        return modelAndView;
    }

    @GetMapping("/sort")
    @ResponseBody
    public ModelAndView showAll(@RequestParam("field") Optional<String> field) {
        ModelAndView modelAndView = new ModelAndView("sort");
        Sort sort = Sort.by(Sort.Direction.DESC, field.orElse("cityName"));
        modelAndView.addObject("citys", cityService.findAll(sort));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city", new City());
        modelAndView.addObject("country", countryService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public Object create(@Valid @ModelAttribute(value = "city") City city, BindingResult bindingResult) {
        validateName.validate(city, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("country", countryService.findAll());
            return modelAndView;
        }
        return "redirect:/citys";
    }

    @GetMapping("/edit")
    public ModelAndView showEditForm(@RequestParam long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView;
        if (city.isPresent()) {
            modelAndView = new ModelAndView("edit");
            modelAndView.addObject("city", city.get());
            modelAndView.addObject("country", countryService.findAll());

        } else {
            modelAndView = new ModelAndView("/error_404");
        }
        return modelAndView;
    }

    @PostMapping("/edit")
    public Object updateStaff(@Valid @ModelAttribute(value = "city") City city, BindingResult bindingResult) {
        validateName.validate(city, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("country", countryService.findAll());
            return modelAndView;
        }
        cityService.save(city);
        return "redirect:/citys";
    }

    @GetMapping("/delete")
    public ModelAndView showDeleteForm(@RequestParam long id) {
        Optional<City> city = cityService.findById(id);
        ModelAndView modelAndView;
        if (city.isPresent()) {
            modelAndView = new ModelAndView("delete");
            modelAndView.addObject("city", city.get());

        } else {
            modelAndView = new ModelAndView("/error_404");
        }
        return modelAndView;
    }

    @PostMapping("/delete")
    public String deleteStaff(@ModelAttribute("city") City city) {
        cityService.delete(city.getId());
        return "redirect:citys";
    }

    @GetMapping("/view{id}")
    public ModelAndView viewStaff(@PathVariable("id") Long id) {
        Optional<City> city = cityService.findById(id);
        if (!city.isPresent()) {
            return new ModelAndView("/error_404");
        }

        Iterable<City> user1 = cityService.findAll();

        ModelAndView modelAndView = new ModelAndView("views");
        modelAndView.addObject("city", city.get());
        modelAndView.addObject("citys", user1);
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("search");
        modelAndView.addObject("citys", cityService.findByName(search));
        return modelAndView;
    }
}
