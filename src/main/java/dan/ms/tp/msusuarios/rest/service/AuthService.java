package dan.ms.tp.msusuarios.rest.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import dan.ms.tp.msusuarios.dao.UsuarioJpaRepository;
import dan.ms.tp.msusuarios.modelo.Usuario;
import dan.ms.tp.msusuarios.rest.UsuarioWithToken;


@Service
public class AuthService {
    
    //should be a env variable
    private static String JWT_SECRET = "722fccc6319149bfe4b0aace255dec2b3ce3120de8647abe7e900e56dbca1da2";

    @Autowired
    private UsuarioJpaRepository usuarioRepo;

    public String generateToken(String user) {
        
        return JWT.create()
            .withIssuer("dan-tp")
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plusSeconds(2*3600))
            .withSubject(user)
            .sign(Algorithm.HMAC256(JWT_SECRET));
    }

    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC256(JWT_SECRET)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UsuarioWithToken register(Usuario user) {
        
        if (usuarioRepo.findAll().stream().filter(u -> u.getUserName().equals(user.getUserName())).findAny().isPresent())
            throw new RuntimeException("Username already exists");

        Usuario newUser = usuarioRepo.save(user);
        UsuarioWithToken userWithToken = new UsuarioWithToken(newUser);
        userWithToken.setToken(generateToken(newUser.getUserName()));
        return userWithToken;
    }

    public UsuarioWithToken login(String userName, String pass) {
        Usuario user = usuarioRepo.findByUserName(userName);
        if (user == null || !user.getPassword().equals(pass))
            throw new RuntimeException("Invalid credentials");
            
        UsuarioWithToken userWithToken = new UsuarioWithToken(user);
        userWithToken.setToken(generateToken(user.getUserName()));
        return userWithToken;
    }
}
