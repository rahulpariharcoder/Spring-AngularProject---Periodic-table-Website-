package com.example.demo.Domain;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;



@MappedSuperclass
public abstract class Model
{
    @Id
    @Column(name = "id", updatable = false)
    @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    public Long getId()
//    {
//        return id;
//    }
}
