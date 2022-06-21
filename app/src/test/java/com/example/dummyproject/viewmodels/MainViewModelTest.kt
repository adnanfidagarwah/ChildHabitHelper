package com.example.dummyproject.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.dummyproject.data.LocalDataSource
import com.example.dummyproject.data.RemoteDataSource
import com.example.dummyproject.data.repository.RepositoryImpl
import com.example.dummyproject.data.database.RepositoriesDao
import com.example.dummyproject.ui.MainViewModel
import com.example.dummyproject.ui.model.RepositoriesResponse
import com.example.dummyproject.util.NetworkResult
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : TestCase() {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var repository: RepositoryImpl
    private lateinit var remote: RemoteDataSource
    private lateinit var local: LocalDataSource
    private lateinit var viewModel: MainViewModel
    private lateinit var repositoriesDao: RepositoriesDao


    @Before
    public override fun setUp() {
        Dispatchers.setMain(testDispatcher)

        remote = mock(RemoteDataSource::class.java)
        local = mock(LocalDataSource::class.java)

        repositoriesDao = mock(RepositoriesDao::class.java)

        repository = RepositoryImpl(remoteDataSource = remote, localDataSource = local)

        viewModel = MainViewModel(repository)
    }

    @After
    public override fun tearDown() {
        super.tearDown()
        Dispatchers.resetMain()
    }


    @Test
    fun getRepositoriesSuccessCase() = testDispatcher.runBlockingTest {
        val repositoriesResponseObserver =
            mockk<NetworkResult<RepositoriesResponse>>(relaxed = true)

        Mockito.`when`(repository.remote.getRepositories())
            .thenReturn(Response.success(RepositoriesResponse()))

        Mockito.`when`(repository.local.readRepositories())
            .thenReturn(RepositoriesResponse())

        viewModel.getRepositories()

        viewModel.repositoriesResponse.observeForever { repositoriesResponseObserver.data }

        Assert.assertTrue(viewModel.repositoriesResponse.value?.data?.incompleteResults == false)
        Assert.assertTrue(viewModel.repositoriesResponse.value?.data?.items?.isEmpty() == true)

    }
}