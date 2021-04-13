package model.mapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import model.Administrator;
import model.Client;
import model.Coach;
import model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Map {@link Client} data to JSON database.
 * This "JSON database" is a self-defined database and it should only be used in this software.
 * <p>
 * Offering {@code add}, {@code delete}, {@code modify} and {@code find} methods for client data, which include ({@link User}, {@link Coach} and {@link Administrator}).
 * In this class, we use {@link com.alibaba.fastjson.JSON} to manage our {@link Client} POJO.
 *
 * @author Yubo Wu
 * @version 1.0
 * @see JSON
 * @see Client
 * @see User
 * @see Coach
 * @see Administrator
 * @since 12 April 2021
 */
public class ClientMapping {
    public static final String DATA_PATH = "data/client.json";
    public static final int SUCCESS = 0;
    public static final int DUPLICATE_ID = 1;
    public static final int DUPLICATE_NICKNAME = 6;
    public static final int CLIENT_NOT_FOUND = 2;
    public static final int USER_NOT_FOUND = 3;
    public static final int COACH_NOT_FOUND = 4;
    public static final int ADMIN_NOT_FOUND = 5;


    /**
     * Add client({@link User}, {@link Coach}, {@link Administrator}) to JSON database.
     *
     * @param type instance of class extended {@link Client} class
     * @param <T>  extends {@link Client} class
     * @return status code: {@value DUPLICATE_ID}, {@value DUPLICATE_NICKNAME} or {@value SUCCESS}
     * @throws IOException when IO issue occur
     */
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

    /**
     * Delete Client({@link User}, {@link Coach}, {@link Administrator}) from JSON database.
     *
     * @param id ID of the {@link Client} instance which you want to delete
     * @return Status code: {@value CLIENT_NOT_FOUND} or {@value SUCCESS}
     * @throws IOException when IO issue occur
     */
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

    /**
     * Update Client({@link User}, {@link Coach}, {@link Administrator}) data in JSON database.
     * The method need {@code ID} to locate where the data is so do not set a new value to the {@code ID}.
     *
     * @param type updated instance of client class extended {@link Client}
     * @param <T>  extends {@link Client} class
     * @return status code: {@value DUPLICATE_NICKNAME}, {@value CLIENT_NOT_FOUND} or {@value SUCCESS}
     * @throws IOException when IO issue occur
     */
    public static <T extends Client> int modify(T type) throws IOException {
        int index = -1;
        ArrayList<Client> clients = readAllClients();
        for (Client client : clients) {
            if (client.getNickName().equals(type.getNickName())
                    && client.getId() != type.getId()) {
                return DUPLICATE_NICKNAME;
            }
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

    /**
     * Find client({@link User}, {@link Coach}, {@link Administrator}) from JSON database, and return the results to a {@link ArrayList<Client>}.
     * The parameter are {@link HashMap}, you need to put all {@code AND} conditions in hashmap.
     * This {@code find} method can't handle other conditions except {@code AND}, like {@code OR} or {@code BETWEEN} or others.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList<Client>} contained results.
     * @throws FileNotFoundException when {@value DATA_PATH} not found
     */
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

    /**
     * Find {@link User} from JSON database, return the result to an {@link ArrayList<User>}.
     * The difference between the method and {@link ClientMapping#find(HashMap)} is that the return value of this method will contains the instances of {@link User}, not {@link Client}.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList<User>} contained results.
     * @throws FileNotFoundException when {@value DATA_PATH} not found
     * @see ClientMapping#find(HashMap)
     */
    public static ArrayList<User> findUser(HashMap<String, String> map) throws FileNotFoundException {
        map.put("role", "2");
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Client> clients = find(map);
        clients.forEach(client -> {
            users.add((User) client);
        });
        return users;
    }

    /**
     * Find {@link Coach} from JSON database, return the result to an {@link ArrayList<Coach>}.
     * The difference between the method and {@link ClientMapping#find(HashMap)} is that the return value of this method will contains the instances of {@link Coach}, not {@link Client}.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList<Coach>} contained results.
     * @throws FileNotFoundException when {@value DATA_PATH} not found
     * @see ClientMapping#find(HashMap)
     */
    public static ArrayList<Coach> findCoach(HashMap<String, String> map) throws FileNotFoundException {
        map.put("role", "1");
        ArrayList<Coach> coaches = new ArrayList<>();
        ArrayList<Client> clients = find(map);
        clients.forEach(client -> {
            coaches.add((Coach) client);
        });
        return coaches;
    }

    /**
     * Find {@link Administrator} from JSON database, return the result to an {@link ArrayList<Administrator>}.
     * The difference between the method and {@link ClientMapping#find(HashMap)} is that the return value of this method will contains the instances of {@link Administrator}, not {@link Client}.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList<Administrator>} contained results.
     * @throws FileNotFoundException when {@value DATA_PATH} not found
     * @see ClientMapping#find(HashMap)
     */
    public static ArrayList<Administrator> findAdministrator(HashMap<String, String> map) throws FileNotFoundException {
        map.put("role", "0");
        ArrayList<Administrator> administrators = new ArrayList<>();
        ArrayList<Client> clients = find(map);
        clients.forEach(client -> {
            administrators.add((Administrator) client);
        });
        return administrators;
    }

    /**
     * Read {@value DATA_PATH} to the program and convert it to POJO using fastJSON.
     *
     * @return all data in {@value DATA_PATH}
     * @throws FileNotFoundException when {@value DATA_PATH} not found
     */
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

    /**
     * Write all {@code clients} in {@link ArrayList<Client>} to the file using fastJSON.
     * This method will overwrite the {@value DATA_PATH} file, so this method is set to be {@code private} to protect the data.
     *
     * @param clients the {@link Client} data that you want to write
     * @throws IOException when IO issue occur
     */
    private static void writeAll(ArrayList<Client> clients) throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter(DATA_PATH));
        writer.config(SerializerFeature.DisableCircularReferenceDetect, true);
        writer.config(SerializerFeature.PrettyFormat, true);
        writer.startArray();
        for (Client client : clients) {
            writer.writeValue(client);
        }
        writer.endArray();
        writer.close();
    }
}
