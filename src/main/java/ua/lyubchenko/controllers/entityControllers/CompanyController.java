package ua.lyubchenko.controllers.entityControllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lyubchenko.controllers.IControllers;
import ua.lyubchenko.domains.Company;
import ua.lyubchenko.repositories.entityRepositories.CompanyRepositories;

import javax.validation.Valid;


@Controller
@RequestMapping("companies")
public class CompanyController implements IControllers<Company> {


    private final CompanyRepositories companyRepositories;

    public CompanyController(CompanyRepositories companyRepositories) {
        this.companyRepositories = companyRepositories;
    }

    @Override
    @GetMapping()
    public String read(Model model) {
        model.addAttribute("companies", companyRepositories.read());
        return "companyViews/read";
    }

    @Override
    @GetMapping("{id}")
    public String get(@PathVariable("id") int id, Model model) {
        model.addAttribute("company", companyRepositories.get(id));
        return "companyViews/get";
    }

    @Override
    @GetMapping("new")
    public String newCompany(@ModelAttribute("company") Company company) {
        return "companyViews/create";
    }

    @Override
    @PostMapping()
    public String create(@ModelAttribute("company") @Valid Company company, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "companyViews/create";

        companyRepositories.create(company);
        return "redirect:companies";
    }

    @Override
    @GetMapping("{id}/update")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("company", companyRepositories.get(id));
        return "companyViews/update";
    }

    @Override
    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("company") Company company) {
        companyRepositories.update(id, company);
        return "redirect:/companies";
    }

    @Override
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        companyRepositories.delete(id);
        return "redirect:/companies";
    }
}
