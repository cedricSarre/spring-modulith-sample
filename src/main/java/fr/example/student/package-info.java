@org.springframework.modulith.ApplicationModule(
        allowedDependencies = {"establishment", "establishment::establishment-event-spi", "classroom", "core::core" +
                "-exception-spi"}
)
package fr.example.student;