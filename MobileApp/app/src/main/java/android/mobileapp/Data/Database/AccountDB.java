package android.mobileapp.Data.Database;

import android.content.Context;
import android.mobileapp.Data.DAO.AccountDAO;
import android.mobileapp.Data.Entity.Account;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Account.class}, version = 1)
public abstract class AccountDB  extends RoomDatabase {
    private static final String DB = "tourismRecommendation.db";

    public abstract AccountDAO accountDAO();

    public static AccountDB accountDB;

    public static AccountDB getInstance(Context context) {
        if (accountDB == null) {
            accountDB = Room.databaseBuilder(context.getApplicationContext(), AccountDB.class, DB).build();
        }
        return accountDB;
    }
}
