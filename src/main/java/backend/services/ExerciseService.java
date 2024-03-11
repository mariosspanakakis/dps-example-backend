package backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Exercise;
import backend.repositories.ExerciseRepository;

import java.util.List;

/* A service implements the backend logic such as data validation, manipulation,
 * etc., and serves as a bridge from the controller to the repository. */
@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    public Exercise createExercise(Exercise exercise) {
        // validity check
        if (exercise.getTitle() == null || exercise.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        return exerciseRepository.save(exercise);
    }
}