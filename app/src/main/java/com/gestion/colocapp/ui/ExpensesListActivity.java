package com.gestion.colocapp.ui;

// ExpensesListActivity.java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.gestion.colocapp.R;
import com.gestion.colocapp.data.ExpenseService;
import com.gestion.colocapp.data.RetrofitClient;
import com.gestion.colocapp.pojo.Expense;
import com.gestion.colocapp.pojo.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpensesListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpenseAdapter expenseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create instance of Retrofit service
        ExpenseService expenseService = RetrofitClient.getRetrofitInstance().create(ExpenseService.class);

        User user = getIntent().getParcelableExtra("user");
        // Call API to get expenses
        Call<List<Expense>> call = expenseService.getAllExpenses(user.getUsername());

        // Execute API call asynchronously
        call.enqueue(new Callback<List<Expense>>() {
            @Override
            public void onResponse(Call<List<Expense>> call, Response<List<Expense>> response) {
                if (response.isSuccessful()) {
                    List<Expense> expensesList = response.body();
                    expenseAdapter = new ExpenseAdapter(expensesList);
                    recyclerView.setAdapter(expenseAdapter);
                } else {
                    // Handle API error
                }
            }

            @Override
            public void onFailure(Call<List<Expense>> call, Throwable t) {
                // Handle network error
            }
        });
    }
}
