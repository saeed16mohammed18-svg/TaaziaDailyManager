
package com.taazia.dailyactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button btnActivities = findViewById(R.id.btn_activities);
        Button btnEvaluations = findViewById(R.id.btn_evaluations);
        Button btnIssues = findViewById(R.id.btn_issues);
        Button btnReports = findViewById(R.id.btn_reports);
        
        btnActivities.setOnClickListener(v -> startActivity(new Intent(this, ActivityForm.class)));
        btnEvaluations.setOnClickListener(v -> startActivity(new Intent(this, EvaluationForm.class)));
        btnIssues.setOnClickListener(v -> startActivity(new Intent(this, IssuesForm.class)));
        btnReports.setOnClickListener(v -> startActivity(new Intent(this, ReportsActivity.class)));
    }
                                         }
