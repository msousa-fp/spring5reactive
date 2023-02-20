package com.msousa.fp.reactiveexamples.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Person {
    private Integer id;
    String firstName;
    String lastName;
}
