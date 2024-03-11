package backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.models.Exercise;

/* A repository manages the connection to the database. */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    // define custom query methods here (which is typically not required)
}
