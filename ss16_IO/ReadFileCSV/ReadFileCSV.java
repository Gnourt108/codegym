package ss16_IO.ReadFileCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileCSV {
    public static void main(String[] args) {
        String path = "country.csv";

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;

            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");

                int id = Integer.parseInt(fields[0]);
                String code = fields[1].replace("\"", "");
                String name = fields[2].replace("\"", "");

                Country country = new Country(id, code, name);
                System.out.println(country);
            }
        }catch (IOException e){
            System.out.println("Lỗi đọc file: "+e.getMessage());
        }
    }
}
