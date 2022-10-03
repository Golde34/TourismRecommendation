package tr.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import tr.mobileapp.Database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String DB_NAME = "tourismRecommendation.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "Sample.db")
                .addMigrations(MIGRATION_1_2)
                .build();
        Log.d("DATABASE", "Successful create db: " + database.isOpen());
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Empty implementation, because the schema isn't changing.
        }
    };
}