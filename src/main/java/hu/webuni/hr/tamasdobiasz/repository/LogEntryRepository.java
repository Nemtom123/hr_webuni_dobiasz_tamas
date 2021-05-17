package hu.webuni.hr.tamasdobiasz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.tamasdobiasz.model.LogEntry;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long>{

}
