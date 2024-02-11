package recipes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipe_id")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @NotBlank
    @NotEmpty()
    private String name;

    @Column(name = "description")
    @NotBlank
    @NotEmpty()
    private String description;

    @Column(name = "category")
    @NotBlank
    @NotEmpty()
    private String category;

    @Column(name = "date")
    private LocalDateTime date;


    @NotEmpty
    @ElementCollection
    @NotNull
    @Column(name = "ingredients")
    private List<String> ingredients;

    @NotEmpty
    @NotNull
    @ElementCollection
    @Column(name = "directions")
    private List<String> directions;

    //    @NotBlank
    @JsonIgnore
    @Column(name = "user_id")
    private Long user_id;

    //@ElementCollection
    //@CollectionTable(name = "USER", joinColumns = @JoinColumn(name = "recipe_id"))
    //private List<User> userList = new ArrayList<>();

    // @JsonIgnore
    ////Cant use cascade.all because user in that case will be deleted with recipe
    //@ManyToOne(cascade = CascadeType.MERGE)
    //@JoinColumn(name = "recipe_id")
    //private User user;




    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

    public void setDirection(String direction) {
        this.directions.add(direction);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}