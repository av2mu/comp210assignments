package assn07;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below
        System.out.println("Enter Master Password");
        String masterPassword = scanner.nextLine();
        while (!masterPassword.equals("password123")){
            System.out.println("Enter Master Password");
            masterPassword = scanner.nextLine();
        }
        String command = null;
        while (!Objects.equals(command, "Exit")){
            command = scanner.nextLine();
            switch (command){
                case "New password": {
                    String website = scanner.nextLine();
                    String password = scanner.nextLine();
                    passwordManager.put(website, password);
                    System.out.println("New password added");
                    break;
                }
                case "Get password":{
                    String website = scanner.nextLine();
                    String password = passwordManager.get(website);
                    if (password == null){
                        System.out.println("Account does not exist");
                        }
                    else {
                        System.out.println(password);
                    }
                    break;
                }
                case "Delete account": {
                    String website = scanner.nextLine();
                    String password = passwordManager.get(website);
                    if (password == null){
                        System.out.println("Account does not exist");
                    }
                    else {
                        passwordManager.remove(website);
                        System.out.println("Account deleted");
                    }
                    break;
                }
                case "Check duplicate password": {
                    String password = scanner.nextLine();
                    List<String> duplicates = passwordManager.checkDuplicate(password);
                    if (duplicates.size() == 0){
                        System.out.println("No account uses that password");
                    }
                    else {
                        System.out.println("Websites using that password:");
                        for (String duplicate : duplicates) {
                            System.out.println(duplicate);
                        }
                    }
                    break;
                }
                case "Get accounts":{
                    ArrayList<String> websites = new ArrayList<>(passwordManager.keySet());
                        System.out.println("Your accounts:");
                        for (String website : websites) {
                            System.out.println(website);
                        }
                    break;
                    }
                case "Generate random password":{
                    String length = scanner.nextLine();
                    System.out.println(passwordManager.generateRandomPassword(Integer.parseInt(length)));
                    break;
                }
                default:
                    System.out.println("Command not found");
                    break;
                }



        }

    }
}
