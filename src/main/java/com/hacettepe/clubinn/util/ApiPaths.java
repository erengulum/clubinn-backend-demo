package com.hacettepe.clubinn.util;

public class ApiPaths {

    private static final String BASE_PATH = "/api";

    public static final String LOCAL_CLIENT_BASE_PATH = "http://localhost:4200";

    public static final class UserPath {
        public static final String CTRL = BASE_PATH + "/user";
    }

    public static final class AdminPath {
        public static final String CTRL = BASE_PATH + "/admin";
    }

    public static final class ClubAdminPath {
        public static final String CTRL = BASE_PATH + "/clubadmin";
    }

}
