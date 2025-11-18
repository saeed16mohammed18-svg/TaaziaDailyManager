package com.taazia.dailyactivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class EvaluationForm extends Activity {
    
    private Spinner spProjectType, spIsolation, spVillage;
    private EditText etProjectName, etCompletionRate, etIssues, etSolutions, etRecommendations;
    private Button btnSave;
    private DatabaseHelper db;
    private HashMap<String, String[]> criteriaMap = new HashMap<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        
        db = new DatabaseHelper(this);
        initializeCriteria();
        initializeViews();
    }
    
    private void initializeCriteria() {
        criteriaMap.put("المباني الصحية", new String[]{"لا تشققات في الهيكل", "التكييف يعمل بكفاءة", "الكهرباء مطابقة للمواصفات"});
        criteriaMap.put("الطرق الإسفلتية", new String[]{"لا تشققات", "الميول مناسبة", "علامات المرور واضحة"});
        criteriaMap.put("الآبار", new String[]{"منسوب الماء مناسب", "المضخة تعمل", "جودة الماء مطابقة للمواصفات"});
        criteriaMap.put("المدارس", new String[]{"الفصول كافية", "الكتب المدرسية متوفرة", "الإضاءة مناسبة"});
    }
    
    private void initializeViews() {
        spProjectType = findViewById(R.id.sp_project_type);
        spIsolation = findViewById(R.id.sp_isolation);
        spVillage = findViewById(R.id.sp_village);
        etProjectName = findViewById(R.id.et_project_name);
        etCompletionRate = findViewById(R.id.et_completion_rate);
        etIssues = findViewById(R.id.et_issues);
        etSolutions = findViewById(R.id.et_solutions);
        etRecommendations = findViewById(R.id.et_recommendations);
        btnSave = findViewById(R.id.btn_save);
        
        btnSave.setOnClickListener(v -> saveEvaluation());
    }
    
    private void saveEvaluation() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String projectType = spProjectType.getSelectedItem().toString();
        String projectName = etProjectName.getText().toString();
        String isolation = spIsolation.getSelectedItem().toString();
        String village = spVillage.getSelectedItem().toString();
        int completionRate = Integer.parseInt(etCompletionRate.getText().toString());
        double technicalScore = calculateTechnicalScore(projectType);
        double managementScore = 75.0;
        
        long id = db.addEvaluation(date, isolation, village, projectType, projectName, completionRate, technicalScore, managementScore, etIssues.getText().toString(), etSolutions.getText().toString(), "", etRecommendations.getText().toString());
        
        if (id > 0) {
            Toast.makeText(this, "تم حفظ التقييم بنجاح", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    
    private double calculateTechnicalScore(String projectType) {
        return 85.0;
    }
    }
