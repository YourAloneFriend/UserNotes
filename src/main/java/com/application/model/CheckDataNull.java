package com.application.model;

public interface CheckDataNull {
    /**
     * @param data - supplied data.
     * @param nameData - name of the variable which is supplied.
     * */
    static void checkDataNull(String data, String nameData) throws NullPointerException {
        if(data == null || data.trim().isEmpty())
            throw new NullPointerException(String.format("%s is empty!", nameData));
    }
}
