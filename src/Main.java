import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter origin: ");
        String origin = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter number of passengers: ");
        int passengerCount = scanner.nextInt();

        System.out.print("Enter number of users to simulate: ");
        int numberOfUsers = scanner.nextInt();

        for (int i = 0; i < numberOfUsers; i++) {
            Thread userThread = new Thread(new UserSimulation(origin, destination, passengerCount));
            userThread.start();
        }

        scanner.close();
    }
}