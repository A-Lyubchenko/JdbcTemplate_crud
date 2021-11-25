package ua.lyubchenko.controllers.entityControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lyubchenko.controllers.IControllers;
import ua.lyubchenko.domains.Skill;
import ua.lyubchenko.repositories.entityRepositories.SkillRepositories;

import javax.validation.Valid;

@Controller
@RequestMapping("skills")
public class SkillController implements IControllers<Skill> {
    private final SkillRepositories skillRepositories;

    public SkillController(SkillRepositories skillRepositories) {
        this.skillRepositories = skillRepositories;
    }

    @Override
    @GetMapping()
    public String read(Model model) {
        model.addAttribute("skills", skillRepositories.read());
        return "skillViews/read";
    }

    @Override
    @GetMapping("{id}")
    public String get(@PathVariable("id") int id, Model model) {
        model.addAttribute("skill", skillRepositories.get(id));
        return "skillViews/get";
    }

    @Override
    @GetMapping("new")
    public String newCompany(@ModelAttribute("skill") Skill skill) {
        return "skillViews/create";
    }

    @Override
    @PostMapping()
    public String create(@ModelAttribute("skill") @Valid Skill skill, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "skillViews/create";

        skillRepositories.create(skill);
        return "redirect:skills";
    }

    @Override
    @GetMapping("{id}/update")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("skill", skillRepositories.get(id));
        return "skillViews/update";
    }

    @Override
    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("skill") Skill skill) {
        skillRepositories.update(id, skill);
        return "redirect:/skills";
    }

    @Override
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        skillRepositories.delete(id);
        return "redirect:/skills";
    }
}
