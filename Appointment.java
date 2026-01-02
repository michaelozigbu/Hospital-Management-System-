package com.example.medicalbookingapp.models;


public class Appointment {

        private int id;
        private int patientId;
        private int doctorId;
        private String date;
        private String time;
        private String status; // booked or cancelled
        private String reason; // reason for visit
        private String duration; //in minutes

        public Appointment(int id, int patientId, int doctorId, String date, String time, String status, String reason, String duration) {
            this.id = id;
            this.patientId = patientId;
            this.doctorId = doctorId;
            this.date = date;
            this.time = time;
            this.status = status;
            this.reason= reason;
            this.duration= duration;
        }

        public int getId() { return id; }
        public int getPatientId() { return patientId; }
        public int getDoctorId() { return doctorId; }
        public String getDate() { return date; }
        public String getTime() { return time; }
        public String getStatus() { return status; }
        public String getReason() { return reason; }

    public String getDuration() {
        return duration;
    }
}


