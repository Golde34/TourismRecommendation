package tr.mobileapp.Entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
public class Role {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = this.name;
    }

    public Role () {}

    public JSONObject toJson() {
        JSONObject role = new JSONObject();
        try {
            role.put("id", 1);
            role.put("name", "user");
            return role;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
