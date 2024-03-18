package com.ashcollege.utils;

import com.ashcollege.entities.Post;
import com.ashcollege.entities.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class DbUtils {

    private Connection connection = null;

    @PostConstruct
    public Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "1234");
            System.out.println("Connection success");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public boolean signIn(String username, String password) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT users.username " +
                                    "FROM users WHERE username = ? " +
                                    "AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean registerUser(User user) {
        boolean result = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO users (username, password) VALUE (?, ?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public boolean usernameAvailable(String username) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT users.username " +
                                    "FROM users WHERE username = ? ");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllFollowing(String username) {
        List<User> allFollowing = null;
        try {
            allFollowing = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT DISTINCT follow FROM followers WHERE follower = ?"
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setUsername(resultSet.getString("follow"));
                allFollowing.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allFollowing;
    }

    public List<User> getUserSearchResult(String search) throws SQLException {
        List<User> searchResult = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT username FROM users WHERE username LIKE ?");
        preparedStatement.setString(1, search + "%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setUsername(resultSet.getString("username"));
            searchResult.add(user);
        }
        return searchResult;
    }

    public boolean follow(String username, String name) {
        boolean result = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO followers (follower, follow) VALUE (?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    public boolean addPost(String username, String post) {
        boolean result = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "INSERT INTO posts (username, post) VALUE (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, post);
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<Post> getAllPosts(String username) {
        List<Post> allPosts = null;
        try {
            allPosts = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM posts WHERE username = ? ORDER BY date DESC"
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post=new Post();
                post.setPost(resultSet.getString("post"));
                post.setTime(resultSet.getString("date"));
                allPosts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allPosts;
    }

    public List<Post> showFeed(String username) {
        List<Post> feed =  new ArrayList<>();
        List<User> following = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT *" +
                            " FROM posts JOIN followers ON posts.username = followers.follow" +
                            "  WHERE follower = ? ORDER BY posts.date DESC LIMIT 20");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post=new Post();
                post.setUsername(resultSet.getString("username"));
                post.setPost(resultSet.getString("post"));
                post.setTime(resultSet.getString("date"));
                feed.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }
}
