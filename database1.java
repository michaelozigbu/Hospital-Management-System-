package com.example.medicalbookingapp.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.medicalbookingapp.models.Appointment;
import com.example.medicalbookingapp.models.Availability;
import com.example.medicalbookingapp.models.Doctor;
import com.example.medicalbookingapp.models.User;

import java.util.ArrayList;
import java.util.List;


public class database1 extends SQLiteOpenHelper{





        private static final String DATABASE_NAME = "medical_booking.db";
        private static final int DATABASE_VERSION = 1;

       //tables
        private static final String TABLE_USER = "user";
        private static final String TABLE_DOCTOR = "doctor";
        private static final String TABLE_AVAILABILITY = "availability";
        private static final String TABLE_APPOINTMENT = "appointment";

        // use
        private static final String USER_ID = "id";
        private static final String USER_NAME = "name";
        private static final String USER_EMAIL = "email";
        private static final String USER_PHONE = "phone";
        private static final String USER_ROLE = "role"; // patient / doctor / admin

        //doctor
        private static final String DOCTOR_ID = "id";
        private static final String DOCTOR_NAME = "name";
        private static final String DOCTOR_SPECIALTY = "specialty";
        private static final String DOCTOR_PHONE = "phone";
        private static final String DOCTOR_EMAIL = "email";
        //availabilyity
        private static final String AVAIL_ID = "id";
        private static final String AVAIL_DOCTOR_ID = "doctor_id";
        private static final String AVAIL_DAY = "day";
        private static final String AVAIL_START = "start_time";
        private static final String AVAIL_END = "end_time";
        private static final String AVAIL_HOLIDAY = "holiday";

        //appointment
        private static final String APPT_ID = "id";
        private static final String APPT_PATIENT_ID = "patient_id"; // link to user
        private static final String APPT_DOCTOR_ID = "doctor_id";
        private static final String APPT_DATE = "date";
        private static final String APPT_TIME = "time";
        private static final String APPT_STATUS = "status";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // USER TABLE
            db.execSQL(
                    "CREATE TABLE " + TABLE_USER + " (" +
                            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            USER_NAME + " TEXT, " +
                            USER_EMAIL + " TEXT, " +
                            USER_PHONE + " TEXT, " +
                            USER_ROLE + " TEXT)"
            );

            // DOCTOR TABLE
            db.execSQL(
                    "CREATE TABLE " + TABLE_DOCTOR + " (" +
                            DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            DOCTOR_NAME + " TEXT, " +
                            DOCTOR_SPECIALTY + " TEXT, " +
                            DOCTOR_PHONE + " TEXT, " +
                            DOCTOR_EMAIL + " TEXT)"
            );

            // AVAILABILITY TABLE
            db.execSQL(
                    "CREATE TABLE " + TABLE_AVAILABILITY + " (" +
                            AVAIL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            AVAIL_DOCTOR_ID + " INTEGER, " +
                            AVAIL_DAY + " TEXT, " +
                            AVAIL_START + " TEXT, " +
                            AVAIL_END + " TEXT, " +
                            AVAIL_HOLIDAY + " TEXT)"
            );

            // APPOINTMENT TABLE
            db.execSQL(
                    "CREATE TABLE " + TABLE_APPOINTMENT + " (" +
                            APPT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            APPT_PATIENT_ID + " INTEGER, " +
                            APPT_DOCTOR_ID + " INTEGER, " +
                            APPT_DATE + " TEXT, " +
                            APPT_TIME + " TEXT, " +
                            APPT_STATUS + " TEXT)"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTOR);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_AVAILABILITY);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENT);
            onCreate(db);
        }

        // ===================== USER METHODS =====================
        public long addUser(User user) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(USER_NAME, user.getName());
            cv.put(USER_EMAIL, user.getEmail());
            cv.put(USER_PHONE, user.getPhone());
            cv.put(USER_ROLE, user.getRole());
            return db.insert(TABLE_USER, null, cv);
        }

        public List<User> getAllUsers() {
            List<User> list = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USER, null);

            while (c.moveToNext()) {
                list.add(new User(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                ));
            }
            c.close();
            return list;
        }

        public User getUserById(int userId) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.query(TABLE_USER, null, USER_ID + "=?",
                    new String[]{String.valueOf(userId)}, null, null, null);

            if (c.moveToFirst()) {
                User u = new User(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                );
                c.close();
                return u;
            }
            c.close();
            return null;
        }

        // ===================== DOCTOR METHODS =====================
        public long addDoctor(Doctor doctor) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DOCTOR_NAME, doctor.getName());
            cv.put(DOCTOR_SPECIALTY, doctor.getSpecialty());
            cv.put(DOCTOR_PHONE, doctor.getPhone());
            cv.put(DOCTOR_EMAIL, doctor.getEmail());
            return db.insert(TABLE_DOCTOR, null, cv);
        }

        public List<Doctor> getAllDoctors() {
            List<Doctor> list = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DOCTOR, null);

            while (c.moveToNext()) {
                list.add(new Doctor(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)
                ));
            }
            c.close();
            return list;
        }

        public long addAvailability(Availability a) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(AVAIL_DOCTOR_ID, a.getDoctorId());
            cv.put(AVAIL_DAY, a.getDay());
            cv.put(AVAIL_START, a.getStartTime());
            cv.put(AVAIL_END, a.getEndTime());
            cv.put(AVAIL_HOLIDAY, a.getHoliday());
            return db.insert(TABLE_AVAILABILITY, null, cv);
        }

        public List<Availability> getAvailabilityForDoctor(int doctorId) {
            List<Availability> list = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.query(
                    TABLE_AVAILABILITY,
                    null,
                    AVAIL_DOCTOR_ID + "=?",
                    new String[]{String.valueOf(doctorId)},
                    null, null, null
            );

            while (c.moveToNext()) {
                list.add(new Availability(
                        c.getInt(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5)
                ));
            }
            c.close();
            return list;
        }

        //methods
        public boolean isSlotFree(int doctorId, String date, String time) {
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.query(
                    TABLE_APPOINTMENT,
                    null,
                    APPT_DOCTOR_ID + "=? AND " + APPT_DATE + "=? AND " + APPT_TIME + "=?",
                    new String[]{String.valueOf(doctorId), date, time},
                    null, null, null
            );
            boolean free = !c.moveToFirst();
            c.close();
            return free;
        }

        public long addAppointment(Appointment a) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(APPT_PATIENT_ID, a.getPatientId());
            cv.put(APPT_DOCTOR_ID, a.getDoctorId());
            cv.put(APPT_DATE, a.getDate());
            cv.put(APPT_TIME, a.getTime());
            cv.put(APPT_STATUS, a.getStatus());
            return db.insert(TABLE_APPOINTMENT, null, cv);
        }

        public List<Appointment> getAppointmentsForDoctor(int doctorId) {
            List<Appointment> list = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();

            Cursor c = db.query(
                    TABLE_APPOINTMENT,
                    null,
                    APPT_DOCTOR_ID + "=?",
                    new String[]{String.valueOf(doctorId)},
                    null, null, null
            );

            while (c.moveToNext()) {
                list.add(new Appointment(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5)
                ));
            }
            c.close();
            return list;
        }
    }



}
