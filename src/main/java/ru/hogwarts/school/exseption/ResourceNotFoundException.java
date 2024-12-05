package ru.hogwarts.school.exseption;

public class ResourceNotFoundException extends RuntimeException {


  public ResourceNotFoundException() {
  }

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
