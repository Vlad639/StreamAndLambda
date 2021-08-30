import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static List<Person> personList;
    private static List<String > addressesList;

    private static void fillPersonListFromJSONFile(File file){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            personList = objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showAllPersonsFullName(){
        System.out.println("ФИО:");
        Stream<Person> personStream = personList.stream();
        personStream
                .map(Person::getFullName)
                .forEach(fullName -> System.out.println("\t"+fullName)
                );
    }

    private static void showPersonBornIn(int year){
        System.out.println("ФИО людей, рождённых в " + year +" г.");
        Stream<Person> personStream = personList.stream();
        personStream
                .filter(person -> person.getBornYear() == year)
                .forEach(person ->
                    System.out.println("\t" + person.getFullName())
                );
    }

    private static void fillAddressesListFromPersonList(){
        addressesList = new ArrayList<>();
        Stream<Person> personStream = personList.stream();
        personStream
                .collect(Collectors.groupingBy(Person::getAddress))
                .forEach(
                        (address, resident) -> addressesList.add(address)
                );
    }

    private static void showAddresses(){
        System.out.println("Адреса:");
        for (String address: addressesList)
            System.out.println("\t" + address);

    }

    private static void fillAddressesAndShowResidents(){
        System.out.println("Адреса и проживающие:");
        addressesList = new ArrayList<>();
        Stream<Person> personStream = personList.stream();
        personStream
                .collect(Collectors.groupingBy(Person::getAddress))
                .forEach((address, residents) -> {
                    addressesList.add(address);
                    System.out.println(address + ":");
                    for (Person resident : residents) {
                        System.out.println("\t" + resident.getFullName());
                    }
                });

    }

    public static void main(String[] args)  {
        File file = new File("src/main/resources/peoples.json");
        fillPersonListFromJSONFile(file);
        showAllPersonsFullName();
        showPersonBornIn(1992);
        fillAddressesListFromPersonList();
        showAddresses();
        fillAddressesAndShowResidents();

    }
}
