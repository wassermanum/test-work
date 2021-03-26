package com.github.wassermanum.neolab_test_work.checker;

public class CheckerException extends RuntimeException {
    public CheckerException(String message) {
        super(message);
    }

    public CheckerException(String message, Throwable cause) {
        super(message, cause);
    }
}
