package com.ms.demo.auth.domain.exception;

public class SecurityConfigurationException extends RuntimeException {
  public SecurityConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }
}
