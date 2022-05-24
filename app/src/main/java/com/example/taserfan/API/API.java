package com.example.taserfan.API;

public class API {
    public static class Routes {
        // Oracle

        public static final String PUERTO="4567";
        public static final String IP ="http://10.11.0.191";
        public static final String URL = IP +":"+PUERTO;

        public static final String AUTHENTICATE = "/authenticate";
        public static final String VEHICULOS ="/vehiculos";
        public static  final String TIPO_VEHICULO="/tipo";

        public static  final String MOTO ="/moto";


        public static  final String COCHE="/coche";
        public static  final String BICI="/bici";
        public static  final String PATINETE="/patinete";

        private String ip="10.11.0.191";

    }


}
