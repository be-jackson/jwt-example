package com.github.jackson.jwtexample.user

import javax.persistence.*

@Entity
@Table(name = "groups")
data class Group(
    @Id
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name")
    var name: String,

    @OneToMany(mappedBy = "group")
    val permissions: List<GroupPermission> = emptyList()
)