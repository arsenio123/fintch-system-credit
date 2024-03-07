package com.malagueta.fintch.api;


import com.malagueta.fintch.config.AppConfig;
import com.malagueta.fintch.domain_service.impl.factory.ProductoServicoFactory;
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

    private ProductoServico productoService;
    private ProductoRepository productoRepository;
    private AppConfig config;

    public ProdutoAPI(ProductoRepository productoRepository, AppConfig config ){
        this.productoRepository=productoRepository;
        this.config=config;
        this.productoService= ProductoServicoFactory.getProductoService(config.getProductoServiceImpl(), productoRepository);
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
    public List<ProductoEntity> listarPorEstado( @RequestParam ProductoEstados status){

        return productoService.listarPorEstado(status);
    }

    @GetMapping("producto/listar")
    @CrossOrigin
    public List<ProductoEntity> listAll( ){

        return productoService.listarTodos();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerProductFluxException(RuntimeException ex){

        return ex.getMessage();
    }


}
