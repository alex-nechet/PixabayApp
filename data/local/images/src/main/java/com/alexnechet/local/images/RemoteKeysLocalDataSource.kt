package com.alexnechet.local.images

import com.alexnechet.local.images.db.RemoteKeysDao
import com.alexnechet.local.images.model.RemoteKeys
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface RemoteKeysLocalDataSource {
    suspend fun insertAll(remoteKey: List<RemoteKeys>)
    suspend fun remoteKeysImageId(imageId: Long): RemoteKeys?
    suspend fun clearRemoteKeys()
}

class RemoteKeysLocalDataSourceImpl(
    private val dao: RemoteKeysDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteKeysLocalDataSource {
    override suspend fun insertAll(remoteKey: List<RemoteKeys>) = withContext(dispatcher) {
        dao.insertAll(remoteKey)
    }

    override suspend fun remoteKeysImageId(imageId: Long): RemoteKeys? = withContext(dispatcher) {
        dao.remoteKeysRepoId(imageId)
    }

    override suspend fun clearRemoteKeys() = withContext(dispatcher) {
        dao.clearRemoteKeys()
    }
}