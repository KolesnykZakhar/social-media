package com.gmail.kolesnyk.zakhar.userService;

import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.userService.friendsPage.UsersPage;

import java.util.List;


/**
 * The {@code UserService} class used for operations associated with User
 *
 * @author Kolesnyk Zakhar
 * @see com.gmail.kolesnyk.zakhar.AbstractService
 * @since JDK1.8
 */
public interface UserService {

    /**
     * method allow to get example of User by it email address or login
     *
     * @param loginOrEmail login or email address of User
     * @return {@link User} example User
     */
    User getUserByLoginOrEmail(String loginOrEmail) throws IllegalAccessException;

    /**
     * method allow to get register new User in system
     *
     * @param firstName   first name of User
     * @param lastName    last name of User
     * @param birthDate   birth date of User
     * @param login       login of User
     * @param pass        password of User
     * @param confirmPass confirming password of User
     * @param email       email of User
     * @param phone       phone of User
     * @param gender      if entered '0' User gender set 'Male', if '1' User gender set Female
     * @throws com.gmail.kolesnyk.zakhar.validation.RegistrationValidateException when validation of User data not passed
     * @see com.gmail.kolesnyk.zakhar.validation.RegistrationValidateException
     * @see com.gmail.kolesnyk.zakhar.validation.Validator
     */
    void registrationUser(String firstName, String lastName, String birthDate, String login, String pass, String confirmPass, String email, String phone, Integer gender);

    /**
     * method allow to get page of Friends {@link UsersPage} by ID User and number of page
     *
     * @param idUser     ID User
     * @param pageNumber number of expected page
     * @return {@link UsersPage} page of Friends
     */
    UsersPage friendsSublist(int idUser, int pageNumber);

    /**
     * method allow to get example of User {@link User} by it ID
     *
     * @param idUser ID User
     * @return {@link User} example of User
     */
    User getUserById(int idUser);

    /**
     * method allow to update User fields in system by it new example
     *
     * @param user example of User
     */
    void update(User user);

    /**
     * method allow to confirm email address of User in system by hash string
     *
     * @param hasLink unique hash string
     */
    void confirmEmail(String hasLink);

    /**
     * method allow to discard registration of new User in system by hash string
     *
     * @param hasLink unique hash string
     */
    void discardRegistration(String hasLink);

    /**
     * method allow to create possibility of restoring forgotten password of User
     * Means: create unique hash string for restoring password in system
     * and send to email address of User instructions how he can to restore password or discard restoring
     * with two links 'restore' and 'discard', if successful follow one of this link both are becoming not valid
     *
     * @param email email address of User
     */
    void createRestorePassword(String email);

    /**
     * method allow to remove created possibility of restoring forgotten password of User by created hash string
     *
     * @param hash unique hash string
     * @see com.gmail.kolesnyk.zakhar.userService.UserService#createRestorePassword(String)
     */
    void removeRestorePassword(String hash);

    /**
     * method allow to create new password of User and save it to system instead his old password
     * by his login or email address
     *
     * @param loginOrEmail    login or email address of User
     * @param password        new password of User
     * @param confirmPassword confirming new password of User
     * @see com.gmail.kolesnyk.zakhar.userService.UserService#createRestorePassword(String)
     */
    void createNewPassword(String loginOrEmail, String password, String confirmPassword);

    /**
     * method allow to know what two User are friends
     *
     * @param idUser        ID of assumed User
     * @param idCurrentUser ID User
     * @return true if two Users are friends, and false if two Users not are Friends
     */
    boolean isFriends(int idUser, int idCurrentUser);

    /**
     * method allow to create in system of invitation for friendship from User to other User
     *
     * @param idCurrentUser ID User that send invitation for friendship
     * @param idUser        ID User that receive invitation of friendship
     */
    void inviteForFriendship(int idCurrentUser, int idUser);

    /**
     * method allow to remove friendship by unilaterally
     *
     * @param idCurrentUser ID User that want to remove friend
     * @param idUser        ID User that will lose friend
     */
    void removeFromFriends(int idCurrentUser, int idUser);

    /**
     * method allow to know that exist invitation for friendship from one User to other
     *
     * @param idCurrentUser ID User that maybe send invitation for friendship
     * @param idUser        ID User that maybe receive invitation for friendship
     * @return true if current User earlier sent invitations for friendship to other User, false if it not
     */
    boolean isInvitedForFriendship(int idCurrentUser, int idUser);

    /**
     * method allow to know amount of all invitations for friendship from other Users
     *
     * @param idUser ID User that receive invitations
     * @return {@link Integer} amount of invitations
     */
    Integer amountOfInvitations(int idUser);

    /**
     * method allow to get list of Users {@link List<User>} that sent to current User invitations for friendship
     *
     * @param idUser ID User that receive invitations
     * @return {@link List<User>} list of users
     */
    List<User> listInvitationsForFriendship(int idUser);

    /**
     * method allow to create friendship in system between two Users by there IDs,
     * both Users equals in rights
     *
     * @param idCurrentUser ID User that accepted invitation for friendship
     * @param idUser        ID User that send invitations for friendship
     */
    void addFriendship(int idCurrentUser, int idUser);

    /**
     * method allow to decline invitations for friendship by his ID
     * means: removing from system sent invitation other User to current User
     *
     * @param idCurrentUser ID User that receive and decline invitation
     * @param idUser        ID User that sent earlier invitation for friendship to current User
     */
    void declineInvitationForFriendship(int idCurrentUser, int idUser);

    /**
     * method allow to get page of Users {@link UsersPage} that contains found Users by search string and number of page
     *
     * @param search     string for searching Users
     * @param pageNumber number of page
     * @return {@link UsersPage} page of Users
     */
    UsersPage foundUsersSublist(String search, int pageNumber);
}
