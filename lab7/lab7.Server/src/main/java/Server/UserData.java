package Server;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

import static Server.Hashing.*;

public class UserData {
    Map<String, Map<String, byte[]>> userList = new HashMap<>();
    String answer;
    Boolean isAccess = false;
    public boolean check(String login, String password) {
        boolean s = false;
        for(Map.Entry<String, Map<String, byte[]>> entry:userList.entrySet()) {
            if(entry.getKey().equals(login)) {
                for (Map.Entry<String, byte[]> entry1:entry.getValue().entrySet())
                if(entry1.getKey().equals(getHash(password, entry1.getValue()))) {
                     answer = "Авторизация успешна";
                     isAccess = true;
                    s = true;
                }
            }
        }
        if (!s) {
            isAccess = false;
            answer = "Неправильно введен логин или пароль";
        }
        return s;
    }
    public void add(String login, String password){
        for(Map.Entry<String,Map<String,byte[]>> entry:userList.entrySet()){
            if (entry.getKey().equals(login)){
                answer = "Такой логин уже есть, войдите, или создайте аккаунт с другим логином";
                isAccess = false;
                return;
            }
        }
        byte[] bytes = getSalt();
        HashMap<String, byte[]> hashPass = new HashMap<>();
        hashPass.put(getHash(password, bytes), bytes);
        userList.put(login, hashPass);
        DataBaseManager.addUser(login,hashPass);
        answer = "Пользователь успешно добавлен \n Ваш логин - " + login;
    }
    public void loadUsers(){
        userList = DataBaseManager.loadUsers();
    }
}
