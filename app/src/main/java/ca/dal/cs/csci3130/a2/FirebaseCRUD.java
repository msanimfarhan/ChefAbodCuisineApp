package ca.dal.cs.csci3130.a2;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseCRUD {
    private FirebaseDatabase database;
    private DatabaseReference netIDRef = null;

    private DatabaseReference emailRef = null;
    private DatabaseReference roleRef = null;
    private String extractedNetID;
    private String extractedEmailAddress;
    private String extractedRole;


    public FirebaseCRUD(FirebaseDatabase database) {
        this.database = database;
        this.initializeDatabaseRefs();
        this.initializeDatabaseRefListeners();
    }

    protected DatabaseReference getNetIDRef() {
        return this.database.getReference("netID");
    }

    protected void setNetID(String netID) {
        //incomplete method, add your implementation
        if (netIDRef != null) {
            netIDRef.setValue(netID);
        }

    }

    protected DatabaseReference getEmailRef() {
        return this.database.getReference("email");
    }

    protected void setEmail(String email) {
        //incomplete method, add your implementation
        if (emailRef != null) {
            emailRef.setValue(email);
        }

    }

    protected DatabaseReference getRoleRef() {
        return this.database.getReference("role");
    }

    protected void setRole(String role) {
        //incomplete method, add your implementation
        if (roleRef != null) {
            roleRef.setValue(role);
        }

    }

    protected void setNetIDListener() {
        this.netIDRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //incomplete method, add your implementation
                if (snapshot.exists()) {
                    extractedNetID = snapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    protected void setEmailListener() {
        this.emailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    extractedEmailAddress = snapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle potential errors
            }
        });
    }


    protected void setRoleListener() {
        this.roleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    extractedRole = snapshot.getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    protected void initializeDatabaseRefs() {
        //Incomplete method, add your implementation
        this.netIDRef = getNetIDRef();
        this.emailRef = getEmailRef();
        this.roleRef = getRoleRef();

    }

    protected void initializeDatabaseRefListeners() {
        //Incomplete method, add your implementation
        this.setNetIDListener();
        this.setEmailListener(); // Make sure to call this method
        this.setRoleListener();
    }

    public String getExtractedNetID() {
        return this.extractedNetID;
    }

    public String getExtractedEmailAddress() {
        return this.extractedEmailAddress;
    }

    public String getExtractedRole() {
        return this.extractedRole;
    }
}
