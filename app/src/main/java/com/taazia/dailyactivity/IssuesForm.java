package com.taazia.dailyactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IssuesForm extends Activity {
    
    private Spinner spType, spCategory, spPriority, spStatus;
    private EditText etIsolation, etVillage, etDescription, etResponsible, etNotes;
    private Button btnSave;
    private DatabaseHelper db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        
        db = new DatabaseHelper(this);
        initializeViews();
    }
    
    private void initializeViews() {
        spType = findViewById(R.id.sp_type);
        spCategory = findViewById(R.id.sp_category);
        spPriority = findViewById(R.id.sp_priority);
        spStatus = findViewById(R.id.sp_status);
        etIsolation = findViewById(R.id.et_isolation);
        etVillage = findViewById(R.id.et_village);
        etDescription = findViewById(R.id.et_description);
        etResponsible = findViewById(R.id.et_responsible);
        etNotes = findViewById(R.id.et_notes);
        btnSave = findViewById(R.id.btn_save);
        
        btnSave.setOnClickListener(v -> saveIssue());
    }
    
    private void saveIssue() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String type = spType.getSelectedItem().toString();
        String category = spCategory.getSelectedItem().toString();
        String priority = spPriority.getSelectedItem().toString();
        String status = spStatus.getSelectedItem().toString();
        
        long id = db.addIssue(date, etIsolation.getText().toString(), etVillage.getText().toString(), type, category, etDescription.getText().toString(), etResponsible.getText().toString(), priority, status, "", etNotes.getText().toString());
        
        if (id > 0) {
            Toast.makeText(this, "تم حفظ البند بنجاح", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
                                  }
