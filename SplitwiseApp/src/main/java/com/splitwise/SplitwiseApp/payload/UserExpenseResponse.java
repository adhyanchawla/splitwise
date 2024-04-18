package com.splitwise.SplitwiseApp.payload;

import com.splitwise.SplitwiseApp.models.OwedBy;
import com.splitwise.SplitwiseApp.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserExpenseResponse {
    List<OwedBy> owedByList;
    User paidBy;
}
