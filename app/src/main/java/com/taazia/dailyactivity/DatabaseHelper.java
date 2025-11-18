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
    public static final String TABLE_EVALUATIONS = "evaluation_visits";
    public static final String TABLE_ISSUES = "issues_suggestions";

    public DatabaseHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ACTIVITIES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, nature TEXT, isolation TEXT, village TEXT, details TEXT, attendees TEXT, recommendations TEXT, attachments TEXT, notes TEXT)");
        
        db.execSQL("CREATE TABLE " + TABLE_EVALUATIONS + " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, isolation TEXT, village TEXT, project_type TEXT, project_name TEXT, completion_rate INTEGER, technical_score REAL, management_score REAL, issues TEXT, solutions TEXT, attachments TEXT, recommendations TEXT)");
        
        db.execSQL("CREATE TABLE " + TABLE_ISSUES + " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, isolation TEXT, village TEXT, type TEXT, category TEXT, description TEXT, responsible_party TEXT, priority TEXT, status TEXT, attachments TEXT, notes TEXT)");
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVALUATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ISSUES);
        onCreate(db);
    }
    
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
    }
