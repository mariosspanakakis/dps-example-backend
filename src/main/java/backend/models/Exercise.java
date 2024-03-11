package backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* An entitiy corresponds to a table in the database. It is important that the
 * specified datatypes match exactly, otherwise the table will not be found. */
@Entity
@Table(name = "exercises")
public class Exercise {

    // decorators for ID generation, will autoincrement the ID of each entry
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;            // -> in postgres type SERIAL PRIMARY KEY
    private String title;       // -> in postgres type VARCHAR(255)
    private String description; // -> in postgres type TEXT
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
