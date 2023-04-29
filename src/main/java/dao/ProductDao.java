package dao;

import config.JpaConfiguration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import model.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductDao {

    ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);

    //@Autowired
    EntityManager entityManager = context.getBean(EntityManager.class);

    public List<String> findCustomerNameByPattern(String pattern) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);

        query.select(customer);
        query.where(builder.and(
                builder.equal(customer.get("active"), false),
                builder.like(customer.get("lastName"), "%" + pattern + "%")));

        // only names:
        List<String> customers = entityManager.createQuery(query).getResultList().stream().map(c -> c.getLastName()).collect(Collectors.toList());

        return customers;
    }

    public List<Customer> findCustomerByPattern(String pattern) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);
        Root<Customer> customer = query.from(Customer.class);

        query.select(customer);
        query.where(builder.and(
                builder.equal(customer.get("active"), true),
                builder.like(customer.get("lastName"), "%" + pattern + "%")));

        // only names:
        List<Customer> customers = entityManager.createQuery(query).getResultList();

        return customers;
    }
}
