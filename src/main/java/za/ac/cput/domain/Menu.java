package za.ac.cput.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Menu implements Serializable {
    @Id
    private String menuId;

    private String menuDetails;
    private String title;

    @Embedded
    private Order order;

    protected Menu(){
    }

    private Menu(Builder builder){
        this.menuId = builder.menuId;
        this.menuDetails = builder.menuDetails;
        this.title=builder.title;
        this.order = builder.order;
    }

    public String getMenuId() {return menuId;}

    public String getMenuDetails() {return menuDetails;}
    public String getTitle() {return title;}
    public Order getOrder() {return order;}

    @Override
    public String toString() {
        return "Menu{" +
                "menuId='" + menuId + '\'' +
                ", menuDetails='" + menuDetails + '\'' +
                ", title='" + title + '\'' +
                ", order=" + order +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return menuId.equals(menu.menuId) && menuDetails.equals(menu.menuDetails) && title.equals(menu.title) && order.equals(menu.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, menuDetails,title, order);
    }

    public static class Builder{
        private String menuId, menuDetails,title;
        private Order order;

        public Builder menuId(String menuId){
            this.menuId = menuId;
            return this;
        }

        public Builder menuDetails(String menuDetails){
            this.menuDetails = menuDetails;
            return this;
        }
        public Builder title(String title){
            this.title= title;
            return this;
        }

        public Builder order(Order order){
            this.order = order;
            return this;
        }

        public Menu build(){
            return new Menu(this);
        }
    }
}

