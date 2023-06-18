package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.domain.repositories.IUsersRepository
import javax.inject.Inject

class GetUsersUseCase
    @Inject constructor(
        private val usersRepository: IUsersRepository,
    ) : IGetUsersUseCase {
    override suspend fun invoke(): List<UserDefault.User> {
        val users = usersRepository.getUsers()
        return users
    }
}