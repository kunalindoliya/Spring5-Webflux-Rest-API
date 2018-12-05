package kunal.springframework.spring5webfluxrest.controllers;

import kunal.springframework.spring5webfluxrest.domain.Vendor;
import kunal.springframework.spring5webfluxrest.respositories.VendorRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Kunal Indoliya.
 */
@RestController
public class VendorController  {
    private final VendorRepository vendorRepository;

    public VendorController(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }
    @GetMapping("/api/v1/vendors")
    public Flux<Vendor> list(){
        return vendorRepository.findAll();
    }
    @GetMapping("/api/v1/vendors/{id}")
    public Mono<Vendor> getById(@PathVariable String id){
        return vendorRepository.findById(id);
    }
}