package com.example.accountapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rvProducts;
    private ProductAdapter adapter;
    private ProductRepository repository;
    private Button btnAddProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        rvProducts = findViewById(R.id.rvProducts);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        repository = new ProductRepository();
        adapter = new ProductAdapter(new ArrayList<>(), product -> {
            // ویرایش محصول روی کلیک
            Toast.makeText(this, "Edit: " + product.getName(), Toast.LENGTH_SHORT).show();
        });

        rvProducts.setLayoutManager(new LinearLayoutManager(this));
        rvProducts.setAdapter(adapter);

        btnAddProduct.setOnClickListener(v -> {
            Intent i = new Intent(this, ProductEditActivity.class);
            startActivity(i);
        });

        // دریافت محصولات و real-time update
        repository.listenToProducts(products -> {
            adapter.updateList(products);
        });
    }
}