package android.mobileapp.Listing.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.arch.lifecycle.ViewModelProviders;
import android.mobileapp.Listing.Adapter.AccountAdapter;
import android.mobileapp.Listing.ViewModel.MainViewModel;
import android.mobileapp.R;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        final AccountAdapter adapter = new AccountAdapter();
//        viewModel.pokemonList.observe(this, adapter::setList);
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_pokemons);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.span_count)));
//        recyclerView.setAdapter(adapter);
    }
}