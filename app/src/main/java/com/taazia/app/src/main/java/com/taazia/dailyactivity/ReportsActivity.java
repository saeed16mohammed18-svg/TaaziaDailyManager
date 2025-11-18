package com.taazia.dailyactivity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import java.util.ArrayList;

public class ReportsActivity extends Activity {
    
    private PieChart pieChart;
    private Button btnExportPDF, btnExportExcel;
    private LinearLayout reportContainer;
    private DatabaseHelper db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        
        db = new DatabaseHelper(this);
        initializeViews();
        generateGeneralReport();
    }
    
    private void initializeViews() {
        pieChart = findViewById(R.id.pie_chart);
        btnExportPDF = findViewById(R.id.btn_export_pdf);
        btnExportExcel = findViewById(R.id.btn_export_excel);
        reportContainer = findViewById(R.id.report_container);
        
        btnExportPDF.setOnClickListener(v -> exportAsPDF());
        btnExportExcel.setOnClickListener(v -> exportAsExcel());
    }
    
    private void generateGeneralReport() {
        Cursor activities = db.getAllActivities();
        int count = activities.getCount();
        
        TextView tvSummary = new TextView(this);
        tvSummary.setText("إجمالي الأنشطة: " + count);
        tvSummary.setTextSize(18);
        reportContainer.addView(tvSummary);
        
        setupPieChart();
    }
    
    private void setupPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(30f, "أنشطة ميدانية"));
        entries.add(new PieEntry(20f, "اجتماعات"));
        entries.add(new PieEntry(50f, "تقييمات"));
        
        PieDataSet dataSet = new PieDataSet(entries, "التقرير العام");
        dataSet.setColors(new int[]{android.R.color.holo_blue_dark, android.R.color.holo_green_dark, android.R.color.holo_orange_dark});
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.invalidate();
    }
    
    private void exportAsPDF() {
        Toast.makeText(this, "جاري تصدير PDF...", Toast.LENGTH_SHORT).show();
    }
    
    private void exportAsExcel() {
        Toast.makeText(this, "جاري تصدير Excel...", Toast.LENGTH_SHORT).show();
    }
                       }
