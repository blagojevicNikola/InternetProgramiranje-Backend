package com.example.ip_etfbl_api.base;

import com.example.ip_etfbl_api.exceptions.NotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Getter
public abstract class CrudController<ID extends Serializable, REQ, RESP> {

    private final Class<RESP> respClass;
    private final CrudService<ID> crudService;

    protected CrudController(Class<RESP> respClass, CrudService<ID> crudService) {
        this.respClass = respClass;
        this.crudService = crudService;
    }

    @GetMapping
    public List<RESP> findAll()
    {
        return crudService.findAll(respClass);
    }

    @GetMapping("/{id}")
    public RESP findById(@PathVariable ID id) throws NotFoundException
    {
        return crudService.findById(id, respClass);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id)
    {
        crudService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RESP insert(@RequestBody REQ object)
    {
        return crudService.insert(object, respClass);
    }

    @PutMapping("/{id}")
    public RESP update(@RequestBody REQ object,@PathVariable ID id) throws NotFoundException
    {
        return crudService.update(id, object, respClass);
    }


}
