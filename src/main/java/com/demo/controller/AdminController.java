package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repository.AccountReponsitory;
import com.demo.repository.CategoryRepository;
import com.demo.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    CategoryRepository categoryRepository;

    //  Category
    @GetMapping("/admin/category/index")
    public String listCategory(Model model) {
        List<Category> listCategory = categoryRepository.findAll();
        model.addAttribute("listCategory", listCategory);
        return "admin/category/list";
    }

    @GetMapping("/admin/category/form")
    public String viewCategory() {
        return "admin/category/form";
    }

    @PostMapping("/admin/category/create")
    public String createCategory(@RequestParam("id") String id, @RequestParam("name") String name) {
        Category category = new Category().builder()
                .id(id)
                .name(name)
                .build();
        categoryRepository.save(category);
        return "redirect:/admin/category/index";
    }

    @PostMapping("/admin/category/update")
    public String updateCategory(@RequestParam("id") String id, @RequestParam("name") String name) {
       Category category = Category.builder()
               .id(id)
               .name(name)
               .build();
        Category findCategory = categoryRepository.findById(id).orElse(null);
        category.setId(findCategory.getId());
        BeanUtils.copyProperties(category, findCategory);
        categoryRepository.save(findCategory);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String editCategory(@PathVariable("id") String id, Model model) {
        Category category = categoryRepository.findById(id).orElse(null);
        model.addAttribute("category", category);
        return "admin/category/form_update";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/category/index";
    }

    //  Product
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/admin/product/index")
    public String listProduct(Model model) {
        List<Product>listProduct = productRepository.findAll();
        model.addAttribute("listProduct", listProduct);
        return "admin/product/list";
    }

    @GetMapping("/admin/product/form")
    public String viewProduct(Model model) {
       List<Category> category = categoryRepository.findAll();
       model.addAttribute("listCategory",category);
        return "admin/product/form";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(
                                @RequestParam("name") String name, @RequestParam("price") String price,
                                @RequestParam("createDate") String createDate, @RequestParam("category") String categoryID) {
        Category category = categoryRepository.findById(categoryID).orElse(null);
        Product product = Product.builder()
                .name(name)
                .price(Integer.valueOf(price))
                .createDate(Date.valueOf(createDate))
                .category(category)
                .build();
         productRepository.save(product);
        return "redirect:/admin/product/index";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(@RequestParam("id")Integer id,
                                @RequestParam("name") String name, @RequestParam("price") String price,
                                @RequestParam("createDate") String createDate, @RequestParam("category") String categoryID) {
        Category category = categoryRepository.findById(categoryID).orElse(null);
        Product product = Product.builder()
                .name(name)
                .price(Integer.valueOf(price))
                .createDate(Date.valueOf(createDate))
                .category(category)
                .build();
        Product findProduct = productRepository.findById(id).orElse(null);
        product.setId(findProduct.getId());
        BeanUtils.copyProperties(product, findProduct);
        productRepository.save(product);
        return "redirect:/admin/product/index";
    }


    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable("id") String id, Model model) {
        Product product = productRepository.findById(Integer.valueOf(id)).orElse(null);
        List<Category> category = categoryRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("listCategory",category);
        return "admin/product/form_update";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        productRepository.deleteById(Integer.valueOf(id));
        return "redirect:/admin/product/index";
    }

    //  Account
    @Autowired
    private AccountReponsitory accountReponsitory;

    @GetMapping("/admin/account/index")
    public String listAccount(Model model) {
        List<Account> listAcount = accountReponsitory.findAll();
        model.addAttribute("listAccount", listAcount);
        return "admin/account/list";
    }

    @GetMapping("/admin/account/form")
    public String viewAccount() {
        return "admin/account/form";
    }

    @PostMapping("/admin/account/create")
    public String createAccount(@RequestParam("user") String username, @RequestParam("pass") String password,
                                @RequestParam("fullname") String fullname, @RequestParam("email") String email) {
        Account account = Account.builder()
                .username(username)
                .password(password)
                .fullname(fullname)
                .email(email)
                .build();
        accountReponsitory.save(account);
        return "redirect:/admin/account/index";
    }

    @PostMapping("/admin/account/update")
    public String updateAccount(@RequestParam("user") String username, @RequestParam("pass") String password,
                                @RequestParam("fullname") String fullname, @RequestParam("email") String email) {
        Account account = Account.builder()
                .username(username)
                .password(password)
                .fullname(fullname)
                .email(email)
                .build();
        Account findAccount = accountReponsitory.findById(username).orElse(null);
        account.setUsername(findAccount.getUsername());
        BeanUtils.copyProperties(account, findAccount);
        accountReponsitory.save(findAccount);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/admin/account/edit/{id}")
    public String editAccount(@PathVariable("id") String username, Model model) {
        Account account = accountReponsitory.findById(username).orElse(null);
        model.addAttribute("account", account);
        return "admin/account/form_update";
    }

    @GetMapping("/admin/account/delete/{id}")
    public String delete(@PathVariable("id") String username) {
        accountReponsitory.deleteById(username);
        return "redirect:/admin/account/index";
    }
}
