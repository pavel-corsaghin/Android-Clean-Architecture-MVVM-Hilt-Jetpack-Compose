package io.github.pavel.jetpack.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pavel.jetpack.domain.model.News
import io.github.pavel.jetpack.domain.usecase.GetNewsListUseCase
import io.github.pavel.jetpack.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val useCase: GetNewsListUseCase
) : BaseViewModel() {

    private val _newsList = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> = _newsList

    fun getNewsList() {
        call({
            useCase.getNewsList()
        }, onSuccess = {
            _newsList.postValue(it)
        })
    }
}