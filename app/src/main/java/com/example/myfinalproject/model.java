package com.example.myfinalproject;

public class model {
    String pname,Category,price,description,image;
    public model()
    {

    }

    public model(String pname, String category, String price, String description, String image) {
        this.pname = pname;
        Category = category;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public String getPname() {
        return pname;
    }

    public String getCategory() {
        return Category;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
