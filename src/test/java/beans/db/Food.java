package beans.db;

import utils.db.DataBaseUtils;

import java.sql.SQLException;
import java.util.List;

/***
 *Create a Bean class Food that has following fields:
 *  id, description, food_type, image_url,name,price
 *  Override and Implement equals() , hashcode(), compareTo() methods,
 *  that will help you to perform comparison and sorting operations.
 */
public class Food implements Comparable< Food > {
    private Integer id;
    private String description;
    private Integer food_type;
    private String image_url;
    private String name;
    private Double price;

    public Food(){}

    public Food(String line){
        String[] arr = line.split(",");
        this.id = Integer.parseInt(arr[0]);
        this.description = arr[1];
        this.food_type = Integer.parseInt(arr[2]);
        this.image_url = arr[3];
        this.name = arr[4];
        this.price = Double.parseDouble(arr[5]);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFood_type() {
        return food_type;
    }

    public void setFood_type(Integer food_type) {
        this.food_type = food_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static List<Food> getAll() throws SQLException {
        String q = "SELECT * FROM food";
        return DataBaseUtils.executeQueryToBean(Food.class, q);
    }

    @Override
    public int compareTo(Food o) {
        return this.getId().compareTo(o.getId());
    }

     @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Food food = (Food) o;
            return id.equals(food.id) &&
                    description.equals(food.description)
                    && food_type.equals(food.food_type)
                    && image_url.equals(food.image_url)
                    && name.equals(food.name)
                    && price.equals(food.price);
        }

        @Override
        public int hashCode() {
            return id;
        }

    //To see the data in Error messages
    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", food_type=" + food_type +
                ", image_url='" + image_url + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String toCSVString() {
        return  id + "," +
                description + "," +
                food_type + "," +
                image_url + "," +
                name + "," +
                price;
    }
}
