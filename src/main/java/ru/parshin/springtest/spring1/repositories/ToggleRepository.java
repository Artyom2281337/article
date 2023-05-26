package ru.parshin.springtest.spring1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.parshin.springtest.spring1.models.Toggle;

public interface ToggleRepository extends JpaRepository<Toggle, Long> {
}
