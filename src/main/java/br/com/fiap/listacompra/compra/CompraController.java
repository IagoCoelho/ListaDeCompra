package br.com.fiap.listacompra.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/compra")
public class CompraController {
    
    @Autowired
    CompraService service;

    @GetMapping
    public String index(Model model){
        model.addAttribute("compras", service.findAll());
        return "compra/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)) {
            redirect.addFlashAttribute("sucess","Compra apagada com sucesso");
        }else{
            redirect.addFlashAttribute("error", "Compra não encontrada");
        }
        return "redirect:/compra";
    }

    @GetMapping("new")
    public String form(Compra compra){
        return "compra/form";
    }

    @PostMapping
    public String create(Compra compra, RedirectAttributes redirect, BindingResult result){
        if (result.hasErrors()) return "compra/form";

        service.save(compra);
        redirect.addFlashAttribute("success", "Compra cadastrada com sucesso");
        return "redirect:/compra";
    }
}
