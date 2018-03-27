package agilepoker.repositories;

import org.springframework.data.repository.CrudRepository;

import agilepoker.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
