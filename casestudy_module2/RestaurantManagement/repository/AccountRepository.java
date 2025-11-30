package casestudy_module2.RestaurantManagement.repository;

import casestudy_module2.RestaurantManagement.entity.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AccountRepository<T extends Account> implements IRepository<T>{
    private final String csvFile;
    private final Map<String, Integer> loginAttempts = new HashMap<>();

    protected abstract T parseFromCsv(String[] data);
    protected abstract String[] toCsvArray(T entity);
    protected abstract String getCsvHeader();


    public AccountRepository(String csvFile) {
        this.csvFile = csvFile;
        isFileExist();
    }

    private void isFileExist() {
        File file = new File(csvFile);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(getCsvHeader());
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Lỗi tạo file: " + e.getMessage());
            }
        }
    }



    @Override
    public void add(T account) {
        List<T> all = getAll();

        int autoId = 0;
        for (T a : all){
            if(a.getIdUser() != null && a.getIdUser() > autoId){
                autoId = a.getIdUser();
            }
        }

        account.setIdUser(autoId + 1);
        all.add(account);
        saveAll(all);
    }

    @Override
    public void update(T account) {
        List<T> all = getAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getIdUser().equals(account.getIdUser())){
                all.set(i, account);
                saveAll(all);
                return;
            }
        }
    }

    @Override
    public void delete(Integer id) {
        List<T> all = getAll();
        all.removeIf(a -> a.getIdUser().equals(id));
        saveAll(all);
    }

    @Override
    public T getById(Integer id) {
        List<T> all = getAll();
        for (T account : all){
            if(account.getIdUser().equals(id)){
                return account;
            }
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
            String line = reader.readLine();

            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()){
                    continue;
                }

                String[] data = parseCsvLine(line);

                T object = parseFromCsv(data);
                if(object != null){
                    result.add(object);
                }
            }
        }catch (IOException e){
            System.out.println("Lỗi đọc file: "+e.getMessage());
        }
        return result;
    }

    protected void saveAll(List<T> accounts){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))){
            writer.write(getCsvHeader());
            writer.newLine();

            for (T account : accounts){
                String[] data = toCsvArray(account);
                writer.write(toCsvLine(data));
                writer.newLine();
            }
        }catch (IOException e){
            System.out.println("Lỗi ghi file: "+e.getMessage());
        }
    }

    private String toCsvLine(String[] data) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            String field = data[i];

            if(field.contains(",") || field.contains("\\") || field.contains("\n")){
                field = "\"" + field.replace("\"", "\"\"") + "\"";
            }

            line.append(field);
            if(i < data.length - 1){
                line.append(",");
            }
        }
        return line.toString();
    }

    private String[] parseCsvLine(String line) {
        List<String> result = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString().trim());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString().trim());

        return result.toArray(new String[0]);
    }

    public List<T> searchByUsername(String username) {
        List<T> all = getAll();
        List<T> result = new ArrayList<>();
        for (T account : all) {
            if (account.getUserName().toLowerCase().contains(username.toLowerCase())) {
                result.add(account);
            }
        }
        return result;
    }

    public T getByUsername(String username) {
        List<T> all = getAll();
        for (T account : all) {
            if (account.getUserName().equals(username)) {
                return account;
            }
        }
        return null;
    }

    public int getLoginAttempts(String username) {
        return loginAttempts.getOrDefault(username, 0);
    }

    public void incrementLoginAttempts(String username) {
        loginAttempts.put(username, getLoginAttempts(username) + 1);
    }

    public void resetLoginAttempts(String username) {
        loginAttempts.remove(username);
    }

    public void lockAccount(Integer id) {
        T account = getById(id);
        if (account != null) {
            account.setLocked(true);
            update(account);
        }
    }

    public void unlockAccount(Integer id) {
        T account = getById(id);
        if (account != null) {
            account.setLocked(false);
            update(account);
        }
    }
}