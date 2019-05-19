import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Class DatabaseClass that runs the database VideoGames.
 * 
 * @author Wesley Stahl
 * @version 6/5/2017
 */
public class DatabaseClass implements Runnable{
    public static final String LINE_BREAK = System.getProperty("line.separator");
    private static StringBuilder myStr = new StringBuilder();
    private static Scanner myScan = new Scanner(System.in);
    private static int CHOICE;
    private static Connection CONN;
    private static Statement STATEMENT;
    private static ResultSet RESULTS;
    private static DatabaseClass clas;
    
    public static void main(final String[] args) throws SQLException {
    	System.out.print("Enter your cssgate username: ");
    	String name = myScan.next();
    	System.out.println();
    	System.out.println("Enter cssgate password: ");
    	String pass = myScan.next();
        CONN = DriverManager.getConnection("jdbc:mysql://cssgate.insttech.washington.edu:3306/" + name, name, pass) ;
        STATEMENT = CONN.createStatement(); 
        clas = new DatabaseClass();
    	new Thread(clas).start();
    }
    
    public void run() {
    	try {
    		System.out.println("Welcome to the Video Games Database, where you can find out all sorts of information regaridng different games!");
			helperPrint();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
   
    /**
     * Initializer for all methods.
     * 
     * @throws SQLException
     */
    private static void helperPrint() throws SQLException {
    	System.out.println("[1] Insert new data.");
    	System.out.println("[2] Find data.");
    	System.out.println("[3] Delete data.");
    	System.out.println("[4] Quit.");
    	System.out.print("What would you like to do? ");
    	int val = myScan.nextInt();
    	System.out.println();
    	while (val < 1 || val > 4) {
    		System.out.print("I'm sorry, the input you entered was invalid. Please try again. ");
    		val = myScan.nextInt();
    	}
    	if (val == 1) {
    		InsertInit();
    	} else  if (val == 2) {
    		ViewInit();
    	} else if (val == 3) {
    		DeleteInit();
    	} else {
    		new Thread(clas).stop();
    	}
    }

    /**
     * Insert initializer.
     * 
     * @throws SQLException
     */
	private static void InsertInit() throws SQLException {
    	System.out.println("[0] Enter new Producer.");
    	System.out.println("[1] Enter new Developer.");
    	System.out.println("[2] Enter new Game.");
    	System.out.println("[3] Enter new Community.");
    	System.out.println("[4] Enter new Community Group.");
    	System.out.println("[5] Enter new Community Member.");
    	System.out.println("[6] Enter new Publisher.");
    	System.out.println("[7] Enter new Console.");
    	System.out.println("[8] Enter new Lead Developer.");
    	System.out.println("[9] Enter new Lead Design.");
    	System.out.print("What type of data would you like to add? ");
    	int val = myScan.nextInt();
    	while (val < 0 && val > 9) {
    		System.out.print("I'm sorry, the input you entered was invalid. Please try again. ");
    		val = myScan.nextInt();
    	}
    	if (val == 0) {
    		insertIntoProducer();
    	} else if (val == 1) {
    		insertIntoDeveloper();
    	} else if (val == 2) {
    		insertIntoGames();
    	} else if (val == 3) {
    		insertIntoCommunity();
    	} else if (val == 4) {
    		insertIntoCommunityGroup();
    	} else if (val == 5) {
    		insertIntoCommunityMember();
    	} else if (val == 6) {
    		insertIntoPublisher();
    	} else if (val == 7) {
    		insertIntoConsole();
    	} else if (val == 8) {
    		insertIntoLeadDeveloper();
    	} else {
    		insertIntoLeadDesigner();
    	}
    	helperPrint();
    }
   
	/**
	 * Insert data into lead designer table.
	 * 
	 * @throws SQLException
	 */
    private static void insertIntoLeadDesigner() throws SQLException {
        System.out.print("Please enter Lead Designer Name: ");
        String name = myScan.next();
        System.out.print("Please enter Lead Designer Information: ");
        String description = myScan.next();
        System.out.print("Please enter Lead Designer's Experience: ");
        String address = myScan.next();
        String addresss = myScan.nextLine();
        myStr.append("'" + name + "','" + description + "','" + address + addresss + "')");
        STATEMENT.execute("INSERT INTO LeadDesign (designer_name,designer_info,designer_experience) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity());
	}

    /**
     * Insert data into lead developer table.
     * 
     * @throws SQLException
     */
	private static void insertIntoLeadDeveloper() throws SQLException {
        System.out.print("Please enter Lead Developer Name: ");
        String name = myScan.next();
        System.out.print("Please enter Lead Developer Information: ");
        String description = myScan.next();
        System.out.print("Please enter Lead Developer's Experience: ");
        String address = myScan.next();
        String addresss = myScan.nextLine();
        myStr.append("'" + name + "','" + description + "','" + address + addresss + "')");
        STATEMENT.execute("INSERT INTO LeadDeveloper (developer_name,developer_info,developer_experience) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity());
	}

	/**
	 * Insert data into console table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoConsole() throws SQLException {
        System.out.print("Please enter the Console's Name: ");
        String name = myScan.next();
        String address = myScan.nextLine();
        if (name == "Xbox One") {
        	name = "Xbox 1";
        } else if (name == "PS4" || name == "Playstation Four" || name == "Play Station 4" || name == "Play Station Four") {
        	name = "Playstation 4";
        }
        myStr.append("'" + name + address + "')");
        STATEMENT.execute("INSERT INTO Console (consoleName) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity());
	}

	/**
	 * Insert data into publisher table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoPublisher() throws SQLException {
        System.out.print("Please enter the Publisher's Name: ");
        String name = myScan.next();
        String address = myScan.nextLine();
        myStr.append("'" + name + address + "')");
        STATEMENT.execute("INSERT INTO Publisher (publisherName) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity());
	}

	/**
	 * Insert data into Community member table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoCommunityMember() throws SQLException {
        System.out.print("Please enter the Community Member's Name: ");
        String name = myScan.next();
        System.out.print("Please enter the Community Member's Description: ");
        String description = myScan.next();
        String address = myScan.nextLine();
        myStr.append("'" + name + "','" + description + address + "')");
        STATEMENT.execute("INSERT INTO Console (consoleName) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity());
		
	}

	/**
	 * Insert Data into community group table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoCommunityGroup() throws SQLException {
        System.out.print("Please enter the Community Group's Description: ");
        String description = myScan.next();
        String address = myScan.nextLine();
        myStr.append("'" + description + address + "')");
        STATEMENT.execute("INSERT INTO Console (consoleName) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity());
	}

	/**
	 * Insert data into Community table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoCommunity() throws SQLException {
        System.out.print("Please enter the Community's Size: ");
        String name = myScan.next();
        System.out.print("Please enter the Community's Description: ");
        String description = myScan.next();
        System.out.print("Please enter the Community's Work: ");
        String work = myScan.next();
        String address = myScan.nextLine();
        myStr.append("'" + name + "','" + description + "','" + work + address + "')");
        STATEMENT.execute("INSERT INTO Console (consoleName) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity()); 
	}

	/**
	 * Insert data into producer table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoProducer() throws SQLException {
        System.out.print("Please enter Producer Name: ");
        String name = myScan.next();
        System.out.print("Please enter Producer Description: ");
        String description = myScan.next();
        System.out.print("Please enter Producer's Address: ");
        String address = myScan.next();
        String addresss = myScan.nextLine();
        myStr.append("'" + name + "','" + description + "','" + address + addresss + "')");
        STATEMENT.execute("INSERT INTO Producer(producer_Name,producer_description,producer_Location) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity()); 
	}

	/**
	 * Insert data into developer table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoDeveloper() throws SQLException {
        System.out.print("Please enter Developer Name: ");
        String name = myScan.next();
        System.out.print("Please enter Developer Description: ");
        String description = myScan.next();
        System.out.print("Please enter Developer's Address: ");
        String address = myScan.next();
        String addresss = myScan.nextLine();
        myStr.append("'" + name + "','" + description + "','" + address + addresss + "')");
        STATEMENT.execute("INSERT INTO Developer(developerName,developer_description,developer_location) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity()); 
		
	}
	/**
	 * Insert data into games table.
	 * 
	 * @throws SQLException
	 */
	private static void insertIntoGames() throws SQLException {
        System.out.print("Please enter Game Title: ");
        String title = myScan.next();
        System.out.print("Please enter Game Description: ");
        String description = myScan.next();
        System.out.print("Please enter Game ESRB Rating: ");
        String esrb = myScan.next();
        System.out.print("Please enter Game Popularity Rating: ");
        int rating = myScan.nextInt();
        System.out.print("Please enter Game Price: ");
        double price = myScan.nextDouble();
        System.out.print("Please enter Game Type: ");
        String type = myScan.next();
        System.out.print("Please enter Game Genre: ");
        String genre = myScan.next();
        String address = myScan.nextLine();
        myStr.append("'" + title + "','" + description + "','" + esrb + "'," + rating + ",'" + price + "','" + type + "','" + genre + address + "')");
        STATEMENT.execute("INSERT INTO Game(game_title,game_description,game_Rating,game_popularity,game_price,game_type,game_genre) "
                + "VALUES (" + myStr.toString());
        System.out.println();
        System.out.println("Added Successfully!");
        myStr.delete(0, myStr.capacity());    

    }
    
	/**
	 * Delete initializer.
	 * 
	 * @throws SQLException
	 */
	private static void DeleteInit() throws SQLException {
    	System.out.println("[0] Delete Producer.");
    	System.out.println("[1] Delete Developer.");
    	System.out.println("[2] Delete Game.");
    	System.out.println("[3] Delete Community.");
    	System.out.println("[4] Delete Community Member.");
    	System.out.println("[5] Delete Publisher.");
    	System.out.println("[6] Delete Console.");
    	System.out.println("[7] Delete Lead Developer.");
    	System.out.println("[8] Delete Lead Design.");
    	System.out.print("What type of data would you like to delete? ");
    	int val = myScan.nextInt();
    	while (val < 0 && val > 9) {
    		System.out.print("I'm sorry, the input you entered was invalid. Please try again. ");
    		val = myScan.nextInt();
    	}
    	if (val == 0) {
    		deleteProducer();
    	} else if (val == 1) {
    		deleteDeveloper();
    	} else if (val == 2) {
    		deleteGame();
    	} else if (val == 3) {
    		deleteCommunity();
    	} else if (val == 4) {
    		deleteCommunityMember();
    	} else if (val == 5) {
    		deletePublisher();
    	} else if (val == 6) {
    		deleteConsole();
    	} else if (val == 7) {
    		deleteLeadDeveloper();
    	} else if (val == 8) {
    		deleteLeadDesigner();
    	}
    	helperPrint();
	}
    
	/**
	 * Delete statement for a Lead Designer.
	 * 
	 * @throws SQLException
	 */
    private static void deleteLeadDesigner() throws SQLException {
		System.out.print("Please enter the Lead Design Name you wish to delete: ");
		String name = myScan.next();
		STATEMENT.execute("DELETE FROM LeadDesign WHERE designer_name = " + name);
		System.out.println();
		System.out.println("Deleted Successfully!");
	}

    /**
     * Delete statement for a Lead Developer.
     * 
     * @throws SQLException
     */
	private static void deleteLeadDeveloper() throws SQLException {
		System.out.print("Please enter the Lead Developer Name you wish to delete: ");
		String name = myScan.next();
		STATEMENT.execute("DELETE FROM LeadDeveloper WHERE developer_name = " + name);
		System.out.println();
		System.out.println("Deleted Successfully!");
	}

	/**
	 * Delete statement for a console.
	 * 
	 * @throws SQLException
	 */
	private static void deleteConsole() throws SQLException {
		System.out.print("Please enter the Console name you wish to delete: ");
		String name = myScan.next();
		STATEMENT.execute("DELETE FROM Console WHERE consoleName = " + name);
		System.out.println();
		System.out.println("Deleted Successfully!");
	}
	
	/**
	 * Delete statement for a publisher.
	 * 
	 * @throws SQLException
	 */
	private static void deletePublisher() throws SQLException {
		System.out.print("Please enter the Publisher name you wish to delete: ");
		String name = myScan.next();
		STATEMENT.execute("DELETE FROM Publisher WHERE publisherName = " + name);
		System.out.println();
		System.out.println("Deleted Successfully!");
	}
	
	/**
	 * Delete statement for a community member.
	 * 
	 * @throws SQLException
	 */
	private static void deleteCommunityMember() throws SQLException {
		System.out.print("Please enter the Community Member you wish to delete: ");
		String name = myScan.next();
		STATEMENT.execute("DELETE FROM Community_Member WHERE memberID = " + name);
		System.out.println();
		System.out.println("Deleted Successfully!");
	}
	
	/**
	 * Delete statment for a community.
	 * 
	 * @throws SQLException
	 */
	private static void deleteCommunity() throws SQLException {
		System.out.print("Please enter the Community size you wish to delete: ");
		String name = myScan.next();
		STATEMENT.execute("DELETE FROM Community WHERE community_size = " + name);
		System.out.println();
		System.out.println("Deleted Successfully!");
	}
	
	/**
	 * Helper method for deleteGame().
	 * 
	 * @throws SQLException
	 */
	private static void deleteGames() throws SQLException {
		System.out.println("[1] Game Title");
		System.out.println("[2] Game ESRB");
		System.out.println("[3] Game Popularity");
		System.out.println("[4] Game Genre");
		System.out.print("What do you want to delete based on? ");
	}
	
	/**
	 * Delete statement for a game.
	 * 
	 * @throws SQLException
	 */
	private static void deleteGame() throws SQLException {
		CHOICE = myScan.nextInt();
		switch(CHOICE) {
			case 0:
				deleteGames();
				break;
			case 1:
				System.out.print("Please enter the name you wish to delete: ");
				String name = myScan.next();
				STATEMENT.execute("DELETE FROM Game WHERE game_title = " + name);
				System.out.println();
				System.out.println("Deleted Successfully!");
				break;
			case 2:
				System.out.print("Please enter the esrb rating you wish to delete: ");
				String esrb = myScan.next();
				STATEMENT.execute("DELETE FROM Game WHERE game_Rating = " + esrb);
				System.out.println();
				System.out.println("Deleted Successfully!");
				break;
			case 3:
				System.out.print("Please enter the popularity rating you wish to delete: ");
				int popularity = myScan.nextInt();
				STATEMENT.execute("DELETE FROM Game WHERE game_popularity = " + popularity);
				System.out.println();
				System.out.println("Deleted Successfully!");
				break;
			case 4:
				System.out.print("Please enter the genre you wish to delete: ");
				String genre = myScan.next();
				STATEMENT.execute("DELETE FROM Game WHERE game_genre = " + genre);
				System.out.println();
				System.out.println("Deleted Successfully!");
				break;
		}
	}
	
	/**
	 * Delete statement for a developer.
	 * 
	 * @throws SQLException
	 */
	private static void deleteDeveloper() throws SQLException {
        System.out.print("Please enter Developer Name to delete: ");
        String name = myScan.next();
        STATEMENT.execute("DELETE FROM Developer WHERE developerName = " + name);
        System.out.println();
        System.out.println("Deleted Successfully!");
	}
	
	/**
	 * Delete statement for a producer.
	 * 
	 * @throws SQLException
	 */
	private static void deleteProducer() throws SQLException {
        System.out.print("Please enter Producer Name to delete: ");
        String name = myScan.next();
        STATEMENT.execute("DELETE FROM Producer WHERE producer_name = " + name);
        System.out.println();
        System.out.println("Deleted Successfully!");
    }
	/**
	 * View games initializer.
	 * 
	 * @throws SQLException
	 */
	private static void ViewInit() throws SQLException {
	    String aString;
	    myStr.append(LINE_BREAK);
	    myStr.append("View all Games");
	    myStr.append(LINE_BREAK);
	    myStr.append("Find Games by their Producer");
	    myStr.append(LINE_BREAK);
	    myStr.append("Find Games by their Developer");
	    myStr.append(LINE_BREAK);
	    myStr.append("Find Games by Console");
	    myStr.append(LINE_BREAK);
	    myStr.append("Back");
	    myStr.append(LINE_BREAK);
	    myStr.append(LINE_BREAK);
	    System.out.print(myStr.toString());
	    System.out.print("Enter a command: ");
	    myStr.delete(0, myStr.capacity());
	    CHOICE = myScan.nextInt();
	    switch (CHOICE) {
	        case 0:              
	            break;
	        case 1: 
	            viewGamesOne();
	            break;
	        case 2:
	            System.out.print("Enter a Producer: ");
	            aString = myScan.next();
	            viewGamesTwo(aString);
	            break;
	        case 3: 
	            System.out.print("Enter a Developer: ");
	            aString = myScan.next();
	            viewGamesThree(aString);
	            break;
	        case 4:
	            System.out.print("Enter a Console: ");
	            aString = myScan.next();      
	            viewGamesFour(aString);
	            break;
	    }
	    helperPrint();
	}
	/**
	 * View all games.
	 * 
	 * @throws SQLException
	 */
	private static void viewGamesOne() throws SQLException {
		int count = 1;
	    String aQuery = "Select * from Members;";
	    RESULTS = STATEMENT.executeQuery(aQuery);
	    boolean isOk = RESULTS.first();
	    if (isOk) {
	    	do {
	    		System.out.print(count + ". Game Title: " + RESULTS.getString(4));
	    		System.out.print(" Game Description: " + RESULTS.getString(5));
	    		System.out.print(" Game Rating: " + RESULTS.getString(6));
	    		System.out.print(" Game popularity: " + RESULTS.getString(7));
	    		System.out.print(" Game price: " + RESULTS.getString(8));
	    		System.out.print(" Game type: " + RESULTS.getString(9));
	    		System.out.print(" Game genre: " + RESULTS.getString(10));
	    	} while (RESULTS.next());
	    }

	}
	
	/**
	 * View game based on Producer.
	 * 
	 * @param theName producer name
	 * @throws SQLException
	 */
	private static void viewGamesTwo(String theName) throws SQLException {
	    String aQuery = "Select *, producer_name from Game, Producer inner join Producer on game.producerId in (Select Producer.producerID from Producer) where producer_name = " + theName +";";
	    int count = 1;
	    RESULTS = STATEMENT.executeQuery(aQuery);
	    boolean isOk = RESULTS.first();
	    if (isOk) {
	    	do {
	    		System.out.print(count + ". Producer Name");
	    		System.out.print("Game Title: " + RESULTS.getString(4));
	    		System.out.print(" Game Description: " + RESULTS.getString(5));
	    		System.out.print(" Game Rating: " + RESULTS.getString(6));
	    		System.out.print(" Game popularity: " + RESULTS.getString(7));
	    		System.out.print(" Game price: " + RESULTS.getString(8));
	    		System.out.print(" Game type: " + RESULTS.getString(9));
	    		System.out.print(" Game genre: " + RESULTS.getString(10));
	    	} while (RESULTS.next());
	    }

	}
	
	/**
	 * View game based on developer.
	 * 
	 * @param theName developer name
	 * @throws SQLException
	 */
	private static void viewGamesThree(String theName) throws SQLException {
	    String aQuery = "Select *, developername from Game, Developerr inner join Developer on game.developerId in (Select Developer.developerID from Developer) where developerName = " + theName +";";
	    int count = 1;
	    RESULTS = STATEMENT.executeQuery(aQuery);
	    boolean isOk = RESULTS.first();
	    if (isOk) {
	    	do {
	    		System.out.print(count + ". Producer Name");
	    		System.out.print("Game Title: " + RESULTS.getString(4));
	    		System.out.print(" Game Description: " + RESULTS.getString(5));
	    		System.out.print(" Game Rating: " + RESULTS.getString(6));
	    		System.out.print(" Game popularity: " + RESULTS.getString(7));
	    		System.out.print(" Game price: " + RESULTS.getString(8));
	    		System.out.print(" Game type: " + RESULTS.getString(9));
	    		System.out.print(" Game genre: " + RESULTS.getString(10));
	    	} while (RESULTS.next());
	    }
	}
	/**
	 * View game based on console.
	 * 
	 * @param theName console name
	 * @throws SQLException
	 */
	private static void viewGamesFour(String theName) throws SQLException {
	    String aQuery = "Select *, consoleName from Game_Developed,Console inner join Game on Game_Developed.gameKey in (Select Game.gameKey from Game) where consoleName = " + theName +";";
	    int count = 1;
	    RESULTS = STATEMENT.executeQuery(aQuery);
	    boolean isOk = RESULTS.first();
	    if (isOk) {
	    	do {
	    		System.out.print(count + ". Producer Name");
	    		System.out.print("Game Title: " + RESULTS.getString(4));
	    		System.out.print(" Game Description: " + RESULTS.getString(5));
	    		System.out.print(" Game Rating: " + RESULTS.getString(6));
	    		System.out.print(" Game popularity: " + RESULTS.getString(7));
	    		System.out.print(" Game price: " + RESULTS.getString(8));
	    		System.out.print(" Game type: " + RESULTS.getString(9));
	    		System.out.print(" Game genre: " + RESULTS.getString(10));
	    	} while (RESULTS.next());
	    }
	}
}
