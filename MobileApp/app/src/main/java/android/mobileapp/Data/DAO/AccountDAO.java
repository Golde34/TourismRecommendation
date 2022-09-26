package android.mobileapp.Data.DAO;

import android.mobileapp.Data.Entity.Account;

import androidx.room.*;

import java.util.List;

@Dao
public interface AccountDAO {
    @Query("SELECT * FROM Account")
    List<Account> loadAllAccounts();

    @Insert
    void insertUserAccount(Account... account);

    @Delete
    void deleteUserAccount(Account... account);

}
