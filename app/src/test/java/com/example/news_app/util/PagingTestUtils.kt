package com.example.news_app.util

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.TestScope

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T : Any> PagingData<T>.collectData(
    diffCallback: DiffUtil.ItemCallback<T>, testScope: TestScope
): List<T> {
    val differ = AsyncPagingDataDiffer(
        diffCallback = diffCallback,
        updateCallback = NoopListUpdateCallback,
        workerDispatcher = Dispatchers.Main
    )

    differ.submitData(this)
    testScope.advanceUntilIdle()

    return differ.snapshot().items
}


@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T : Any> Flow<PagingData<T>>.collectFlowData(
    diffCallback: DiffUtil.ItemCallback<T>, testScope: TestScope
): List<T> {
    val differ = AsyncPagingDataDiffer(
        diffCallback = diffCallback,
        updateCallback = NoopListUpdateCallback,
        workerDispatcher = Dispatchers.Main
    )

    differ.submitData(this.first())
    testScope.advanceUntilIdle()

    return differ.snapshot().items
}


object NoopListUpdateCallback : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}


