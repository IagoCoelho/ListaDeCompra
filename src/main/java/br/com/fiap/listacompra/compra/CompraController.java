package br.com.fiap.listacompra.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @Autowired
    MessageSource messageSource;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
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
    public String form(Compra compra, Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        return "compra/form";
    }

    @PostMapping
    public String create(Compra compra, RedirectAttributes redirect, BindingResult result){
        if (result.hasErrors()) return "compra/form";

        service.save(compra);
        redirect.addFlashAttribute("success", "Compra cadastrada com sucesso");
        return "redirect:/compra";
    }

    @GetMapping("/dec/{id}")
    public String decrement(@PathVariable Long id){
        service.decrement(id);
        return "redirect:/compra";
    }

    @GetMapping("/inc/{id}")
    public String increment(@PathVariable Long id){
        service.increment(id);
        return "redirect:/compra";
    }

    @GetMapping("/catch/{id}")
    public String cacthCompra(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){
        service.cacthCompra(id, user);
        return "redirect:/compra";
    }

    @GetMapping("/drop/{id}")
    public String dropCompra(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){
        service.dropCompra(id, user);
        return "redirect:/compra";
    }
}
