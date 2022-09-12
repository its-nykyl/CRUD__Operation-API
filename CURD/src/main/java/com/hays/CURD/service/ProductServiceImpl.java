package com.hays.CURD.service;

import com.hays.CURD.model.Product;
import com.hays.CURD.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDb = this.productRepository.findById(product.getId());
        if (productDb.isPresent()) {
            Product productUpdate = productDb.get();
            productUpdate.setId(product.getId());
            productUpdate.setName(product.getName());
            productUpdate.setDesc(product.getDesc());
            productRepository.save(productUpdate);
            return productUpdate;
        }
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        Optional<Product> productDb = this.productRepository.findById(productId);
        if (productDb.isPresent()) {
            return productDb.get();
        }
        return null;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> productDb = this.productRepository.findById(id);
        if (productDb.isPresent()) {
            this.productRepository.delete(productDb.get());
        }


    }
}
