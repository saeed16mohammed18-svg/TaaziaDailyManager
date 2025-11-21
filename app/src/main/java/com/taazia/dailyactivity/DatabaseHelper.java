package com.taazia.dailyactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "taazia.db";
    private static final int DB_VERSION = 2;
    
    public static final String TABLE_ACTIVITIES = "activities";
    public static final String TABLE_EVALUATIONS = "evaluations";
    public static final String TABLE_ISSUES = "issues";

    public DatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // جدول الأنشطة اليومية
        db.execSQL("CREATE TABLE " + TABLE_ACTIVITIES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "nature TEXT, " +
                "isolation TEXT, " +
                "village TEXT, " +
                "details TEXT, " +
                "attendees TEXT, " +
                "recommendations TEXT, " +
                "attachments TEXT, " +
                "notes TEXT)");
        
        // جدول نزولات التقييم
        db.execSQL("CREATE TABLE " + TABLE_EVALUATIONS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "isolation TEXT, " +
                "village TEXT, " +
                "project_type TEXT, " +
                "project_name TEXT, " +
                "completion_rate INTEGER, " +
                "technical_score REAL, " +
                "management_score REAL, " +
                "issues TEXT, " +
                "solutions TEXT, " +
                "attachments TEXT, " +
                "recommendations TEXT)");
        
        // جدول المشكلات والمقترحات
        db.execSQL("CREATE TABLE " + TABLE_ISSUES + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "isolation TEXT, " +
                "village TEXT, " +
                "type TEXT, " +
                "category TEXT, " +
                "description TEXT, " +
                "responsible_party TEXT, " +
                "priority TEXT, " +
                "status TEXT, " +
                "attachments TEXT, " +
                "notes TEXT)");
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVALUATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ISSUES);
        onCreate(db);
    }
    
    // === دوال الأنشطة ===
    public long addActivity(String date, String nature, String isolation, String village, String details, String attendees, String recommendations, String attachments, String notes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date", date);
        cv.put("nature", nature);
        cv.put("isolation", isolation);
        cv.put("village", village);
        cv.put("details", details);
        cv.put("attendees", attendees);
        cv.put("recommendations", recommendations);
        cv.put("attachments", attachments);
        cv.put("notes", notes);
        return db.insert(TABLE_ACTIVITIES, null, cv);
    }
    
    public Cursor getAllActivities() {
        return getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_ACTIVITIES + " ORDER BY date DESC", null);
    }
    
    // === دوال التقييم ===
    public long addEvaluation(String date, String isolation, String village, String projectType, String projectName, int completionRate, double technicalScore, double managementScore, String issues, String solutions, String attachments, String recommendations) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date", date);
        cv.put("isolation", isolation);
        cv.put("village", village);
        cv.put("project_type", projectType);
        cv.put("project_name", projectName);
        cv.put("completion_rate", completionRate);
        cv.put("technical_score", technicalScore);
        cv.put("management_score", managementScore);
        cv.put("issues", issues);
        cv.put("solutions", solutions);
        cv.put("attachments", attachments);
        cv.put("recommendations", recommendations);
        return db.insert(TABLE_EVALUATIONS, null, cv);
    }
    
    // === دوال المشكلات ===
    public long addIssue(String date, String isolation, String village, String type, String category, String description, String responsibleParty, String priority, String status, String attachments, String notes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date", date);
        cv.put("isolation", isolation);
        cv.put("village", village);
        cv.put("type", type);
        cv.put("category", category);
        cv.put("description", description);
        cv.put("responsible_party", responsibleParty);
        cv.put("priority", priority);
        cv.put("status", status);
        cv.put("attachments", attachments);
        cv.put("notes", notes);
        return db.insert(TABLE_ISSUES, null, cv);
    }
}
