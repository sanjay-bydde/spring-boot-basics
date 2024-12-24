package com.sample.basics.JPA;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.basics.Pojo.Employee;

@Repository
public interface JpaRepo extends JpaRepository<Employee,Integer> {

}
