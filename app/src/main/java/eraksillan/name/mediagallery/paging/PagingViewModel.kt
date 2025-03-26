package eraksillan.name.mediagallery.paging

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eraksillan.name.mediagallery.architecture.NetworkResult
import eraksillan.name.mediagallery.architecture.onException
import eraksillan.name.mediagallery.architecture.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class PagingViewModel<T : Any> : ViewModel() {

    abstract var getPageCallback: (page: Int, pageSize: Int) -> Flow<NetworkResult<List<T>>>

    val list = mutableStateListOf<T>()

    var canPaginate by mutableStateOf(false)
    var listState by mutableStateOf(PagingListState.IDLE)

    val pageNo: Int
        get() = page

    private var page by mutableIntStateOf(1)
    private val pageSize = 24

    private val isFirstPage: Boolean
        get() = page == 1
    private val canPaginateFurther: Boolean
        get() = (page != 1 && canPaginate)
    private val isInIdle: Boolean
        get() = (listState == PagingListState.IDLE)

    fun getPageData() {
        viewModelScope.launch {
            if (isFirstPage || (canPaginateFurther && isInIdle)) {
                listState = if (isFirstPage) {
                    PagingListState.LOADING
                } else {
                    PagingListState.PAGINATING
                }

                getPageCallback(page, pageSize).collect {
                    it.onSuccess {
                        canPaginate = it.size == pageSize

                        if (isFirstPage) {
                            list.clear()
                            list.addAll(it)
                        } else {
                            list.addAll(it)
                        }

                        listState = if (canPaginate) {
                            PagingListState.IDLE
                        } else {
                            PagingListState.PAGINATION_EXHAUST
                        }

                        if (canPaginate) {
                            page++
                        }
                    }.onException {
                        listState = if (isFirstPage) {
                            PagingListState.ERROR
                        } else {
                            PagingListState.PAGINATION_EXHAUST
                        }
                    }
                }
            }
        }
    }

    fun reset() {
        list.clear()
        page = 1
        listState = PagingListState.IDLE
        canPaginate = false
    }

    override fun onCleared() {
        reset()

        super.onCleared()
    }
}
