package agilepoker.services;

import org.springframework.beans.factory.annotation.Autowired;

import agilepoker.domain.Product;
import agilepoker.repositories.ProductRepository;


public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public void setProductRepository(ProductRepository productRepository) {
	    this.productRepository = productRepository;
	}
	
	@Override
	public Iterable <Product> listAllProducts() {
	    return productRepository.findAll();
	}
	
	@Override
	public Product getProductById(Integer id) {
	    return productRepository.findOne(id);
	}
	
	@Override
	public Product saveProduct(Product product) {
	    return productRepository.save(product);
	}
	
	@Override
	public void deleteProduct(Integer id) {
	    productRepository.delete(id);
	}
	
}
