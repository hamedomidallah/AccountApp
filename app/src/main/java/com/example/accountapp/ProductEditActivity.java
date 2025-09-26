package com.example.accountapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

public class ProductEditActivity extends AppCompatActivity {

    private EditText etName, etDescription, etASIN, etBrand, etModel, etColor, etVolume,
            etWeight, etPurchasePrice, etPackagingCost, etShippingCost, etAmazonFee, etAdCost,
            etSellingPrice, etStockOrigin, etStockDestination;
    private Button btnSave;
    private ProductRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_edit);

        repository = new ProductRepository();

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etASIN = findViewById(R.id.etASIN);
        etBrand = findViewById(R.id.etBrand);
        etModel = findViewById(R.id.etModel);
        etColor = findViewById(R.id.etColor);
        etVolume = findViewById(R.id.etVolume);
        etWeight = findViewById(R.id.etWeight);
        etPurchasePrice = findViewById(R.id.etPurchasePrice);
        etPackagingCost = findViewById(R.id.etPackagingCost);
        etShippingCost = findViewById(R.id.etShippingCost);
        etAmazonFee = findViewById(R.id.etAmazonFee);
        etAdCost = findViewById(R.id.etAdCost);
        etSellingPrice = findViewById(R.id.etSellingPrice);
        etStockOrigin = findViewById(R.id.etStockOrigin);
        etStockDestination = findViewById(R.id.etStockDestination);
        btnSave = findViewById(R.id.btnSaveProduct);

        btnSave.setOnClickListener(v -> saveProduct());
    }

    private void saveProduct() {
        Product p = new Product();
        p.setName(etName.getText().toString());
        p.setDescription(etDescription.getText().toString());
        p.setAsin(etASIN.getText().toString());
        p.setBrand(etBrand.getText().toString());
        p.setModel(etModel.getText().toString());
        p.setColor(etColor.getText().toString());
        p.setVolume(etVolume.getText().toString());
        p.setWeight(parseDouble(etWeight.getText().toString()));
        p.setPurchasePrice(parseDouble(etPurchasePrice.getText().toString()));
        p.setPackagingCost(parseDouble(etPackagingCost.getText().toString()));
        p.setShippingCost(parseDouble(etShippingCost.getText().toString()));
        p.setAmazonFee(parseDouble(etAmazonFee.getText().toString()));
        p.setAdCost(parseDouble(etAdCost.getText().toString()));
        p.setSellingPrice(parseDouble(etSellingPrice.getText().toString()));
        p.setStockOrigin(parseInt(etStockOrigin.getText().toString()));
        p.setStockDestination(parseInt(etStockDestination.getText().toString()));

        repository.addProduct(p, new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ProductEditActivity.this, "Product saved", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ProductEditActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private double parseDouble(String s) {
        try { return Double.parseDouble(s); } catch (Exception e) { return 0; }
    }

    private int parseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }
}