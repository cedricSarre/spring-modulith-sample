package fr.example.core.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.CompletedEventPublications;
import org.springframework.modulith.events.IncompleteEventPublications;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.time.Duration.ofSeconds;

@Component
@RequiredArgsConstructor
@Slf4j
class EventPublications {
    private final IncompleteEventPublications incompleteEventPublications;
    private final CompletedEventPublications completedEventPublications;

    @Scheduled(fixedRate = 60_000, initialDelay = 1_000)
    void reSubmitIncompleteEventsOlderThan60s() {
        log.info("Resubmit incomplete publications");
        incompleteEventPublications.resubmitIncompletePublicationsOlderThan(ofSeconds(60));
    }

    @Scheduled(fixedRate = 60_000, initialDelay = 1_000)
    void listCompleteEvents() {
        int numberOfPublications = completedEventPublications.findAll().size();
        log.info("List of {} complete publications", numberOfPublications);
        completedEventPublications.findAll().forEach(event -> {
            log.info("Event Id={}", event.getIdentifier());
            log.info("Event={}", event.getEvent());
            log.info("Publication Date={}", event.getPublicationDate());
            log.info("Completion Date={}", event.getCompletionDate());
        });
    }
}
