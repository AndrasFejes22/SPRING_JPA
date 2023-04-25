package model;


import jakarta.persistence.*;

@Entity
@Table(name = "product", schema = "public")
@SequenceGenerator(name = "productIdGenerator", sequenceName = "product_seq", schema = "public", initialValue = 1, allocationSize = 1)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGenerator")
    @Column(name = "product_id")
    private int productID;

    @Column(name = "unit_price")
    private int unitPrice;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    private String name;

    private String description;

    public Product(int productID, int unitPrice, int stockQuantity, String name, String description) {
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.description = description;
    }

    public Product() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", unitPrice=" + unitPrice +
                ", stockQuantity=" + stockQuantity +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
