package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {
  
  DatabaseAccessor db = new DatabaseAccessorObject();

  public static void main(String[] args) {
    FilmQueryApp app = new FilmQueryApp();
//    app.test();
    app.launch();
  }

//  private void test() {
//    Film film = db.findFilmById(1);
//    System.out.println(film);
//  Actor actor = db.findActorById(5);
//  System.out.println(actor);
//  List<Actor> actors = db.findActorsByFilmId(1);
//  System.out.println(actors);
//    System.out.println(film.getActors());
//  }

  private void launch() {
    Scanner input = new Scanner(System.in);
    
    startUserInterface(input);
    
    input.close();
  }

  private void startUserInterface(Scanner input) {
	boolean keepGoing = true;
	while (keepGoing) {  
	  
    menu();
    System.out.println("Enter a number: ");
    int choice = input.nextInt();
    
    switch(choice) {
    case 1:
    	System.out.println("Enter Film ID: ");
    	int filmId = input.nextInt();
    	Film result = db.findFilmById(filmId);
    	if (result == null) {
    		System.out.println("Invalid Film ID. Try again.");
    	}
    	else {
    		System.out.println(result);
    	}
    	System.out.println();
    	break;
    case 2:
    	break;
    case 3:
    	System.out.println("Good-bye");
    	keepGoing = false;
    	}
	}
	
  }
  
  private void menu() {
	  System.out.println("******* Main Menu *******");
	  System.out.println("1. Find Film By ID");
	  System.out.println("2. Find Film With Keyword");
	  System.out.println("3. Exit");
	  System.out.println("*************************");
  } 
  
 

}
