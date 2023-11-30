package com.malagueta.fintch.api;


import com.malagueta.fintch.domain_service.impl.ProductoServicoImpl;
import com.malagueta.fintch.domain_service.value.ProductoEstados;
import com.malagueta.fintch.entity.ProductoEntity;
import com.malagueta.fintch.port.input.services.ProductoServico;
import com.malagueta.fintch.port.output.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j(topic = "ProdutoAPI")
@RestController
public class ProdutoAPI {

    ProductoServico productoService;
    ProductoRepository productoRepository;

    public ProdutoAPI(ProductoRepository productoRepository ){
        this.productoService=new ProductoServicoImpl(productoRepository);
    }
    @PostMapping("producto/create")
    @CrossOrigin
    public ProductoEntity create(@RequestBody ProductoEntity producto){
        log.debug("hello");
        return productoService.creat(producto);
    }

    @PostMapping("producto/atualizar")
    @CrossOrigin
    public ProductoEntity atualizar(@RequestBody ProductoEntity producto){

        return productoService.atualizar(producto);
    }

    @GetMapping("producto/find")
    @CrossOrigin
    public List<ProductoEntity> listar( @RequestParam ProductoEstados status){

        return productoService.listarPorEstado(status);
    }

    @GetMapping("producto/listar")
    @CrossOrigin
    public List<ProductoEntity> listar( ){

        return productoService.listarTodos();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerProductFluxException(RuntimeException ex){

        return ex.getMessage();
    }


}
