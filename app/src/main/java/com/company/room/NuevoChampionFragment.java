package com.company.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.company.room.databinding.FragmentNuevoChampionBinding;

public class NuevoChampionFragment extends Fragment {
    private FragmentNuevoChampionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentNuevoChampionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChampionsViewModel championsViewModel = new ViewModelProvider(requireActivity()).get(ChampionsViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();

                switch (nombre){
                    case "kaisa":
                        championsViewModel.insertar(new Champion(R.drawable.kaisa,nombre, descripcion));
                        break;
                    case "ezreal":
                        championsViewModel.insertar(new Champion(R.drawable.ezreal,nombre, descripcion));
                        break;
                    default:
                        championsViewModel.insertar(new Champion(R.drawable.soraka,nombre, descripcion));
                        break;
                }
                navController.popBackStack();
            }
        });
    }
}