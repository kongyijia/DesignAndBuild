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

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Map {@link Client} data to JSON database.
 * This "JSON database" is a self-defined database and it should only be used in this software.
 * <br>
 * Offering {@code add}, {@code delete}, {@code modify} and {@code find} methods for client data, which include ({@link User}, {@link Coach} and {@link Administrator}).
 * In this class, we use {@link com.alibaba.fastjson.JSON} to manage our {@link Client} POJO.
 *
 * @author Yubo Wu
 * @version 1.4
 * @see JSON
 * @see Client
 * @see User
 * @see Coach
 * @see Administrator
 * @since 8 May 2021
 */
public class ClientMapping {
    /**
     * JSON file path root
     */
    public static final String DATA_PATH = "data/client.json";
    /**
     * Avatar source path root
     */
    public static final String AVATAR_PATH = "data/image/";
    /**
     * Default avatar source name
     */
    public static final String DEFAULT_AVATAR = "default.jpeg";
    /**
     * Operation success status code
     */
    public static final int SUCCESS = 0;
    /**
     * Duplicate Client ID
     */
    public static final int DUPLICATE_ID = 1;
    /**
     * Duplicate Client nickname, must be unique.
     */
    public static final int DUPLICATE_NICKNAME = 6;
    /**
     * Cannot found Client in database
     */
    public static final int CLIENT_NOT_FOUND = 2;
    /**
     * Cannot found User in database
     */
    public static final int USER_NOT_FOUND = 3;
    /**
     * Cannot found Coach in database
     */
    public static final int COACH_NOT_FOUND = 4;
    /**
     * Cannot found Administrator in database
     */
    public static final int ADMIN_NOT_FOUND = 5;
    /**
     * No image found in certain path
     */
    public static final int NOT_IMAGE = 7;


    /**
     * Add client({@link User}, {@link Coach}, {@link Administrator}) to JSON database.
     *
     * @param type instance of class extended {@link Client} class
     * @param <T>  extends {@link Client} class
     * @return status code: {@link ClientMapping#DUPLICATE_ID}, {@link ClientMapping#DUPLICATE_NICKNAME} or {@link ClientMapping#SUCCESS}
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
        type.setAvatarSrc(AVATAR_PATH + DEFAULT_AVATAR);
        clients.add(type);
        writeAll(clients);
        return SUCCESS;
    }

    /**
     * Delete Client({@link User}, {@link Coach}, {@link Administrator}) from JSON database.
     *
     * @param id ID of the {@link Client} instance which you want to delete
     * @return Status code: {@link ClientMapping#CLIENT_NOT_FOUND} or {@link ClientMapping#SUCCESS}
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
     * Cancel Client({@link User}, {@link Coach}, {@link Administrator}) account.
     * Lazy deletion.
     * That is, to set "cancel" to true.
     *
     * @param id ID of the {@link Client} instance which you want to delete
     * @return Status code: {@link ClientMapping#CLIENT_NOT_FOUND} or {@link ClientMapping#SUCCESS}
     * @throws IOException when IO issue occur
     */
    public static int cancel(int id) throws IOException {
        ArrayList<Client> clients = readAllClients();
        for (Client client : clients) {
            if (client.getId() == id) {
                client.setCancel(true);
                return modify(client);
            }
        }
        return CLIENT_NOT_FOUND;
    }

    /**
     * Update Client({@link User}, {@link Coach}, {@link Administrator}) data in JSON database.
     * The method need {@code ID} to locate where the data is so do not set a new value to the {@code ID}.
     * <br>
     * NOTE: You can not use this method to modify {@code avatarSrc} or {@code recordHistory} in Client.
     * If you want to modify them, you need to use {@link ClientMapping#modifyAvatar(int, String)}.
     *
     * @param type updated instance of client class extended {@link Client}
     * @param <T>  extends {@link Client} class
     * @return status code: {@link ClientMapping#DUPLICATE_NICKNAME}, {@link ClientMapping#CLIENT_NOT_FOUND} or {@link ClientMapping#SUCCESS}
     * @throws IOException when IO issue occur
     * @see ClientMapping#modifyAvatar(int, String)
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
        clients.add(index, type);
        writeAll(clients);
        return SUCCESS;
    }

    /**
     * Update Client({@link User}, {@link Coach} and {@link Administrator}} {@code recordHistory} data.
     * <br>
     * If the {@code history} you want to modified not exist, then add it to database automatically.
     * If exist, then modify it.
     * <br>
     * NOTE: If you want to modify recordHistory and the data itself simultaneously, you need to call this method first.
     *
     * @param id      Client id you want to modified
     * @param history history record you want to modified
     * @return status code: {@link ClientMapping#CLIENT_NOT_FOUND} or {@link ClientMapping#SUCCESS}
     * @throws IOException when IO issue occur
     */
    public static int modifyRecordHistory(int id, Client.RecordHistory history) throws IOException {
        ArrayList<Client> clients = readAllClients();
        for (Client client : clients) {
            if (client.getId() == id) {
                int index = -1;
                ArrayList<Client.RecordHistory> histories = client.getRecordHistory();
                for (Client.RecordHistory h : histories) {
                    if (h.getVideoId() == history.getVideoId()) {
                        index = histories.indexOf(h);
                        break;
                    }
                }
                if (index != -1) {
                    // if found, remove it to change to the new one
                    histories.remove(index);
                }
                // if not found, add it directly
                histories.add(history);
                writeAll(clients);
                return SUCCESS;
            }
        }
        return CLIENT_NOT_FOUND;
    }

    /**
     * Update Client({@link User}, {@link Coach} and {@link Administrator}} avatar data.
     * It will first copy the image to {@link ClientMapping#AVATAR_PATH}, then update the srcPath of image to JSON database.
     *
     * @param id           Client id you want to modify
     * @param originalPath the original path of the profile photo. Must be absolute path
     * @return {@link ClientMapping#NOT_IMAGE} or {@link ClientMapping#SUCCESS}
     * @throws IOException when IO issue occur
     */
    public static int modifyAvatar(int id, String originalPath) throws IOException {
        File file = new File(originalPath);
        // check if image?
        String mimeType = Files.probeContentType(file.toPath());
        String type = mimeType.split("/")[0];
        if (!type.equals("image"))
            return NOT_IMAGE;

        // copy image to data/image
        String avatarNewId = UUID.randomUUID().toString();
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        String newPath = AVATAR_PATH + avatarNewId + suffix;
        File newFile = new File(newPath);
        Files.copy(file.toPath(), newFile.toPath());

        // update data in JSON database
        ArrayList<Client> clients = find("id", String.valueOf(id));
        if (clients.size() == 0)
            return CLIENT_NOT_FOUND;
        Client client = clients.get(0);
        client.setAvatarSrc(newPath);
        return modify(client);
    }

    /**
     * Find client({@link User}, {@link Coach}, {@link Administrator}) from JSON database, and return the results to a {@link ArrayList}.
     * The parameter are {@link HashMap}, you need to put all {@code AND} conditions in hashmap.
     * This {@code find} method can't handle other conditions except {@code AND}, like {@code OR} or {@code BETWEEN} or others.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList} contained results.
     * @throws FileNotFoundException when {@link ClientMapping#DATA_PATH} not found
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
     * Find client({@link User}, {@link Coach}, {@link Administrator}) from JSON database, and return the results to a {@link ArrayList}.
     * This method supports {@code fuzzy search}.
     * This method require a key-value pair.
     * It a simple version of {@link ClientMapping#find(HashMap)}. If you have only one condition to search, you can use this method.
     *
     * @param key   the key of the value you want to search
     * @param value the value you want to search
     * @return {@link ArrayList} contained results.
     * @throws FileNotFoundException when {@link ClientMapping#DATA_PATH} not found
     */
    public static ArrayList<Client> find(String key, String value) throws FileNotFoundException {
        ArrayList<Client> clients = new ArrayList<>();
        JSONReader reader = new JSONReader(new FileReader(DATA_PATH));
        reader.startArray();
        while (reader.hasNext()) {
            String s = reader.readString();
            JSONObject object = JSON.parseObject(s);
            if (object.getString(key).contains(value)) {
                if (object.getInteger("role") == 0) {
                    clients.add(JSON.parseObject(s, Administrator.class));
                } else if (object.getInteger("role") == 1) {
                    clients.add(JSON.parseObject(s, Coach.class));
                } else if (object.getInteger("role") == 2) {
                    clients.add(JSON.parseObject(s, User.class));
                }
            }
        }
        return clients;
    }

    /**
     * Find {@link User} from JSON database, return the result to an {@link ArrayList}.
     * The difference between the method and {@link ClientMapping#find(HashMap)} is that the return value of this method will contains the instances of {@link User}, not {@link Client}.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList} contained results.
     * @throws FileNotFoundException when {@link ClientMapping#DATA_PATH} not found
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
     * Find {@link Coach} from JSON database, return the result to an {@link ArrayList}.
     * The difference between the method and {@link ClientMapping#find(HashMap)} is that the return value of this method will contains the instances of {@link Coach}, not {@link Client}.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList} contained results.
     * @throws FileNotFoundException when {@link ClientMapping#DATA_PATH} not found
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
     * Find {@link Administrator} from JSON database, return the result to an {@link ArrayList}.
     * The difference between the method and {@link ClientMapping#find(HashMap)} is that the return value of this method will contains the instances of {@link Administrator}, not {@link Client}.
     *
     * @param map the WHERE conditions are represented by K-V pairs using {@link HashMap}
     * @return {@link ArrayList} contained results.
     * @throws FileNotFoundException when {@link ClientMapping#DATA_PATH} not found
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
     * Read {@link ClientMapping#DATA_PATH} to the program and convert it to POJO using fastJSON.
     *
     * @return all data in {@link ClientMapping#DATA_PATH}
     * @throws FileNotFoundException when {@link ClientMapping#DATA_PATH} not found
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
     * Write all {@code clients} in {@link ArrayList} to the file using fastJSON.
     * This method will overwrite the {@link ClientMapping#DATA_PATH} file, so this method is set to be {@code private} to protect the data.
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
