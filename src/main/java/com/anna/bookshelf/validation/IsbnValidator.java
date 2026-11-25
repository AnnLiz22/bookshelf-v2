package com.anna.bookshelf.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class IsbnValidator implements ConstraintValidator<ValidIsbn, String> {

  @Override
  public boolean isValid(String isbn, ConstraintValidatorContext context) {
    if (isbn == null || isbn.isBlank()) {
      return true;
    }
    String cleanIsbn = isbn.replace("-", "");

    if (!cleanIsbn.matches("\\d{9}[\\dX]|\\d{13}")) {
      return false;
    }
    if (cleanIsbn.length() == 13) {
      return isValidIsbn13(cleanIsbn);
    }
    if (cleanIsbn.length() == 10) {
      return isValidIsbn10(cleanIsbn);
    }
    return false;
  }

  private boolean isValidIsbn13(String isbn){
    String cleanIsbn = isbn.replace("-", "");
    char controlNumber = cleanIsbn.charAt(cleanIsbn.length()-1);
    List<Integer> numbers = new ArrayList<>();
    int sum = 0;

    for(char n : cleanIsbn.toCharArray()) {
      numbers.add(Character.getNumericValue(n));
    }
    for (int i = 0; i < numbers.size() - 1; i++) {

      if (i % 2 == 0) {
        sum += numbers.get(i);
      } else {
        sum += numbers.get(i) * 3;
      }
    }
    int expectedCheckDigit = (10 - (sum % 10)) % 10;
    return expectedCheckDigit == Character.getNumericValue(controlNumber);
  }

  private boolean isValidIsbn10(String isbn) {
    String cleanIsbn = isbn.replace("-", "");
    char controlNumber = cleanIsbn.charAt(cleanIsbn.length() - 1);
    int sum = 0;

    List<Integer> numbers = cleanIsbn.chars()
        .mapToObj(c -> (char) c)
        .map(c -> c == 'X' ? 10 : Character.getNumericValue(c))
        .toList();

    for (int i = 0; i < numbers.size() -1; i++) {
      sum += numbers.get(i) * (10 - i);

    }
    int expectedCheckDigit = (11 - (sum % 11)) % 11;

    int actualCheckDigit = controlNumber == 'X'
        ? 10
        : Character.getNumericValue(controlNumber);

    return expectedCheckDigit == actualCheckDigit;
  }
}