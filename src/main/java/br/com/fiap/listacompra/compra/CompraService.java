package br.com.fiap.listacompra.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import br.com.fiap.listacompra.user.User;

@Service
public class CompraService {
    
    @Autowired
    CompraRepository repository;

    public List<Compra> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id){
        var compra = repository.findById(id);

        if (compra.isEmpty()) return false;

        repository.deleteById(id);
        return true;
    }

    public void save(Compra compra) {
        repository.save(compra);
    }

    public void decrement(Long id) {
        var optional = repository.findById(id);

        if (optional.isEmpty())
            throw new RuntimeException("compra não encontrada");

        var compra = optional.get();

        if (compra.getStatus() == null || compra.getStatus() <= 0)
            throw new RuntimeException("compra não pode ter status negativo");

        compra.setStatus(compra.getStatus() - 10);

        // salvar
        repository.save(compra);
    }

    public void increment(Long id) {
        var optional = repository.findById(id);

        if (optional.isEmpty())
            throw new RuntimeException("compra não encontrada");

        var compra = optional.get();

        if (compra.getStatus() == null)
            compra.setStatus(0);

        if (compra.getStatus() == 100) {
            throw new RuntimeException("compra não pode ter status maior que 100");
        }

        compra.setStatus(compra.getStatus() + 10);

        if (compra.getStatus() == 100){
            var user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.addScore(User.convert(user) , compra.getScore());
        }

        // salvar
        repository.save(compra);
    }

    public void cacthCompra(Long id, OAuth2User user) {
        var optional = repository.findById(id);

        if (optional.isEmpty())
            throw new RuntimeException("compra não encontrada");

        var compra = optional.get();

        if (compra.getUser() != null)
            throw new RuntimeException("compra já atribuída");

        compra.setUser(User.convert(user));

        repository.save(compra);

    }

    public void dropCompra(Long id, OAuth2User user) {
        var optional = repository.findById(id);

        if (optional.isEmpty())
            throw new RuntimeException("compra não encontrada");

        var compra = optional.get();

        if (compra.getUser() == null)
            throw new RuntimeException("compra não atribuída");
            
        if (!compra.getUser().equals(User.convert(user)))
            throw new RuntimeException("não pode largar compra de outra pessoa");

        compra.setUser(null);

        repository.save(compra);
    }
    }
