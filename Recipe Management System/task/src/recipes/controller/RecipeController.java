package recipes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.businesslayer.RecipeService;
import recipes.entity.Recipe;
import recipes.entity.User;
import recipes.repository.UserRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@Validated
public class RecipeController {
    User user;
    @Autowired
    RecipeService recipeService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/recipe/new")
    public ResponseEntity<Object> setNewRecipe(@Valid @RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails userDetails) {
        user = userRepository.findUserByEmail(userDetails.getUsername());
        recipe.setUser_id(user.getId());
        recipeService.saveOrUpdate(recipe);

        Map<String, Long> respondedId = new HashMap<>();
        respondedId.put("id", recipe.getId());
        return new ResponseEntity<>(respondedId, HttpStatus.OK);
    }

    @PutMapping("recipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable int id, @Valid @RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails userDetails) {
        user = userRepository.findUserByEmail(userDetails.getUsername());
        Recipe recipeExistenceValidation = recipeService.getRecipeById((long) id);
        if (recipeExistenceValidation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else if (user.getId() == recipeExistenceValidation.getUser_id()) {
            recipe.setId((long) id);
            recipe.setUser_id(user.getId());
            recipeService.saveOrUpdate(recipe);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        Recipe recipe = recipeService.getRecipeById((long) id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    //@RequestParam --> When the parameter isn't specified, the method parameter is bound to null.
    @GetMapping("recipe/search")
    public ResponseEntity<List<Recipe>> searchRecipe(@RequestParam(required = false, name = "category") String category,
                                                     @RequestParam(required = false, name = "name") String name) {
        if ((category == null && name == null) || (!(category == null) && !(name == null))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!(category == null)) {
            return new ResponseEntity<>(recipeService.searchByCategory(category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(recipeService.searchByName(name), HttpStatus.OK);
        }
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<Object> deleteRecipe(@Valid @PathVariable int id, @AuthenticationPrincipal UserDetails userDetails) {
        user = userRepository.findUserByEmail(userDetails.getUsername());
        Recipe recipe = recipeService.getRecipeById((long) id);
        if (recipeService.getById((long) id).isPresent()) {
            if (recipe.getUser_id() == user.getId()) {
                recipeService.deletebyId((long) id);
                return ResponseEntity.noContent().build();
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}