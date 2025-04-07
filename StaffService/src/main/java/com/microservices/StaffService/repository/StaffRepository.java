package com.microservices.StaffService.repository;

import java.util.List;

import com.microservices.StaffService.model.StaffModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffModel,Integer>{

}
