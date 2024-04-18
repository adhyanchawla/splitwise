package com.splitwise.SplitwiseApp;

import com.splitwise.SplitwiseApp.Repositories.ExpenseRepo;
import com.splitwise.SplitwiseApp.Repositories.UserRepo;
import com.splitwise.SplitwiseApp.Services.UserService;
import com.splitwise.SplitwiseApp.Services.impl.ExpenseServiceImpl;
import com.splitwise.SplitwiseApp.Services.impl.UserServiceImpl;
import com.splitwise.SplitwiseApp.SplitFactory.SplitFactory;
import com.splitwise.SplitwiseApp.commands.*;
import com.splitwise.SplitwiseApp.controllers.ExpenseController;
import com.splitwise.SplitwiseApp.controllers.UserController;
import com.splitwise.SplitwiseApp.strategies.EqualSplitStrategy;
import com.splitwise.SplitwiseApp.strategies.ExactSplitStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		UserRepo userRepo = new UserRepo();
		UserService userService = new UserServiceImpl(userRepo);
		ExpenseRepo expenseRepo = new ExpenseRepo();
		SplitFactory factory = new SplitFactory();
		ExpenseServiceImpl expenseService = new ExpenseServiceImpl(userRepo, expenseRepo, factory);

		ExpenseController expenseController = new ExpenseController(expenseService);
		UserController userController = new UserController(userService);

		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String command = input.split(" ")[0];
		do {
//			Scanner scanner = new Scanner(System.in);

			switch (command) {
				case "ADD_USER":
					new CreateUserCommand(input, userController).execute();
					break;
				case "SHOW":
					new ShowAllUsersBalanceCommand(input, expenseController).execute();
					break;
				case "SHOW_USER":
					new ShowUserBalanceCommand(input, expenseController).execute();
					break;
				case "EXPENSE":
					new CreateExpenseCommand(input, expenseController).execute();
					break;
				default:
					System.out.println("incorrect command");
					return;
			}
			input = scanner.nextLine();
			command = input.split(" ")[0];
		} while(command.equals("ADD_USER") || command.equals("SHOW") || command.equals("SHOW_USER") || command.equals("EXPENSE"));

	}
}

// functional requirements
// user: initiates a request to split bw the other users
// a split can be equal split, %split, total split
// we can have groups of users, where expense will be shared amongst the people of the group
// we can have expense between individuals
// we should show record history of whatever expense the user1 owes to user2 and so on

// adding users to expense
// creating, update, delete an expense
// settle-up an expense
// show expense history

// user: user_id, email, name
// expenseType: INDIVIDUAL, GROUP
// splitType: EQUAL, PERCENT, TOTAL
// expense: expense_id, expense_name, split-type, list<OwedBy> owedBy, list<OwedTo> owedTo, amount, expenseType
// OwedBy: user_id, amount
// OwedTo: user_id, amount


// UserService: addUser(UserDetails user)
// UserRepo: map<String, User> userMap

// expenseService: createExpense(ExpenseDetails expense), settleUp(Expense expense, User user)
// expenseRepo: map<expense_id, expense> expenseMap,
// map<expense_id, list<user>>
// map<user_id, list<user>> expenseUserMap

// U1 1200 EQUAL U1 U2 U3 U4
// PaidBy U1 -> 900
// OwedBy U2 -> 300
// OwedBy U3 -> 300
// OwedBy U4 -> 300

