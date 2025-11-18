package com.taazia.dailyactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityForm extends Activity {
    
    private EditText etIsolation, etVillage, etDetails, etAttendees, etRecommendations, etNotes;
    private Spinner spNature;
    private DatabaseHelper db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        
        db = new DatabaseHelper(this);
        initializeViews();
    }
    
    private void initializeViews() {
        etIsolation = findViewById(R.id.et_isolation);
        etVillage = findViewById(R.id.et_village);
        etDetails = findViewById(R.id.et_details);
        etAttendees = findViewById(R.id.et_attendees);
        etRecommendations = findViewById(R.id.et_recommendations);
        etNotes = findViewById(R.id.et_notes);
        spNature = findViewById(R.id.sp_nature);
        
        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(v -> saveActivity());
    }
    
    private void saveActivity() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String nature = spNature.getSelectedItem().toString();
        String isolation = etIsolation.getText().toString();
        String village = etVillage.getText().toString();
        String details = etDetails.getText().toString();
        String attendees = etAttendees.getText().toString();
        String recommendations = etRecommendations.getText().toString();
        String notes = etNotes.getText().toString();
        
        long id = db.addActivity(date, nature, isolation, village, details, attendees, recommendations, "", notes);
        
        if (id > 0) {
            Toast.makeText(this, "تم الحفظ بنجاح", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
          }
