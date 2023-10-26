package org.bahadir;

import org.bahadir.util.JdbcHelper;
import org.bahadir.util.Menu;

import java.sql.Connection;

/*
    1-PersonDB oluşturulım -> id,first_name,last_name,joined_date
    2-util pakedinde jdbcHelper classı oluşturalım -> getConnection metodu yapalım -> bu metodta bize connection nesnesi dönecek
    3-Burdaki databasebilgileri farklı bir sınıftan çekelim -> util içinde jdbcConstant sınıfından çekelim
    4-Database'e veri ekleme -> Register işlemi -> kullanıcıdan isim soy isim email alıcaz ve databaseye kaydedicez. -> repository katmanını yazıyoruz.
 */
public class Main {
    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.menuYaz();

    }
}