package kunal.springframework.spring5webfluxrest.bootstrap;

import kunal.springframework.spring5webfluxrest.domain.Category;
import kunal.springframework.spring5webfluxrest.domain.Vendor;
import kunal.springframework.spring5webfluxrest.respositories.CategoryRepository;
import kunal.springframework.spring5webfluxrest.respositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Kunal Indoliya.
 */
@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count().block()==0){
            System.out.println("### Loading Data ###");
            categoryRepository.save(new Category("Fruits")).block();
            categoryRepository.save(new Category("Nuts")).block();
            categoryRepository.save(new Category("Dried")).block();
            categoryRepository.save(new Category("Fresh")).block();

            System.out.println("Loaded Categories:"+categoryRepository.count().block());

            vendorRepository.save(new Vendor("Kunal","Indoliya")).block();
            vendorRepository.save(new Vendor("Mike","Rolls")).block();
            vendorRepository.save(new Vendor("Joe","Buck")).block();

            System.out.println("Loaded Vendors:"+vendorRepository.count().block());
        }

    }
}
