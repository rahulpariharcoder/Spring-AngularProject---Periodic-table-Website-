package com.example.demo.Domain;

import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
public class Element extends Model
{

    @Max(120)
    @Min(1)
    @Column(nullable = false)
    @NotNull
    private int atomicNumber;
    @Column(name = "weight", nullable = false,precision=7,scale=4,updatable = false)
    @DecimalMax("294.0000")
    @DecimalMin("1.0080")
    @Pattern(regexp = "((\\d{1,3})((\\.\\d{1,4})?))$")
    @NotNull
    private String weight;
    public void setWeight(String weight)
    {
        this.weight = weight;
    }
    @Column(nullable=false)
    @Size(min=1,max=2)
    @NotNull
    private String symbol;
    @Column(nullable=false)
    @Size(min=1)
    @NotNull
    private String name;
    Element() {
        super();
    }
    public Element(int atomicNumber, String weight, String symbol, String name)
    {
        this();
        this.atomicNumber = atomicNumber;
        this.weight = weight;
        this.symbol = symbol;
        this.name = name;
    }
    public int getAtomicNumber()
    {
        return atomicNumber;
    }
    public String getWeight()
    {
        return weight;
    }
    public String getSymbol()
    {
        return symbol;
    }
    public String getName()
    {
        return name;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Element))
        {
            return false;
        }
        Element element = (Element) o;
        return getAtomicNumber() == element.getAtomicNumber() || Objects.equals(getWeight(), element.getWeight()) || Objects.equals(getSymbol(),
                                                                                                                                    element.getSymbol())
            || Objects.equals(getName(), element.getName());
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(getAtomicNumber(), getWeight(), getSymbol(), getName());
    }

}
