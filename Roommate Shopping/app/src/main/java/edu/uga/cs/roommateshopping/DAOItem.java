package edu.uga.cs.roommateshopping;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOItem {
    private DatabaseReference dbr;
    public DAOItem() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbr = db.getReference(Item.class.getSimpleName());
    }
    public Task<Void> add(Item item) {
       return dbr.push().setValue(item);
    }
}
