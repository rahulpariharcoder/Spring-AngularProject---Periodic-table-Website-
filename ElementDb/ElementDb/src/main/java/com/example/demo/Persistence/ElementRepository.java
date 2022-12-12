package com.example.demo.Persistence;

import com.example.demo.Domain.Element;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public interface ElementRepository extends ModelRepository<Element>
{

    Optional<Element> findElementByAtomicNumber(int atomicNumber);

    Optional<Element> findByWeight(double weight);

    Optional<Element> findBySymbol(String symbol);

    Optional<Element> findByName(String symbol);
}
