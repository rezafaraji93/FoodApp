package com.faraji.opeque.core.domain.use_cases

import com.faraji.opeque.core.data.repository.MenuRepository
import com.faraji.opeque.core.domain.models.MenuItem
import com.faraji.opeque.core.presentation.util.Resource

class GetMenuUseCase(
    private val repository: MenuRepository
) {
    suspend operator fun invoke(): Resource<List<MenuItem>> {
        return repository.getMenu()
    }
}