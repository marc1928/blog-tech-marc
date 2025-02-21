package tch_tech.com.userservice.service;

import tch_tech.com.userservice.model.UserEntity;
import tch_tech.com.userservice.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Ajoute un nouvel utilisateur dans le système.
     *
     * @param userEntity entité représentant l'utilisateur à ajouter
     * @return l'utilisateur ajouté après son enregistrement avec ses détails mis à jour
     */
    UserEntity addNewUser(UserEntity userEntity);

    /**
     * Ajoute un nouveau rôle dans le système.
     *
     * @param userRole entité représentant le rôle à ajouter
     * @return le rôle ajouté après son enregistrement
     */
    UserRole addNewRole(UserRole userRole);

    /**
     * Associe un rôle à un utilisateur.
     *
     * @param username nom d'utilisateur de l'utilisateur
     * @param roleName nom du rôle à attribuer
     */
    void addRoleToUser(String username, String roleName);

    /**
     * Charge un utilisateur spécifique en utilisant son nom d'utilisateur.
     * permet de retrouver un utilisateur en fonction de son nom d'utilisateur
     *
     * @param username nom d'utilisateur de l'utilisateur recherché
     * @return l'utilisateur correspondant au nom d'utilisateur
     */
    Optional<UserEntity> loadUserByUsername(String username);

    /**
     * Retourne la liste de tous les utilisateurs enregistrés dans le système.
     *
     * @return liste des utilisateurs
     */
    List<UserEntity> listAllUsers();

    /**
     * Recherche un utilisateur spécifique en fonction de son identifiant unique.
     *
     * @param id identifiant unique de l'utilisateur
     * @return un objet Optional contenant l'utilisateur s'il est trouvé, sinon un objet vide
     */
    Optional<UserEntity> getUserById(Long id);

    /**
     * Met à jour les informations d'un utilisateur spécifique.
     *
     * @param id identifiant unique de l'utilisateur à mettre à jour
     * @param userEntity entité contenant les nouvelles informations
     * @return un objet Optional contenant l'utilisateur mis à jour, sinon un objet vide si l'utilisateur n'existe pas
     */
    Optional<UserEntity> updateUser(Long id, UserEntity userEntity);

    void deleteUserById(Long id);
}
