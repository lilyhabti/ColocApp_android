package com.gestion.colocapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gestion.colocapp.R;
import com.gestion.colocapp.pojo.Expense;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<Expense> expenseList;

    public ExpenseAdapter(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenseList.get(position);
        holder.bind(expense);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView descriptionTextView;
        private TextView amountTextView;
        private TextView amountPerPersonTextView;
        private ImageView deleteImageView;

        ExpenseViewHolder(View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionex);
            amountTextView = itemView.findViewById(R.id.amountgen);
            amountPerPersonTextView = itemView.findViewById(R.id.amouperper);
            deleteImageView = itemView.findViewById(R.id.supprimer);
        }

        void bind(Expense expense) {
            String descr = expense.getDescriptionE();
            String amount = "Amount : " + expense.getAmount();
            descriptionTextView.setText(descr);
            amountTextView.setText(amount);
            amountPerPersonTextView.setText(amount);

            // Set a click listener for the delete button
            deleteImageView.setOnClickListener(v -> {
                // Handle delete expense here
            });
        }
    }
}