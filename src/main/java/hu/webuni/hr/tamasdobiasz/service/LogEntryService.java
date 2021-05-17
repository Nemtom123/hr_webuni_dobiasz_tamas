package hu.webuni.hr.tamasdobiasz.service;

import hu.webuni.hr.tamasdobiasz.model.LogEntry;
import hu.webuni.hr.tamasdobiasz.repository.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;

import java.time.LocalDateTime;
import java.util.Random;


@Service
public class LogEntryService {

    @Autowired
    LogEntryRepository logEntryRepository;

    public void createLog(String description) {
        logEntryRepository.save(
        		new LogEntry(0L, LocalDateTime.now(),
        		description, SecurityContextHolder.getContext().getAuthentication().getName()));
    }

    private void callBackendSystem() {
        if (new Random().nextInt(4)==1) {
            throw new RuntimeErrorException(null);
        }
    }

}
