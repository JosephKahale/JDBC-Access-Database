package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void QueryOne(Statement stmt) throws SQLException {
        System.out.println("\nQuery 1: Display the schedule of all trips for a given StartLocationName and Destination Name,\n" +
                "and Date. In addition to these attributes, the schedule includes: Scheduled StartTime,\n" +
                "ScheduledArrivalTime , DriverID, and BusID.\n");

        String sql = "SELECT a.StartLocationName, a.DestinationName, b.Datee, b.ScheduledStartTime, b.ScheduledArrivalTime, b.DriverName, b.BusID\n" +
                "FROM Trip AS a, TripOffering AS b\n" +
                "WHERE a.TripNumber = b.TripNumber AND a.StartLocationName = \"New York\" AND a.DestinationName = \"Boston\" AND b.Datee = \"2023-05-01\";";
        ResultSet resultSet = stmt.executeQuery(sql);

        //Query 1
        while(resultSet.next()) {
            String StartLocationName = resultSet.getString("StartLocationName");
            String DestinationName = resultSet.getString("DestinationName");

            String Date = resultSet.getString("Datee");

            String ScheduledStartTime = resultSet.getString("ScheduledStartTime");
            String ScheduledArrivalTime = resultSet.getString("ScheduledArrivalTime");
            String DriverName = resultSet.getString("DriverName");
            int BusID = resultSet.getInt("BusID");

            System.out.print(StartLocationName + " ");
            System.out.print(DestinationName + " ");
            System.out.print(Date + " ");
            System.out.print(ScheduledStartTime + " ");
            System.out.print(ScheduledArrivalTime + " ");
            System.out.print(DriverName + " ");
            System.out.print(BusID + " \n");
        }
    }

    public static void QueryTwoA(Statement statement) throws SQLException {
        System.out.println("\nQuery 2 - Delete a trip offering specified by Trip#, Date, and ScheduledStartTime;\n");

        String sql2 = "DELETE *\n" +
                "FROM TripOffering\n" +
                "WHERE TripNumber=3 And [Datee]=\"2023-05-02\" And ScheduledStartTime=\"10:00:00\";\n";

        statement.executeUpdate(sql2);

        String sql2output = "SELECT *\n" +
                "FROM TripOffering\n";
        statement.executeQuery(sql2output);
        ResultSet resultSet2 = statement.executeQuery(sql2output);

        System.out.println("\nRemaining Values in TripOffering Table: \n");
        while (resultSet2.next()){
            int TripNumber = resultSet2.getInt("TripNumber");
            String Date = resultSet2.getString("Datee");
            String ScheduledStart = resultSet2.getString("ScheduledStartTime");
            String ScheduledArrival = resultSet2.getString("ScheduledArrivalTime");
            String DriverName = resultSet2.getString("DriverName");
            int BusID = resultSet2.getInt("BusID");

            System.out.print(TripNumber + " ");
            System.out.print(Date + " ");
            System.out.print(ScheduledStart + " ");
            System.out.print(ScheduledArrival + " ");
            System.out.print(DriverName + " ");
            System.out.print(BusID + " \n");
        }
    }

    public static void QueryTwoB(Statement statement) throws SQLException {
        System.out.println("\nQuery 2 - Add a set of trip offerings assuming the values of all attributes are given (the software\n" +
                "asks if you have more trips to enter).\n");

        Scanner scanner = new Scanner(System.in);

        int done = 1;

        while(done == 1) {
            System.out.println("Please enter TripNumber: ");
            int tripnum = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Please enter Date: ");
            String dateTwo = scanner.nextLine();
            System.out.println("Please enter ScheduledStart: ");
            String startTime = scanner.nextLine();
            System.out.println("Please enter ScheduledArrival: ");
            String arrivalTime = scanner.nextLine();
            System.out.println("Please enter DriverName: ");
            String driver = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Please enter BusID: ");
            int bus = scanner.nextInt();

            String sql2 = "INSERT INTO TripOffering\n" +
                    "VALUES ('" + tripnum + "', '" + dateTwo + "', '" + startTime + "', '" + arrivalTime + "', '" + driver + "', '" + bus + "');\n";

            statement.executeUpdate(sql2);

            String sql2output = "SELECT *\n" +
                    "FROM TripOffering\n";
            statement.executeQuery(sql2output);
            ResultSet resultSet2 = statement.executeQuery(sql2output);

            System.out.println("\nRemaining Values in TripOffering Table: \n");
            while (resultSet2.next()) {
                int TripNumber = resultSet2.getInt("TripNumber");
                String Date = resultSet2.getString("Datee");
                String ScheduledStart = resultSet2.getString("ScheduledStartTime");
                String ScheduledArrival = resultSet2.getString("ScheduledArrivalTime");
                String DriverName = resultSet2.getString("DriverName");
                int BusID = resultSet2.getInt("BusID");

                System.out.print(TripNumber + " ");
                System.out.print(Date + " ");
                System.out.print(ScheduledStart + " ");
                System.out.print(ScheduledArrival + " ");
                System.out.print(DriverName + " ");
                System.out.print(BusID + " \n");
            }

            System.out.println("Do you have more trips to enter? If yes, enter 1. Else, 0");
            done = scanner.nextInt();
        }
    }

    public static void QueryTwoC(Statement statement) throws SQLException {
        System.out.println("\nQuery 2 - Change the driver for a given Trip offering (i.e given TripNumber, Date,\n" +
                "ScheduledStartTime);\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a TripNumber: ");
        int tripnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter a Date: Ex: 2023-05-01");
        String dateTwo = scanner.nextLine();
        System.out.println("Please enter a ScheduledStart: Ex: 2023-05-01");
        String startTime = scanner.nextLine();
        System.out.println("Please enter a new DriverName: ");
        String driver = scanner.nextLine();
        scanner.nextLine();

        String sql2 = "UPDATE TripOffering SET DriverName = '"+ driver +"'\n" +
                "WHERE TripNumber= "+ tripnum +" And [Datee]='"+ dateTwo +"' And ScheduledStartTime='"+ startTime +"';";

        statement.executeUpdate(sql2);

        String sql2output = "SELECT *\n" +
                "FROM TripOffering\n";
        statement.executeQuery(sql2output);
        ResultSet resultSet2 = statement.executeQuery(sql2output);

        System.out.println("\nRemaining Values in TripOffering Table: \n");
        while (resultSet2.next()){
            int TripNumber = resultSet2.getInt("TripNumber");
            String Date = resultSet2.getString("Datee");
            String ScheduledStart = resultSet2.getString("ScheduledStartTime");
            String ScheduledArrival = resultSet2.getString("ScheduledArrivalTime");
            String DriverName = resultSet2.getString("DriverName");
            int BusID = resultSet2.getInt("BusID");

            System.out.print(TripNumber + " ");
            System.out.print(Date + " ");
            System.out.print(ScheduledStart + " ");
            System.out.print(ScheduledArrival + " ");
            System.out.print(DriverName + " ");
            System.out.print(BusID + " \n");
        }
    }

    public static void QueryTwoD(Statement statement) throws SQLException {
        System.out.println("\nQuery 2 - Change the bus for a given Trip offering.\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a TripNumber: ");
        int tripnum = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter a Date: Ex: 2023-05-01");
        String dateTwo = scanner.nextLine();
        System.out.println("Please enter a ScheduledStart: Ex: 10:00:00");
        String startTime = scanner.nextLine();
        System.out.println("Please enter a new BusID: ");
        String busID = scanner.nextLine();
        scanner.nextLine();

        String sql2 = "UPDATE TripOffering SET BusID = '"+ busID +"'\n" +
                "WHERE TripNumber= "+ tripnum +" And [Datee]='"+ dateTwo +"' And ScheduledStartTime='"+ startTime +"';";

        statement.executeUpdate(sql2);

        String sql2output = "SELECT *\n" +
                "FROM TripOffering\n";
        statement.executeQuery(sql2output);
        ResultSet resultSet2 = statement.executeQuery(sql2output);

        System.out.println("\nRemaining Values in TripOffering Table: \n");
        while (resultSet2.next()){
            int TripNumber = resultSet2.getInt("TripNumber");
            String Date = resultSet2.getString("Datee");
            String ScheduledStart = resultSet2.getString("ScheduledStartTime");
            String ScheduledArrival = resultSet2.getString("ScheduledArrivalTime");
            String DriverName = resultSet2.getString("DriverName");
            int BusID = resultSet2.getInt("BusID");

            System.out.print(TripNumber + " ");
            System.out.print(Date + " ");
            System.out.print(ScheduledStart + " ");
            System.out.print(ScheduledArrival + " ");
            System.out.print(DriverName + " ");
            System.out.print(BusID + " \n");
        }
    }

    public static void QueryThree(Statement statement) throws SQLException {
        System.out.println("\nQuery 3 - Display the stops of a given trip ( i.e. the attributes of the table TripStopInfo).\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a TripNumber: ");
        int tripnum = scanner.nextInt();
        scanner.nextLine();

        String sql3 = "SELECT TripStopInfo.TripNumber, TripStopInfo.StopNumber, TripStopInfo.SequenceNumber, TripStopInfo.DrivingTime\n" +
                "FROM TripStopInfo\n" +
                "WHERE (((TripStopInfo.[TripNumber])='" + tripnum + "'));";

        statement.executeQuery(sql3);

        ResultSet resultSet2 = statement.executeQuery(sql3);

        System.out.println("\nValues in TripStopInfo Table: \n");
        while (resultSet2.next()){
            int TripNumber = resultSet2.getInt("TripNumber");
            String StopNumber = resultSet2.getString("StopNumber");
            String SequenceNumber = resultSet2.getString("SequenceNumber");
            String DrivingTime = resultSet2.getString("DrivingTime");

            System.out.print(TripNumber + " ");
            System.out.print(StopNumber + " ");
            System.out.print(SequenceNumber + " ");
            System.out.print(DrivingTime + " ");

        }
    }

    public static void QueryFour(Statement statement) throws SQLException {
        System.out.println("\nQuery 4 - Display the weekly schedule of a given driver and date.\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a DriverName: ");
        String driverName = scanner.nextLine();
        System.out.println("Please enter a starting Date: ");
        String date = scanner.nextLine();
        System.out.println("Please enter an ending Date: ");
        String date2 = scanner.nextLine();

        String sql3 = "SELECT DriverName, ScheduledStartTime, ScheduledArrivalTime\n" +
                "FROM TripOffering\n" +
                "WHERE DriverName='" + driverName +"' And [Datee] Between '" + date + "' And '"+ date2 +"';\n";

        statement.executeQuery(sql3);

        ResultSet resultSet2 = statement.executeQuery(sql3);

        System.out.println("\nValues in TripOffering Table: \n");
        while (resultSet2.next()){
            String DriverName = resultSet2.getString("DriverName");
            String ScheduledStartTime = resultSet2.getString("ScheduledStartTime");
            String ScheduledArrivalTime = resultSet2.getString("ScheduledArrivalTime");

            System.out.print(DriverName + " ");
            System.out.print(ScheduledStartTime + " ");
            System.out.print(ScheduledArrivalTime + " \n");

        }
    }

    public static void QueryFive(Statement statement) throws SQLException {
        System.out.println("\nQuery 5 - Add a drive.\n");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a DriverName: ");
        String driverName = scanner.nextLine();
        System.out.println("Please enter a DriverTelephoneNumber: ");
        String driverTelephoneNumber = scanner.nextLine();

        String sql3 = "INSERT INTO DRIVER\n" +
                "VALUES ('"+ driverTelephoneNumber +"', '"+ driverName +"');";

        statement.executeUpdate(sql3);

        String sql2output = "SELECT *\n" +
                "FROM Driver\n";
        statement.executeQuery(sql2output);
        ResultSet resultSet2 = statement.executeQuery(sql2output);

        System.out.println("\nValues in Driver Table: \n");
        while (resultSet2.next()){
            String DriverName = resultSet2.getString("DriverName");
            String DriverTelephoneNumber = resultSet2.getString("DriverTelephoneNumber");

            System.out.print(DriverName + " ");
            System.out.print(DriverTelephoneNumber + " \n");

        }
    }

    public static void QuerySix(Statement statement) throws SQLException {
        System.out.println("\nQuery 6 - Add a bus.\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a BusID: ");
        int bus = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter a Model: ");
        String model = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Please enter a Model: ");
        int year = scanner.nextInt();

        String sql3 = "INSERT INTO Bus\n" +
                "VALUES (" + bus +", '"+ model +"', " + year +");\n";

        statement.executeUpdate(sql3);

        String sql2output = "SELECT *\n" +
                "FROM Bus\n";
        statement.executeQuery(sql2output);
        ResultSet resultSet2 = statement.executeQuery(sql2output);

        System.out.println("\nValues in Bus Table: \n");
        while (resultSet2.next()){
            int busid = resultSet2.getInt("BusID");
            String modelnum = resultSet2.getString("Model");
            int year1 = resultSet2.getInt("Year");

            System.out.print(busid + " ");
            System.out.print(modelnum + " \n");
            System.out.print(year1 + " \n");

        }
    }

    public static void QuerySeven(Statement statement) throws SQLException {
        System.out.println("\nQuery 7 - Delete a bus.\n");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a BusID: ");
        int bus = scanner.nextInt();

        String sql3 = "DELETE *\n" +
                "FROM Bus\n" +
                "WHERE BusID = " + bus +";";

        statement.executeUpdate(sql3);

        String sql2output = "SELECT *\n" +
                "FROM Bus\n";
        statement.executeQuery(sql2output);
        ResultSet resultSet2 = statement.executeQuery(sql2output);

        System.out.println("\nValues in Bus Table: \n");
        while (resultSet2.next()){
            int busid = resultSet2.getInt("BusID");
            String modelnum = resultSet2.getString("Model");
            int year1 = resultSet2.getInt("Year");

            System.out.print(busid + " ");
            System.out.print(modelnum + " \n");
            System.out.print(year1 + " \n");

        }
    }

    public static void QueryEight(Statement statement) throws SQLException {
        System.out.println("\nQuery 8 - Record (insert) the actual data of a given trip offering specified by its key. The actual\n" +
                "data include the attributes of the table ActualTripStopInfo.\n");

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Please enter a TripNumber: ");
        int tripNum = scanner.nextInt();

        String sql3 = "INSERT INTO TripOffering ( TripNumber, Datee, ScheduledStartTime, ScheduledArrivalTime )\n" +
                "SELECT TripNumber, Datee, ScheduledStartTime, ScheduledArrivalTime\n" +
                "FROM ActualTripStopInfo\n" +
                "WHERE TripNumber = "+ tripNum +";\n";

        statement.executeUpdate(sql3);

        String sql2output = "SELECT *\n" +
                "FROM TripOffering\n";
        statement.executeQuery(sql2output);
        ResultSet resultSet2 = statement.executeQuery(sql2output);

        System.out.println("\nRemaining Values in TripOffering Table: \n");
        while (resultSet2.next()){
            int TripNumber = resultSet2.getInt("TripNumber");
            String Date = resultSet2.getString("Datee");
            String ScheduledStart = resultSet2.getString("ScheduledStartTime");
            String ScheduledArrival = resultSet2.getString("ScheduledArrivalTime");
            String DriverName = resultSet2.getString("DriverName");
            int BusID = resultSet2.getInt("BusID");

            System.out.print(TripNumber + " ");
            System.out.print(Date + " ");
            System.out.print(ScheduledStart + " ");
            System.out.print(ScheduledArrival + " ");
            System.out.print(DriverName + " ");
            System.out.print(BusID + " \n");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C://Users//Joseph//Desktop//Database//JDBC-Access-Database//Transit.accdb");
            Statement statement = conn.createStatement();

//            QueryOne(statement);
//            QueryTwoA(statement);
//            QueryTwoB(statement);
//            QueryTwoC(statement);
//            QueryTwoD(statement);
//            QueryThree(statement);
//            QueryFour(statement);
//            QueryFive(statement);
//            QuerySix(statement);
//            QuerySeven(statement);
            QueryEight(statement);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }




}