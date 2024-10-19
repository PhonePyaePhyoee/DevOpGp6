//package com.napier.sem;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CapitalUnitTest {
//
//    private PrintStream originalOut; // Store the original System.out
//    private capitalQuery query;
//
//    @BeforeEach
//    public void setUp() {
//        originalOut = System.out; // Capture the original System.out
//        query = new capitalQuery(); // Initialize the capitalQuery object
//    }
//
//    @Test
//    public void testGetAllCapitalCitiesOrderedByPopulation() throws SQLException {
//        // Arrange
//        Connection mockConnection = Mockito.mock(Connection.class);
//        Statement mockStatement = Mockito.mock(Statement.class);
//        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
//
//        Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
//        Mockito.when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(mockResultSet);
//
//        // Mocking the ResultSet to return two rows
//        Mockito.when(mockResultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
//        Mockito.when(mockResultSet.getString("Name")).thenReturn("Capital1").thenReturn("Capital2");
//        Mockito.when(mockResultSet.getString("CountryName")).thenReturn("Country1").thenReturn("Country2");
//        Mockito.when(mockResultSet.getInt("Population")).thenReturn(1000000).thenReturn(500000);
//
//        // Act
//        List<Capital> capitals = query.getAllCapitalCitiesOrderedByPopulation(mockConnection);
//
//        // Assert
//        assertEquals(2, capitals.size());
//        assertEquals("Capital1", capitals.get(0).getName());
//        assertEquals("Country1", capitals.get(0).getCountry());
//        assertEquals(1000000, capitals.get(0).getPopulation());
//        assertEquals("Capital2", capitals.get(1).getName());
//        assertEquals("Country2", capitals.get(1).getCountry());
//        assertEquals(500000, capitals.get(1).getPopulation());
//    }
//
//    @Test
//    public void testDisplayAllCapitalCitiesOrderedByPopulation() {
//        // Arrange
//        List<Capital> capitals = List.of(
//                new Capital("Capital1", "Country1", 1000000),
//                new Capital("Capital2", "Country2", 500000)
//        );
//
//        // Act
//        // Redirect System.out to capture the output
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        query.displayAllCapitalCitiesOrderedByPopulation(capitals);
//
//        // Assert
//        String expectedOutput = "\nNo. 17 Capital City Report (All Capitals by Population):\n" +
//                String.format("%-40s | %-40s | %-15s%n", "Capital Name", "Country Name", "Population") +
//                "-----------------------------------------------------------------------------------------------------------------------------\n" +
//                String.format("%-40s | %-40s | %,15d%n", "Capital1", "Country1", 1000000) +
//                String.format("%-40s | %-40s | %,15d%n", "Capital2", "Country2", 500000);
//
//        assertEquals(expectedOutput, outputStream.toString());
//
//        // Reset System.out
//        System.setOut(originalOut);
//    }
//
//    @Test
//    public void testDisplayAllCapitalCitiesOrderedByPopulation_EmptyList() {
//        // Test with an empty list
//        List<Capital> capitals = new ArrayList<>();
//
//        // Act
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        query.displayAllCapitalCitiesOrderedByPopulation(capitals);
//
//        // Assert
//        assertEquals("No capital cities found.\n", outputStream.toString().trim());
//
//        // Reset System.out
//        System.setOut(originalOut);
//    }
//
//    @Test
//    public void testDisplayAllCapitalCitiesOrderedByPopulation_NullList() {
//        // Test with a null list
//        // Act
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        query.displayAllCapitalCitiesOrderedByPopulation(null);
//
//        // Assert
//        assertEquals("No capital cities found.\n", outputStream.toString().trim());
//
//        // Reset System.out
//        System.setOut(originalOut);
//    }
//
//    @Test
//    public void testDisplayAllCapitalCitiesOrderedByPopulation_SingleCapital() {
//        // Test with a single capital
//        List<Capital> capitals = new ArrayList<>();
//        capitals.add(new Capital("Test Capital", "Test Country", 1000000));
//
//        // Act
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        query.displayAllCapitalCitiesOrderedByPopulation(capitals);
//
//        // Assert
//        String expectedOutput = "\nNo. 17 Capital City Report (All Capitals by Population):\n" +
//                String.format("%-40s | %-40s | %-15s%n", "Capital Name", "Country Name", "Population") + "\n" +
//                "-----------------------------------------------------------------------------------------------------------------------------\n" +
//                String.format("%-40s | %-40s | %,15d%n", "Test Capital", "Test Country", 1000000);
//        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
//
//        // Reset System.out
//        System.setOut(originalOut);
//    }
//
//    @Test
//    public void testDisplayAllCapitalCitiesOrderedByPopulation_MultipleCapitals() {
//        // Test with multiple capitals
//        List<Capital> capitals = new ArrayList<>();
//        capitals.add(new Capital("Capital One", "Country One", 2000000));
//        capitals.add(new Capital("Capital Two", "Country Two", 1500000));
//
//        // Act
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        query.displayAllCapitalCitiesOrderedByPopulation(capitals);
//
//        // Assert
//        String expectedOutput = "\nNo. 17 Capital City Report (All Capitals by Population):\n" +
//                String.format("%-40s | %-40s | %-15s%n", "Capital Name", "Country Name", "Population") + "\n" +
//                "-----------------------------------------------------------------------------------------------------------------------------\n" +
//                String.format("%-40s | %-40s | %,15d%n", "Capital One", "Country One", 2000000) + "\n" +
//                String.format("%-40s | %-40s | %,15d%n", "Capital Two", "Country Two", 1500000);
//        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
//
//        // Reset System.out
//        System.setOut(originalOut);
//    }
//
//    // Clean up after each test
//    @AfterEach
//    public void tearDown() {
//        System.setOut(originalOut); // Restore original System.out
//    }
//}
