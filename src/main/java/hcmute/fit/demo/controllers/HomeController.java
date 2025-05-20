package hcmute.fit.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        // (Tuỳ chọn) Lấy danh sách sản phẩm nổi bật
        // List<Product> featuredProducts = productService.getFeaturedProducts();
        // model.addAttribute("featuredProducts", featuredProducts);

        return "home";
    }
}
