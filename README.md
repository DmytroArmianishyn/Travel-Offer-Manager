*Travel Offer Manager Documentation**

**Overview**
Travel Offer Manager is a Java application designed to efficiently manage travel offers. It provides functionalities to read travel offers from text files, store them in a database, and present them through a graphical user interface (GUI) built using Java Swing. This documentation outlines the key aspects of the Travel Offer Manager application.

**Features**
- **File Reading:**
  - The application reads travel offers from text files stored in a specified directory.
  - Upon startup, the program scans the designated directory for text files containing travel offers.
  - Each line in the text files represents a single travel offer, with various details such as destination, dates, price, and description.
  - The file reading process is handled by the `TravelData` class, which parses the text files and extracts relevant information to create travel offer objects.
  
- **Database Integration:**
  - Travel offers parsed from the text files are stored in a relational database for efficient management and retrieval.
  - The database integration is facilitated by the `Database` class, which utilizes JDBC (Java Database Connectivity) to interact with the underlying database.
  - Upon startup, the program creates a database table to store travel offer details, such as destination, dates, price, and description.
  - Each travel offer is represented as a row in the database table, allowing for easy storage and retrieval of offer information.
  
- **Graphical User Interface (GUI):**
  - The application provides a user-friendly GUI built using Java Swing to interact with the stored travel offers.
  - Users can view a list of available travel offers, including details such as destination, dates, and price.
  - Users can also add new travel offers to the database directly from the GUI, providing details such as destination, dates, price, and description.
  
- **Multilingual Support:**
  - Travel Offer Manager offers multilingual support, allowing users to view travel offers in different languages based on their preferences. 
  - It uses resource bundles to manage localized strings, providing translations for various languages. 
  - Users can specify their preferred locale, and the application dynamically loads the corresponding language resources for displaying offer details in the chosen language.

**Usage**
To use the Travel Offer Manager application, follow these steps:
1. Run the Main class to start the application.
2. Upon startup, the program will scan the designated directory for text files containing travel offers and parse them.
3. The parsed travel offers will be stored in the database, and the GUI will be displayed to the user.
4. Use the GUI to browse, search, filter, and interact with the stored travel offers.
5. Additionally, users can add new travel offers to the database directly from the GUI by providing relevant details.

**Dependencies**
- Java 8 or higher
- JDBC (Java Database Connectivity)
- Java Swing library

**Contributors**
- [Armianishyn Dmytro]
