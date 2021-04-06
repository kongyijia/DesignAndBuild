package mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import models.Administrator;
import models.Client;
import models.Coach;
import models.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientMapping {
    public static final String DATA_PATH = "data/client.json";
    public static final int SUCCESS = 0;
    public static final int DUPLICATE_ID = 1;
    public static final int DUPLICATE_NICKNAME = 6;
    public static final int CLIENT_NOT_FOUND = 2;
    public static final int USER_NOT_FOUND = 3;
    public static final int COACH_NOT_FOUND = 4;
    public static final int ADMIN_NOT_FOUND = 5;


    public static <T extends Client> int add(T type) throws IOException {
        ArrayList<Client> clients = readAllClients();
        for (Client client : clients) {
            if (type.getId() == client.getId()) {
                return DUPLICATE_ID;
            }
            if (type.getNickName().equals(client.getNickName())) {
                return DUPLICATE_NICKNAME;
            }
        }
        clients.add(type);
        writeAll(clients);
        return SUCCESS;
    }

    public static int delete(int id) throws IOException {
        int index = -1;
        ArrayList<Client> clients = readAllClients();
        for (Client client : clients) {
            if (client.getId() == id) {
                index = clients.indexOf(client);
            }
        }
        if (index == -1) {
            return CLIENT_NOT_FOUND;
        }
        clients.remove(index);
        writeAll(clients);
        return SUCCESS;
    }

    public static <T extends Client> int modify(T type) throws IOException {
        int index = -1;
        ArrayList<Client> clients = readAllClients();
        for (Client client : clients) {
            if (client.getId() == type.getId()) {
                index = clients.indexOf(client);
            }
        }
        if (index == -1) {
            return CLIENT_NOT_FOUND;
        }
        clients.remove(index);
        clients.add(type);
        writeAll(clients);
        return SUCCESS;
    }

    public static ArrayList<Client> find(HashMap<String, String> map) throws FileNotFoundException {
        ArrayList<Client> clients = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader(DATA_PATH));
        reader.startArray();
        while (reader.hasNext()) {
            String s = reader.readString();
            JSONObject object = JSON.parseObject(s);
            int tmp = 0;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String v = object.getString(entry.getKey());
                if (v == null) {
                    continue;
                }
                if (v.equals(entry.getValue())) {
                    tmp++;
                }
            }
            if (tmp == map.size()) {
                if (object.getInteger("role") == 0) {
                    clients.add(JSON.parseObject(s, Administrator.class));
                } else if (object.getInteger("role") == 1) {
                    clients.add(JSON.parseObject(s, Coach.class));
                } else if (object.getInteger("role") == 2) {
                    clients.add(JSON.parseObject(s, User.class));
                }
            }
        }
        reader.endArray();
        reader.close();
        return clients;
    }

    public static ArrayList<User> findUser(HashMap<String, String> map) throws FileNotFoundException {
        map.put("role", "2");
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Client> clients = find(map);
        clients.forEach(client -> {
            users.add((User) client);
        });
        return users;
    }

    public static ArrayList<Coach> findCoach(HashMap<String, String> map) throws FileNotFoundException {
        map.put("role", "1");
        ArrayList<Coach> coaches = new ArrayList<>();
        ArrayList<Client> clients = find(map);
        clients.forEach(client -> {
            coaches.add((Coach) client);
        });
        return coaches;
    }

    public static ArrayList<Administrator> findAdministrator(HashMap<String, String> map) throws FileNotFoundException {
        map.put("role", "0");
        ArrayList<Administrator> administrators = new ArrayList<>();
        ArrayList<Client> clients = find(map);
        clients.forEach(client -> {
            administrators.add((Administrator) client);
        });
        return administrators;
    }


    public static ArrayList<Client> readAllClients() throws FileNotFoundException {
        ArrayList<Client> clients = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader(DATA_PATH));
        reader.startArray();
        while (reader.hasNext()) {
            String s = reader.readString();
            JSONObject object = JSON.parseObject(s);
            if (object.getInteger("role") == 0) {
                clients.add(JSON.parseObject(s, Administrator.class));
            } else if (object.getInteger("role") == 1) {
                clients.add(JSON.parseObject(s, Coach.class));
            } else if (object.getInteger("role") == 2) {
                clients.add(JSON.parseObject(s, User.class));
            }
        }
        reader.endArray();
        reader.close();
        return clients;
    }

    private static void writeAll(ArrayList<Client> clients) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter(DATA_PATH));
        writer.startArray();
        for (Client client : clients) {
            writer.writeValue(client);
        }
        writer.endArray();
        writer.close();
    }
}
