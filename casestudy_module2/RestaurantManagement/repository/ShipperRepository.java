package casestudy_module2.RestaurantManagement.repository;

import casestudy_module2.RestaurantManagement.entity.Shipper;

import java.util.ArrayList;
import java.util.List;

public class ShipperRepository extends AccountRepository<Shipper>{

    public ShipperRepository(String csvFile) {
        super("data/shippers.csv");
    }

    @Override
    protected Shipper parseFromCsv(String[] data) {
        if(data.length < 8){
            return null;
        }

        Shipper s = new Shipper(
                Integer.parseInt(data[0]),
                data[1],
                data[2],
                data[3],
                data[4],
                Boolean.parseBoolean(data[5]),
                Boolean.parseBoolean(data[6]),
                data[7],
                new ArrayList<>(),
                Boolean.parseBoolean(data[5]),
                new ArrayList<>()
        );
        return s;
    }

    @Override
    protected String[] toCsvArray(Shipper s) {
        return new String[] {
                s.getIdUser().toString(),
                s.getUserName(),
                s.getPassword(),
                s.getPhoneNumber(),
                s.getAddress(),
                String.valueOf(s.isLocked()),
                String.valueOf(s.isBusy()),
                s.getCarType() != null ? s.getCarType() : ""
        };
    }

    @Override
    protected String getCsvHeader() {
        return "idUser,userName,password,phoneNumber,address,isLocked,isBusy,carType";
    }

    public List<Shipper> getAvailableShippers(){
        List<Shipper> all = getAll();
        List<Shipper> available = new ArrayList<>();
        for (Shipper s : all){
            if(!s.isBusy() && !s.isLocked()){
                available.add(s);
            }
        }
        return available;
    }

    public void setShipperBusy(Integer shipperId, boolean busy){
        Shipper s = getById(shipperId);
        if (s != null){
            s.setBusy(busy);
            update(s);
        }
    }
}