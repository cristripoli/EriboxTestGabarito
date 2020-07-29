package inatel.eribox.test.service;

import java.util.List;

import inatel.eribox.test.entity.Product;

public interface ProductService {
	public Product update(Product accessLog);
	public void delete(Product product);
	public Product findById(int productId);
	public List<Product> findAll();
}
