package tr.mobileapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tr.mobileapp.Entity.Role;
import tr.mobileapp.Entity.Role;

@Dao
public interface RoleDAO {
    @Query("SELECT * FROM Role")
    List<Role> loadAllRoles();

    @Query("SELECT * FROM Role WHERE id = :id")
    List<Role> loadRoleById(int id);

    @Insert
    void insertRole(Role... role);

    @Update
    void updateRole(Role... role);

    @Delete
    void deleteRole(Role role);
}
