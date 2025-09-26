package com.example.accountapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private static final String TAG = "ProductRepository";
    private static final String COLLECTION_NAME = "products";

    private final FirebaseFirestore db;
    private final CollectionReference productsRef;

    public ProductRepository() {
        db = FirebaseFirestore.getInstance();
        productsRef = db.collection(COLLECTION_NAME);
    }

    // اضافه کردن کالا
    public void addProduct(Product product, OnCompleteListener<DocumentReference> listener) {
        productsRef.add(product.toMap()).addOnCompleteListener(listener);
    }

    // ویرایش کالا
    public void updateProduct(String id, Product product, OnCompleteListener<Void> listener) {
        productsRef.document(id).set(product.toMap()).addOnCompleteListener(listener);
    }

    // حذف کالا
    public void deleteProduct(String id, OnCompleteListener<Void> listener) {
        productsRef.document(id).delete().addOnCompleteListener(listener);
    }

    // دریافت لیست همه کالاها
    public void getAllProducts(OnCompleteListener<QuerySnapshot> listener) {
        productsRef.get().addOnCompleteListener(listener);
    }

    // دریافت یک کالا بر اساس id
    public void getProductById(String id, OnCompleteListener<DocumentSnapshot> listener) {
        productsRef.document(id).get().addOnCompleteListener(listener);
    }

    // Listener برای به‌روز بودن real-time
    public void listenToProducts(ProductListener listener) {
        productsRef.addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.w(TAG, "Listen failed.", error);
                return;
            }
            if (value != null) {
                List<Product> list = new ArrayList<>();
                for (DocumentSnapshot doc : value.getDocuments()) {
                    Product p = doc.toObject(Product.class);
                    if (p != null) {
                        p.setId(doc.getId());
                        list.add(p);
                    }
                }
                listener.onProductsChanged(list);
            }
        });
    }

    // Interface برای ارسال تغییرات به UI
    public interface ProductListener {
        void onProductsChanged(List<Product> products);
    }
}