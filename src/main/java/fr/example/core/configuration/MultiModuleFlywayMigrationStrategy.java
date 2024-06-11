package fr.example.core.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class MultiModuleFlywayMigrationStrategy implements FlywayMigrationStrategy {

    @Override
    public void migrate(Flyway flyway) {
        DataSource dataSource = flyway.getConfiguration().getDataSource();

        Flyway studentModule = Flyway.configure()
                .schemas("students")
                .createSchemas(true)
                .locations("db/migration/student")
                .dataSource(dataSource)
                .load();
        studentModule.migrate();

        Flyway classroomModule = Flyway.configure()
                .schemas("classrooms")
                .createSchemas(true)
                .locations("db/migration/classroom")
                .dataSource(dataSource)
                .load();
        classroomModule.migrate();

        Flyway establishmentModule = Flyway.configure()
                .schemas("establishments")
                .createSchemas(true)
                .locations("db/migration/establishment")
                .dataSource(dataSource)
                .load();
        establishmentModule.migrate();

        Flyway eventModule = Flyway.configure()
                .createSchemas(true)
                .locations("db/migration/event")
                .dataSource(dataSource)
                .load();
        eventModule.migrate();
    }
}
