package tr.mobileapp.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import tr.mobileapp.DAO.AccountDAO;
import tr.mobileapp.DAO.RoleDAO;
import tr.mobileapp.Entity.Account;
import tr.mobileapp.Entity.Role;
import tr.mobileapp.Entity.Tour;

@Database(entities = { Account.class,
        Role.class
}, version = 4)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDAO getAccountDAO();
    public abstract RoleDAO getRoleDAO();
}
