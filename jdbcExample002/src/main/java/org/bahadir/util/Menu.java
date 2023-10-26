package org.bahadir.util;

import org.bahadir.controller.PersonController;
import org.bahadir.entity.Person;
import org.bahadir.repository.PersonRepository;

import java.sql.Connection;
import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);
    private final PersonController personController;
    public Menu() {
        this.personController = new PersonController();
    }

    public void menuYaz(){
        int id;
        while(true){
            menuDetaylar();
            System.out.print("Seçiminiz: ");
            int secim= sc.nextInt();sc.nextLine();

            switch (secim){
                case 1:
                    System.out.print("First_name: ");
                    String first_name = sc.nextLine();
                    System.out.print("Last_name: ");
                    String last_name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    Person person = new Person(first_name,last_name,email);
                    personController.register(person);
                    break;
                case 2:
                    System.out.print("Güncellemek istediğiniz person'nın id'sini girin: ");
                    id = sc.nextInt();
                    personController.update(id);
                    break;
                case 3:
                    System.out.print("Güncellemek istediğiniz person'nın id'sini girin: ");
                    id = sc.nextInt();
                    personController.delete(id);
                    break;
                case 4:
                    System.out.print("Bilgilerini görmek istediğiniz person'nın id'sini girin: ");
                    id = sc.nextInt();
                    personController.findById(id);
                    break;
                case 5:
                    personController.getAllPersons();
                    break;
                case 0:
                    System.out.println("Sistem kapatılıyor....");
                    System.exit(0);
            }
        }
    }

    private static void menuDetaylar() {
        System.out.println("-----------DATABASE İŞLEMLERİ--------------");
        System.out.println("1-)Database'ye Person ekle");
        System.out.println("2-)Person güncelle");
        System.out.println("3-)Person Sil");
        System.out.println("4-)Id'ye göre person bul");
        System.out.println("5-)Bütün person'ları getir");
        System.out.println("0-)Çıkış");
    }
}
