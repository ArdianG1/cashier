package com.smk.cashier.service;

import com.smk.cashier.model.Barang;
import com.smk.cashier.model.Stok;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class StokService {
    FileReader stokServiceReader;
    FileWriter stokServiceWriter;
    List<Stok> stokList = new LinkedList<>();
    private static StokService stokService=null;
    private StokService() {
        try {
            stokServiceReader = new
                    FileReader("stok.txt");
            stokServiceWriter = new
                    FileWriter("stok.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static synchronized StokService getInstance() {
        if (stokService == null) {
            stokService = new StokService();
        }
        return stokService;
    }
    private void readFile() {
        BufferedReader bufferedReader = new BufferedReader(stokServiceReader);
        List<String> stringList = bufferedReader.lines().toList();
        stokList = new LinkedList<>();
        for (String string: stringList) {
            stokList.add(parsingLineToStok(string));
        }
    }
    private void writeFile() {
        try {
            stokServiceWriter = new FileWriter("barang.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(stokServiceWriter);
        for (int i = 0; i < stokList.size(); i++) {
            Stok stok = stokList.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(stok.getId());
            sb.append(stok.getKodeBarang());
            sb.append("|");
            sb.append(stok.getStokBarang());
            sb.append("|");
            try {
                bufferedWriter.write(sb.toString());
                if (i < stokList.size() - 1) {
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Stok parsingLineToStok(String string) {
        StringTokenizer st = new StringTokenizer(string, "|");
        int id = 0;
        Stok stok = new Stok();
        while (st.hasMoreElements()) {
            if (id == 0){
                stok.setId(Integer.parseInt(st.nextToken()));
            } else if (id == 1) {
                stok.setKodeBarang(st.nextToken());
            } else if (id == 2) {
                stok.setStokBarang(Integer.parseInt(st.nextToken()));
            }
            id ++;
        }
        return stok;
    }
    public List<Stok> getStokList(){
        readFile();
        return stokList;
    }
    public void addStok(Stok barang){
        stokList.add(barang);
        writeFile();
    }
    public List<Stok>
    findByKode(String name){
        List<Stok>
                resultList = stokList.stream().filter(stok -> stok.getKodeBarang().equals(name)).toList();
        return resultList;
    }
}


// setelah branch pertemuan-8 buatlah branch pertemuan-9
// Buat file baru di folder Dao dengan nama Dao.java
// jangan lupa cek pom.xml (cashier) karena terdapat error

// Dao.java
// public interface Dao<T,I> {
// Optional<T> get(int id);
// Collection<T> getAll();
// Optional<I> save(T t);
// void update(T t);
// void delete(T t);
// }

// Buatlah file baru di folder Dao dengan nama JdbcConnection.java
// JdbcConnection.java
// public class JdbcConnection {
// private static Optional<Connection>
// connection = Optional.empty();
//
// public static Optional<Connection> getConnection() {
//    if (connection.isEmpty()) {
//       String url = "jdbc:postgresql://localhost:5432/cashier";
//       String user = "cashier";
//       String password = "c45h13r456";
//       Class.forName("org.postgresql.Driver"); (Surround with Try Catch)
//       connection = Optional.ofNullable (
//              DriverManager.getConnection (url, user, password); (Bawahnya SQL Exception)
//       } catch
//           e.printStackTrace()
//    }
// }

// Buatlah file baru di folder Dao dengan nama BarangDao
// public class BarangDao implements Dao<Barang, Interger> { (Implements Method)
//
//    private final Option<Connection>.connection; (initialize)
//
//    public BarangDao() {
//        connection = JdbcConnection.getConnetion();
//
//    }
//
//    @Override
//    public Optional<Barang> get(int id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Collection<Barang> getAll() {
//        return null;
//    }
//
//    @Override
//    public Optional<Integer> save(Barang barang) {
//    Barang nonNullBarang = Objects.requireNonNull(barang);
//    String  sql = "INSERT INTO Barang (kode_barang, nama_barang, harga_barang, created_by, updated_by, date_create, last_modified)" +
//       "VALUES (?,?,?,?,?,?,?)";
//       return connection.flatMap(conn -> {
//          Optional<Interger> generateId = Optional.empty();
//   try {
//       Prepare Statment ps = conn.prepareStatment(sql, Statment.RETURN_GENERATED_KEYS) (Surround with Try Catch)
//       ps.setString(barang.getKodeBarang());
//       ps.setString(barang.getNamaBarang());
//       ps.setInt(barang.getHargaBarang());
//       ps.setString(barang.getCreatedBy());
//       ps.setString(barang.getUpdatedBy());
//       ps.setDate(new Date (barang.getLastModified().getTime()));
//       int numberOfInsertedRows = ps.executeUpdate();
//       if(numberOfInsertedRows > 0) {
//           ResultSet rs = ps.getGeneratedKeys();
//           if(rs.next()) {
//               generatedId = Optional.of(rs.getInt(1));
//           }
//       }
//
//       } catch (SQLException e) {
//         e.printStackTrace();
//         }
//         return generatedId;
//       });
//    }
//
//    @Override
//    public void update(Barang barang) {
//
//    }
//
//    @Override
//    public void delete(Barang barang){
//
//    }
// }