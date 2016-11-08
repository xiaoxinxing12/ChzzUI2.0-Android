package org.chzz.demo.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.chzz.demo.databinding.ItemRepoBinding;
import org.chzz.demo.model.RefreshModel;

import java.util.Collections;
import java.util.List;


public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<RefreshModel> repositories;

    public RepositoryAdapter() {
        this.repositories = Collections.emptyList();
    }

    public RepositoryAdapter(List<RefreshModel> repositories) {
        this.repositories = repositories;
    }

    public void setRepositories(List<RefreshModel> repositories) {
        this.repositories = repositories;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new RepositoryViewHolder(null);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        //  holder.bindRepository(repositories.get(position));
      //  holder.getBinding().setViewModel(repositories.get(position));
        //holder.getBinding().getViewModel.s(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories == null ? 0 : repositories.size();
    }

    public static class RepositoryViewHolder extends RecyclerView.ViewHolder {
         ItemRepoBinding binding;

        public void setBinding(ItemRepoBinding binding) {
            this.binding = binding;
        }

        public RepositoryViewHolder(ViewDataBinding binding) {
            super(((ItemRepoBinding) binding).cardView);
            ItemRepoBinding b = (ItemRepoBinding) binding;

            this.binding = b;
        }

        public ItemRepoBinding getBinding() {
            return binding;
        }

        void bindRepository(RefreshModel repository) {
          //  binding.setViewModel(repository);

        }

    }
}
