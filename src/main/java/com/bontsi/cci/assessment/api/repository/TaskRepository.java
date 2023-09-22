package com.bontsi.cci.assessment.api.repository;

import com.bontsi.cci.assessment.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
