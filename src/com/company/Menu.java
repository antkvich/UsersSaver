package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    public static UserPrinter userPrinter = new UserPrinter();
    public static ArrayList<User> usersList = new ArrayList<>();
    private static String filename = "users.dat";

    static int menu(){
        loadUsers();
        Menu.printMenu();
        int userChoice = getMenuChoice();
        menuHandler(userChoice);
        return userChoice;
    }

    static void printMenu(){
        System.out.println("1 - добавить пользователя.");
        System.out.println("2 - редактировать пользователя.");
        System.out.println("3 - удалить пользователя.");
        System.out.println("4 - вывести информацию о пользователе");
        System.out.println("5 - вывести информацию о всех пользователях");
        System.out.println("0 - выйти из программы");
    }

    static int getMenuChoice(){
        boolean correctInput = false;
        int userChoice = 10;
        Scanner choice = new Scanner(System.in);
        while (!correctInput){
            System.out.print("Ваш выбор: ");
            userChoice = choice.nextInt();
            if ((userChoice <= 5)   &   (userChoice >= 0)){
                correctInput = true;
            }else{
                System.out.println("Некорректный ввод.");
            }
        }
        return  userChoice;
    }

    static void menuHandler(int menuChoice){
        switch (menuChoice){
            case 1: {
                addUser();
                break;
            }
            case 2: {
                editUser();
                break;
            }
            case 3: {
                removeUser();
                break;
            }
            case 4: {
                printUser();
                break;
            }
            case 5: {
                printAllUsers();
            }

        }
        saveUsers();
    }

    private static void printAllUsers() {
        for (int i = 0; i < usersList.size(); i++) {
            printUser(i);
        }
    }

    private static void printUser() {
        int userId;
        userId = getIdByEmail();
        if(userId == -1){
            System.out.println("User not found");
        } else {
            userPrinter.printUser(usersList.get(userId));
        }
    }

    private static void printUser(int id) {
        userPrinter.printUser(usersList.get(id));
    }

    private static void removeUser() {
        int userId;
        userId = getIdByEmail();
        if(userId == -1){
            System.out.println("User not found");
        } else {
            usersList.remove(userId);
        }
    }

    private static void editUser() {
        int userId;
        userId = getIdByEmail();
        edit(userId);
    }

    private static void addUser(){
        usersList.add(createUser());
    }

    private static User createUser(){
        User user = new User();
        Scanner input = new Scanner(System.in);
        System.out.print("Введите имя: ");
        user.setFirstName(input.nextLine());
        System.out.print("Введите фамилию: ");
        user.setSecondName(input.nextLine());
        user.setRole();
        user.setEmail(inputEmail());
        user.setPhone();
        return user;
    }

    private static String inputEmail(){
        Scanner input = new Scanner(System.in);
        String email = "f";
        System.out.print("Введите e-mail адрес:");
        email = input.nextLine();
        while (!validateEmail(email)){
            System.out.print("Введите e-mail адрес:");
            email = input.nextLine();
        }
        return email;
    }

    private static boolean validateEmail(String mail){
        for (int i = 0; i < mail.length(); i++) {
            if (mail.charAt(i) == '@'){
                for (int j = i; j < mail.length(); j++) {
                    if (mail.charAt(j) == '.'){
                        return true;
                    }
                }
            }
        }
        System.out.println("Неверный формат e-mail адреса. Повторите попытку.");
        return false;
    }
    
    private static int getIdByEmail(){
        System.out.println("Введите email пользователя");
        Scanner input = new Scanner(System.in);
        String email;
        email = input.nextLine();
        for (int i = 0; i < usersList.size(); i++) {
            if (email.equals(usersList.get(i).getEmail()) ){
                return i;
            }
        }
        return -1;
    }

    private static void edit(int id){
        printEditMenu();
        Scanner input = new Scanner(System.in);
        int userChoice = input.nextInt();
        switch (userChoice){
            case 1:{
                System.out.print("Введите имя: ");
                usersList.get(id).setFirstName(input.nextLine());
                break;
            }
            case 2:{
                System.out.println("Введите фамилию: ");
                usersList.get(id).setSecondName(input.nextLine());
                break;
            }
            case 3:{
                String email = input.nextLine();
                System.out.print("Введите e-mail: ");
                if (validateEmail(email)) {
                    usersList.get(id).setEmail(email);
                }else {
                    System.out.println("Неверный формат!");
                }
                break;
            }
            case 4:{
                usersList.get(id).setPhone();
                break;
            }
            case 5:{
                usersList.get(id).setRole();
            }
        }
    }

    private static void printEditMenu(){
        System.out.println("Изменить имя - 1");
        System.out.println("Изменить фамилию - 2");
        System.out.println("Изменить email - 3");
        System.out.println("Изменить телефоны - 4");
        System.out.println("Изменить роли - 5");
    }

    private static void saveUsers(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oos.writeObject(usersList);
            System.out.println("Сохранено");
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }

    private static void loadUsers(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
        {

            usersList=((ArrayList<User>)ois.readObject());
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }


}
