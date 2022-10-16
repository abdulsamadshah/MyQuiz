package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Activitys.Quiz;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models.CategoryModel;
import com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> implements Filterable {

    Context context;
    ArrayList<CategoryModel> categoryModels;
    ArrayList<CategoryModel> backup;


    public CategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels) {
        this.context = context;
        this.categoryModels = categoryModels;
        backup=new ArrayList<>(categoryModels);
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_layout,null);
        return new CategoryViewHolder(view);
    }

    public void filterlist( ArrayList<CategoryModel> filterdata){
        categoryModels=filterdata;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final CategoryModel model = categoryModels.get(position);

        holder.textView.setText(model.getCategoryName());
        Picasso.get()
                .load(model.getCategoryImage())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Quiz.class);
                intent.putExtra("catId", model.getCategoryId());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }



    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter=new Filter() {
        @Override
        // background thread
        protected FilterResults performFiltering(CharSequence keyword)
        {
            ArrayList<CategoryModel> filtereddata=new ArrayList<>();

            if(keyword.toString().isEmpty())
                filtereddata.addAll(backup);

            else {
                for(CategoryModel obj : backup)
                {
                    if(obj.getCategoryName().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }

            FilterResults results=new FilterResults();
            results.values=filtereddata;
            return results;
        }

        @Override  // main UI thread
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            categoryModels.clear();
            categoryModels.addAll((ArrayList<CategoryModel>)results.values);
            notifyDataSetChanged();
        }
    };


    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.quizimg);
            textView = itemView.findViewById(R.id.quizname);
        }
    }
}
