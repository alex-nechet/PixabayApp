package com.alexnechet.local.images

import com.alexnechet.local.images.db.RemoteKeysDao
import com.alexnechet.local.images.model.RemoteKeys

interface RemoteKeysLocalDataSource {
    suspend fun insertAll(remoteKey: List<RemoteKeys>)
    suspend fun remoteKeysImageId(imageId: Long): RemoteKeys?
    suspend fun clearRemoteKeys()
}

class RemoteKeysLocalDataSourceImpl(
    private val dao: RemoteKeysDao
) : RemoteKeysLocalDataSource {
    override suspend fun insertAll(remoteKey: List<RemoteKeys>) = dao.insertAll(remoteKey)

    override suspend fun remoteKeysImageId(imageId: Long): RemoteKeys? =
        dao.remoteKeysRepoId(imageId)

    override suspend fun clearRemoteKeys() = dao.clearRemoteKeys()
}