package com.example.medicalbookingapp.models;


public class Availability {

        private int doctorId;
        private String day;
        private String startTime;
        private String endTime;
        private String holiday;

        public Availability(int doctorId, String day, String startTime, String endTime, String holiday ) {
            this.doctorId = doctorId;
            this.day = day;
            this.startTime = startTime;
            this.endTime = endTime;
            this.holiday = holiday;
        }

        public int getDoctorId() { return doctorId; }
        public String getDay() { return day; }
        public String getStartTime() { return startTime; }
        public String getEndTime() { return endTime; }
        public String getHoliday(){
            return holiday;
        }
    }


