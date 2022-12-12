package com.example.demo.Domain;

import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class ErrorField extends Model
{
    String error;
    ErrorField() {
        super();
    }
    public ErrorField(String error)
    {
        this();
        this.error = error;
    }
    public String getError()
    {
        return error;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof ErrorField))
        {
            return false;
        }
        ErrorField error1 = (ErrorField) o;
        return Objects.equals(getError(), error1.getError());
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(getError());
    }
}
