package com.example.sopthomework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopthomework.databinding.ListRepositoryElementBinding

class RepoListAdapter: RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>(){

    val repoList = mutableListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder{
        val binding = ListRepositoryElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

//    override fun notifyDataSetChanged(){
//        super.notifyDataSetChanged()
//    }

    class RepoViewHolder (
        private val binding: ListRepositoryElementBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(RepoInfo: Repository){
            binding.repositoryName.text = RepoInfo.repo_name
            binding.repositoryInfo.text = RepoInfo.repo_info
            binding.repositoryLang.text = RepoInfo.repo_lang
        }
    }
}