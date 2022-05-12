package com.example.taserfan.base;

import com.example.taserfan.objects.Empleado;

public class LoggedInUserRepository {
    private static LoggedInUserRepository logged;
    private static Empleado empleado;

    public LoggedInUserRepository(){};

    public static LoggedInUserRepository getInstance(){
        if (logged==null){
            logged=new LoggedInUserRepository();
        }
        return logged;
    }
    public void login(Empleado empleado){
        LoggedInUserRepository.empleado=empleado;
    }
    public Empleado getLoggedUser(){
        return empleado;
    }
    public void logOut(){
        empleado=null;
    }
}
