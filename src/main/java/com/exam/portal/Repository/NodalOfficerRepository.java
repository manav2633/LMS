package com.exam.portal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.Model.NodalOfficer;

public interface NodalOfficerRepository extends JpaRepository<NodalOfficer, Integer> {

    NodalOfficer findByEmail(String email);

}
