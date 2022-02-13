package com.marchmenow.backenddemo.exception;

import java.io.IOException;

public class QuoteException extends IOException {
    public QuoteException(String message) {
        super(message);
    }
}
