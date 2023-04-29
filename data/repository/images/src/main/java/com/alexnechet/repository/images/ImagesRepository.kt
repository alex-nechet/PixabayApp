package com.alexnechet.repository.images

import android.app.DownloadManager.Query
import java.time.LocalDate

interface ImagesRepository {
     fun getImages(string: Query, page: Int) : Result<List<Image>>

     fun getImageById(id: Long) : Result<Image>
}

class ImagesRepositoryImpl() : ImagesRepository{

}