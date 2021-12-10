package com.company.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.company.room.databinding.FragmentMostrarChampionBinding;


public class MostrarChampionFragment extends Fragment {
    private FragmentMostrarChampionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMostrarChampionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChampionsViewModel championsViewModel = new ViewModelProvider(requireActivity()).get(ChampionsViewModel.class);

        championsViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Champion>() {
            @Override
            public void onChanged(Champion champion) {
                binding.nombre.setText(champion.nombre);
                binding.descripcion.setText(champion.descripcion);
                binding.imagen.setImageResource(champion.imagen);

            }
        });
    }
}