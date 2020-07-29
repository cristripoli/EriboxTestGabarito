package inatel.eribox.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inatel.eribox.test.dao.ProductDAO;
import inatel.eribox.test.entity.Product;
import inatel.eribox.test.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	public Product update(Product product) {
		return productDAO.update(product);
	}

	@Override
	public void delete(Product product) {
		productDAO.delete(product);
	}

	@Override
	public Product findById(int productId) {
		return productDAO.findById(productId);
	}

	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}
}