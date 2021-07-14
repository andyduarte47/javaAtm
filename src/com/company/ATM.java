package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import  java.text.DecimalFormat;

public class ATM {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<User>user = new ArrayList<>();
    static User logged_in = null;
    static String name;


    public static void main(String[] args) {
        while(true) {
            System.out.println("Welcome to the ATM, [create],[login],[logout],[display],[delete] account,[update] pin,[withdraw],[deposit],[check]balance,[exit]");
            System.out.print("> ");
            String input = sc.next();
            if(input.equalsIgnoreCase("create")) {
                create();
            }else if(input.equalsIgnoreCase("display")){
                displayAccounts();
            }else if(input.equalsIgnoreCase("login")){
                login();
            }else if(input.equalsIgnoreCase("logout") && logged_in != null){
                logged_in = null;
                continue;
            }else if(input.equalsIgnoreCase("deposit") && logged_in != null){
                deposit();
            }else if(input.equalsIgnoreCase("check")&& logged_in != null){
                displayBalance();
            }else if(input.equalsIgnoreCase("withdraw") && logged_in != null){
                withDraw();
            }else if(input.equalsIgnoreCase("exit")){
                System.exit(0);
            }else if(input.equalsIgnoreCase("delete")&& logged_in != null){
                delete(name);
            }else if(input.equalsIgnoreCase("update")&& logged_in != null){
                update();
            }
            else{
                System.out.println("create or log into an account.");
            }
        }


    }



    public static void login(){
        System.out.println("User ID: ");
        String UserID = sc.next();
        System.out.println("Pin: ");
        String pin = sc.next();
        for (User i : user){
            if(UserID.equalsIgnoreCase(i.userID)){
                if(pin.equalsIgnoreCase(i.pin)){
                    System.out.println("Login Successful");
                    logged_in = i;
                    break;
                }
            }
        }

    }

    public static void create(){

            System.out.println("First Name? ");
            String name = sc.next();
            System.out.println(("Last Name?"));
            String last = sc.next();
            System.out.println("create a user id: ");
            String userId = sc.next();
            System.out.println("create a pin? ");
            String pin = sc.next();
            User users = new User(name, last, userId, pin);
            user.add(users);
    }

    public static void deposit() {
            System.out.println("Are you broke?");
            String broke = sc.next();
             if (broke.equalsIgnoreCase("yes")) {
                System.out.println("Do you have Money in the ATM to withdraw? ");
                String money = sc.next();
                if (money.equalsIgnoreCase("yes")) {
                    System.out.print("pin: ");
                    String pin = sc.next();
                    if (pin.equalsIgnoreCase(logged_in.pin)) {
                        System.out.print("Amount you would like to deposit: ");
                        Integer depositAmount = sc.nextInt();
                        logged_in.amount.balance += depositAmount;
                    }else{
                        System.out.println("Invalid Pin");
                    }

                }else if(money.equalsIgnoreCase("no")){
                    System.exit(0);
                }
            }else if(broke.equalsIgnoreCase("no")){
                 System.out.println("Cool.");
                 System.out.print("pin: ");
                 String pin = sc.next();
                 if (pin.equalsIgnoreCase(logged_in.pin)) {
                     System.out.print("Amount you would like to deposit: ");
                     Integer depositAmount = sc.nextInt();
                     logged_in.amount.balance += depositAmount;
                 }else{
                     System.out.println("Invalid Pin");
                 }
             }

    }

    public static void displayBalance() {
                System.out.println("Pin: ");
                String pin = sc.next();
                if (pin.equalsIgnoreCase(logged_in.pin)) {
                        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
                        String format = decimalFormat.format(logged_in.amount.balance);
                        System.out.println("Current Balance : $" + format);
                    }else{
                    System.out.println("Invalid Input");
                }



    }


    public static void withDraw() {
                System.out.println("How much would you like to withdraw? ");
                Integer withdraw = sc.nextInt();
                double add = logged_in.amount.balance - withdraw;
                if(withdraw > logged_in.amount.balance){
                    System.out.println("insufficient funds. Try Again.");
                }else{
                    System.out.println("Current Balance : " + add);
                    logged_in.amount.balance = add;
                }
    }

    public static void displayAccounts(){
        for (User i : user) {
            System.out.println("first name: "+ i.first_name + "  "+"last name: " + i.last_name + " - " + i.pin);
        }
    }

    public static void delete(String name) {
        System.out.println("Name: ");
        String delete = sc.next();
            if (delete.equalsIgnoreCase(logged_in.first_name)) {
                for (User i : user) {
                    user.remove(i);
                    break;

                }
            }else{
                System.out.println("Delete your own account");
            }

    }

    public static void update() {
        boolean madeUpdate = false;
        System.out.println("Current Pin: ");
        String pin = sc.next();
        for (User i : user) {
            if (pin.equalsIgnoreCase(logged_in.pin)) {
                System.out.print("New pin: ");
                String changePin = sc.next();
                i.pin = changePin;
                madeUpdate = true;
            }
        }
        if (!madeUpdate) {
            System.out.println("that is not your pin!");
        }
    }


    }



