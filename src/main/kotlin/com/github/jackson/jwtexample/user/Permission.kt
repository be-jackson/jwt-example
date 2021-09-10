package com.github.jackson.jwtexample.user

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "permissions")
data class Permission(
    @Id
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String
)