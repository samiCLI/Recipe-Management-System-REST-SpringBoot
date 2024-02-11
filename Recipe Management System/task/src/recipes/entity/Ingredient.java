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
@Table(name="ingredients")
public class Ingredient {
    @Id
    @Column(name = "ingredient_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientId;

    @Column(name="ingredient_name")
    @NotBlank
    @NotEmpty()
    private String ingredientName;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @NotNull
    private Recipe recipe;

    public Ingredient() {}
    public Ingredient(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient(Recipe recipe, String ingredientName) {
        this.recipe = recipe;
        this.ingredientName = ingredientName;
    }

    public Ingredient(Recipe recipe, Long id) {
        this.recipe = recipe;
        this.ingredientId = id;
    }

    public Ingredient(Recipe recipe, Long id, String ingredientName) {
        this.recipe = recipe;
        this.ingredientId = id;
        this.ingredientName = ingredientName;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String toString() {
        return String.format("Ingredient id:%l Ingredient name:%s", ingredientId, ingredientName);
    }
}
