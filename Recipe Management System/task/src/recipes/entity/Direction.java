package recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
//@NoArgsConstructor

@Entity
@Table(name="directions")
public class Direction {
    @Id
    @Column(name = "direction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long directionId;

    @Column(name = "direction_name")
    @NotBlank
    @NotEmpty()
    private String directionName;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @NotNull
    private Recipe recipe;

    public Direction() {}
    public Direction(Recipe recipe) {
        this.recipe = recipe;
    }

    public Direction(Recipe recipe, String directionName) {
        this.recipe = recipe;
        this.directionName = directionName;
    }

    public Direction(Recipe recipe, Long id, String directionName) {
        this.recipe = recipe;
        this.directionName = directionName;
        this.directionId = id;
    }

    public Direction(Recipe recipe, Long id) {
        this.recipe = recipe;
        this.directionId = id;
    }

    public long getDirectionId() {
        return directionId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setDirectionId(long directionId) {
        this.directionId = directionId;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }

    public String getDirectionName() {
        return directionName;
    }

    @Override
    public String toString() {
        return String.format("Direction id:%l Direction name:%s", directionId, directionName);
    }
}
