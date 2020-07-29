package inatel.eribox.test.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import inatel.eribox.test.entity.Product;
import inatel.eribox.test.service.ProductService;

@Controller
@Scope("view")
public class ProductController implements Serializable{

	private static final long serialVersionUID = 1499046711178227904L;

	@Autowired
	private ProductService productService;
	
	private Product product;
	
	private List<Product> products;
	
	
	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		products = productService.findAll();
	}
	
	public void save() {
		productService.update(product);
		products = productService.findAll();
	}
	
	public void createProduct() {
		product = new Product();
	}
	
	public void delete() {
		productService.delete(product);
		products = productService.findAll();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
