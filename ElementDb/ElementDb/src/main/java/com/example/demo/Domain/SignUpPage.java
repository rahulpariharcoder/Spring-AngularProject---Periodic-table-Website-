package com.example.demo.Domain;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
@Entity
public class SignUpPage extends Model
{

    @Size(min=1)
    @NotNull
    @Pattern(regexp = "^[A-Za-z].*$")
    String userName;

    @NotNull
    @Size(min=1)
    String password;

    SignUpPage() {
        super();
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    public SignUpPage(String userName, String password)
    {
        this();
        this.userName = userName;
        this.password = password;
    }
    public String getUserName()
    {
        return userName;
    }
    public String getPassword()
    {
        return password;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof LoginPage))
        {
            return false;
        }
        LoginPage loginPage = (LoginPage) o;
        return Objects.equals(getUserName(), loginPage.getUserName()) && Objects.equals(getPassword(), loginPage.getPassword());
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(getUserName(), getPassword());
    }
}
