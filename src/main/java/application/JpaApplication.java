package application;

import config.JpaConfiguration;
import dao.ProductDao;
import model.Customer;
import model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import repository.CustomerRepository;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;

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

        //LOGGER.info("customerList: {}", customerList);

        queryByExample(customerRepository);

        ProductDao productDao = new ProductDao();
        List<Customer> customersByPattern = productDao.findCustomerByPattern("D");
        //LOGGER.info("customersByPattern: {}", customersByPattern);
        for (int i = 0; i < customersByPattern.size(); i++) {
            LOGGER.info("CustomerID: {}", customersByPattern.get(i).getCustomerID());
            LOGGER.info("LastName: {}", customersByPattern.get(i).getLastName());
            LOGGER.info("FirstName: {}", customersByPattern.get(i).getFirstName());
            System.out.println("------------------------");
        }

        List<String> customerNameByPattern = productDao.findCustomerNameByPattern("ová");
        LOGGER.info("customersByPattern: {}", customerNameByPattern);

    }

    // Example Matchers
    private static void queryByExample(CustomerRepository customerRepository) {

        Customer customer = new Customer(); // use a default constructor
        customer.setFirstName("B");
        customer.setEmail("gmail");

        //ExampleMatcher customerMatcher = ExampleMatcher.matching().withIgnorePaths("customerID", "active");
        ExampleMatcher customerMatcher = ExampleMatcher.matching()
                .withIgnorePaths("customerID", "active")
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("firstName", matcher -> matcher.startsWith());


        Example<Customer> userExample = Example.of(customer, customerMatcher);

        List<Customer> pendingUsers = customerRepository.findAll(userExample);

        LOGGER.info("Pending users: {}", pendingUsers);

        Optional<Customer> repositoryBy = customerRepository.findBy(userExample, query -> query.stream().findFirst());

        LOGGER.info("repositoryBy: {}", repositoryBy);

    }
}
