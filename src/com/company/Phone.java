package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Phone implements Serializable {
    private ArrayList<String> phones = new ArrayList<>();

    public ArrayList<String> getPhones(){
        return phones;
    }

    public void setPhones(){
        phones.clear();
        int userChoise = 2;
        Scanner input = new Scanner(System.in);
        while ((userChoise != 0) && (phones.size() < 3)) {
            System.out.println("Хотите добавить телефон? 1-Да 0-Нет");
            userChoise = input.nextInt();
            if (userChoise == 1){
                phoneAdd();
            }
        }
    }
    private void phoneAdd(){
        String userPhone;
        Scanner input = new Scanner(System.in);
        System.out.print("Введите номер телефона N" + (phones.size() + 1) + ": 375");
        userPhone = input.nextLine();
        if (validateNumber(userPhone)){
            phones.add(userPhone);
        }

    }

    public boolean validateNumber(String num){
        String regex = "[0-9]+";
        if (num.matches(regex) && (num.length() == 9)){
            return true;
        }
        System.out.println("Некорректный ввод. повторите попытку");
        return false;
    }
}
