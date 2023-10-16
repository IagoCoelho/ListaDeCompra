package br.com.fiap.listacompra.compra;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
