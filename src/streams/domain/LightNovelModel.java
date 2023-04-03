package streams.domain;

public class LightNovelModel {
    private String title;
    private double price;
    private CategoryEnum category;

    public LightNovelModel(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public LightNovelModel(String title, double price, CategoryEnum category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }
    @Override
    public String toString() {
        return "LightNovelModel{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }
}
