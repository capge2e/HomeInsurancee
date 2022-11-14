package com.abc.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.abc.insurance.entity.HomeInsurance;


@Repository
public interface HomeInsuranceRepository extends JpaRepository<HomeInsurance, Integer>,IHomeInsuranceCustomRepository
{


}
