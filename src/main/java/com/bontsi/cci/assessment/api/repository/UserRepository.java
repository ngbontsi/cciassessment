package com.bontsi.cci.assessment.api.repository;

import com.bontsi.cci.assessment.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
