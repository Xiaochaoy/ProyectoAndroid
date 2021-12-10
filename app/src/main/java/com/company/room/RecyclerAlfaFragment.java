package com.company.room;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecyclerAlfaFragment extends RecyclerChampionsFragment {
    @Override
    LiveData<List<Champion>> obtenerElementos() {
        return championsViewModel.alfabetico();
    }
}
