package com.splitwise.SplitwiseApp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String userId;
    private String name;
    private String email;
    private String phone;
}
