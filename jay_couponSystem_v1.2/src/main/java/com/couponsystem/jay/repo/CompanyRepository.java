package com.couponsystem.jay.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.couponsystem.jay.beans.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
