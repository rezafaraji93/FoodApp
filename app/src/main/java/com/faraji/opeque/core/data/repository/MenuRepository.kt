package com.faraji.opeque.core.data.repository

import com.faraji.opeque.core.domain.models.MenuItem
import com.faraji.opeque.core.presentation.util.Resource

interface MenuRepository {

    suspend fun getMenu(): Resource<List<MenuItem>>
}