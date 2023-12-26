import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Zoo {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    boolean continueProgram = true;
    Admin admin = new Admin();

    while (continueProgram) {
      System.out.println("Welcome to Zoo Management System");
      System.out.println("1. Enter as Admin");
      System.out.println("2. Enter as a Visitor");
      System.out.println("3. View Special Deals");

      System.out.print("Select an option: ");
      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          admin.login();
          break;
        case 2:
          Visitor.showOptions();
          break;

        case 3:
          admin.viewSpecialDeals();
          break;

        default:
          System.out.println("Invalid choice");
      }

      System.out.println("\nDo you want to continue? (yes/no)");
      sc.nextLine(); // Consume newline
      String userInput = sc.nextLine().toLowerCase();
      if (!userInput.equals("yes")) {
        continueProgram = false;
      }
    }
  }
}

class Admin {

  String username;
  String password;
  Scanner sc = new Scanner(System.in);

  public void login() {

    System.out.print("Enter admin username: ");
    username = sc.nextLine();

    System.out.print("Enter admin password: ");
    password = sc.nextLine();

    if (username.equals("admin") && password.equals("1234")) {
      showMenu();
    } else {
      System.out.println("Invalid credentials");
    }
  }

  public void setDiscounts() {

    int choice;

    while (true) {

      System.out.println("\nSet Discounts");
      System.out.println("1. Add Discount");
      System.out.println("2. Modify Discount");
      System.out.println("3. Remove Discount");
      System.out.println("4. View Discount");

      System.out.println("5. Exit");

      System.out.print("Enter your choice: ");
      choice = sc.nextInt();

      switch (choice) {
        case 1:
          addDiscount();
          break;

        case 2:
          modifyDiscount();
          break;

        case 3:
          removeDiscount();
          break;

        case 4:
          viewDiscounts();

        case 5:
          return;

        default:
          System.out.println("Invalid option");
      }
    }
  }

  public static Map<String, Integer> discounts = new HashMap<>();

  public static Map<String, Integer> getDiscounts() {
    return discounts;
  }

  public void addDiscount() {
    sc.nextLine(); // Consume newline character left by previous nextInt()

    System.out.println("Select Discount Category:");
    System.out.println("1. Student (Age < 18)");
    System.out.println("2. Senior Citizen (Age > 60)");
    int choice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (choice == 1) {
      System.out.print("Enter Discount Percent for Students: ");
    } else if (choice == 2) {
      System.out.print("Enter Discount Percent for Senior Citizens: ");
    } else {
      System.out.println("Invalid choice");
      return;
    }

    int percentage = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (percentage >= 0 && percentage <= 100) {
      String category = (choice == 1) ? "Student" : "SeniorCitizen";

      // Store the discount category and percentage
      discounts.put(category, percentage);

      System.out.println("Discount added successfully for " + category + " category.");
    } else {
      System.out.println("Invalid discount percentage. Please enter a value between 0 and 100.");
    }
  }

  public void modifyDiscount() {
    sc.nextLine(); // Consume newline character left by previous nextInt()

    System.out.println("Select Discount Category to Modify:");
    System.out.println("1. Student (Age < 18)");
    System.out.println("2. Senior Citizen (Age > 60)");
    int choice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (choice == 1 || choice == 2) {
      String category = (choice == 1) ? "Student" : "SeniorCitizen";
      if (discounts.containsKey(category)) {
        System.out.print("Enter new Discount Percent for " + category + ": ");
        int newPercentage = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (newPercentage >= 0 && newPercentage <= 100) {
          discounts.put(category, newPercentage);
          System.out.println("Discount modified successfully for " + category + " category.");
        } else {
          System.out.println("Invalid discount percentage. Please enter a value between 0 and 100.");
        }
      } else {
        System.out.println("Discount category not found.");
      }
    } else {
      System.out.println("Invalid choice");
    }
  }

  public void removeDiscount() {
    sc.nextLine(); // Consume newline character left by previous nextInt()

    System.out.println("Select Discount Category to Remove:");
    System.out.println("1. Student (Age < 18)");
    System.out.println("2. Senior Citizen (Age > 60)");
    int choice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (choice == 1 || choice == 2) {
      String category = (choice == 1) ? "Student" : "SeniorCitizen";
      if (discounts.containsKey(category)) {
        discounts.remove(category);
        System.out.println("Discount removed successfully for " + category + " category.");
      } else {
        System.out.println("Discount category not found.");
      }
    } else {
      System.out.println("Invalid choice");
    }
  }

  public void viewDiscounts() {
    System.out.println("\nDiscounts:");
    for (Map.Entry<String, Integer> entry : discounts.entrySet()) {
      String category = entry.getKey();
      int percentage = entry.getValue();
      System.out.println(category + " Discount: " + percentage + "%");
    }
  }

  public void applyDiscount(String discountCode, double cost) {
    Integer discountPercentage = discounts.get(discountCode);

    if (discountPercentage != null) {
      double discountedCost = cost * (1 - (discountPercentage / 100.0));
      System.out.println("Discount applied successfully.");
      System.out.println("Total cost after discount: ₹" + discountedCost);
    } else {
      System.out.println("Invalid discount code");
    }
  }

  public void manageAnimals() {
    int choice;
    while (true) {
      System.out.println("\nManage Animals");
      System.out.println("1. Add Animal");
      System.out.println("2. Update Animal Details");
      System.out.println("3. Remove Animal");
      System.out.println("4. View Animals");
      System.out.println("5. Exit");

      System.out.print("Enter your choice: ");
      choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          addAnimal();
          break;

        case 2:
          updateAnimalDetails();
          break;

        case 3:
          removeAnimal();
          break;

        case 4:
          viewAnimals();
          break;

        case 5:
          return;

        default:
          System.out.println("Invalid option");
      }
    }
  }

  public static List<Animal> animals = new ArrayList<>(
      Arrays.asList(
          new Animal("Elephant", "Mammal", "Large herbivorous mammals known for their trunk."),
          new Animal("Monkey", "Mammal", "Small to medium-sized primates."),
          new Animal("Frog", "Amphibian", "Amphibians known for their jumping abilities."),
          new Animal("Salamander", "Amphibian", "Semi-aquatic amphibians with slender bodies."),
          new Animal("Snake", "Reptile", "Legless reptiles known for their slithering movement."),
          new Animal("Turtle", "Reptile", "Shelled reptiles that are primarily aquatic.")));

  public List<Animal> getAnimals() {
    return animals;
  }

  public void addAnimal() {
    System.out.print("Enter Animal Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Animal Type: ");
    String type = sc.nextLine();

    System.out.print("Enter Description of " + name + ": ");
    String description = sc.nextLine();

    Animal newAnimal = new Animal(name, type, description);
    animals.add(newAnimal);

    System.out.println("Animal added to Zoo.");
  }

  public void updateAnimalDetails() {
    System.out.print("Enter Animal Name to Update: ");
    String nameToUpdate = sc.nextLine();

    for (Animal animal : animals) {
      if (animal.getName().equals(nameToUpdate)) {
        System.out.print("Enter New Animal Name: ");
        String newName = sc.nextLine();

        System.out.print("Enter New Animal Type: ");
        String newType = sc.nextLine();

        System.out.print("Enter New Description: ");
        String newDescription = sc.nextLine();

        animal.setName(newName);
        animal.setType(newType);
        animal.setDescription(newDescription);

        System.out.println("Animal details updated successfully.");
        return;
      }
    }

    System.out.println("Animal not found.");
  }

  public void removeAnimal() {
    System.out.print("Enter Animal Name to Remove: ");
    String nameToRemove = sc.nextLine();

    for (Animal animal : animals) {
      if (animal.getName().equals(nameToRemove)) {
        animals.remove(animal);
        System.out.println("Animal removed successfully.");
        return;
      }
    }

    System.out.println("Animal not found.");
  }

  public void viewAnimals() {
    System.out.println("\nAnimals in the Zoo:");
    for (Animal animal : animals) {
      System.out.println("Name: " + animal.getName());
      System.out.println("Type: " + animal.getType());
      System.out.println("Description: " + animal.getDescription());
      System.out.println("-----");
    }
  }

  public void viewFeedback() {
    for (Visitor visitor : Visitor.registeredVisitors) {
      if (visitor.getFeedback() != null && !visitor.getFeedback().isEmpty()) {
        System.out.println("\nVisitor Name: " + visitor.getName());
        System.out.println("Visitor Age: " + visitor.getAge());
        System.out.println("Visitor Phone Number: " + visitor.getPhoneNumber());
        System.out.println("Visitor Email: " + visitor.getEmail());
        System.out.println("Visitor Feedback: " + visitor.getFeedback());
      }
    }
  }

  public void showMenu() {

    int choice;

    while (true) {

      System.out.println("\nAdmin Menu:");
      System.out.println("1. Manage Attractions");
      System.out.println("2. Manage Animals");
      System.out.println("3. Schedule Events");
      System.out.println("4. Set Discounts");
      System.out.println("5. Set Special Deal");
      System.out.println("6. View Visitor Stats");
      System.out.println("7. View Feedbacks");
      System.out.println("8. Exit");

      System.out.print("Enter choice: ");
      choice = sc.nextInt();

      switch (choice) {
        case 1:
          manageAttractions();
          break;
        case 2:
          manageAnimals();
          break;
        case 3:
          scheduleAttractionEvents();
          break;
        case 4:
          setDiscounts();
          break;
        case 5:
          setSpecialDeal();
          break;
        case 6:
          Visitor.viewVisitorStats();
          break;
        case 7:
          viewFeedback();
          break;
        case 8:
          System.out.println("Logged Out");
          return;
        default:
          System.out.println("Invalid option");
      }
    }
  }

  public static List<Attraction> attractions = new ArrayList<>();

  public List<Attraction> getAttractions() {
    return attractions;
  }

  public void manageAttractions() {

    int choice;

    while (true) {

      System.out.println("\nManage Attractions");
      System.out.println("1. Add Attraction");
      System.out.println("2. View Attractions");
      System.out.println("3. Modify Attraction");
      System.out.println("4. Remove Attraction");
      System.out.println("5. Exit");

      System.out.print("Enter your choice: ");
      choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          addAttraction();
          break;

        case 2:
          viewAttractions();
          break;

        case 3:
          modifyAttraction();
          break;

        case 4:
          removeAttraction();
          break;

        case 5:
          return; // Exit

        default:
          System.out.println("Invalid option");
      }
    }
  }

  public void addAttraction() {
    System.out.print("Enter Attraction Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Attraction Description: ");
    String description = sc.nextLine();

    System.out.print("Enter Exploration Price for " + name + ": ");
    double price = sc.nextDouble();
    sc.nextLine(); // Consume newline

    Attraction newAttraction = new Attraction(name, description);
    newAttraction.setExplorationPrice(price);

    attractions.add(newAttraction);

    System.out.println("Attraction added successfully.");
  }

  public void viewAttractions() {
    System.out.println("\nAttractions in the Zoo:");
    for (Attraction attraction : attractions) {
      System.out.println("Name: " + attraction.getName());
      System.out.println("Description: " + attraction.getDescription());
      System.out.println("Exploration Price: ₹" + attraction.getExplorationPrice()); // Added line for price
      System.out.println("-----");
    }
  }

  public void modifyAttraction() {
    System.out.print("Enter Attraction Name to Modify: ");
    String nameToModify = sc.nextLine();

    for (Attraction attraction : attractions) {
      if (attraction.getName().equals(nameToModify)) {
        System.out.print("Enter New Attraction Name: ");
        String newName = sc.nextLine();
        System.out.print("Enter New Attraction Description: ");
        String newDescription = sc.nextLine();
        System.out.print("Enter New Exploration Price: ");
        double newPrice = sc.nextDouble();
        sc.nextLine(); // Consume newline
        attraction.setName(newName);
        attraction.setDescription(newDescription);
        attraction.setExplorationPrice(newPrice); // Set the new exploration price
        System.out.println("Attraction modified successfully.");
        return;
      }
    }

    System.out.println("Attraction not found.");
  }

  public void removeAttraction() {
    System.out.print("Enter Attraction Name to Remove: ");
    String nameToRemove = sc.nextLine();

    for (Attraction attraction : attractions) {
      if (attraction.getName().equals(nameToRemove)) {
        attractions.remove(attraction);
        System.out.println("Attraction removed successfully.");
        return;
      }
    }

    System.out.println("Attraction not found.");
  }

  public void setSpecialDeal() {
    while (true) {
      System.out.println("\nSet Special Deals:");
      System.out.println("1. Add Deal");
      System.out.println("2. View Deals");
      System.out.println("3. Remove Deal");
      System.out.println("4. Exit");

      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          System.out.println("\nAdd Special Deal:");
          System.out.print("Minimum number of tickets to buy: ");
          int minAttractions = sc.nextInt();
          System.out.print("Percent Discount offered: ");
          int discountPercentage = sc.nextInt();
          addSpecialDeal(minAttractions, discountPercentage);
          System.out.println("Deal added successfully.");
          break;
        case 2:
          viewSpecialDeals();
          break;
        case 3:
          System.out.println("\nRemove Special Deal:");
          System.out.print("Enter the index of the deal to remove: ");
          int dealIndex = sc.nextInt();
          removeSpecialDeal(dealIndex);
          break;
        case 4:
          return; // Exit
        default:
          System.out.println("Invalid option");
      }
    }
  }

  public static List<SpecialDeal> specialDeals = new ArrayList<>();

  // Method to add a special deal
  public void addSpecialDeal(int minAttractions, int discountPercentage) {
    specialDeals.add(new SpecialDeal(minAttractions, discountPercentage));
    System.out.println("Special deal added successfully.");
  }

  // Method to remove a special deal
  public void removeSpecialDeal(int index) {
    if (index >= 0 && index < specialDeals.size()) {
      specialDeals.remove(index);
      System.out.println("Special deal removed successfully.");
    } else {
      System.out.println("Invalid index.");
    }
  }

  // Method to apply special deals during ticket purchase
  public double applySpecialDeals(List<Attraction> selectedAttractions, double totalCost) {
    int numSelectedAttractions = selectedAttractions.size();

    for (SpecialDeal deal : specialDeals) {
      if (numSelectedAttractions > deal.getMinAttractions()) {
        double discountAmount = totalCost * (deal.getDiscountPercentage() / 100.0);
        return totalCost - discountAmount;
      }
    }

    return totalCost;
  }

  public void viewSpecialDeals() {
    System.out.println("\nSpecial Deals:");
    int index = 1;
    for (SpecialDeal deal : specialDeals) {
      System.out.println(
          index + ". Buy " + deal.getMinAttractions() + " tickets and get " + deal.getDiscountPercentage() + "% off");
      index++;
    }
  }

  public SpecialDeal getApplicableDeal(int numTickets) {
    for (SpecialDeal deal : specialDeals) {
      if (deal.getMinAttractions() <= numTickets) {
        return deal;
      }
    }
    return null; // No applicable deal found
  }

  public void scheduleAttractionEvents() {
    System.out.println("\nSchedule Attractions Events:");

    System.out.println("Select Attraction to Schedule Event:");

    List<Attraction> attractions = getAttractions();
    for (int i = 0; i < attractions.size(); i++) {
      System.out.println((i + 1) + ". " + attractions.get(i).getName());
    }

    System.out.println("0. Exit");
    System.out.print("Enter your choice: ");
    int choice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (choice == 0) {
      return;
    }

    if (choice >= 1 && choice <= attractions.size()) {
      Attraction selectedAttraction = attractions.get(choice - 1);

      System.out.print("Enter Opening Time (HH:mm): ");
      String openingTime = sc.nextLine();

      System.out.print("Enter Closing Time (HH:mm): ");
      String closingTime = sc.nextLine();

      System.out.print("Enter Ticket Price: ");
      double ticketPrice = sc.nextDouble();
      sc.nextLine(); // Consume newline

      selectedAttraction.setOpeningTime(openingTime);
      selectedAttraction.setClosingTime(closingTime);
      selectedAttraction.setExplorationPrice(ticketPrice);

      System.out.println("Event scheduled successfully for " + selectedAttraction.getName() + ".");
    } else {
      System.out.println("Invalid option");
    }
  }

}

class Attraction {
  private String name;
  private String description;
  private String openingTime;
  private String closingTime;

  public Attraction(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public double explorationPrice;

  public void setExplorationPrice(double price) {
    this.explorationPrice = price;
  }

  public double getExplorationPrice() {
    return explorationPrice;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getOpeningTime() {
    return openingTime;
  }

  public void setOpeningTime(String openingTime) {
    this.openingTime = openingTime;
  }

  public String getClosingTime() {
    return closingTime;
  }

  public void setClosingTime(String closingTime) {
    this.closingTime = closingTime;
  }
}

class Animal {
  private String name;
  private String type;
  private String description;

  public Animal(String name, String type, String description) {
    this.name = name;
    this.type = type;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}

class SpecialDeal {
  private int minAttractions;
  private int discountPercentage;

  public SpecialDeal(int minAttractions, int discountPercentage) {
    this.minAttractions = minAttractions;
    this.discountPercentage = discountPercentage;
  }

  public int getMinAttractions() {
    return minAttractions;
  }

  public int getDiscountPercentage() {
    return discountPercentage;
  }
}

class MembershipData {
  private String membershipType;
  private int numTicketsBought;

  public MembershipData(String membershipType, int numTicketsBought) {
    this.membershipType = membershipType;
    this.numTicketsBought = numTicketsBought;
  }

  public String getMembershipType() {
    return membershipType;
  }

  public void setMembershipType(String membershipType) {
    this.membershipType = membershipType;
  }

  public int getNumTicketsBought() {
    return numTicketsBought;
  }

  public void setNumTicketsBought(int numTicketsBought) {
    this.numTicketsBought = numTicketsBought;
  }

  public void incrementTicketsBought(int numTickets) {
    this.numTicketsBought += numTickets;
  }

  @Override
  public String toString() {
    return "Membership Type: " + membershipType + ", Number of Tickets Bought: " + numTicketsBought;
  }
}

class SpendingRecord {
  private String visitorUsername;
  private double membershipSpending;
  private double ticketSpending;
  private double totalSpending;

  public SpendingRecord(String visitorUsername, double membershipSpending, double ticketSpending,
      double totalSpending) {
    this.visitorUsername = visitorUsername;
    this.membershipSpending = membershipSpending;
    this.ticketSpending = ticketSpending;
    this.totalSpending = totalSpending;
  }

  public String getVisitorUsername() {
    return visitorUsername;
  }

  public double getMembershipSpending() {
    return membershipSpending;
  }

  public double getTicketSpending() {
    return ticketSpending;
  }

  public double getTotalSpending() {
    return totalSpending;
  }
}

class Visitor {
  public static List<Visitor> registeredVisitors = new ArrayList<>();
  public String name;
  public int age;
  public String phoneNumber;
  public double balance;
  public String email;
  public String password;
  public String feedback;
  Admin admin = new Admin();
  private double originalBalance;
  private MembershipData membershipData = new MembershipData("NONE", 0);

  Scanner sc = new Scanner(System.in);

  public static void showOptions() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      System.out.println("\nVisitor Menu:");
      System.out.println("1. Register");
      System.out.println("2. Login");
      System.out.println("3. Exit");

      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          register();
          break;

        case 2:
          login();
          break;

        case 3:
          return;

        default:
          System.out.println("Invalid choice");
      }
    }
  }

  public void leaveFeedback() {
    // sc.nextLine(); // Consume newline character left by previous nextInt()

    System.out.print("Enter your feedback: ");
    feedback = sc.nextLine();

    System.out.println("Thank you for your feedback!");
  }

  public String getFeedback() {
    return feedback;
  }

  private static void register() {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter Visitor Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Visitor Age: ");
    int age = sc.nextInt();
    sc.nextLine(); // Consume newline

    System.out.print("Enter Visitor Phone Number: ");
    String phoneNumber = sc.nextLine();

    System.out.print("Enter Visitor Balance: ");
    double balance = sc.nextDouble();
    sc.nextLine(); // Consume newline

    System.out.print("Enter Visitor Email: ");
    String email = sc.nextLine();

    System.out.print("Enter Visitor Password: ");
    String password = sc.nextLine();

    Visitor newVisitor = new Visitor(name, age, phoneNumber, balance, email, password);
    registeredVisitors.add(newVisitor);

    System.out.println("\nRegistration is successful.");
    // newVisitor.showMenu();
  }

  private static void login() {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter Visitor Email: ");
    String email = sc.nextLine();

    System.out.print("Enter Visitor Password: ");
    String password = sc.nextLine();

    for (Visitor visitor : registeredVisitors) {
      if (visitor.getEmail().equals(email) && visitor.getPassword().equals(password)) {
        System.out.println("\nLogin Successful.");
        visitor.showMenu();
        return;
      }
    }

    System.out.println("\nInvalid email or password. Please try again.");
  }

  public Visitor(String name, int age, String phoneNumber, double balance, String email, String password) {
    this.name = name;
    this.age = age;
    this.phoneNumber = phoneNumber;
    this.balance = balance;
    this.email = email;
    this.password = password;
    this.originalBalance = balance;

  }

  public void viewAttractions() {
    System.out.println("\nAttractions in the Zoo:");
    for (Attraction attraction : Admin.attractions) {
      System.out.println("Name: " + attraction.getName());
      System.out.println("Description: " + attraction.getDescription());
      System.out.println("-----");
    }
  }

  public void exploreZoo() {
    int choice;
    while (true) {
      System.out.println("\nExplore the Zoo:");
      System.out.println("1. View Attractions");
      System.out.println("2. View Animals");
      System.out.println("3. Exit");

      System.out.print("Enter your choice: ");
      choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          admin.viewAttractions();
          break;
        case 2:
          admin.viewAnimals();
          break;
        case 3:
          return;
        default:
          System.out.println("Invalid option");
      }
    }
  }

  public void buyMembership() {
    int choice;

    while (true) {
      System.out.println("\nBuy Membership:");
      System.out.println("1. Basic Membership (₹20)");
      System.out.println("2. Premium Membership (₹50)");
      System.out.println("3. Exit");

      System.out.print("Enter your choice: ");
      choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          purchaseMembership(20, "Basic");
          break;
        case 2:
          purchaseMembership(50, "Premium");
          break;
        case 3:
          return;
        default:
          System.out.println("Invalid option");
      }
    }
  }

  public void purchaseMembership(double cost, String membershipType) {
    // Get the discounts HashMap from the Admin class
    Map<String, Integer> discounts = Admin.getDiscounts();

    // Check if the visitor is eligible for a discount based on age
    if (age < 18 && discounts.containsKey("Student")) {
      System.out.println("You are eligible for a discount.");
      System.out.println("Discount code: MINOR" + discounts.get("Student") + "%");

      System.out.print("Enter discount code (or press Enter to skip): ");
      String discountCode = sc.nextLine();

      if (discountCode.equals("MINOR" + discounts.get("Student"))) {
        cost *= (1 - (discounts.get("Student") / 100.0));
      } else {
        System.out.println("Invalid discount code. Proceeding without discount.");
      }
    } else if (age > 60 && discounts.containsKey("SeniorCitizen")) {
      System.out.println("You are eligible for a discount.");
      System.out.println("Discount code: SENIOR" + discounts.get("SeniorCitizen") + "%");

      System.out.print("Enter discount code (or press Enter to skip): ");
      String discountCode = sc.nextLine();

      if (discountCode.equals("SENIOR" + discounts.get("SeniorCitizen"))) {
        cost *= (1 - (discounts.get("SeniorCitizen") / 100.0));
      } else {
        System.out.println("Invalid discount code. Proceeding without discount.");
      }
    } else {
      System.out.println("Discount not applicable.");
    }

    System.out.println("\nThe ticket for membership costs ₹" + cost);

    System.out.print("Do you want to proceed with the purchase? (yes/no): ");
    String confirmation = sc.nextLine();

    if (confirmation.equalsIgnoreCase("yes")) {
      if (balance >= cost) {
        balance -= cost;
        System.out.println("Membership purchased successfully.");
        this.membershipType = membershipType;

        System.out.println("Remaining balance: ₹" + balance);
        membershipData = new MembershipData(membershipType, 0);
      } else {
        System.out.println("Insufficient balance. Please recharge your account.");
      }
    } else {
      System.out.println("Purchase canceled.");
    }
  }

  public HashMap<String, Integer> ticketsBought = new HashMap<>();

  public void buyTickets() {
    int choice;

    while (true) {
      System.out.println("\nBuy Tickets:");
      admin.viewSpecialDeals();
      System.out.print("Enter the number of tickets: ");
      int numTickets = sc.nextInt();
      sc.nextLine(); // Consume newline

      System.out.println("\nSelect an Attraction to Buy a Ticket:");

      List<Attraction> attractions = admin.getAttractions();
      for (Attraction attraction : attractions) {
        int index = 1;
        System.out.println(index + ". " + attraction.getName() + " (₹" + attraction.getExplorationPrice() + ")");
        index++;
      }
      System.out.println("0. Exit");

      System.out.print("Enter your choice: ");
      choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      if (choice == 0) {
        return;
      }

      if (choice >= 1 && choice <= attractions.size()) {
        Attraction selectedAttraction = attractions.get(choice - 1);
        String attractionName = selectedAttraction.getName();
        double cost = selectedAttraction.getExplorationPrice();
        purchaseTicket(attractionName, cost, numTickets);
      } else {
        System.out.println("Invalid option");
      }
    }
  }

  public String membershipType = "NONE";

  public void purchaseTicket(String attraction, double cost, int numTickets) {

    if (membershipType.equals("Premium")) {
      System.out.println("Premium members don't need to purchase tickets.");
      showMenu();
      return;
    }

    double totalCost = cost * numTickets;

    // Check if there is a special deal for the number of tickets being purchased
    SpecialDeal applicableDeal = admin.getApplicableDeal(numTickets);

    if (applicableDeal != null) {
      System.out.println("You are eligible for a " + applicableDeal.getDiscountPercentage() + "% discount!");
      double discountAmount = (applicableDeal.getDiscountPercentage() / 100.0) * totalCost;
      totalCost -= discountAmount;
    }

    while (true) {

      if (balance >= totalCost) {
        // Purchase successful
        System.out.println("Tickets purchased successfully!");
        balance -= totalCost;
        membershipData.incrementTicketsBought(numTickets);
        ticketsBought.put(attraction, ticketsBought.getOrDefault(attraction, 0) + numTickets);
        System.out.println("Remaining balance is " + balance);
        showMenu();
        return;

      } else {
        // Insufficient balance

        System.out.println("Insufficient balance for " + numTickets + " tickets.");
        System.out.println("1. Try lower number of tickets");
        System.out.println("2. Back to main menu");

        int choice = sc.nextInt();
        if (choice == 2) {
          // Go back to menu
          showMenu();
          return;
        }

        // Try again with lower ticket quantity
        System.out.print("Enter number of tickets to purchase: ");
        numTickets = sc.nextInt();
        totalCost = cost * numTickets;
      }
    }
  }

  public void visitAttractions() {
    while (true) {
      System.out.println("\nVisit Attractions:");
      System.out.println("Select an Attraction to Visit:");

      List<Attraction> attractions = admin.getAttractions();
      for (int i = 0; i < attractions.size(); i++) {
        System.out.println((i + 1) + ". " + attractions.get(i).getName());
      }
      System.out.println("0. Exit");

      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      if (choice == 0) {
        return;
      }

      if (choice >= 1 && choice <= attractions.size()) {
        Attraction selectedAttraction = attractions.get(choice - 1);
        String attractionName = selectedAttraction.getName();

        if (membershipType.equals("Premium")) {
          System.out.println("Thank you for visiting " + attractionName + "!");
        } else if (membershipType.equals("Basic")) {
          if (ticketsBought.containsKey(attractionName) && ticketsBought.get(attractionName) > 0) {
            System.out.println("Thank you for visiting " + attractionName + "!");
            ticketsBought.put(attractionName, ticketsBought.get(attractionName) - 1);
          } else {
            System.out.println("Ticket not available for " + attractionName + ".");
          }
        } else {
          System.out.println("Please buy a membership first.");
        }
      } else {
        System.out.println("Invalid option");
      }
    }
  }

  public void visitAnimals() {
    if (membershipType.equals("NONE")) {
      System.out.println("Please buy a membership first.");
      return;
    }

    // Show the list of animals
    System.out.println("\nSelect an Animal to Visit:");
    List<Animal> zooAnimals = admin.getAnimals();
    for (int i = 0; i < zooAnimals.size(); i++) {
      System.out.println((i + 1) + ". " + zooAnimals.get(i).getName());
    }

    // Ask for choice
    System.out.print("Enter your choice: ");
    int choice = sc.nextInt();
    sc.nextLine(); // Consume newline

    if (choice >= 1 && choice <= zooAnimals.size()) {
      Animal selectedAnimal = zooAnimals.get(choice - 1);

      System.out.println("You selected: " + selectedAnimal.getName());
      System.out.println("What would you like to do?");
      System.out.println("1. Feed");
      System.out.println("2. Know more");

      System.out.print("Enter your choice: ");
      int actionChoice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (actionChoice) {
        case 1:
          String animalType = selectedAnimal.getType();
          if (animalType.equals("Mammal")) {
            System.out.println("The " + animalType + " roars.");
          } else if (animalType.equals("Amphibian")) {
            System.out.println("The " + animalType + " makes a clicking sound.");
          } else if (animalType.equals("Reptile")) {
            System.out.println("The " + animalType + " hisses.");
          } else {
            System.out.println("The " + animalType + " makes a sound.");
          }
          break;

        case 2:
          System.out.println("Description: " + selectedAnimal.getDescription());
          break;

        default:
          System.out.println("Invalid option");
      }
    } else {
      System.out.println("Invalid option");
    }
  }

  public static void viewVisitorStats() {
    double totalVisitors = 0;
    double totalRevenue = 0;
    for (Visitor visitor : Visitor.registeredVisitors) {

      double revenue = visitor.originalBalance - visitor.balance;
      totalRevenue += revenue;

      if (revenue > 0) {
        totalVisitors++;
      }
    }
    System.out.println("Total Visitors: " + totalVisitors);
    System.out.println("Total Revenue: ₹" + totalRevenue);
  }

  public void showMenu() {
    while (true) {
      System.out.println("\nVisitor Menu:");
      System.out.println("1. Explore the Zoo");
      System.out.println("2. Buy Membership");
      System.out.println("3. Buy Tickets");
      System.out.println("4. View Discounts");
      System.out.println("5. View Special Deals");
      System.out.println("6. Visit Animals");
      System.out.println("7. Visit Attractions");
      System.out.println("8. Leave Feedback");
      System.out.println("9. Log Out");

      System.out.print("Enter your choice: ");
      int choice = sc.nextInt();
      sc.nextLine(); // Consume newline

      switch (choice) {
        case 1:
          exploreZoo();
          break;
        case 2:
          buyMembership();
          break;
        case 3:
          buyTickets();
          break;
        case 4:
          admin.viewDiscounts();
          break;
        case 5:
          admin.viewSpecialDeals();
          break;
        case 6:
          visitAnimals();
          break;
        case 7:
          visitAttractions();
          break;
        case 8:
          leaveFeedback();
          break;
        case 9:
          System.out.println("Logged Out.");
          return;
        default:
          System.out.println("Invalid option");
      }
    }
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public int getAge() {
    return age;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public static void main(String[] args) {
    showOptions();
  }
}
