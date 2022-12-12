package com.example.demo.Persistence;

import com.example.demo.Domain.Model;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.Set;

public interface ModelRepository<T extends Model> extends PagingAndSortingRepository<T,Long>
{
    Set<T> findAll(Sort sort);
}
