package backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import backend.models.Exercise;
import backend.services.ExerciseService;

import java.net.URI;
import java.util.List;

/* A controller handles requests and directs the dataflow between the client
 * and existing services. */
@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    /* GET request, returns a list of all entries in the table 'exercises'. */
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        List<Exercise> exercises = exerciseService.getAllExercises();
        return ResponseEntity.ok().body(exercises);
    }

    /* POST request, adds a new entry to 'exercises'. */
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise createdExercise = exerciseService.createExercise(exercise);
        return ResponseEntity.created(URI.create("/exercises/" + createdExercise.getId())).body(createdExercise);
    }
}