package com.example.dishdiary.presentation.screen.searchscreen

import com.example.dishdiary.viewmodel.MealViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.dishdiary.R
import com.example.dishdiary.data.network.model.RecipeDetails
import com.example.dishdiary.viewmodel.SearchUiState

@Composable
fun SearchScreen(
    viewModel: MealViewModel,
    onRecipeListItemClicked: (RecipeDetails) -> Unit,
    modifier: Modifier = Modifier,
) {
    val searchValue by viewModel.searchValue
    val searchUiState = viewModel.searchUiState

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        OutlinedTextField(
            value = searchValue,
            onValueChange = { viewModel.onSearchValueChange(it) },
            label = { Text(stringResource(id = R.string.search)) },
            trailingIcon = {
                if (searchValue.isNotEmpty()) {
                    IconButton(onClick = {
                        viewModel.onSearchValueChange("")
                    }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.getRecipesBySearchValue()
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        when (searchUiState) {
            is SearchUiState.Success -> {
                if(searchUiState.recipes.isEmpty()) {
                    Box(contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = stringResource(R.string.no_results_found),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                } else {
                    RecipeSearcItem(
                        recipes = searchUiState.recipes,
                        onRecipeListItemClicked = onRecipeListItemClicked,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
            is SearchUiState.Empty -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.empty_items_please_do_a_search),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            is SearchUiState.Loading ->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator() // Circular indicator
                        Text(
                            text = stringResource(R.string.loading),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
            is SearchUiState.Error -> {
                Text(
                    text = stringResource(R.string.error_something_went_wrong),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
