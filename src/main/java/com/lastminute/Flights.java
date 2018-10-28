/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute;


import static com.lastminute.CsvFiles.readAllRecords;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author Aurélien
 */
public class Flights {
    
    String Origin;
    String Destination;
    String Code;
    
    float BasePrice;
    
    Flights(String mOrigin, String mDestination, String mCode)
    {
       Origin =  mOrigin;
       Destination = mDestination;
       Code = mCode;
    }
    
    private Flights(String mCode)
    {
       Code = mCode;
    }
    
    void setOrigin(String mOrigin)
    {
        Origin =  mOrigin;
    }
    void setDestination(String mDestination)
    {
        Destination =  mDestination;
    }
    void setCode(String mCode)
    {
        Code =  mCode;
    }
    void setBasePrice(Float mBasePrice)
    {
        BasePrice =  mBasePrice;
    }
    
    
    String getOrigin()
    {
        return Origin ;
    }
    String getDestination()
    {
        return Destination;
    }
    String getCode()
    {
        return Code;
    }
    Float getBasePrice()
    {
        return BasePrice;
    }
    
    static List<Flights> GetFlightsFromOrigin(List<Flights> mList,String Origin)
    {
        ArrayList<Flights> TempFlights = new ArrayList<>();
        
        mList.stream().map((_item) -> { 
            return _item;
        }).forEachOrdered((_item) -> {
        
            if(_item.getOrigin().equals(Origin))
            {
                TempFlights.add(_item);
            }
            
        }); 
        
        return TempFlights;
    }
    
      static List<Flights> GetFlightsFromOriginAndDest(List<Flights> mList,String Origin, String Dest)
    {
        ArrayList<Flights> TempFlights = new ArrayList<>();
        
        mList.stream().map((_item) -> { 
            return _item;
        }).forEachOrdered((_item) -> {
        
            if(_item.getOrigin().equals(Origin) && _item.getDestination().equals(Dest))
            {
                TempFlights.add(_item);
            }
            
        }); 
        return TempFlights;
    }
    
     static int GetPosInListFromCode(List<Flights> mList, String Code)
    {
        //System.out.println("mCode :" + Code);
        return mList.indexOf(new Flights(Code));
    }
    
    static Flights GetFlightInListFromCode(List<Flights> mList, String Code)
    {
        //System.out.println("mCode :" + Code);
        return mList.get(mList.indexOf(new Flights(Code)));
    }
    
    static List<Flights> GetAllFlight()
    {
        ArrayList<Flights> TempFlights = new ArrayList<>();
        
        List<List<String>> routes = readAllRecords(("C:/Temp/resources/flight-routes.csv"));  
        for(int i = 0; i < routes.size(); i++)
        {
            TempFlights.add(new Flights(routes.get(i).get(0), routes.get(i).get(1), routes.get(i).get(2)));
        }
        List<List<String>> prices = readAllRecords(("C:/Temp/resources/flight-prices.csv")); 
        for(int i = 0; i < prices.size(); i++)
        {
            //System.out.println(GetPosInListFromCode(AllFlights,prices.get(i).get(0)));
            try
            {
                TempFlights.get(GetPosInListFromCode(TempFlights,prices.get(i).get(0))).setBasePrice(Float.parseFloat(prices.get(i).get(1)));
            }
            catch(java.lang.ArrayIndexOutOfBoundsException e)
            {
                System.out.println("No Price Input Possible - Flight Doesn't exist");
            }
        }
        
        return TempFlights;
    }
    
    void Display()
    {
            System.out.println("-----------------------------");
            System.out.println("Origin :" + this.getOrigin());
            System.out.println("Destination :" + this.getDestination());
            System.out.println("Code :" + this.getCode());
            System.out.println("Price :" + this.getBasePrice());
     }
    
      void DisplayWithComputedPrice(int nbPers, int diffDay)
    {
            System.out.println("-----------------------------");
            System.out.println("Origin :" + this.getOrigin());
            System.out.println("Destination :" + this.getDestination());
            System.out.println("Code :" + this.getCode());
            System.out.println("Price :" + getComputedFullPrice(diffDay, this.getBasePrice(), nbPers));
     }
    
    static void DisplayFlightsList(List<Flights> mList)
    {
        mList.stream().map((_item) -> { 
            return _item;
        }).forEachOrdered((_item) -> {
            System.out.println("-----------------------------");
            System.out.println("Origin :" + _item.getOrigin());
            System.out.println("Destination :" + _item.getDestination());
            System.out.println("Code :" + _item.getCode());
            System.out.println("Price :" + _item.getBasePrice());
        });    
    }
    
    static void DisplayFlightsListWithComputedPrice(List<Flights> mList, int nbPers, int diffDay)
    {
        mList.stream().map((_item) -> { 
            return _item;
        }).forEachOrdered((_item) -> {
            System.out.println("-----------------------------");
            System.out.println("Origin :" + _item.getOrigin());
            System.out.println("Destination :" + _item.getDestination());
            System.out.println("Code :" + _item.getCode());
            System.out.println("Price :" + getComputedFullPrice(diffDay, _item.getBasePrice(), nbPers));
        });    
    }
    


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.Origin);
        hash = 83 * hash + Objects.hashCode(this.Destination);
        hash = 83 * hash + Objects.hashCode(this.Code);
        hash = 83 * hash + Float.floatToIntBits(this.BasePrice);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Flights other = (Flights) obj;

        return Objects.equals(this.Code, other.Code);  // I considere the code as unique Index
    }
    
    static float getComputedFullPrice(int diffDay, float basePrice, int nbPers)
    {
        if(diffDay < 3)
        {
            return (float) (basePrice * 1.5 * nbPers);
        }
        else if(diffDay >= 3 & diffDay < 16)
        {
           return (float) (basePrice * 1.2 * nbPers); 
        }
        else if(diffDay >= 16 & diffDay <= 30)
        {
            return (float) (basePrice * 1 * nbPers);
        }
        else  // if(diffDay > 30)
        {
            return (float) (basePrice * 0.8 * nbPers);
        }
    }
    
    float ComputedPrice(int diffDay)
    {
        if(diffDay < 3)
        {
            return (float) (this.BasePrice * 1.5);
        }
        else if(diffDay >= 3 & diffDay < 16)
        {
           return (float) (this.BasePrice * 1.2); 
        }
        else if(diffDay >= 16 & diffDay <= 30)
        {
            return (float) (this.BasePrice * 1);
        }
        else  // if(diffDay > 30)
        {
            return (float) (this.BasePrice * 0.8);
        }
    }  
  
}
