package com.adrosonic.adrocafe.loginmvvm.repository.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.adrosonic.adrocafe.loginmvvm.data.User;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    Flowable<User> getUserByName(String username);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(User user);

}
