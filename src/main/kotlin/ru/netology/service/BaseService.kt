package ru.netology.service

interface BaseService<E> {
    fun add(entity: E): Int
    fun update(entity: E): Boolean
    fun delete(entity: E): Boolean
    fun getUser(ownerId: Int): Array<E>
    fun getById(id: Int): E
    fun restore(id: Int): Boolean
}