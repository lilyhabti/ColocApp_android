package com.gestion.colocapp.data;


import com.gestion.colocapp.pojo.Expense;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ExpenseService {
    @GET("/api/expenses")
    Call<List<Expense>> getAllExpenses(@Query("username") String ownerUsername);
}
