package tr.mobileapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tr.mobileapp.Entity.Account;

@Dao
public interface AccountDAO {
    @Query("SELECT * FROM Account")
    List<Account> loadAllAccounts();

    @Query("SELECT * FROM Account WHERE account_id = :id")
    List<Account> loadAccountById(String id);

    @Insert
    void insertUserAccount(Account... account);

    @Update
    void updateUserAccount(Account... account);

    @Delete
    void deleteUserAccount(Account account);

}
