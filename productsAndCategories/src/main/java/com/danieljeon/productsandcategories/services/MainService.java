package com.danieljeon.productsandcategories.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieljeon.productsandcategories.models.Category;
import com.danieljeon.productsandcategories.models.Product;
import com.danieljeon.productsandcategories.repositories.CategoryRepo;
import com.danieljeon.productsandcategories.repositories.ProductRepo;

@Service
public class MainService {
	@Autowired
	private final ProductRepo productRepo;
	@Autowired
	private final CategoryRepo categoryRepo;
	
	public MainService(ProductRepo pr, CategoryRepo cr) {
		this.productRepo = pr;
		this.categoryRepo = cr;
	}
	
	public List<Product> allProducts() {
		return productRepo.findAll();
	}
	
	public List<Category> allCategories() {
		return categoryRepo.findAllByOrderByName();
	}
	
	public Product createProduct(Product product) {
		return productRepo.save(product);
	}
	
	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}
	
	public Product findProduct(Long id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			return optionalProduct.get();
		}
		else {
			return null;
		}
	}
	
	public Category findCategory(Long id) {
		Optional<Category> optionalCategory = categoryRepo.findById(id);
		if (optionalCategory.isPresent()) {
			return optionalCategory.get();
		}
		else {
			return null;
		}
	}
	
	public Product updateProduct(Product product) {
		Product editedProduct = this.findProduct(product.getId());
		editedProduct.setName(product.getName());
		editedProduct.setDescription(product.getDescription());
		editedProduct.setPrice(product.getPrice());
		return productRepo.save(product);
	}
	
	public Category updateCategory(Category category) {
		Category editedCategory = this.findCategory(category.getId());
		editedCategory.setName(category.getName());
		return categoryRepo.save(category);
	}
	
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}
	
	public void deleteCategory(Long id) {
		categoryRepo.deleteById(id);
	}
	
	public Product addCategoryToProduct(Product product, Long categoryId) {
		Product editedProduct = this.findProduct(product.getId());
		Category addedCategory = this.findCategory(categoryId);
		List<Category> currentCategories = editedProduct.getCategories();
		currentCategories.add(addedCategory);
		editedProduct.setCategories(currentCategories);
		return productRepo.save(product);
	}
	
	public Category addProductToCategory(Category category, Long productId) {
		Category editedCategory = this.findCategory(category.getId());
		Product addedProduct = this.findProduct(productId);
		List<Product> currentProducts = editedCategory.getProducts();
		currentProducts.add(addedProduct);
		editedCategory.setProducts(currentProducts);
		return categoryRepo.save(category);
	}
}
