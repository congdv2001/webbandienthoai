package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Order;
import com.demo.model.OrderDetail;
import com.demo.model.Product;
import com.demo.repository.AccountReponsitory;
import com.demo.repository.OrderDetailRepo;
import com.demo.repository.OrderRepo;
import com.demo.repository.ProductRepository;
import com.demo.service.CartService;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    HttpSession session;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CartService cart;

    @Autowired
    AccountReponsitory accountRepo;

    @ModelAttribute("cart")
    CartService getCart(){
        return cart;
    }

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderDetailRepo orderDetailRepo;

    @Data @AllArgsConstructor
    public static class PriceRange{
        int id;
        int minValue;
        int maxValue;
        String display;
    }

    List<PriceRange> priceRangeList = Arrays.asList(
            new PriceRange(0,0, Integer.MAX_VALUE, "Tất cả"),
            new PriceRange(1,0, 10000000, "Dưới 10 triệu"),
            new PriceRange(2,10000000, 20000000, "Từ 10-20 triệu"),
            new PriceRange(3,20000000, Integer.MAX_VALUE, "Trên 20 triệu")
    );

    @RequestMapping("/")
    public String index(
            @RequestParam(defaultValue="") String keyword,
            @RequestParam(defaultValue="") String categoryId,
            @RequestParam(defaultValue="0") int priceRangeId,
            @RequestParam(defaultValue="0") int page,
            Model model) {

        if(session.getAttribute("username") == null){
            return  "redirect:/login";
        }
        model.addAttribute("priceRangeList", priceRangeList);
        model.addAttribute("categoryList", categoryService.getAll());
        model.addAttribute("productList", productService.getAll());

        int minPrice = priceRangeList.get(priceRangeId).minValue;
        int maxPrice = priceRangeList.get(priceRangeId).maxValue;

        System.out.println("keyword=" + keyword);
        System.out.println("categoryId=" + categoryId);
        System.out.println("minPrice=" + minPrice);
        System.out.println("maxPrice=" + maxPrice);
        System.out.println("page=" + page);

        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> data;
        if(categoryId.isEmpty()){
            data = productRepo.searchByNamePrice("%" + keyword + "%", minPrice, maxPrice, pageable);
        }else{
            data = productRepo.searchByCategoryNamePrice( categoryId,"%" + keyword + "%",minPrice,maxPrice,pageable);
        }
        model.addAttribute("page",data);
        return "home/index";
    }

    @GetMapping("/detail/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "home/detail";
    }

    @RequestMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable int id){
        cart.add(id);
        return "redirect:/cart";
    }

    @RequestMapping("/remove-cart/{id}")
    public String removeCart(@PathVariable int id) {
        cart.remove(id);
        if(cart.getTotal() == 0){
            return "redirect:/";
        }
        return "redirect:/cart";
    }

    @RequestMapping("/update-cart/{id}")
    public String updateCart(@PathVariable int id, int quantity) {
        cart.update(id, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String cart(){
        return "home/cart";
    }

    @GetMapping("/confirm")
    public String confirm(){
        return "home/confirm";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        return "home/about";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        Account acc = accountRepo.findByUsernameAndPassword(
                username, password).orElse(null);

        if(Objects.nonNull(acc)) {
            session.setAttribute("username", username);
            return "redirect:/";
        }
        model.addAttribute("message", "Tên đăng nhập/mật khẩu không đúng");
        return "login";
    }

    @PostMapping("/purchase")
    public String purchase(@RequestParam String address){
        System.out.println("address=" + address);
        System.out.println("items=" + cart.getItems());
        String un = (String) session.getAttribute("username");
        Account acc = accountRepo.findById(un).orElse(null);
        if(acc != null) {
            Order order = new Order();
            order.setAccount(acc);
            order.setAddress(address);
            orderRepo.save(order);
            for(OrderDetail item : cart.getItems()){
                item.setOrder(order);
                orderDetailRepo.save(item);
            }
            cart.clear();
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(){
        session.removeAttribute("username");
        return "redirect:/login";
    }
}
