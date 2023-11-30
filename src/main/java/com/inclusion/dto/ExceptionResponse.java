package com.inclusion.dto;

import java.time.LocalDateTime;

public record ExceptionResponse(String message,
                                int status,
                                LocalDateTime date) { }
