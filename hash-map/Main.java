package hash_map;

import java_intro.Transaction;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();
        System.out.println("Enter Master Password");
        String masterPassword = scanner.nextLine();

        while (!passwordManager.checkMasterPassword(masterPassword)){
            System.out.println("Enter Master Password");
            masterPassword = scanner.nextLine();
        }
        
        boolean exit = false;
        while (!exit){
            String command = scanner.nextLine();
            if (command.equals("New password")){
                String key = scanner.nextLine();
                String value = scanner.nextLine();
                passwordManager.put(key, value);
                System.out.println("New password added");
            } else if (command.equals("Get password")){
                String key = scanner.nextLine();
                if (passwordManager.get(key) != null){
                    System.out.println(passwordManager.get(key));
                } else{
                    System.out.println("Account does not exist");
                }
            } else if (command.equals("Delete account")){
                String key = scanner.nextLine();
                if (passwordManager.remove(key) != null){
                    System.out.println("Account deleted");
                } else{
                    System.out.println("Account does not exist");
                }
            } else if (command.equals("Check duplicate password")){
                String value = scanner.nextLine();
                if (passwordManager.checkDuplicate(value).size() == 0){
                    System.out.println("No account uses that password");
                } else{
                    System.out.println("Websites using that password:");
                    for (int i = 0; i < passwordManager.checkDuplicate(value).size(); i++){
                        System.out.println(passwordManager.checkDuplicate(value).get(i));
                    }
                }
            } else if (command.equals("Get accounts")){
                System.out.println("Your accounts:");
                for (String key: passwordManager.keySet()){
                    System.out.println(key);
                }
            } else if (command.equals("Generate random password")){
                int length = Integer.parseInt(scanner.nextLine());
                System.out.println(passwordManager.generateRandomPassword(length));
            } else if(command.equals("Exit")){
                exit = true;
            } else {
                System.out.println("Command not found");
            }
        }
    }
}
