package com.company;

public class UserPrinter {



    User user =  new User();


    public void printUser(User userToPrint){
        System.out.println();
        System.out.println("Имя : " + userToPrint.getFirstName());
        System.out.println("Фамилия : " + userToPrint.getFirstName());
        System.out.println("E-mail : " + userToPrint.getEmail());
        System.out.print("Роли : ");
        printRoles(userToPrint);
        System.out.print("Телефоны : ");
        printPhones(userToPrint);
        System.out.println();
    }
    private void printRoles(User userRolesPrint){
        for (int i = 0; i < userRolesPrint.getRole().size(); i++) {
            System.out.print(userRolesPrint.getRole().get(i) + " ");
        }
        System.out.println();
    }
    private void printPhones(User userPhonesPrint){
        for (int i = 0; i < userPhonesPrint.getPhone().size(); i++) {
            System.out.print( "375"+ userPhonesPrint.getPhone().get(i) + " ");
        }
        System.out.println();
    }

}
