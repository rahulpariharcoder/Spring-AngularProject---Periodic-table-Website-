package com.example.demo.Persistence;
import com.example.demo.Domain.Element;
import com.example.demo.Domain.SignUpPage;

import java.util.Optional;

public interface SignUpPageRepository extends ModelRepository<SignUpPage>
{
    Optional<SignUpPage> findByUserName(String userName);
}
