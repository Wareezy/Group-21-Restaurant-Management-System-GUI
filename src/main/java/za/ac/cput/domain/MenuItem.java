package za.ac.cput.domain;

import com.sun.istack.NotNull;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class MenuItem implements Serializable {
    @NotNull
    @Id
    private String title;
    @NotNull

    @Embedded
    private Order order;
    protected MenuItem(){}

    private MenuItem (Builder builder){

        this.title = builder.title;
        this.order = builder.order;

    }
    public String getTitle() {
        return title;
    }

    public Order getOrder() {
        return order;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem that = (MenuItem) o;
        return title.equals(that.title) && order.equals(that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, order);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "title='" + title + '\'' +
                ", order=" + order +
                '}';
    }

    public static class Builder {
        private String title;
        private Order order;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }
        public Builder copy(MenuItem menuItem){
            this.title = menuItem.title;
            this.order = menuItem.order;
            return this;
        }

        public MenuItem build(){
            return new MenuItem(this);
        }
    }
}

