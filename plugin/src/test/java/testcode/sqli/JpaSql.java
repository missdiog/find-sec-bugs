package testcode.sqli;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;

public class JpaSql {

    @Inject
    EntityManager em;

    //The following query are subject to injection

    public void getUserByUsername(String username) {
        TypedQuery<UserEntity> q = em.createQuery(
                String.format("select * from Users where name = %s", username),
                UserEntity.class);

        UserEntity res = q.getSingleResult();
    }

    public void getUserByUsernameAlt2(String username) {
        TypedQuery<UserEntity> q = em.createQuery(
                "select * from Users where name = '" + username + "'",
                UserEntity.class);

        UserEntity res = q.getSingleResult();
    }


    //The following should be safe

    public UserEntity getFirst() {
        TypedQuery<UserEntity> q = em.createQuery(
                "select * from Users",
                UserEntity.class);
        return q.getSingleResult();
    }

    public UserEntity getFirstAlt2() {
        final String sql = "select * from Users";
        TypedQuery<UserEntity> q = (TypedQuery<UserEntity>) em.createQuery(sql);
        return q.getSingleResult();
    }

}
