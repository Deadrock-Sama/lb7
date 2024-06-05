package lb.project.lb6_server.client.builders;

import lb.project.lb6_server.lib.entities.User;
import lb.project.lb6_server.lib.ui.UIController;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class UserBuilder extends Builder{
    public UserBuilder(UIController controller) {
        super(controller);
    }

    public User build() {
        return new User(login, password);
    }

    public UserBuilder setLogin() {
        login = controller.readString("Введите логин: ");
        if (login == null || login.isEmpty())
            setLogin();

        return this;
    }

    public UserBuilder setPassword() {
        PasswordHasher hasher = new PasswordHasher();
        password = hasher.hash(controller.readPassword("Введите пароль: "));
        return this;
    }

    public User repeatPassword() {
        PasswordHasher hasher = new PasswordHasher();
        String enteredPassword = hasher.hash(controller.readPassword("Повторите пароль: "));

        if (enteredPassword.equals(password))
            return new User(login, password);

        return null;
    }
    private String login;
    private String password;
    private class PasswordHasher {

        private byte[] getSalt() {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            return salt;
        }
        public String hash(char[] password) {

            //byte[] salt = getSalt();
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD2");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            md.update("123".getBytes());

            String hashedPassword = new String(md.digest(toBytes(password)), StandardCharsets.UTF_8);
            return hashedPassword;

        }

        private byte[] toBytes(char[] chars) {
            CharBuffer charBuffer = CharBuffer.wrap(chars);
            ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
            byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                    byteBuffer.position(), byteBuffer.limit());
            Arrays.fill(byteBuffer.array(), (byte) 0);
            return bytes;
        }
    }

}
