package com.kodilla.eprojectkbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Love Calculator not found!")
public class LoveCalculatorNotFoundException extends Exception{
}
