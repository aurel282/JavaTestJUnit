/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute;

import static com.lastminute.Flights.*;
import java.util.List;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Aurélien
 */
public class FlightClassTest {
    
    List<Flights> mFlights = GetAllFlight();
    int nbFlights = 89;
    
    Random random = new Random();
    
    
    @Test
    public void TestGetFlightsFromOrigin()
    {
        String or = mFlights.get(random.nextInt(nbFlights)).getOrigin();
        List<Flights> tempFlights = GetFlightsFromOrigin(mFlights, or);
        
         tempFlights.stream().map((_item) -> { 
            return _item;
        }).forEachOrdered((_item) -> {
            assertEquals(_item.getOrigin(), or);
        }); 
    }
    
    @Test
    public void TestGetFlightsFromOriginAndDest()
    {
        String or = mFlights.get(random.nextInt(nbFlights)).getOrigin();
        String dest = mFlights.get(random.nextInt(nbFlights)).getDestination();
        List<Flights> tempFlights = GetFlightsFromOriginAndDest(mFlights, or, dest);
        
         tempFlights.stream().map((_item) -> { 
            return _item;
        }).forEachOrdered((_item) -> {
            assertEquals(_item.getOrigin(), or);
            assertEquals(_item.getDestination(), dest);
        }); 
    }
    
    @Test
    public void TestGetPosInListFromCode()
    {
        String code = mFlights.get(random.nextInt(nbFlights)).getCode();
        
        assertEquals(mFlights.get(GetPosInListFromCode(mFlights,code)).getCode(), code);
    }
    @Test
    public void TestGetFlightInListFromCode()
    {
       String code = mFlights.get(random.nextInt(nbFlights)).getCode(); 
       
       assertEquals(GetFlightInListFromCode(mFlights,code).getCode(), code);
    }
    @Test
    public void TestGetAllFlight()
    {
        assertEquals(GetAllFlight().size(), nbFlights);

        
        assertEquals(GetAllFlight().get(0).getCode(), "IB2818");
        assertEquals(GetAllFlight().get(nbFlights - 1).getCode(), "LH7260");
    }

}
