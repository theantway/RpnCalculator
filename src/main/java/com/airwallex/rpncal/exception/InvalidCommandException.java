package com.airwallex.rpncal.exception;

import com.airwallex.rpncal.command.Command;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(Command command, String message) {
        super(String.format("operator %s (position: %d): %s",
                command.getOperator(), command.getPositionOfInput(), message));
    }
}
