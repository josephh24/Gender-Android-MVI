package com.example.mviproyect

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.mviproyect.domain.model.GenderUser
import com.example.mviproyect.ui.GenderIntent
import com.example.mviproyect.ui.GenderScreen
import com.example.mviproyect.ui.GenderUiState
import com.example.mviproyect.ui.GenderUserInfo
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class GenderScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenTypingInNameField_textIsDisplayed() {
        // 1. Arrange: Set up the screen with an initial state
        composeTestRule.setContent {
            GenderScreen(
                uiState = GenderUiState(isLoading = false),
                onIntent = {}
            )
        }

        // 2. Act: Find the text field by its label and type text
        composeTestRule.onNodeWithText("Name")
            .performTextInput("Juan")

        // 3. Assert: Verify that the text was correctly entered
        composeTestRule.onNodeWithText("Juan").assertExists()
    }

    @Test
    fun whenNameIsEnteredAndSearchClicked_searchIntentIsFired() {
        var receivedIntent: GenderIntent? = null

        // Arrange
        composeTestRule.setContent {
            GenderScreen(
                uiState = GenderUiState(),
                onIntent = { receivedIntent = it }
            )
        }

        // Act
        composeTestRule
            .onNodeWithTag("NameTextField")
            .performTextInput("Pedro")

        composeTestRule
            .onNodeWithTag("SearchButton")
            .performClick()

        // Assert
        Assert.assertTrue(receivedIntent is GenderIntent.SearchGenderUser)
        Assert.assertEquals(
            "Pedro",
            (receivedIntent as GenderIntent.SearchGenderUser).name
        )
    }

    @Test
    fun whenIsLoading_progressIndicatorIsShown() {
        // Arrange
        composeTestRule.setContent {
            GenderUserInfo(
                isLoading = true,
                genderUser = null
            )
        }

        // Assert
        composeTestRule
            .onNodeWithTag("Loading")
            .assertIsDisplayed()
    }

    @Test
    fun whenUserIsAvailable_userDataIsShown() {
        // Arrange
        val user = GenderUser(100, "Ana", "female", 0.8f)
        composeTestRule.setContent {
            GenderUserInfo(
                isLoading = false,
                genderUser = user
            )
        }

        // Act (Wait for UI to settle)
        composeTestRule.waitForIdle()

        // Assert
        composeTestRule.onNodeWithText("Ana").assertIsDisplayed()
        composeTestRule.onNodeWithText("female", substring = true).assertIsDisplayed()
    }
}