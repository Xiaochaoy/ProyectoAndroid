package com.company.room;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.company.room.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ChampionsViewModel championsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        championsViewModel = new ViewModelProvider(this).get(ChampionsViewModel.class);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                // Top-level destinations:
                R.id.recyclerChampionsFragment, R.id.recyclerAlfaFragment, R.id.homeFragment, R.id.mapa1Fragment, R.id.mapa2Fragment, R.id.mapa3Fragment, R.id.mapa4Fragment, R.id.mapa5Fragment, R.id.mapa6Fragment, R.id.mapa7Fragment, R.id.mapa8Fragment, R.id.mapa9Fragment, R.id.mapa10Fragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();

        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.nuevoChampionFragment
                        || destination.getId() == R.id.mostrarChampionFragment) {
                    binding.navView.setVisibility(View.GONE);
                } else {
                    binding.navView.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}