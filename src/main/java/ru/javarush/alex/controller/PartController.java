package ru.javarush.alex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javarush.alex.domain.Part;
import ru.javarush.alex.repository.PartRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class PartController {
    @Autowired
    private PartRepository partRepository;

    @GetMapping("/")
    public String main(@RequestParam(required = false, defaultValue = "") String search,
                       Model model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Part> parts;

        if (search != null && !search.isEmpty()) {
            parts = partRepository.findByName(search, pageable);
        } else {
            parts = partRepository.findAll(pageable);
        }

        model.addAttribute("parts", parts);
        model.addAttribute("search", search);
        model.addAttribute("computers", computers());

        return "index";
    }


    @GetMapping("/add")
    public String showSignUpForm(Part part) {
        return "operations/add-part";
    }

    @PostMapping("/addpart")
    public String addPart(@Valid Part part, BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
            return "operations/add-part";
        }

        partRepository.save(part);
        model.addAttribute("parts", partRepository.findAll(pageable));
        model.addAttribute("computers", computers());
        return "redirect:/";

    }

    // additional CRUD methods
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("part", part);
        model.addAttribute("computers", computers());
        return "operations/update-part";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Part part,
                             BindingResult result, Model model, Pageable pageable) {
        if (result.hasErrors()) {
            part.setId(id);
            return "operations/update-part";
        }

        partRepository.save(part);
        model.addAttribute("parts", partRepository.findAll(pageable));
        model.addAttribute("computers", computers());
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model, Pageable pageable) {
        Part part = partRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        partRepository.delete(part);
        model.addAttribute("parts", partRepository.findAll(pageable));
        model.addAttribute("computers", computers());
        return "redirect:/";
    }

    @GetMapping("/filter")
    public String filterParts(@RequestParam(value = "filter", required = false, defaultValue = "") String filterNeeded,
                            Model model,
                            @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<Part> parts;

        if (filterNeeded.equals("")) {
            parts = partRepository.findAll(pageable);
        } else if (filterNeeded.equals("true")) {
            parts = partRepository.findByNeeded(true, pageable);
        } else {
            parts = partRepository.findByNeeded(false, pageable);
        }

        model.addAttribute("parts", parts);
        model.addAttribute("filterNeeded", filterNeeded);
        model.addAttribute("computers", computers());

        return "index";
    }

    private Integer computers() {
        List<Part> neededParts = partRepository.findByNeeded(true);
        if (neededParts.isEmpty()) return 0;
        List<Integer> amount = new ArrayList<>();
        neededParts.forEach(part -> amount.add(part.getAmount()));
        return Collections.min(amount);
    }


}
