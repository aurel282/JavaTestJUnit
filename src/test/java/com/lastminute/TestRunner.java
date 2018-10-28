/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lastminute;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author Aurélien
 */
public class TestRunner {
     public static void main(String[] args) {
         
      System.out.println("TestRead");   
      
      Result result = JUnitCore.runClasses(ReadFlightsTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println("IsSuccessfull? " + result.wasSuccessful());
      
      System.out.println("TestTreatment");  
      
      result = JUnitCore.runClasses(FlightClassTest.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
	
      System.out.println("IsSuccessfull? " + result.wasSuccessful());
      
      System.out.println(result.wasSuccessful());
   }
}
