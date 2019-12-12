package com.kodilla.eprojectkbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Motive not found!")
public class MotiveNotFoundException extends Exception {
}
