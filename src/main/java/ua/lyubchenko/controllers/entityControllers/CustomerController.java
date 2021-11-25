package ua.lyubchenko.controllers.entityControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lyubchenko.controllers.IControllers;
import ua.lyubchenko.domains.Customer;
import ua.lyubchenko.repositories.entityRepositories.CustomerRepositories;

import javax.validation.Valid;

@Controller
@RequestMapping("customers")
public class CustomerController implements IControllers<Customer> {
    private final CustomerRepositories customerRepositories;

    public CustomerController(CustomerRepositories customerRepositories) {
        this.customerRepositories = customerRepositories;
    }

    @Override
    @GetMapping()
    public String read(Model model) {
        model.addAttribute("customers", customerRepositories.read());
        return "customerViews/read";
    }

    @Override
    @GetMapping("{id}")
    public String get(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerRepositories.get(id));
        return "customerViews/get";
    }

    @Override
    @GetMapping("new")
    public String newCompany(@ModelAttribute("customer") Customer company) {
        return "customerViews/create";
    }

    @Override
    @PostMapping()
    public String create(@ModelAttribute("customer")  @Valid Customer company, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "customerViews/get";

        customerRepositories.create(company);
        return "redirect:customers";
    }

    @Override
    @GetMapping("{id}/update")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("customer", customerRepositories.get(id));
        return "customerViews/update";
    }

    @Override
    @PatchMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("customer") Customer company) {
        customerRepositories.update(id, company);
        return "redirect:/customers";
    }

    @Override
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        customerRepositories.delete(id);
        return "redirect:/customers";
    }
}
