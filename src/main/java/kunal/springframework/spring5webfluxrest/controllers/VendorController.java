package kunal.springframework.spring5webfluxrest.controllers;

import kunal.springframework.spring5webfluxrest.domain.Vendor;
import kunal.springframework.spring5webfluxrest.respositories.VendorRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/api/v1/vendors")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@RequestBody Publisher<Vendor> vendorPublisher){
        return vendorRepository.saveAll(vendorPublisher).then();
    }
    @PutMapping("/api/v1/vendors/{id}")
    public Mono<Vendor> update(@RequestBody Vendor vendor,@PathVariable String id){
        vendor.setId(id);
        return vendorRepository.save(vendor);
    }
    @PatchMapping("/api/v1/vendors/{id}")
    public Mono<Vendor> patch(@RequestBody Vendor vendor,@PathVariable String id){
        return vendorRepository.findById(id)
                .map(foundVendor ->{
                   if(!foundVendor.getFirstName().equals(vendor.getFirstName())){
                       foundVendor.setFirstName(vendor.getFirstName());
                       if(!foundVendor.getLastName().equals(vendor.getLastName()))
                           foundVendor.setLastName(vendor.getLastName());
                       vendorRepository.save(foundVendor);
                   }
                    return foundVendor;
                });

    }
}
