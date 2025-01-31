package it.unibs.ing.model.user;

public interface IUser {
    String getUsername();
    boolean login(String username, String password);
}
