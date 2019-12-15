package com.centralserver.kafka.consumer;

import com.centralserver.model.products.Product;
import com.centralserver.model.users.User;
import com.centralserver.repositories.ProductRepository;
import com.centralserver.repositories.UserRepository;
import com.centralserver.repositories.UserdataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KafkaSyncService {


 /*   @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserdataRepository userdataRepository;

    public void syncProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public void syncUser(User user) {
        userRepository.saveAndFlush(user);
    }

    public void syncUserdata(User user) {
        userRepository.saveAndFlush(user);
    }*/

}
