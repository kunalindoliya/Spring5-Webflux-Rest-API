package kunal.springframework.spring5webfluxrest.controllers;

import kunal.springframework.spring5webfluxrest.domain.Category;
import kunal.springframework.spring5webfluxrest.respositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Kunal Indoliya.
 */
@RestController
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/v1/categories")
    public Flux<Category> list(){
       return categoryRepository.findAll();
    }

    @GetMapping("/api/v1/categories/{id}")
    public Mono<Category> getById(@PathVariable String id){
        return categoryRepository.findById(id);
    }
    @PostMapping("/api/v1/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@RequestBody  Publisher<Category> categoryPublisher){
        return categoryRepository.saveAll(categoryPublisher).then();
    }
    @PutMapping("/api/v1/categories/{id}")
    public Mono<Category> update(@RequestBody Category category,@PathVariable String id){
        category.setId(id);
        return categoryRepository.save(category);
    }
    @PatchMapping("/api/v1/categories/{id}")
    public Mono<Category> patch(@RequestBody Category category,@PathVariable String id){
      return categoryRepository.findById(id)
              .map(foundCategory ->{
                  if (!foundCategory.getDescription().equals(category.getDescription())){
                      foundCategory.setDescription(category.getDescription());
                       categoryRepository.save(foundCategory);
                  }
                  return foundCategory;
              });
    }
}
