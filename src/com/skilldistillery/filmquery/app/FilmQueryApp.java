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
//    Film film = db.findFilmById(6);
//    System.out.println(film);
//  Actor actor = db.findActorById(5);
//  System.out.println(actor);
//  List<Actor> actors = db.findActorsByFilmId(1);
//  System.out.println(actors);
//    System.out.println(film.getActors());
//  	List<Film> films = db.findFilmByKeyword("wif");
//  	System.out.println(films);
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
    	Film film = db.findFilmById(filmId);
    	if (film == null) {
    		System.out.println("Invalid Film ID. Try again.");
    	}
    	else {
    		System.out.println();
    		System.out.println(film.getTitle());
    		System.out.println(film.getReleaseYear());
    		System.out.println(film.getRating());
    		System.out.println(film.getDescription());
    		System.out.println(film.getLanguage());
    		List<Actor> actors = film.getActors();
    		System.out.println("******** Cast *******");
    		for (Actor actor : actors) {
				System.out.println(actor.getFirstName() + " " + actor.getLastName());
			}
    		System.out.println("*********************");
    	}
    	System.out.println();
    	break;
    case 2:
    	System.out.println("Enter a keyword to search for: ");
    	String Keyword = input.next();
    	List<Film> film2 = db.findFilmByKeyword(Keyword);
    	if (film2 == null) {
    		System.out.println("No film associated with that keyword in database. Try again.");
    	}
    	else {
    		System.out.println();
    		for (Film films : film2) {
    			System.out.println(films.getTitle());
			}
    	}
    	System.out.println();
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
