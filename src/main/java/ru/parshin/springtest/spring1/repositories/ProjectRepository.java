package ru.parshin.springtest.spring1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parshin.springtest.spring1.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
