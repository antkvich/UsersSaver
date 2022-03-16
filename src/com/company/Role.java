package com.company;

import javax.sql.rowset.spi.SyncResolver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Role implements Serializable {
    //boolean[] roles = new boolean[5]; //user customer admin provider superadmin
    final ArrayList<String> roles = new ArrayList<>();


    public void setRoles() {
        int userChoice = -1;
        roles.clear();
        while (userChoice != 0){
            printRolesChoice();
            userChoice = checkRoleInput();
            roles.add(switchChoiceToRole(userChoice));
        }
    }



    private void printRolesChoice(){
        System.out.println("Выберите роль для пользователся:");
        System.out.println("1 - user;");
        System.out.println("2 - customer;");
        System.out.println("3 - admin;");
        System.out.println("4 - provider;");
        System.out.println("5 - super admin;");
        System.out.println("0 - продолжить.");
    }

    private int checkRoleInput(){
        boolean correctInput = false;
        int userChoice = 10;
        Scanner choice = new Scanner(System.in);
        while (!correctInput){
            System.out.print("Ваш выбор: ");
            userChoice = choice.nextInt();
            if ((userChoice <= 5)   &&   (userChoice >= 0) && (checkRoleCollision(userChoice))){
                correctInput = true;
            } else{
                System.out.println("Некорректный ввод. Повторите попытку");
            }
        }
        return userChoice;


    }


    private boolean checkRoleCollision(int chosenRole){
        switch (chosenRole){
            case (1): {
                return collision(2);
            }
            case (2): {
                return collision(1);
            }
            case (3): {
                return collision(4);
            }
            case (4): {
                return collision(3);
            }
            case (5): {
                if (roles.size() == 0){
                    return true;
                }
            }
            case (0):
                return true;
        }
        return false;
    }

    private boolean collision(int role1){
        return !roles.contains(switchChoiceToRole(role1)) && (!roles.contains(switchChoiceToRole(5)));
    }

    private String switchChoiceToRole(int choice){
        switch (choice){
            case 1:
                return "user";
            case 2:
                return "customer";
            case 3:
                return "admin";
            case 4:
                return "provider";
            case 5:
                return "superAdmin";
        }
        return "";
    }
}
