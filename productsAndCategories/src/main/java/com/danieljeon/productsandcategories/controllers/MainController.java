package com.danieljeon.productsandcategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.danieljeon.productsandcategories.models.Category;
import com.danieljeon.productsandcategories.models.Product;
import com.danieljeon.productsandcategories.services.MainService;

@Controller
public class MainController {
	@Autowired
	private final MainService mainService;
	
	public MainController(MainService ms) {
		this.mainService = ms;
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		List<Product> products = mainService.allProducts();
		List<Category> categories = mainService.allCategories();;
		model.addAttribute("products", products);
		model.addAttribute("categories", categories);
		return "index.jsp";
	}
	
	@RequestMapping("/products/new")
	public String newProduct(@ModelAttribute("product") Product product) {
		return "products/new.jsp";
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		if (result.hasErrors()) {
			return "products/new.jsp";
		}
		else {
			mainService.createProduct(product);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/categories/new")
	public String newCategory(@ModelAttribute("category") Category category) {
		return "categories/new.jsp";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public String createCategory(@Valid @ModelAttribute("category") Category category, BindingResult result) {
		if (result.hasErrors()) {
			return "categories/new.jsp";
		}
		else {
			mainService.createCategory(category);
			return "redirect:/";
		}
	}
	
	@RequestMapping("/products/{id}")
	public String showProduct(@PathVariable("id") Long id, Model model) {
		Product currentProduct = mainService.findProduct(id);
		List<Category> currentProductCategories = currentProduct.getCategories();
		List<Category> otherCategories = mainService.allCategories();
		otherCategories.removeAll(currentProductCategories);
		model.addAttribute("currentProduct", currentProduct);
		model.addAttribute("currentProductCategories", currentProductCategories);
		model.addAttribute("otherCategories", otherCategories);
		return "products/show.jsp";
	}
	
	@RequestMapping("/categories/{id}")
	public String showCategory(@PathVariable("id") Long id, Model model) {
		Category currentCategory = mainService.findCategory(id);
		List<Product> currentCategoryProducts = currentCategory.getProducts();
		List<Product> otherProducts = mainService.allProducts();
		otherProducts.removeAll(currentCategoryProducts);
		model.addAttribute("currentCategory", currentCategory);
		model.addAttribute("currentCategoryProducts", currentCategoryProducts);
		model.addAttribute("otherProducts", otherProducts);
		return "categories/show.jsp";
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
	public String addCategoryToProduct(@PathVariable("id") Long id, @RequestParam("addedCategory") Long categoryId) {
		Product currentProduct = mainService.findProduct(id);
		mainService.addCategoryToProduct(currentProduct, categoryId);
		return "redirect:/products/" + id;
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.POST)
	public String addProductToCategory(@PathVariable("id") Long id, @RequestParam("addedProduct") Long productId) {
		Category currentCategory = mainService.findCategory(id);
		mainService.addProductToCategory(currentCategory, productId);
		return "redirect:/categories/" + id;
	}
}
