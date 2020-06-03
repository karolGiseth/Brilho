package com.k.brilho.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k.brilho.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Query("UPDATE users SET value =:value WHERE id=:id")
    fun updateUser(value: Int, id: Int)

    @Query("Select * from users where name=:name")
    fun userExist(name: String): User?

    @Query("Select * from users where name=:name AND password=:password")
    fun login(name: String, password: String): User?

    @Query("Select name from users where id <> 0")
    fun getAllUsers(): MutableList<String>

    @Query("Select * from users where name =:name")
    fun getUser(name: String): User
}