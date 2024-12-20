package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "student";
	private String pass = "student";

	
  @Override
  public Film findFilmById(int filmId) {
	  Film film = null;
	  //CONNECT TO DB
	  try {
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  //DEFINE QUERY AND SUB VALUE(S)
	  String sql = "SELECT * FROM film JOIN language ON language_id = language.id WHERE film.id = ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setInt(1, filmId);
	  //EXECUTE QUERY
	  ResultSet filmResult = stmt.executeQuery();
	  //PROCESS RESULT
	  if (filmResult.next()) {
		  film = new Film();
		  film.setId(filmResult.getInt("id"));
		  film.setTitle(filmResult.getString("title"));
		  film.setDescription(filmResult.getString("description"));
		  film.setReleaseYear(filmResult.getInt("release_year"));
		  film.setLanguageID(filmResult.getInt("language_id"));
		  film.setLanguage(filmResult.getString("name"));
		  film.setRentalDuration(filmResult.getInt("rental_duration"));
		  film.setLength(filmResult.getInt("length"));
		  film.setReplacementCost(filmResult.getDouble("replacement_cost"));
		  film.setRating(filmResult.getString("rating"));
		  film.setSpecialFeatures(filmResult.getString("special_features"));
		  film.setActors(findActorsByFilmId(filmId));
	  }
    filmResult.close();
    stmt.close();
    conn.close();
	  } catch (SQLException sqle) {
		  sqle.printStackTrace();
	  }
		  return film;		  
  }
  @Override
  public List<Film> findFilmByKeyword(String keyword) {
	  List<Film> films = new ArrayList<>();
	  Film film = null;
	  keyword = "%" + keyword + "%";
	  //CONNECT
	  try {
	  Connection conn = DriverManager.getConnection(URL, user, pass);
	  //DEFINE
	  String sql = "SELECT * FROM film JOIN language ON film.language_id = language.id"
//	  		+ " JOIN film_actor ON film.id = film_actor.film_id"
//	  		+ " JOIN actor ON film_actor.actor_id = actor.id "
	  		+ " WHERE title LIKE ? OR description LIKE ?";
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setString(1, keyword);
	  stmt.setString(2, keyword);
	  //EXECUTE
	  ResultSet rs = stmt.executeQuery();
	  //PROCESS
	  while (rs.next()) {
		  film = new Film();
		  film.setId(rs.getInt("id"));
		  film.setTitle(rs.getString("title"));
		  film.setDescription(rs.getString("description"));
		  film.setReleaseYear(rs.getInt("release_year"));
		  film.setLanguageID(rs.getInt("language_id"));
		  film.setLanguage(rs.getString("name"));
		  film.setRentalDuration(rs.getInt("rental_duration"));
		  film.setLength(rs.getInt("length"));
		  film.setReplacementCost(rs.getDouble("replacement_cost"));
		  film.setRating(rs.getString("rating"));
		  film.setSpecialFeatures(rs.getString("special_features"));
		  film.setActors(findActorsByFilmId(rs.getInt("id")));
		  films.add(film);
	  	}
	  rs.close();
	  stmt.close();
	  conn.close();
	  
	  } catch (SQLException sqle) {
		  sqle.printStackTrace();
	  }
	  return films;
	}
  
  

@Override
public Actor findActorById(int actorId) {
	Actor actor = null;
	
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);
		
		String sql = "SELECT * FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);

		ResultSet actorResult = stmt.executeQuery();
		
		while(actorResult.next()) {
			actor = new Actor();
			actor.setId(actorResult.getInt("id"));
			actor.setFirstName(actorResult.getString("first_name"));
			actor.setLastName(actorResult.getString("last_name"));
		}
		actorResult.close();
		stmt.close();
		conn.close();
	}
	catch(SQLException sqle) {
		sqle.printStackTrace();
	}
	return actor;
}
		

@Override
public List<Actor> findActorsByFilmId(int filmId) {
	List<Actor> actors = new ArrayList<>();
	Actor actor = null;
	try {
		Connection conn = DriverManager.getConnection(URL, user, pass);

		String sql = "SELECT id, first_name, last_name FROM actor "
				+ " JOIN film_actor ON id = actor_id WHERE film_id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		
		ResultSet listResult = stmt.executeQuery();
		
		while (listResult.next()) {
			actor = new Actor();
			actor.setId(listResult.getInt("id"));
			actor.setFirstName(listResult.getString("first_name"));
			actor.setLastName(listResult.getString("last_name"));
			actors.add(actor);
		}
		listResult.close();
		stmt.close();
		conn.close();
	} catch (SQLException sqle) {
		sqle.printStackTrace();
	}
	return actors;
}
  

static {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	catch (ClassNotFoundException e) {
		System.err.println("Unable to load database driver:");
		e.printStackTrace();
		System.err.println("Exiting.");
		System.exit(1); // No point in continuing.
			}
		}
	}
