package br.com.fiap.listacompra.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}