package com.company.room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.company.room.databinding.FragmentRecyclerChampionsBinding;
import com.company.room.databinding.ViewholderChampionBinding;

import java.util.List;


public class RecyclerChampionsFragment extends Fragment {

    private FragmentRecyclerChampionsBinding binding;
    ChampionsViewModel championsViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerChampionsBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        championsViewModel = new ViewModelProvider(requireActivity()).get(ChampionsViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoChampion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nuevoChampionFragment);
            }
        });

        ElementosAdapter elementosAdapter;
        elementosAdapter = new ElementosAdapter();

        binding.recyclerView.setAdapter(elementosAdapter);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Champion champion = elementosAdapter.obtenerElemento(posicion);
                championsViewModel.eliminar(champion);

            }
        }).attachToRecyclerView(binding.recyclerView);

        obtenerElementos().observe(getViewLifecycleOwner(), new Observer<List<Champion>>() {
            @Override
            public void onChanged(List<Champion> Champions) {
                elementosAdapter.establecerLista(Champions);
            }
        });
    }

    LiveData<List<Champion>> obtenerElementos(){
        return championsViewModel.obtener();
    }

    class ElementosAdapter extends RecyclerView.Adapter<ChampionViewHolder> {

        List<Champion> Champions;

        @NonNull
        @Override
        public ChampionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ChampionViewHolder(ViewholderChampionBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ChampionViewHolder holder, int position) {

            Champion champion = Champions.get(position);

            holder.binding.nombre.setText(champion.nombre);

            Glide.with(RecyclerChampionsFragment.this).load(champion.imagen).into(holder.binding.imagen);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    championsViewModel.seleccionar(champion);
                    navController.navigate(R.id.action_mostrarChampionFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return Champions != null ? Champions.size() : 0;
        }

        public void establecerLista(List<Champion> Champions){
            this.Champions = Champions;
            notifyDataSetChanged();
        }

        public Champion obtenerElemento(int posicion){
            return Champions.get(posicion);
        }
    }

    static class ChampionViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderChampionBinding binding;

        public ChampionViewHolder(ViewholderChampionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}