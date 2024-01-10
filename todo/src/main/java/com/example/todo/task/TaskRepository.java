package com.example.todo.task;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository("This is a repository class")
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByOrderByIdAsc(Pageable pageable);

    Page<Task> findAllByUserIdOrderByIdAsc(Long userId, Pageable pageable);

    Page<Task> findAllByUserIdAndStatusOrderByIdAsc(@Param("userId") Long userId, @Param("status") Task.Status status, Pageable pageable);

}