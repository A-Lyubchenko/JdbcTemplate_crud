package ua.lyubchenko.controllers.entityControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lyubchenko.controllers.IControllers;
import ua.lyubchenko.domains.Developer;
import ua.lyubchenko.repositories.entityRepositories.DeveloperRepositories;

import javax.validation.Valid;

@Controller
@RequestMapping("developers")
public class DeveloperController implements IControllers<Developer> {
    private final DeveloperRepositories developersRepositories;

    public DeveloperController(DeveloperRepositories DevelopersRepositories) {
        this.developersRepositories = DevelopersRepositories;
    }

    @Override
    @GetMapping()
    public String read(Model model) {
        model.addAttribute("developers", developersRepositories.read());
        return "developerViews/read";
    }

    @Override
    @GetMapping("{id}")
    public String get(@PathVariable("id") int id, Model model) {
        model.addAttribute("developer", developersRepositories.get(id));
        return "developerViews/get";
    }

    @Override
    @GetMapping("new")
    public String newCompany(@ModelAttribute("developer") Developer developer) {
        return "developerViews/create";
    }

    @Override
    @PostMapping()
    public String create(@ModelAttribute("developer") @Valid Developer developer, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "developerViews/create";

        developersRepositories.create(developer);
        return "redirect:developers";
    }

    @Override
    @GetMapping("{id}/update")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("developer", developersRepositories.get(id));
        return "developerViews/update";
    }

    @Override
    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("developer") Developer developer) {
        developersRepositories.update(id, developer);
        return "redirect:/developers";
    }

    @Override
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        developersRepositories.delete(id);
        return "redirect:/developers";
    }
}
