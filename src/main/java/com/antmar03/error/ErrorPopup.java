package com.antmar03.error;

import com.antmar03.error.enums.ErrorCode;

import javax.swing.*;

public class ErrorPopup {
    public static void showPopup(ErrorCode errorCode) {
        JOptionPane.showMessageDialog(null, errorCode.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
    }
}
