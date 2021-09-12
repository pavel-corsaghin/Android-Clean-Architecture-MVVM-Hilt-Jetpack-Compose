package io.github.pavel.jetpack.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pavel.jetpack.domain.model.News
import io.github.pavel.jetpack.domain.usecase.GetNewsDetailUseCase
import io.github.pavel.jetpack.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetNewsDetailUseCase
) : BaseViewModel() {

    private val _showSmartMode = MutableLiveData(true)
    val showSmartMode: LiveData<Boolean> = _showSmartMode

    private val _news = MutableLiveData<News>()
    val news: LiveData<News> = _news

    fun getNewsById(newsId: String) {
        call({
            useCase.getNewsDetail(newsId)
        }, onSuccess = {
            _news.postValue(it)
        })
    }

    fun toggleMode() {
        _showSmartMode.value = !_showSmartMode.value!!
    }
}