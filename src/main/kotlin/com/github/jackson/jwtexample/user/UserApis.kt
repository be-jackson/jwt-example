package com.github.jackson.jwtexample.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserApis(
    val userService: UserService
) {
    @GetMapping("/{username}")
    fun me(@PathVariable username: String): UserDto {
        val user: User = userService.findByUsername(username)
            ?: throw IllegalArgumentException("Could not found user for $username")
        return UserDto(user.username, user.group.name)
    }
}