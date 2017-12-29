package com.example.demo;

import org.flywaydb.core.Flyway;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Create the Flyway instance
        Flyway flyway = new Flyway();

        // Point it to the database
//        flyway.setDataSource("jdbc:mysql://localhost:3306/mydb", "root", "Justekpwd");
        flyway.setDataSource("jdbc:h2:file:./target/foobar", "sa", null);
        
        // Start the migration
        flyway.migrate();
    }
}
