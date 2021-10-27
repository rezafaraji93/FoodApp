package com.faraji.opeque.core.data.repository

import com.faraji.opeque.R
import com.faraji.opeque.core.data.remote.MenuApi
import com.faraji.opeque.core.domain.models.MenuItem
import com.faraji.opeque.core.presentation.util.Resource
import com.faraji.opeque.core.presentation.util.UiText
import okio.IOException
import retrofit2.HttpException

class MenuRepositoryImpl(
    private val api: MenuApi
) : MenuRepository {

    override suspend fun getMenu(): Resource<List<MenuItem>> {
        return try {
            val response = api.getMenu()
            if (response.isSuccessful) {
                val data = response.body()?.map { it.toMenuItem() }
                Resource.Success(data)
            } else {
                Resource.Error(
                    UiText.DynamicString(response.message())
                )
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.io_exception_message)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }

    }
}