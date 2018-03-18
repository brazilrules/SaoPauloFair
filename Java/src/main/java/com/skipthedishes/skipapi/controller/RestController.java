package com.skipthedishes.skipapi.controller;

import com.google.gson.Gson;
import com.skipthedishes.skipapi.entity.OrderEntity;
import com.skipthedishes.skipapi.entity.UserEntity;
import com.skipthedishes.skipapi.json.LoginResponse;
import com.skipthedishes.skipapi.json.OrderStatusResponse;
import com.skipthedishes.skipapi.repository.OrderRepository;
import com.skipthedishes.skipapi.repository.ProductRepository;
import com.skipthedishes.skipapi.repository.UserRepository;
import com.skipthedishes.skipapi.util.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private UserRepository userRepository;  
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    OrderRepository orderRepository;
    
    @GetMapping("/login/{user}/{password}")
    public String login(@PathVariable(name="user") String userName, @PathVariable(name="password") String password) {
        Gson gson = new Gson();
        LoginResponse response = new LoginResponse();
        try {
            UserEntity user = userRepository.findUserByUserNameAndUserPassword(userName, password);
            UserTokenManager tokenManager = UserTokenManager.getInstance();
            String token = tokenManager.generate(user);

            response.setToken(token);
            return gson.toJson(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            
            response.setError("There was an internal error. Please Try Again.");
            return gson.toJson(response);
        }
    }
    
    @GetMapping("/products/list/{token}")
    public String listProducts(@PathVariable(name="token") String token) {
        Gson gson = new Gson();
        try {
            UserTokenManager tokenManager = UserTokenManager.getInstance();
            if(!tokenManager.validate(token)) return gson.toJson(new OrderStatusResponse("Invalid token. Please login again")); 

            return gson.toJson(productRepository.findAll());
        } catch (Exception ex) {
            ex.printStackTrace();
            
            return gson.toJson(new OrderStatusResponse("There was an internal error. Please Try Again."));
        }
    }
    
    @GetMapping("/products/{id}/{token}")
    public String findProductById(@PathVariable(name="id") Long id, @PathVariable(name="token") String token) {
        Gson gson = new Gson();
        try {
            UserTokenManager tokenManager = UserTokenManager.getInstance();
            if(!tokenManager.validate(token)) return gson.toJson(new OrderStatusResponse("Invalid token. Please login again")); 

            return gson.toJson(productRepository.findById(id));
        } catch (Exception ex) {
            ex.printStackTrace();
            
            return gson.toJson(new OrderStatusResponse("There was an internal error. Please Try Again."));
        }   
    }
    
    @PostMapping("/orders/{token}")
    public String createOrder(@PathVariable(name="token") String token, @RequestBody OrderEntity order) {
        Gson gson = new Gson();
        try {
        UserTokenManager tokenManager = UserTokenManager.getInstance();
            if(!tokenManager.validate(token)) return gson.toJson(new OrderStatusResponse("Invalid token. Please login again")); 

            return gson.toJson(orderRepository.save(order));
        } catch (Exception ex) {
            ex.printStackTrace();
            
            return gson.toJson(new OrderStatusResponse("There was an internal error. Please Try Again."));
        }
    }
    
    @PutMapping("/orders/cancel/{token}")
    public String cancelOrder(@PathVariable(name="token") String token, @RequestBody OrderEntity order) {
        Gson gson = new Gson();
        try {
            UserTokenManager tokenManager = UserTokenManager.getInstance();
            if(!tokenManager.validate(token)) return gson.toJson(new OrderStatusResponse("Invalid token. Please login again")); 

            order.setOrderStatus(3);
            return gson.toJson(orderRepository.save(order));
        } catch (Exception ex) {
            ex.printStackTrace();
            
            return gson.toJson(new OrderStatusResponse("There was an internal error. Please Try Again."));
        }
    }
    
    @GetMapping("/orders/{id}/{token}")
    public String findOrderStatus(@PathVariable(name="token") String token, @PathVariable(name="id") Long orderId) {
        Gson gson = new Gson();
        try {
            UserTokenManager tokenManager = UserTokenManager.getInstance();
            if(!tokenManager.validate(token)) return gson.toJson(new OrderStatusResponse("Invalid token. Please login again")); 

            OrderEntity order = orderRepository.findById(orderId).orElse(null);
            if(order == null) return gson.toJson(new OrderStatusResponse("Order not found"));

            return gson.toJson(new OrderStatusResponse(order));
        } catch (Exception ex) {
            ex.printStackTrace();
            
            return gson.toJson(new OrderStatusResponse("There was an internal error. Please Try Again."));
        }
    }
}
