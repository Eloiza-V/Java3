package org.example;

@FunctionalInterface
public interface PersonFilter {
    Boolean filter(Person person);

}
