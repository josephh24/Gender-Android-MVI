package com.example.mviproyect.ui

import com.example.mviproyect.domain.model.GenderUser
import com.example.mviproyect.domain.repository.GenderUserRepository
import com.example.mviproyect.domain.use_case.GetGenderUserUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GenderViewModelTest {

    // Test dispatcher to control coroutines execution
    private val dispatcher = StandardTestDispatcher()

    private lateinit var repository: GenderUserRepository
    private lateinit var viewModel: GenderViewModel
    private lateinit var useCase: GetGenderUserUseCase

    @Before
    fun setup() {
        // Replace Main dispatcher with test dispatcher
        Dispatchers.setMain(dispatcher)

        // Create mocks
        repository = mockk()
        useCase = mockk()

        // Create ViewModel with mocked use case
        viewModel = GenderViewModel(useCase)
    }

    @After
    fun tearDown() {
        // Restore original Main dispatcher
        Dispatchers.resetMain()
    }

    @Test
    fun `when searching name updates state correctly`() = runTest {
        // Given: a successful user result
        val user = GenderUser(100, "Pedro", "male", 0.9F)

        coEvery { useCase.invoke("Pedro") } returns flowOf(Result.success(user))

        // When: sending search intent
        viewModel.setIntent(GenderIntent.SearchGenderUser("Pedro"))
        advanceUntilIdle() // Execute pending coroutines

        // Then: state is updated with user data
        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertEquals("Pedro", state.genderUser?.name)
    }

    @Test
    fun `when error occurs emits toast event`() = runTest {
        // Given: an error result
        coEvery { useCase.invoke("Juan") } returns
                flowOf(Result.failure(Exception("Error")))

        // When: sending search intent
        viewModel.setIntent(GenderIntent.SearchGenderUser("Juan"))
        advanceUntilIdle()

        // Then: an error event is emitted
        val event = viewModel.effect.first()
        assertTrue(event is GenderEvent.ShowToast)
    }
}
