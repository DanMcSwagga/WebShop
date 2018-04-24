package ua.kpi.tef.model.entity;

public class Product {

    private int marking;
    private String title;
    private int price;

    public Product() {
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Product(int marking, String title, int price) {
        this.marking = marking;
        this.title = title;
        this.price = price;
    }

    public int getMarking() {
        return marking;
    }

    public void setMarking(int marking) {
        this.marking = marking;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "marking=" + marking +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
