package ru.javarush.alex.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "checkbox")
    private Boolean needed;

    @NotNull(message = "Amount is mandatory")
    private Integer amount;

    public Part() {
    }

    public Part(@NotBlank(message = "Name is mandatory") String name,
                @NotBlank(message = "checkbox") Boolean needed,
                @NotBlank(message = "Amount is mandatory") Integer amount) {
        this.name = name;
        this.needed = needed;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNeeded() {
        return needed;
    }

    public void setNeeded(Boolean needed) {
        this.needed = needed;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", needed=" + needed +
                ", amount=" + amount +
                '}';
    }
}
