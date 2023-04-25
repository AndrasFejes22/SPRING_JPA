package application;

import config.JpaConfiguration;
import model.Customer;
import model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.CustomerRepository;
import repository.ProductRepository;

import java.util.List;

public class JpaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);

        ProductRepository productRepository = context.getBean(ProductRepository.class);
        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);

        Product product = productRepository.findById(1L).orElseThrow();
        List<Customer> customerList = customerRepository.findAll();

        //LOGGER.info("product: {}", product);

        List<Product> productList = productRepository.findAll();

        //LOGGER.info("productList: {}", productList);

        LOGGER.info("customerList: {}", customerList);



    }
}
