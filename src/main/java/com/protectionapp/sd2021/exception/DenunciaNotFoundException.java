package com.protectionapp.sd2021.exception;

public class DenunciaNotFoundException extends RuntimeException{
    private String mensaje;

    public DenunciaNotFoundException(){
        super();
    }

    public DenunciaNotFoundException (String mensaje){
        super(mensaje);
    }

    public DenunciaNotFoundException (String mensaje, ArrayIndexOutOfBoundsException e){
        super(mensaje, e);
    }

    public DenunciaNotFoundException (String mensaje, Exception e){
        super(mensaje, e);
    }

    public String getMensaje() {
        return mensaje;
    }
}
