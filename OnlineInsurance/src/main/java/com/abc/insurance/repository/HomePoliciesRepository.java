package com.abc.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.abc.insurance.entity.HomePolicies;


@Repository
public interface HomePoliciesRepository extends JpaRepository<HomePolicies, Integer>, IHomePoliciesCustomRepository
{
}
