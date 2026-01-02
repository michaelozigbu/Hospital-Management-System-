package com.example.medicalbookingapp.models;


public class User {

        private int id;
        private String name;
        private String email;
        private String phone;

        private String password;
        private String role; // which could be patient or admin.


        public User(int id, String name, String email, String phone, String password, String role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone= phone;
            this.password = password;
            this.role = role;
        }


        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
        public String getPhone(){
            return phone;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }
    }

