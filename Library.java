import java.util.Scanner;

public class Library {
    public static void main(String args[]) {
        Library lib=new Library();
        Scanner scan=new Scanner(System.in);

        while(true) {
            lib.printMenu();
            int choice=scan.nextInt();
            
            if(choice==0) {
                break;
            }
            else if(choice==1) {
                ;
            }
        }

        scan.close();
    }

    private void printMenu() {
        System.out.println("Welcome to the Library Management System !");
        System.out.println("Press 0 - Close");
        System.out.println("Press 1 - View Books");
        System.out.println("Press 2 - Add Book");
        System.out.println("Press 3 - Remove Book");
        System.out.println();
    }
}