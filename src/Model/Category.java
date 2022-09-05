package Model;

import java.util.List;

public class Category {
    private String name;
    private List <Category> categories; //Sottocategorie, recursive

    public Category(String name) {
        this.name = name;
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategories(Category c) {
        categories.add(c);
    }

    public void addCategories(List <Category> c){
        categories.addAll(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
