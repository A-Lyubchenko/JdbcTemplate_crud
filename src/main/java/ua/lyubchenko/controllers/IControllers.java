package ua.lyubchenko.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

public interface IControllers<T> {

    String read(Model model);

    String get(@PathVariable("id") int id, Model model);

    String newCompany(@ModelAttribute T entity);

    String create(@ModelAttribute T entity, BindingResult bindingResult);

    String edit(@PathVariable("id") int id, Model model);

    String update(@PathVariable("id") int id, @ModelAttribute("company") T entity);

    String delete(@PathVariable("id") int id);
}
