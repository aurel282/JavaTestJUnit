/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute;


import static com.lastminute.Flights.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aurélien
 */
public class SearchFlights {
    
    public static void main(String[] args) throws ParseException
    {
        boolean Continue = true;
        
        
        System.out.println("Start of the Test");
        
        List<Flights> AllFlights = GetAllFlight(); 
        
        Scanner sc =new Scanner(System.in);
        String Or, Dest, Code, stDate;
        Date FlightDate;
        Date now = new Date();
        int nbPers, diffDay;
        long diff;
        List<Flights> mFlights;
        
        
        while(Continue)
        {
            System.out.println("What do you want to do?");
            System.out.println("1) Print Flights List?");
            System.out.println("2) Search Flights (Or-Dest)?");
            System.out.println("3) Search Flights (Or)?");
            System.out.println("4) Search Flights (FlightCode)?");
            System.out.println("9) Exit?");
            int r=sc.nextInt();
        
            switch (r) {
            case 1:  
                    DisplayFlightsList(AllFlights);
                    break;
            case 2: 
                    System.out.println("Origin?");
                    Or = sc.next();
                    System.out.println("Destination?");
                    Dest = sc.next();
                    System.out.println("NbPerson?");
                    nbPers = sc.nextInt();
                    System.out.println("Date? Format: dd/MM/yyyy");
                    stDate = sc.next();
                    
                    mFlights = GetFlightsFromOriginAndDest(AllFlights,Or, Dest); 
                    
                    try {
                        FlightDate = new SimpleDateFormat("dd/MM/yyyy").parse(stDate);
                        
                        diff = FlightDate.getTime()  - now.getTime() - (now.getTime() % (1000*60*60*24)); 
                        diffDay = (int) (diff / (1000*60*60*24));
                        
                        DisplayFlightsListWithComputedPrice(mFlights, nbPers, diffDay);
                  
                    } catch (ParseException ex) {
                        Logger.getLogger(SearchFlights.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
                    break;
            case 3:  
                    System.out.println("Origin?");
                    Or = sc.next();
                    mFlights = GetFlightsFromOrigin(AllFlights,Or);
                    DisplayFlightsList(mFlights);
                    break;
            case 4:  
                    System.out.println("Code?");
                    Code = sc.next();
                    System.out.println("NbPerson?");
                    nbPers = sc.nextInt();
                    System.out.println("Date? Format: dd/MM/yyyy");
                    stDate = sc.next();
                    
                    GetFlightInListFromCode(AllFlights,Code).Display();
                    
                    try {
                        FlightDate = new SimpleDateFormat("dd/MM/yyyy").parse(stDate);
                        
                        diff = FlightDate.getTime()  - now.getTime() - (now.getTime() % (1000*60*60*24)); 
                        diffDay = (int) (diff / (1000*60*60*24));
                        GetFlightInListFromCode(AllFlights,Code).DisplayWithComputedPrice(nbPers, diffDay );
                  
                    } catch (ParseException ex) {
                        Logger.getLogger(SearchFlights.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;
            case 9:  
                    Continue = false;
                    break;         
            default: 
                    System.out.println("Invalid Input");
                    break;
            } 
        }
        System.out.println("End of the Test");
    }

   
    
}
