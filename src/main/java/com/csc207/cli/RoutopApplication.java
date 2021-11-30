package com.csc207.cli;

import com.csc207.domain.Week;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories("com.csc207.api")
@EntityScan({"com.csc207.domain"})
@ComponentScan({"com.csc207.api", "com.csc207.cli", "com.csc207.domain"})
public class RoutopApplication implements CommandLineRunner {
    @Autowired
    private final UserInterface ui;

    public RoutopApplication(UserInterface ui){
        this.ui = ui;
    }

    public static void main(String[] args) {
        SpringApplication.run(RoutopApplication.class, args);
    }

    @Override
    public void run(String... args){
        Scanner reader = new Scanner(System.in);
        boolean haveAccount = this.ui.signInOrSignUp(reader);
        Long userId = this.ui.activateSignInOrSignUp(haveAccount, reader);
        int createOrImportChoice;
        if(haveAccount){
            createOrImportChoice = UserInterface.createOrImportWeek(reader);
        } else {
            createOrImportChoice = UserInterface.createWeek(reader);
        }
        Week week = this.ui.activateCreateOrImport(userId, createOrImportChoice, reader);
        System.out.println("Here is your week:");
        System.out.println(week);

        while(true){
            int selectionForScheduling = UserInterface.scheduleDuty(reader);
            this.ui.schedulingDecision(week, selectionForScheduling, reader);
            System.out.println(week);
            if (selectionForScheduling == 4) {
                break;
            }
        }
        reader.close();
    }

}
