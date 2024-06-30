import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cinenest.MealApp
import com.example.cinenest.data.network.model.*
import com.example.cinenest.domain.repository.CategoriesRepository
import com.example.cinenest.domain.repository.RecipesDetailsRepository
import com.example.cinenest.domain.repository.RecipesRepository
import com.example.cinenest.domain.repository.SavedRecipesRepository
import com.example.cinenest.viewmodel.FavoriteRecipesListUiState
import com.example.cinenest.viewmodel.RecipeDetailsUiState
import com.example.cinenest.viewmodel.RecipeListUiState
import com.example.cinenest.viewmodel.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MealViewModel(
    private val recipesRepository: RecipesRepository,
    private val recipesDetailsRepository: RecipesDetailsRepository,
    private val categoriesRepository: CategoriesRepository,
    private val savedRecipesRepository: SavedRecipesRepository
): ViewModel() {
    var recipeListUiState: RecipeListUiState by mutableStateOf(RecipeListUiState.Loading)
    var recipeDetailsUiState: RecipeDetailsUiState by mutableStateOf(RecipeDetailsUiState.Loading)
    var favoriteRecipesListUiState: FavoriteRecipesListUiState by mutableStateOf(FavoriteRecipesListUiState.Loading)
    var searchUiState: SearchUiState by mutableStateOf(SearchUiState.Empty)

    var selectedRecipeName: String = ""
    var categories: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())

    val searchValue = mutableStateOf("")

    init {
        getCategoryList()
        observeCategoriesChanges()
        updateFavoriteRecipes()
    }


    private fun observeCategoriesChanges() {
        viewModelScope.launch {
            categories.collect { newCategories ->
                getRecipesByCategories(newCategories.map { it.strCategory })
            }
        }
    }


    fun getRecipesByCategories(categories: List<String>) {
        recipeListUiState = RecipeListUiState.Loading
        viewModelScope.launch {
            recipeListUiState = try {
                val allRecipes = mutableListOf<Recipe>()
                categories.forEach { category ->
                    val recipes = recipesRepository.getRecipesByCategory(category)
                    allRecipes.addAll(recipes)
                }
                RecipeListUiState.Success(allRecipes)
            } catch (e: IOException) {
                RecipeListUiState.Error
            } catch (e: HttpException) {
                RecipeListUiState.Error
            }
        }
    }

    fun getRecipeDetailsById(id: String) {
        recipeDetailsUiState = RecipeDetailsUiState.Loading
        viewModelScope.launch {
            recipeDetailsUiState = try {
                val recipeDetails = recipesDetailsRepository.getRecipeDetailsById(id)
                RecipeDetailsUiState.Success(
                    recipeDetails,
                    savedRecipesRepository.getRecipe(id) != null)
            } catch (e: IOException) {
                RecipeDetailsUiState.Error
            } catch (e: HttpException) {
                RecipeDetailsUiState.Error
            }
        }
    }


    fun getRecipeDetailsByObject(recipeDetails: RecipeDetails) {
        selectedRecipeName = recipeDetails.strMeal
        viewModelScope.launch {
            recipeDetailsUiState = RecipeDetailsUiState
                .Success(recipeDetails,
                    savedRecipesRepository.getRecipe(recipeDetails.idMeal) != null)
        }
    }


    fun getCategoryList() {
        viewModelScope.launch {
            try {
                categories.emit(categoriesRepository.getCategories())
            } catch (e: IOException) {
                Log.e("ForkTalesViewModel", e.toString())
            } catch (e: HttpException) {
                Log.e("ForkTalesViewModel", e.toString())
            }
        }
    }


    fun onSearchValueChange(newValue: String) {
        searchValue.value = newValue
    }


    fun getRecipesBySearchValue() {
        searchUiState = SearchUiState.Loading
        viewModelScope.launch {
            searchUiState = try {
                val recipes = recipesDetailsRepository.searchRecipes(searchValue.value)
                SearchUiState.Success(recipes)
            } catch (e: IOException) {
                SearchUiState.Error
            } catch (e: HttpException) {
                SearchUiState.Error
            }
        }
    }


    fun saveRecipe(recipeDetails: RecipeDetails) {
        viewModelScope.launch {
            savedRecipesRepository.insertRecipe(recipeDetails)
            updateFavoriteRecipes()
            recipeDetailsUiState = try {
                RecipeDetailsUiState.Success(
                    recipesDetailsRepository.getRecipeDetailsById(recipeDetails.idMeal),
                    true
                )
            } catch(e : IOException) {
                RecipeDetailsUiState.Error
            } catch(e : HttpException) {
                RecipeDetailsUiState.Error
            }
        }
    }


    fun deleteRecipe(recipeDetails: RecipeDetails) {
        viewModelScope.launch {
            savedRecipesRepository.deleteRecipe(recipeDetails)
            updateFavoriteRecipes()
            recipeDetailsUiState = try {
                RecipeDetailsUiState.Success(
                    recipesDetailsRepository.getRecipeDetailsById(recipeDetails.idMeal),
                    false
                )
            } catch(e : IOException) {
                RecipeDetailsUiState.Error
            } catch(e : HttpException) {
                RecipeDetailsUiState.Error
            }
        }
    }


    private fun updateFavoriteRecipes() {
        favoriteRecipesListUiState = FavoriteRecipesListUiState.Loading
        viewModelScope.launch {
            favoriteRecipesListUiState = try {
                val recipes: MutableList<Recipe> = mutableListOf()
                savedRecipesRepository.getSavedRecipes().forEach {
                    recipes.add(getRecipeFromRecipeDetails(it))
                }
                FavoriteRecipesListUiState.Success(recipes)
            } catch(e : IOException) {
                FavoriteRecipesListUiState.Error
            } catch(e : HttpException) {
                FavoriteRecipesListUiState.Error
            }
        }
    }


    private fun getRecipeFromRecipeDetails(recipeDetails: RecipeDetails): Recipe {
        return Recipe(
            idMeal = recipeDetails.idMeal,
            strMeal = recipeDetails.strMeal,
            strMealThumb = recipeDetails.strMealThumb,
            strCategory = recipeDetails.strCategory
        )
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MealApp)
                val recipesRepository = application.container.recipesRepository
                val recipesRepositoryDetails = application.container.recipesDetailsRepository
                val categoriesRepository = application.container.categoriesRepository
                val savedRecipesRepository = application.container.savedRecipesRepository
                MealViewModel(
                    recipesRepository = recipesRepository,
                    recipesDetailsRepository = recipesRepositoryDetails,
                    categoriesRepository = categoriesRepository,
                    savedRecipesRepository = savedRecipesRepository
                )
            }
        }
    }
}