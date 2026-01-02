package com.example.medicalbookingapp.models;


public class Doctor {

        private int id;
        private String name;
        private String specialty;
        private String email;
        private String phone;


        public Doctor(int id, String name, String specialty, String email, String phone) {
            this.id = id;
            this.name = name;
            this.specialty = specialty;
            this.email = email;
            this.phone= phone;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public String getSpecialty() { return specialty; }
        public String getEmail(){return email;}

         public String getPhone() {
        return phone;
    }
}


